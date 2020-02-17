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

import team.zucc.eecs.model.ContentObjective;
import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.PracticeObjective;
import team.zucc.eecs.service.ContentObjectiveService;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CoursePracticeService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;
import team.zucc.eecs.service.EvaluationDetailService;
import team.zucc.eecs.service.EvaluationService;
import team.zucc.eecs.service.PracticeObjectiveService;

@Controller("EvaluationDetailController")
public class EvaluationDetailController {
	@Autowired
	private EvaluationDetailService evaluationDetailService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseContentService courseContentService;
	
	@Autowired
	private CoursePracticeService  coursePracticeService;
	
	@Autowired
	private EvaluationService  evaluationService;
	
	@Autowired
	private PracticeObjectiveService practiceObjectiveService;
	
	
	@Autowired
	private ContentObjectiveService contentObjectiveService;
	
	@RequestMapping(value= {"/getEvaluationDetailList"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getEvaluationDetailList(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-getEvaluationDetailList");

		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			String cs_acad_yr = in.getString("cs_acad_yr");
			String cs_sem = in.getString("cs_sem");
			int et_id = in.getIntValue("et_id");
			
			
			CourseSet courseSet = courseSetService.getCourseSetByCoz_idAndTime(coz_id, cs_acad_yr, cs_sem);
			if(courseSet == null) {
				obj.put("state", "暂无该开课信息！");
				return obj;
			}
			
			int cs_id = courseSet.getCs_id();
			
			List<EvaluationDetail> evaluationDetailList = evaluationDetailService.getEvaluationDatailListByCs_idAndEt_id(cs_id, et_id);
			if(evaluationDetailList == null) {
				evaluationDetailList = new ArrayList<EvaluationDetail>();
			}
			
			List<CourseContent> courseContentList = courseContentService.getCourseContentListByCs_id(cs_id);
			if(courseContentList == null) {
				courseContentList = new ArrayList<CourseContent>();
			}
			
			List<CoursePractice> coursePracticeList = coursePracticeService.getCoursePracticeListByCs_id(cs_id);
			if(coursePracticeList == null) {
				coursePracticeList = new ArrayList<CoursePractice>();
			}
			
			Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			
			obj.put("evaluationDetailList_num", evaluationDetailList.size());
			obj.put("evaluationDetailList", evaluationDetailList);
			obj.put("courseContentList", courseContentList);
			obj.put("courseContentList_num", courseContentList.size());
			obj.put("coursePracticeList", coursePracticeList);
			obj.put("coursePracticeList_num", coursePracticeList.size());
			obj.put("state", "OK");
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/addEvaluationDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addEvaluationDetail(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-addEvaluationDetail");

		JSONObject obj = new JSONObject();
		try {
			int cont_id = in.getIntValue("cont_id");
			int cs_id = in.getIntValue("cs_id");
			int et_id = in.getIntValue("et_id");
			
			String ed_num = in.getString("ed_num");
			String ed_points_tmp = in.getString("ed_points");
			
			ed_num = ed_num.trim();
			if(ed_num.isEmpty()) ed_num = null;
			
			ed_points_tmp = ed_points_tmp.trim();
			if(ed_points_tmp.isEmpty()) ed_points_tmp = null;
			
			if (ed_points_tmp == null || ed_num == null) {
				obj.put("state", "请填写必选项！");
				return obj;
			}
			
			double ed_points;
			try {
				ed_points = Double.valueOf(ed_points_tmp);
				if(ed_points <= 0) {
					obj.put("state", "设定的分数必须大于0！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "设定的分数必须为数字！");
				return obj;
			}
			int f = evaluationDetailService.addEvaluationDetail(cont_id, cs_id, et_id, ed_num, ed_points, 0, 0);
			if(f == 1) {
				obj.put("state", "序号有重复，请重新设置！");
				return obj;
			} else if(f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("state", "OK");
				
				
				if(et_id == 1) {
					List<PracticeObjective> poList = practiceObjectiveService.getPracticeObjectiveByPra_id(cont_id);
					for (PracticeObjective po: poList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(po.getCo_id(), cs_id, et_id);
					}
				} else if(et_id == 2){
					List<ContentObjective> coList = contentObjectiveService.getContentObjectiveByCont_id(cont_id);
					for (ContentObjective co: coList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(co.getCo_id(), cs_id, et_id);
					}
				}
				return obj;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
	}
	
	
	@RequestMapping(value= {"/getEvaluationDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getEvaluationDetail(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-getEvaluationDetail");

		JSONObject obj = new JSONObject();
		try {
			int ed_id = in.getIntValue("ed_id");
			EvaluationDetail evaluationDetail = evaluationDetailService.getEvaluationDetailByEd_id(ed_id);
			
			if(evaluationDetail == null) {
				obj.put("state", "没有这个明细项！");
				return obj;
			}
			obj.put("evaluationDetail", evaluationDetail);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	@RequestMapping(value= {"/updateEvaluationDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateEvaluationDetail(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-updateEvaluationDetail");

		JSONObject obj = new JSONObject();
		try {
			int ed_id = in.getIntValue("ed_id");
			int cont_id = in.getIntValue("cont_id");
			int cs_id = in.getIntValue("cs_id");
			int et_id = in.getIntValue("et_id");
			
			String ed_num = in.getString("ed_num");
			String ed_points_tmp = in.getString("ed_points");
			
			ed_num = ed_num.trim();
			if(ed_num.isEmpty()) ed_num = null;
			
			ed_points_tmp = ed_points_tmp.trim();
			if(ed_points_tmp.isEmpty()) ed_points_tmp = null;
			
			if (ed_points_tmp == null || ed_num == null) {
				obj.put("state", "请填写必选项！");
				return obj;
			}
			
			double ed_points;
			try {
				ed_points = Double.valueOf(ed_points_tmp);
				if(ed_points <= 0) {
					obj.put("state", "设定的分数必须大于0！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "设定的分数必须为数字！");
				return obj;
			}
			
			
			EvaluationDetail evaluationDetail = evaluationDetailService.getEvaluationDetailByEd_id(ed_id);
			double ed_score = evaluationDetail.getEd_score();
			double ed_sc_rt = evaluationDetail.getEd_sc_rt();
			int f = evaluationDetailService.updateEvaluationByEd_id(ed_id, cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
			if(f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("state", "OK");
				
				if(et_id == 1) {
					List<PracticeObjective> poList = practiceObjectiveService.getPracticeObjectiveByPra_id(cont_id);
					for (PracticeObjective po: poList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(po.getCo_id(), cs_id, et_id);
					}
				} else if(et_id == 2){
					List<ContentObjective> coList = contentObjectiveService.getContentObjectiveByCont_id(cont_id);
					for (ContentObjective co: coList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(co.getCo_id(), cs_id, et_id);
					}
				}
				return obj;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
	}
	
	
	@RequestMapping(value= {"/deleteEvaluationDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteEvaluationDetail(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-deleteEvaluationDetail");

		JSONObject obj = new JSONObject();
		try {
			int ed_id = in.getIntValue("ed_id");
			
			EvaluationDetail evaluationDetail = evaluationDetailService.getEvaluationDetailByEd_id(ed_id);
			
			int et_id = evaluationDetail.getEt_id();
			int cont_id = evaluationDetail.getCont_id();
			int cs_id = evaluationDetail.getCs_id();
			
			int f = evaluationDetailService.deleteEvaluationDetailByEd_id(ed_id);
			if(f == -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("state", "OK");
				
				if(et_id == 1) {
					List<PracticeObjective> poList = practiceObjectiveService.getPracticeObjectiveByPra_id(cont_id);
					for (PracticeObjective po: poList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(po.getCo_id(), cs_id, et_id);
					}
				} else if(et_id == 2){
					List<ContentObjective> coList = contentObjectiveService.getContentObjectiveByCont_id(cont_id);
					for (ContentObjective co: coList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(co.getCo_id(), cs_id, et_id);
					}
				}
				return obj;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
	}
}
