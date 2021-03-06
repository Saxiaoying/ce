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
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CoursePracticeService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CoursePracticeController")
public class CoursePracticeController {
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CoursePracticeService coursePracticeService;
	
	@RequestMapping(value = { "/updateCoursePracticeList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCoursePracticeList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CoursePracticeController-updateCoursePracticeList");
		
		List<CoursePractice> coursePracticeList = new ArrayList<CoursePractice>();
		List<CoursePractice> coursePracticeList2 = new ArrayList<CoursePractice>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = -1;
			try {
				cs_id = in.getIntValue("cs_id");
				if(cs_id <= 0) {
					obj.put("state", "开课流水号为正整数！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "开课流水号为正整数！");
				return obj;
			}
		    int num = in.getIntValue("num");
		    JSONArray pra_nameL = in.getJSONArray("pra_name");
		    JSONArray pra_hrsL = in.getJSONArray("pra_hrs");
		    JSONArray pra_contL = in.getJSONArray("pra_cont");
		    JSONArray pra_natureL = in.getJSONArray("pra_nature");
		    JSONArray pra_typL = in.getJSONArray("pra_typ");
			
		    for (int i = 0; i < num; i++) {
		    	int pra_num = i+1;
		    	String pra_name = pra_nameL.getString(i);
		    	Double pra_hrs = 0.0;
		    	try {
		    		pra_hrs = pra_hrsL.getDoubleValue(i);
				} catch (Exception e) {
					obj.put("state", "学时必须是数字！");
					return obj;
				}
		    	String pra_cont = pra_contL.getString(i);
		    	String pra_nature = pra_natureL.getString(i);
		    	String pra_typ = pra_typL.getString(i);
		    	
		    	CoursePractice cp = new CoursePractice();
		    	cp.setCs_id(cs_id);//开课流水号（外码）
		    	cp.setPra_num(pra_num);
		    	cp.setPra_name(pra_name);
		    	cp.setPra_hrs(pra_hrs);
		    	cp.setPra_cont(pra_cont);
		    	cp.setPra_nature(pra_nature);
		    	cp.setPra_typ(pra_typ);
		    	
		    	coursePracticeList.add(cp);
		    }
		    for (CoursePractice cp: coursePracticeList) {
		    	coursePracticeService.updateCoursePractice(cp.getCs_id(), cp.getPra_num(), cp.getPra_name(), 
		    			cp.getPra_hrs(), cp.getPra_cont(), cp.getPra_nature(), cp.getPra_typ());
		    }
		    
		    coursePracticeList2 = coursePracticeService.getCoursePracticeListByCs_id(cs_id);
		    for (int i = coursePracticeList.size(); i < coursePracticeList2.size(); i++) {
				CoursePractice cp = coursePracticeList2.get(i);
				coursePracticeService.deleteCoursePractice(cs_id, cp.getPra_num());
			}
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getCoursePracticeList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCoursePracticeList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CoursePracticeController-getCoursePracticeList");
		
		List<CoursePractice> coursePracticeList = new ArrayList<CoursePractice>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = -1;
			try {
				cs_id = in.getIntValue("cs_id");
				if(cs_id <= 0) {
					obj.put("state", "开课流水号为正整数！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "开课流水号为正整数！");
				return obj;
			}
			
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "暂无该开课信息！");
				return obj;
			}
			
			Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			if(course == null) {
				obj.put("state", "该开课流水号的课程不在数据库内！");
				return obj;
			}
			
			coursePracticeList = coursePracticeService.getCoursePracticeListByCs_id(cs_id);
			if(coursePracticeList == null) {
				coursePracticeList = new ArrayList<CoursePractice>();
			}
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("total", coursePracticeList.size());
			obj.put("coursePracticeList", coursePracticeList);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
