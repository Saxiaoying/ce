//This is maintained by jyl. 
package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Class;
import team.zucc.eecs.model.ContentObjective;
import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.GraduationRequire;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.ObjectiveIndexPoint;
import team.zucc.eecs.model.PracticeObjective;
import team.zucc.eecs.model.Student;
import team.zucc.eecs.service.ClassService;
import team.zucc.eecs.service.ContentObjectiveService;
import team.zucc.eecs.service.CourseArrangementService;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CoursePracticeService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;
import team.zucc.eecs.service.EvaluationDetailService;
import team.zucc.eecs.service.EvaluationService;
import team.zucc.eecs.service.GraduationRequireService;
import team.zucc.eecs.service.IndexPointService;
import team.zucc.eecs.service.ObjectiveIndexPointService;
import team.zucc.eecs.service.PracticeObjectiveService;
import team.zucc.eecs.service.StudentEvaluationDetailService;
import team.zucc.eecs.service.StudentService;

@Controller("AssociationController")
public class AssociationController {
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
	private StudentEvaluationDetailService  studentEvaluationDetailService;
	
	@Autowired
	private StudentService  studentService;
	
	@Autowired
	private PracticeObjectiveService practiceObjectiveService;
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private ContentObjectiveService contentObjectiveService;
	
	@Autowired
	private ObjectiveIndexPointService objectiveIndexPointService;
	
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@Autowired
	private IndexPointService indexPointService;
	
	@Autowired
	private GraduationRequireService graduationRequireService;
	
	@Autowired 
	private CourseArrangementService courseArrangementService;
	
