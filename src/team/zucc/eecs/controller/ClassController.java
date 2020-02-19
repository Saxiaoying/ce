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

}
