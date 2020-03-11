package team.zucc.eecs.controller;

import java.sql.Timestamp;
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

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseAppendix;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CourseAppendixService;
import team.zucc.eecs.service.CourseArrangementService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CourseAppendixController")
public class CourseAppendixController {
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseAppendixService courseAppendixService;
	
	@Autowired
	private CourseArrangementService courseArrangementService;
	
	@RequestMapping(value= {"/getCourseAppendixByCs_id"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseAppendixByCs_id(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseAppendixController-getCourseAppendixByCs_id");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = -1;
			try {
				cs_id = in.getIntValue("cs_id");
				if(cs_id <= 0) {
					obj.put("state", "请输入正确的开课流水号！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "请输入正确的开课流水号！");
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
			
			//课程设置简介表；教学大纲；实验项目卡；教学进度计划表；命题计划书；
			List<CourseAppendix> list1 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "课程设置简介表");
			List<CourseAppendix> list2 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "教学大纲");
			List<CourseAppendix> list3 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "实验项目卡");
			List<CourseAppendix> list4 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "教学进度计划表");
			List<CourseAppendix> list5 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "命题计划书");
			
		
			
			obj.put("course", course);
			obj.put("courseSet", courseSet);
			
		
			obj.put("list1", list1);
			obj.put("list2", list2);
			obj.put("list3", list3);
			obj.put("list4", list4);
			obj.put("list5", list5);
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/addCourseAppendix"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourseAppendix(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseAppendixController-addCourseAppendix");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			String ca_typ = in.getString("ca_typ");
			String ca_url = in.getString("ca_url");
			String ca_name = in.getString("ca_name");
			Timestamp ca_time = in.getTimestamp("ca_time");
			int f = courseAppendixService.addCourseAppendix(cs_id, ca_typ, ca_url, ca_name, ca_time);
			if(f == 0) {
				obj.put("state", "OK");
			} else {
				obj.put("state", "数据库错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/deleteCourseAppendix"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCourseAppendix(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseAppendixController-deleteCourseAppendix");

		JSONObject obj = new JSONObject();
		try {
			int ca_id = in.getIntValue("ca_id");
			int f = courseAppendixService.deleteCourseAppendix(ca_id);
			if(f == 0) {
				obj.put("state", "OK");
			} else {
				obj.put("state", "数据库错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	@RequestMapping(value= {"/getCourseAppendixByCs_idAndTch_id"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseAppendixByCs_idAndTch_id(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseAppendixController-getCourseAppendixByCs_idAndTch_id");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = -1;
			try {
				cs_id = in.getIntValue("cs_id");
				if(cs_id <= 0) {
					obj.put("state", "请输入正确的开课流水号！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "请输入正确的开课流水号！");
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
			
			
			int tch_id1 =  (Integer) request.getSession().getAttribute("TCH_ID");
			
			int has = 0;
			List<CourseArrangement> courseArrangementList = courseArrangementService.getCourseArrangementByCs_id(cs_id);
			for(CourseArrangement cag: courseArrangementList) {
				int tch_id = cag.getTch_id();
				if (tch_id1 == tch_id) {
					has = 1;
					break;
				}
			}
			if(has == 0) {
				obj.put("state", "您没有这门课的权限！");
				return obj;
			}
			
			//课程设置简介表；教学大纲；实验项目卡；教学进度计划表；命题计划书；
			List<CourseAppendix> list1 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "课程设置简介表");
			List<CourseAppendix> list2 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "教学大纲");
			List<CourseAppendix> list3 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "实验项目卡");
			List<CourseAppendix> list4 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "教学进度计划表");
			List<CourseAppendix> list5 = courseAppendixService.getCourseAppendixListByCs_idAndCa_typ(cs_id, "命题计划书");
			
		
			
			obj.put("course", course);
			obj.put("courseSet", courseSet);
			
		
			obj.put("list1", list1);
			obj.put("list2", list2);
			obj.put("list3", list3);
			obj.put("list4", list4);
			obj.put("list5", list5);
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
}