	@RequestMapping(value= {"/getRelatedInformationByCs_id"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getRelatedInformationByCs_id(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入AssociationController-getRelatedInformationByCs_id");

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
			
			List<CourseObjective> courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			if(courseObjectiveList == null || courseObjectiveList.size() == 0) {
				obj.put("state", "该开课记录暂无课程目标，请前往开课管理-课程目标设置！");
				return obj;
			}
			
			List<ObjectiveIndexPoint> objectiveIndexPointList = objectiveIndexPointService.getObjectiveIndexPointListByCs_id(cs_id);
			Set<Integer> ip_idSet = new HashSet<Integer>();
			for(ObjectiveIndexPoint oip: objectiveIndexPointList) {
				ip_idSet.add(oip.getIp_id());
			}
			List<Integer> ip_idList = new ArrayList<Integer>();
			ip_idList.addAll(ip_idSet);
			Collections.sort(ip_idList); 
			
			List<IndexPoint> indexPointList = new ArrayList<IndexPoint>();
			List<GraduationRequire> graduationRequireList = new ArrayList<GraduationRequire>();
			
			for(int i = 0; i < ip_idList.size(); i++) {
				int ip_id = ip_idList.get(i);
				IndexPoint ip = indexPointService.getIndexPointByIp_id(ip_id);
				if(ip == null) {
					objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
				} else {
					GraduationRequire gr = graduationRequireService.getGraduationRequireByGr_id(ip.getGr_id());
					if(gr == null) {
						objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
					} else {
						indexPointList.add(ip);
						graduationRequireList.add(gr);
					}
				}
			}
			
			
			List<EvaluationDetail> evaluationDetailList1 = evaluationDetailService.getEvaluationDatailListByCs_idAndEt_id(cs_id, 1);
			List<EvaluationDetail> evaluationDetailList2 = evaluationDetailService.getEvaluationDatailListByCs_idAndEt_id(cs_id, 2);
			if(evaluationDetailList1 == null)  evaluationDetailList1 = new ArrayList<EvaluationDetail>();
			if(evaluationDetailList2 == null)  evaluationDetailList2 = new ArrayList<EvaluationDetail>();
			
			String[][] pra_co = new String[evaluationDetailList1.size()][courseObjectiveList.size()];
			String[][] cont_co = new String[evaluationDetailList2.size()][courseObjectiveList.size()];
			
			String[][] pra_ip = new String[evaluationDetailList1.size()][indexPointList.size()];
			String[][] cont_ip = new String[evaluationDetailList2.size()][indexPointList.size()];
			
			List<CoursePractice> coursePracticeList = new ArrayList<CoursePractice>();
			for (int i = 0; i < evaluationDetailList1.size(); i++){
				EvaluationDetail ed1 = evaluationDetailList1.get(i);
				int pra_id = ed1.getCont_id();
				CoursePractice pra = coursePracticeService.getCoursePracticeByPra_id(pra_id);
				coursePracticeList.add(pra);
				
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					int co_id = courseObjectiveList.get(j).getCo_id();
					PracticeObjective po = practiceObjectiveService.getPracticeObjectiveByCo_idAndPra_id(co_id, pra_id);
					if(po != null) pra_co[i][j] = "1";
					else pra_co[i][j] = "";
				}
			}
			for (int i = 0; i < evaluationDetailList1.size(); i++){
				for(int k = 0; k < indexPointList.size(); k++) {
					pra_ip[i][k] = "";
				}
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					if(pra_co[i][j].length() > 0) {
						int co_id = courseObjectiveList.get(j).getCo_id();
						for(int k = 0; k < indexPointList.size(); k++) {
							int ip_id = indexPointList.get(k).getIp_id();
							ObjectiveIndexPoint oip = objectiveIndexPointService.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
							if(oip != null) pra_ip[i][k] = "1";
						}
					}
				}
			}
			
			List<CourseContent> courseContentList = new ArrayList<CourseContent>();
			for (int i = 0; i < evaluationDetailList2.size(); i++){
				EvaluationDetail ed2 = evaluationDetailList2.get(i);
				int cont_id = ed2.getCont_id();
				CourseContent cont = courseContentService.getCourseContentByCont_id(cont_id);
				courseContentList.add(cont);
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					int co_id = courseObjectiveList.get(j).getCo_id();
					ContentObjective co = contentObjectiveService.getContentObjectiveByCo_idAndCont_id(co_id, cont_id);
					if(co != null) cont_co[i][j] = "1";
					else cont_co[i][j] = "";
				}
			}
			for (int i = 0; i < evaluationDetailList2.size(); i++){
				for(int k = 0; k < indexPointList.size(); k++) {
					cont_ip[i][k] = "";
				}
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					if(cont_co[i][j].length() > 0) {
						int co_id = courseObjectiveList.get(j).getCo_id();
						for(int k = 0; k < indexPointList.size(); k++) {
							int ip_id = indexPointList.get(k).getIp_id();
							ObjectiveIndexPoint oip = objectiveIndexPointService.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
							if(oip != null) cont_ip[i][k] = "1";
						}
					}
				}
			}
			
			obj.put("course", course);
			obj.put("courseSet", courseSet);
			
			obj.put("indexPointList", indexPointList);
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("graduationRequireList", graduationRequireList);
			
			obj.put("evaluationDetailList1", evaluationDetailList1);
			obj.put("evaluationDetailList2", evaluationDetailList2);
			obj.put("courseContentList", courseContentList);
			obj.put("coursePracticeList", coursePracticeList);
			
			
			obj.put("pra_co", pra_co);
			obj.put("cont_co", cont_co);
			obj.put("pra_ip", pra_ip);
			obj.put("cont_ip", cont_ip);
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/getStudentScoreByCs_id"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getStudentScoreByCs_id(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入AssociationController-getStudentScoreByCs_id");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = -1;
			int et_id = in.getIntValue("et_id");
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
			
			List<CourseObjective> courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			if(courseObjectiveList == null || courseObjectiveList.size() == 0) {
				obj.put("state", "该开课记录暂无课程目标，请前往开课管理-课程目标设置！");
				return obj;
			}
			
			List<ObjectiveIndexPoint> objectiveIndexPointList = objectiveIndexPointService.getObjectiveIndexPointListByCs_id(cs_id);
			Set<Integer> ip_idSet = new HashSet<Integer>();
			for(ObjectiveIndexPoint oip: objectiveIndexPointList) {
				ip_idSet.add(oip.getIp_id());
			}
			List<Integer> ip_idList = new ArrayList<Integer>();
			ip_idList.addAll(ip_idSet);
			Collections.sort(ip_idList); 
			
			List<IndexPoint> indexPointList = new ArrayList<IndexPoint>();
			List<GraduationRequire> graduationRequireList = new ArrayList<GraduationRequire>();
			
			for(int i = 0; i < ip_idList.size(); i++) {
				int ip_id = ip_idList.get(i);
				IndexPoint ip = indexPointService.getIndexPointByIp_id(ip_id);
				if(ip == null) {
					objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
				} else {
					GraduationRequire gr = graduationRequireService.getGraduationRequireByGr_id(ip.getGr_id());
					if(gr == null) {
						objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
					} else {
						indexPointList.add(ip);
						graduationRequireList.add(gr);
					}
				}
			}
			
			
			List<CourseArrangement> courseArrangementList = courseArrangementService.getCourseArrangementByCs_id(cs_id);
			List<Student> studentList = new ArrayList<Student>();
			for(CourseArrangement ca:courseArrangementList) {
				List<Student> tmpList = studentService.getStudentListByCag_id(ca.getCag_id());
				if(tmpList != null)studentList.addAll(tmpList);
			}
			String[] class_nameList = new String[studentList.size()];
			for(int i = 0; i < studentList.size(); i++) {
				int class_id = studentList.get(i).getClass_id();
				Class c = classService.getClassByClass_id(class_id);
				class_nameList[i] = c.getClass_name();
			}
			
			double[][] score_co = new double[studentList.size()][courseObjectiveList.size()];
			double[][] score_ip = new double[studentList.size()][indexPointList.size()];
			
			for(int i = 0; i < studentList.size(); i++) {
				int stu_id = studentList.get(i).getStu_id();
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					int co_id = courseObjectiveList.get(j).getCo_id();
					score_co[i][j] = studentEvaluationDetailService.getStudentScoreByStu_idAndCo_idAndEt_id(stu_id, co_id, et_id);
				}
			}
			
			for(int i = 0; i < studentList.size(); i++) {
				int stu_id = studentList.get(i).getStu_id();
				for(int j = 0; j < indexPointList.size(); j++) {
					int ip_id = indexPointList.get(j).getIp_id();
					score_ip[i][j] = studentEvaluationDetailService.getStudentScoreByStu_idAndIp_idAndEt_id(stu_id, ip_id, et_id);
				}
			}
			
			obj.put("course", course);
			obj.put("courseSet", courseSet);
			
			obj.put("indexPointList", indexPointList);
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("graduationRequireList", graduationRequireList);
			
			obj.put("studentList", studentList);
			obj.put("class_nameList", class_nameList);
			
			obj.put("score_co", score_co);
			obj.put("score_ip", score_ip);
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
}
