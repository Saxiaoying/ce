package team.zucc.eecs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.StudentCourseArrangement;
import team.zucc.eecs.service.StudentCourseArrangementService;

@Controller("StudentCourseArrangementController")
public class StudentCourseArrangementController {
	@Autowired
	private StudentCourseArrangementService studentCourseArrangementService;
	
	@RequestMapping(value = { "/addStudentCourseArrangementList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addStudentCourseArrangementList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentCourseArrangementController-addStudentCourseArrangementList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			int cag_id = in.getIntValue("cag_id");
			JSONArray stu_idList = in.getJSONArray("stu_idList");
			
		    String inf = "";
		    for(int i =0; i < num; i++) {
		    	int stu_id = stu_idList.getIntValue(i);
		    	int f = studentCourseArrangementService.addStudentCourseArrangement(stu_id, cag_id);
		    	if (f == 1) {
		    		inf += "添加学号为" + stu_id + "时出错：该学生已经选择当前开课！";
		    	}
				else if(f == 0);
				else {
					inf += "添加学号为" + stu_id + "时出错：数据库错误！";
				}
		    }
			
			if(inf.compareTo("") == 0) obj.put("state", "OK");
			else obj.put("state", inf);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/deleteStudentCourseArrangementList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteStudentCourseArrangementList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentCourseArrangementController-deleteStudentCourseArrangementList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			int cag_id = in.getIntValue("cag_id");
			JSONArray stu_idList = in.getJSONArray("stu_idList");
			
		    String inf = "";
		    for(int i =0; i < num; i++) {
		    	int stu_id = stu_idList.getIntValue(i);
		    	StudentCourseArrangement studentCourseArrangement = 
		    			studentCourseArrangementService.getStudentCourseArrangementByStu_idAndCag_id(stu_id, cag_id);
		    	if (studentCourseArrangement == null) {
		    		inf += "删除学号为" + stu_id + "时出错：数据缺失！";
				} else {
					int sca_id = studentCourseArrangement.getSca_id();
					int f = studentCourseArrangementService.deleteStudentCourseArrangement(sca_id);
			    	if(f == 0);
					else {
						inf += "删除学号为" + stu_id + "时出错：数据库错误！";
					}
				}
		    }
			
			if(inf.compareTo("") == 0) obj.put("state", "OK");
			else obj.put("state", inf);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

}
