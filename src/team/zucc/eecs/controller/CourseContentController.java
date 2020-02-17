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

import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CourseObjectiveService;

@Controller("CourseContentController")
public class CourseContentController {
	@Autowired
	private CourseContentService courseContentService;
	
	@RequestMapping(value = { "/updateCourseContentList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCourseContentList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseContentController-updateCourseContentList");
		
		List<CourseContent> courseContentList = new ArrayList<CourseContent>();
		List<CourseContent> courseContentList2 = new ArrayList<CourseContent>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
		    int num = in.getIntValue("num");
		    JSONArray cont_nameL = in.getJSONArray("cont_name");
		    JSONArray cont_contL = in.getJSONArray("cont_cont");
		    JSONArray cont_methodL = in.getJSONArray("cont_method");
		    JSONArray cont_keyL = in.getJSONArray("cont_key");
		    JSONArray cont_diffL = in.getJSONArray("cont_diff");
		    JSONArray cont_hrs_tchL = in.getJSONArray("cont_hrs_tch");
		    JSONArray cont_hrs_prL = in.getJSONArray("cont_hrs_pr");
		    JSONArray cont_cla_exeL = in.getJSONArray("cont_cla_exe");
		    JSONArray cont_hwL = in.getJSONArray("cont_hw");
		    for (int i = 1; i <= num; i++) {
		    	
		    	String cont_name = cont_nameL.getString(i);
		    	int cont_num = i;
		    	String cont_cont = cont_contL.getString(i);
		    	String cont_method = cont_methodL.getString(i);
		    	String cont_key = cont_keyL.getString(i);
		    	String cont_diff = cont_diffL.getString(i);
		    	Double cont_hrs_tch = 0.0;
		    	try {
		    		cont_hrs_tch = cont_hrs_tchL.getDoubleValue(i);
				} catch (Exception e) {
					obj.put("state", "学时必须是数字！");
					return obj;
				}
		    	Double cont_hrs_pr = 0.0;
		    	try {
		    		cont_hrs_pr = cont_hrs_prL.getDoubleValue(i);
				} catch (Exception e) {
					obj.put("state", "学时必须是数字！");
					return obj;
				}
		    	String cont_cla_exe = cont_cla_exeL.getString(i);
		    	String cont_hw = cont_hwL.getString(i);
		    	
		    	
		    	CourseContent cc = new CourseContent();
		    	cc.setCs_id(cs_id);//开课流水号（外码）
		    	cc.setCont_name(cont_name);//教学内容的名称
		    	cc.setCont_num(cont_num);//教学内容序号
		    	cc.setCont_cont(cont_cont);//教学主要内容
		    	cc.setCont_method(cont_method);//教学方法与要求
		    	cc.setCont_key(cont_key);//重点
		    	cc.setCont_diff(cont_diff);//难点
		    	cc.setCont_hrs_tch(cont_hrs_tch);//讲课时数
		    	cc.setCont_hrs_pr(cont_hrs_pr);//实验时数
		    	cc.setCont_cla_exe(cont_cla_exe);//课堂练习
		    	cc.setCont_hw(cont_hw);//课后作业
		    	
		    	courseContentList.add(cc);
		    }
		    for (CourseContent cc: courseContentList) {
		    	courseContentService.updateCourseContent(cc.getCs_id(), cc.getCont_name(), cc.getCont_num(), 
		    			cc.getCont_cont(), cc.getCont_method(), cc.getCont_key(), cc.getCont_diff(), cc.getCont_hrs_tch(), 
		    			cc.getCont_hrs_pr(), cc.getCont_cla_exe(), cc.getCont_hw());
		    }
		    
		    courseContentList2 = courseContentService.getCourseContentListByCs_id(cs_id);
		    for (int i = courseContentList.size(); i < courseContentList2.size(); i++) {
				CourseContent cc = courseContentList2.get(i);
				courseContentService.deleteCourseContent(cs_id, cc.getCont_num());
			}
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getCourseContentList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseContentList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseContentController-getCourseContentList");
		
		List<CourseContent> courseContentList = new ArrayList<CourseContent>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			
			courseContentList = courseContentService.getCourseContentListByCs_id(cs_id);
			if(courseContentList == null) {
				courseContentList = new ArrayList<CourseContent>();
			}
			JSONArray arr = new JSONArray();
			CoursePractice tmp = new CoursePractice();
			arr.add(tmp);
			arr.addAll(courseContentList);
			
			obj.put("total", courseContentList.size());
			obj.put("courseContentList", arr);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
