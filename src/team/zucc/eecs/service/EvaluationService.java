//This is maintained by jyl. 
package team.zucc.eecs.service;


import team.zucc.eecs.model.Evaluation;

public interface EvaluationService {
		int existEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id);//0 不存在； 1 存在
		
		Evaluation getEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id);
		
		//0成功
		int addEvaluation(int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score, double eval_sc_rt, double eval_achv);
		
		int deleteEvaluationByEval_id(int eval_id);
		
		int updateEvaluationByEval_id(int eval_id, int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score, double eval_sc_rt, double eval_achv);

		//根据细化项得出总的分数
		int updateEvaluationByCs_idAndCo_idAndEt_id(int co_id, int cs_id, int et_id); 
}
