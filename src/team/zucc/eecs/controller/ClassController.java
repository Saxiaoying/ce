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
import team.zucc.eecs.service.ClassService;

@Controller("ClassController")
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@RequestMapping(value = { "/getClassList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getClassList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入ClassController-getClassList");

		JSONObject obj = new JSONObject();
		List<Class> classList = new ArrayList<Class>();
		try {
			
			classList = classService.getClassList();
			
			obj.put("classList", classList);
			
			if (classList.size() == 0) obj.put("state", "暂无班级的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

	
	@RequestMapping(value = { "/getClassListByCag_id" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getClassListByCag_id(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入ClassController-getClassListByCag_id");

		JSONObject obj = new JSONObject();
		List<Class> classList = new ArrayList<Class>();
		try {
			int cag_id = 0;
			try {
				cag_id = in.getIntValue("cag_id");
			} catch (Exception e) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			if(cag_id <= 0) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			
			classList = classService.getClassListByCag_id(cag_id);
			
			obj.put("classList", classList);
			
			if (classList.size() == 0) obj.put("state", "暂无班级的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

	
}
