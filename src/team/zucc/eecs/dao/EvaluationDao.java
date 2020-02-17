//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Evaluation;


public interface EvaluationDao {
	//查询
	List<Evaluation> getEvaluationListByCs_id(int cs_id);
	List<Evaluation> getEvaluationListByCs_idAndCo_id(int cs_id, int co_id);
	Evaluation getEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addEvaluation(int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score, double eval_sc_rt, double eval_achv);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteEvaluationByEval_id(int eval_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateEvaluationByEval_id(int eval_id, int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score, double eval_sc_rt, double eval_achv);
}
