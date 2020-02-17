//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.ContentObjectiveDao;
import team.zucc.eecs.dao.CourseObjectiveDao;
import team.zucc.eecs.dao.CourseSetDao;
import team.zucc.eecs.dao.EvaluationDao;
import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.EvaluationTypeDao;
import team.zucc.eecs.dao.PracticeObjectiveDao;
import team.zucc.eecs.model.ContentObjective;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.model.Evaluation;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.EvaluationType;
import team.zucc.eecs.model.PracticeObjective;

@Component("EvaluationServiceImpl")
public class EvaluationServiceImpl implements EvaluationService {
	@Autowired
	private  CourseObjectiveDao courseObjectiveDao;
	
	@Autowired
	private  EvaluationTypeDao evaluationTypeDao;
	
	@Autowired
	private  EvaluationDao evaluationDao;
	
	@Autowired
	private  EvaluationDetailDao evaluationDetailDao;
	
	@Autowired
	private  ContentObjectiveDao contentObjectiveDao;
	
	@Autowired
	private  PracticeObjectiveDao practiceObjective;
	
	@Override
	public int existEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id) {
		try {
			Evaluation e = evaluationDao.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			if(e == null) return 0;
			else return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //出错
		}
	}

	@Override
	public Evaluation getEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id) {
		try {
			Evaluation e = evaluationDao.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addEvaluation(int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score,
			double eval_sc_rt, double eval_achv) {
		try {
			evaluationDao.addEvaluation(co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteEvaluationByEval_id(int eval_id) {
		try {
			evaluationDao.deleteEvaluationByEval_id(eval_id);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateEvaluationByEval_id(int eval_id, int co_id, int cs_id, int et_id, double eval_prop, double eval_points,
			double eval_score, double eval_sc_rt, double eval_achv) {
		try {
			evaluationDao.updateEvaluationByEval_id(eval_id, co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateEvaluationByCs_idAndCo_idAndEt_id(int co_id, int cs_id, int et_id) {
		try {
			CourseObjective courseObjective = courseObjectiveDao.getCourseObjectiveByCo_id(co_id);
			if (courseObjective == null || courseObjective.getCs_id() != cs_id) {
				return 1; //不存在
			}
			
			EvaluationType evaluationType = evaluationTypeDao.getEvaluationTypeByEt_id(et_id);
			if(evaluationType == null) {
				return 1; //不存在
			}
			
			Evaluation e = evaluationDao.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			if(e == null) {
				evaluationDao.addEvaluation(co_id, cs_id, et_id, 0, 0, 0, 0, 0);
			}
			
			if(et_id == 2) {
				List<ContentObjective> contentObjectiveList = contentObjectiveDao.getContentObjectiveByCo_id(co_id);
				Set<Integer> cont_idSet = new HashSet<Integer>();
				for(ContentObjective co : contentObjectiveList) {
					cont_idSet.add(co.getCont_id());
				}
				List<EvaluationDetail> edList = new ArrayList<EvaluationDetail>();
				for(Integer cont_id : cont_idSet) {
					List<EvaluationDetail> edList1 = evaluationDetailDao.getEvaluationDatailListByCont_idAndCs_idAndEt_id(cont_id, cs_id, et_id);
					edList.addAll(edList1);
				}
				int eval_id = e.getEval_id();
				double eval_prop = e.getEval_prop();
				double eval_points = 0;
				double eval_score = 0;
				double eval_sc_rt = 0;
				double eval_achv = 1.0;
				for(EvaluationDetail ed : edList) {
					eval_points += ed.getEd_points();
					eval_score += ed.getEd_score();
				}
				if(eval_points > 0)eval_sc_rt = eval_score / eval_points;
				else eval_sc_rt = 0;
				
				eval_achv = eval_sc_rt * eval_prop;
				
				evaluationDao.updateEvaluationByEval_id(eval_id, co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			} else if (et_id == 1) {
				List<PracticeObjective> practiceObjectiveList = practiceObjective.getPracticeObjectiveByCo_id(co_id);
				Set<Integer> pra_idSet = new HashSet<Integer>();
				for(PracticeObjective po : practiceObjectiveList) {
					pra_idSet.add(po.getPra_id());
				}
				List<EvaluationDetail> edList = new ArrayList<EvaluationDetail>();
				for(Integer pra_id : pra_idSet) {
					List<EvaluationDetail> edList1 = evaluationDetailDao.getEvaluationDatailListByCont_idAndCs_idAndEt_id(pra_id, cs_id, et_id);
					edList.addAll(edList1);
				}
				int eval_id = e.getEval_id();
				double eval_prop = e.getEval_prop();
				double eval_points = 0;
				double eval_score = 0;
				double eval_sc_rt = 0;
				double eval_achv = 1.0;
				for(EvaluationDetail ed : edList) {
					eval_points += ed.getEd_points();
					eval_score += ed.getEd_score();
				}
				if(eval_points > 0)eval_sc_rt = eval_score / eval_points;
				else eval_sc_rt = 0;
				
				eval_achv = eval_sc_rt * eval_prop;
				
				evaluationDao.updateEvaluationByEval_id(eval_id, co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
