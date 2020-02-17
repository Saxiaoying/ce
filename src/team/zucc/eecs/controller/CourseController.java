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
import team.zucc.eecs.service.CourseService;

@Controller("CourseController")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value= {"/getCourseList"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseList(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-getCourseList");

		JSONObject obj = new JSONObject();
		List<Course> courseList = new ArrayList<Course>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
			
			courseList = courseService.getCourseListFromAtoB(a, b);
			
			JSONArray arr = new JSONArray();
			arr.addAll(courseList);
			
			obj.put("total", courseService.getCourseNumber());
			obj.put("courseList", arr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/searchCourse"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchCourse(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-searchCourse");
		
		JSONObject obj = new JSONObject();
		List<Course> courseList = new ArrayList<Course>();
		try {
			//处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if(coz_id.isEmpty()) coz_id = null;
			
			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if(coz_name_ch.isEmpty()) coz_name_ch = null;
			
			String coz_nature = in.getString("coz_nature");
			coz_nature = coz_nature.replaceAll("\\s", "");
			if(coz_nature.isEmpty()) coz_nature = null;
			
			if(coz_id == null && coz_name_ch == null && coz_nature == null) {
				obj.put("state", "NULL");
				obj.put("total", 0);
				obj.put("courseList", new JSONArray());
				return obj;
			}
			courseList = courseService.getCourseListByInf(coz_id, coz_name_ch, coz_nature);
			
			JSONArray arr = new JSONArray();
			arr.addAll(courseList);
			
			obj.put("total", courseList.size());
			obj.put("courseList", arr);
			
			if(courseList.size() == 0) {
				obj.put("state", "EMPTY");
				return obj;
			}
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/addCourse"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourse(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-addCourse");

		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if(coz_id.isEmpty()) coz_id = null;
			
			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if(coz_name_ch.isEmpty()) coz_name_ch = null;
			
			String coz_name_eng = in.getString("coz_name_eng");
			coz_name_eng = coz_name_eng.trim();
			if(coz_name_eng.isEmpty()) coz_name_eng = null;
			
			String coz_nature = in.getString("coz_nature");
			coz_nature = coz_nature.replaceAll("\\s", "");
			if(coz_nature.isEmpty()) coz_nature = null;
			
			String tmp_coz_credit = in.getString("coz_credit");
			tmp_coz_credit = tmp_coz_credit.replaceAll("\\s", "");
			if(tmp_coz_credit.isEmpty()) tmp_coz_credit = null;
			
			String coz_hrs_wk = in.getString("coz_hrs_wk");
			coz_hrs_wk = coz_hrs_wk.replaceAll("\\s", "");
			if(coz_hrs_wk.isEmpty()) coz_hrs_wk = null;
			
			String tmp_coz_hours = in.getString("coz_hours");
			tmp_coz_hours = tmp_coz_hours.replaceAll("\\s", "");
			if(tmp_coz_hours.isEmpty()) tmp_coz_hours = null;
			
			
			if(coz_id == null || coz_name_ch == null || coz_name_eng == null || coz_nature == null || 
					tmp_coz_credit == null || coz_hrs_wk == null || tmp_coz_hours == null) {
				obj.put("state", "EMPTY");
				return obj;
			}
			
			if(coz_hrs_wk.startsWith("0") && coz_hrs_wk.endsWith("0")) { 
				obj.put("state", "WK");
				return obj;
			}
			//处理数字
			double coz_credit;
			try {
				coz_credit = Double.valueOf(tmp_coz_credit);
				if(coz_credit <= 0) {
					obj.put("state", "NUM");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "NUM");
				return obj;
			}
			
			double coz_hours;
			try {
				coz_hours = Double.valueOf(tmp_coz_hours);
				if(coz_hours <= 0) {
					obj.put("state", "NUM");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "NUM");
				return obj;
			}
			
			int state = courseService.addCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
			
			if(state == 0) obj.put("state", "OK");
			else obj.put("state", "REPEAT");
			
			return obj;
		} catch (Exception e) {
			obj.put("state", "ERROR");
			e.printStackTrace();
			return obj;
		}
	}
	
	
	
	@RequestMapping(value= {"/updateCourse"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCourse(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-updateCourse");

		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.trim();
			if(coz_id.isEmpty()) coz_id = null;
			
			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.trim();
			if(coz_name_ch.isEmpty()) coz_name_ch = null;
			
			String coz_name_eng = in.getString("coz_name_eng");
			coz_name_eng = coz_name_eng.trim();
			if(coz_name_eng.isEmpty()) coz_name_eng = null;
			
			String coz_nature = in.getString("coz_nature");
			coz_nature = coz_nature.trim();
			if(coz_nature.isEmpty()) coz_nature = null;
			
			String tmp_coz_credit = in.getString("coz_credit");
			tmp_coz_credit = tmp_coz_credit.trim();
			if(tmp_coz_credit.isEmpty()) tmp_coz_credit = null;
			
			String coz_hrs_wk = in.getString("coz_hrs_wk");
			coz_hrs_wk = coz_hrs_wk.trim();
			if(coz_hrs_wk.isEmpty()) coz_hrs_wk = null;
			
			String tmp_coz_hours = in.getString("coz_hours");
			tmp_coz_hours = tmp_coz_hours.trim();
			if(tmp_coz_hours.isEmpty()) tmp_coz_hours = null;
			
			
			if(coz_id == null || coz_name_ch == null || coz_name_eng == null || coz_nature == null || 
					tmp_coz_credit == null || coz_hrs_wk == null || tmp_coz_hours == null) {
				obj.put("state", "EMPTY");
				return obj;
			}
			
			if(coz_hrs_wk.startsWith("0") && coz_hrs_wk.endsWith("0")) { 
				obj.put("state", "WK");
				return obj;
			}
			//处理数字
			double coz_credit;
			try {
				coz_credit = Double.valueOf(tmp_coz_credit);
				if(coz_credit <= 0) {
					obj.put("state", "NUM");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "NUM");
				return obj;
			}
			
			double coz_hours;
			try {
				coz_hours = Double.valueOf(tmp_coz_hours);
				if(coz_hours <= 0) {
					obj.put("state", "NUM");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "NUM");
				return obj;
			}
			
			int state = courseService.updateCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
			
			if(state == 0) obj.put("state", "OK");
			else obj.put("state", "ERROR");
			
			return obj;
		} catch (Exception e) {
			obj.put("state", "ERROR");
			e.printStackTrace();
			return obj;
		}
	}
	
	@RequestMapping(value= {"/getCourse"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourse(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-getCourse");

		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			Course course = courseService.getCourseByCoz_id(coz_id);
			if(course == null) {
				obj.put("state", "NULL");
				return obj;
			}
			obj.put("course", course);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
