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

import team.zucc.eecs.model.GraduationRequire;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.service.GraduationRequireService;
import team.zucc.eecs.service.IndexPointService;

@Controller("GraduationRequireController")
public class GraduationRequireController {
	@Autowired
	private GraduationRequireService graduationRequireService;
	
	@Autowired
	private IndexPointService indexPointService;
	
	@RequestMapping(value = { "/getGraduationRequireList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getGraduationRequireList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入GraduationRequireController-getGraduationRequireList");

		JSONObject obj = new JSONObject();
		List<GraduationRequire> graduationRequireList = new ArrayList<GraduationRequire>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			
			graduationRequireList = graduationRequireService.getGraduationRequireListFromAtoB(a, b);
			int total = graduationRequireService.getGraduationRequireListNumber();
			
			List<List<IndexPoint>> gr_ipList = new ArrayList<List<IndexPoint>>();
			for(GraduationRequire gr: graduationRequireList) {
				List<IndexPoint> tmp = indexPointService.getIndexPointListByGr_id(gr.getGr_id());
				if(tmp == null) tmp = new ArrayList<IndexPoint>();
				gr_ipList.add(tmp);
			}
			obj.put("total", total);
			obj.put("graduationRequireList", graduationRequireList);
			obj.put("gr_ipList", gr_ipList);
			if(graduationRequireList.size() == 0) obj.put("state", "暂无记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getGraduationRequire" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getGraduationRequire(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入GraduationRequireController-getGraduationRequire");

		JSONObject obj = new JSONObject();
		try {
			int gr_id = in.getIntValue("gr_id");
			GraduationRequire graduationRequire = graduationRequireService.getGraduationRequireByGr_id(gr_id);
			
			if (graduationRequire == null) {
				obj.put("state", "数据库内没有该数据！");
			} else {
				obj.put("graduationRequire", graduationRequire);
				obj.put("state", "OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
