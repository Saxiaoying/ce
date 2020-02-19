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

import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.service.TeacherService;

@Controller("TeacherController")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = { "/getTeacherList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getTeacherList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入TeacherController-getTeacherList");

		JSONObject obj = new JSONObject();
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			
			// 处理字符串中的不可见字符
			String tch_name = in.getString("tch_name");
			tch_name = tch_name.replaceAll("\\s", "");
			if (tch_name.isEmpty()) tch_name = "";
			
			teacherList = teacherService.getTeacherListByInfFromAtoB(a, b, tch_name);
          int total = teacherService.getTeacherNumberByInf(tch_name);
			
			
			obj.put("total", total);
			obj.put("teacherList", teacherList);
			
			if (teacherList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getTeacher" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getTeacher(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入TeacherController-getTeacher");

		JSONObject obj = new JSONObject();
		try {
			int tch_id = 0;
	    	try {
	    		tch_id = in.getIntValue("tch_id");
			} catch (Exception e) {
				obj.put("state", "教师编号必须是数字！");
				return obj;
			}
	    	Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
	    	if(teacher == null) {
	    		obj.put("state", "没有这个教师编号！");
	    		return obj;
	    	}
			obj.put("teacher", teacher);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

}
