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
import team.zucc.eecs.model.EvaluationType;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;
import team.zucc.eecs.service.EvaluationTypeService;

@Controller("EvaluationController")
public class EvaluationController {
	@Autowired
	private EvaluationTypeService evaluationTypeService;
	
	@RequestMapping(value= {"/getEvaluationType"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getEvaluationType(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationController-getEvaluationType");

		JSONObject obj = new JSONObject();
		List<EvaluationType> evaluationTypeList = new ArrayList<EvaluationType>();
		try {
			evaluationTypeList = evaluationTypeService.getEvaluationTypeList();

			JSONArray arr = new JSONArray();
			arr.addAll(evaluationTypeList);
			
			obj.put("evaluationTypeList_num", evaluationTypeList.size());
			obj.put("evaluationTypeList", arr);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	@RequestMapping(value= {"/addEvaluationType"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addEvaluationType(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationController-addEvaluationType");

		JSONObject obj = new JSONObject();
		try {
			int et_id = in.getIntValue("et_id");
			String et_name = in.getString("et_name");
			EvaluationType evaluationType = evaluationTypeService.getEvaluationTypeByEt_name(et_name);
			
			if(evaluationType != null) {
				obj.put("state", "类型名重复，请重新添加！");
				return obj;
			}
			evaluationTypeService.addEvaluationType(et_id, et_name);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
}
