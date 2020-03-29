package team.zucc.eecs.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetailFromView;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.EvaluationDetailService;
import team.zucc.eecs.service.EvaluationService;
import team.zucc.eecs.service.StudentEvaluationDetailService;

@Controller("StudentEvaluationDetailController")
public class StudentEvaluationDetailController {
	@Autowired
	private StudentEvaluationDetailService studentEvaluationDetailService;
	
	@Autowired
	private EvaluationDetailService evaluationDetailService;
	
	@Autowired
	private EvaluationService evaluationService;
	
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@RequestMapping(value = { "/getStudentEvaluationDetailList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getStudentEvaluationDetailList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentEvaluationDetailController-getStudentEvaluationDetailList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			JSONArray stu_idList = in.getJSONArray("stu_idList");
			JSONArray ed_idList = in.getJSONArray("ed_idList");
			
			Double[][] scores = new Double [stu_idList.size()][ed_idList.size()];
		    for(int i = 0; i < num; i++) {
		    	for(int j = 0; j < ed_idList.size(); j++) {
		    		int stu_id = stu_idList.getIntValue(i);
		    		int ed_id = ed_idList.getIntValue(j); 
		    		StudentEvaluationDetail se = studentEvaluationDetailService.getStudentEvaluationDetailByStu_idAndEd_id(stu_id, ed_id);
		    		if(se== null) {
		    			int f = studentEvaluationDetailService.addStudentEvaluationDetail(stu_id, ed_id, 0);
		    			if(f != 0) {
		    				obj.put("state", "该学号或者明细ID不存在！");
		    				return obj;
		    			}
		    			scores[i][j] = 0.0;
		    		} else {
		    			scores[i][j] = se.getSe_score();
		    		}
		    	}
		    }
			
			obj.put("state", "OK");
			obj.put("scores", scores);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/updateStudentEvaluationDetailList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateStudentEvaluationDetailList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentEvaluationDetailController-updateStudentEvaluationDetailList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			JSONArray stu_idList = in.getJSONArray("stu_idList");
			JSONArray ed_idList = in.getJSONArray("ed_idList");
			JSONArray se_scoreList = in.getJSONArray("se_scoreList");
			Set<Integer> ed_idSet = new HashSet<Integer>();
			
			String inf = "";
			for (int i = 0; i < num; i++) {
				int stu_id = stu_idList.getIntValue(i);
				int ed_id = ed_idList.getIntValue(i);
				double se_score = se_scoreList.getDoubleValue(i);
				ed_idSet.add(ed_id);
				StudentEvaluationDetail se = studentEvaluationDetailService.getStudentEvaluationDetailByStu_idAndEd_id(stu_id, ed_id);
				if (se == null) {
					inf += "没有学号为" + stu_id + "，明细ID为" + ed_id + "这个数据！\n";
				}
				int se_id = se.getSe_id();
				int f = studentEvaluationDetailService.updateStudentEvaluationDetailSe_score(se_id, se_score);
				
				if(f != 0) {
					inf +=  "录入学号为" + stu_id +  "，明细ID为" + ed_id + "这个数据时产生错误！\n";
				}
			}
			for(Integer ed_id: ed_idSet) {
				evaluationDetailService.updateEvaluationDetailEd_scoreAndEd_sc_rtByEd_id(ed_id);
			}
			
			if(num > 0) {
				int ed_id = ed_idList.getIntValue(0);
				EvaluationDetail ed = evaluationDetailService.getEvaluationDetailByEd_id(ed_id);
				int et_id = ed.getEt_id();
				int cs_id = ed.getCs_id();
				List<CourseObjective> coList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
				for (CourseObjective co: coList) {
					evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(co.getCo_id(), cs_id, et_id);
				}
			}
			
			if(inf.length() == 0) inf = "OK";
			obj.put("state", inf);
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/getStudentEvaluationDetailListAll" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getStudentEvaluationDetailListAll(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入StudentEvaluationDetailController-getStudentEvaluationDetailListAll");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			int et_id = in.getIntValue("et_id");
			
			List<StudentEvaluationDetailFromView> seList = studentEvaluationDetailService.getStudentEvaluationDetailListByCs_idAndEt_id(cs_id, et_id);
			
			
			obj.put("seList", seList);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
