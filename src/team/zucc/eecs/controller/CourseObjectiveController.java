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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CourseObjectiveController")
public class CourseObjectiveController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@RequestMapping(value = { "/updateCourseObjective" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCourseObjective(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseObjectiveController-updateCourseObjective");
		
		List<CourseObjective> courseObjectiveList = new ArrayList<CourseObjective>();
		List<CourseObjective> courseObjectiveList2 = new ArrayList<CourseObjective>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			
		    JSONArray arr_obj = in.getJSONArray("arr_obj");
		    for (Object o : arr_obj) {
		    	String s = (String) o;
		    	String[] tmp = s.split(";");
		    	if (tmp.length < 2) {
		    		obj.put("state", "存在空白项！");
					return obj;
				}
		    	int co_num = Integer.valueOf(tmp[0]);
		    	String co_cont = tmp[1];
		    	co_cont = co_cont.replaceAll("\\s", "");
				if(co_cont.isEmpty()) {
					obj.put("state", "存在空白项！");
					return obj;
				}
		    	
		    	
		    	CourseObjective co = new CourseObjective();
		    	co.setCs_id(cs_id);
		    	co.setCo_num(co_num);
		    	co.setCo_cont(co_cont);
		    	courseObjectiveList.add(co);
		    }
			for (CourseObjective co: courseObjectiveList) {
				courseObjectiveService.updateCourseObjective(co.getCs_id(), co.getCo_num(), co.getCo_cont());
			}
			courseObjectiveList2 = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			
			for (int i = courseObjectiveList.size(); i < courseObjectiveList2.size(); i++) {
				CourseObjective co = courseObjectiveList2.get(i);
				courseObjectiveService.deleteCourseObjective(cs_id, co.getCo_num());
			}
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getCourseObjectiveList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseObjectiveList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseObjectiveController-getCourseObjectiveList");
		
		List<CourseObjective> courseObjectiveList = new ArrayList<CourseObjective>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "暂无开课流水号为" + cs_id+ "的开课情况，请重新查询！");
				return obj;
			}
			String coz_id = courseSet.getCoz_id();
			Course course = courseService.getCourseByCoz_id(coz_id);
			
			courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			if(courseObjectiveList == null) {
				courseObjectiveList = new ArrayList<CourseObjective>();
			}
			JSONArray arr = new JSONArray();
			arr.addAll(courseObjectiveList);
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("total", courseObjectiveList.size());
			obj.put("courseObjectiveList", arr);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
