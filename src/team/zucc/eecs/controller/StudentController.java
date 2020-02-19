
//This is maintained by jyl. 
package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Class;
import team.zucc.eecs.model.Student;
import team.zucc.eecs.service.StudentService;
import team.zucc.eecs.service.ClassService;

@Controller("StudentController")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClassService classService;
	
	@RequestMapping(value = { "/getStudentList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getStudentList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentController-getStudentList");

		JSONObject obj = new JSONObject();
		List<Student> studentList = new ArrayList<Student>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			String class_name = ""; 
			if (in.getString("class_name").compareTo("班级（所有）") != 0) class_name = in.getString("class_name");

			
			// 处理字符串中的不可见字符
			String stu_name = in.getString("stu_name");
			stu_name = stu_name.replaceAll("\\s", "");
			if (stu_name.isEmpty()) stu_name = "";
			
			studentList = studentService.getStudentListByInfFromAtoB(a, b, stu_name, class_name);
            int total = studentService.getStudentNumberByInf(stu_name, class_name);
			
            List<String> classNameList = new ArrayList<String>();
            for(Student stu: studentList) {
            	Class c = classService.getClassByClass_id(stu.getClass_id());
            	classNameList.add(c.getClass_name());
            }
			
			obj.put("total", total);
			obj.put("studentList", studentList);
			obj.put("classNameList", classNameList);
			
			if (studentList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

}
