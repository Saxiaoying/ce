package team.zucc.eecs.service;

import java.util.List;
import team.zucc.eecs.model.EvaluationDetail;

public interface EvaluationDetailService {
	EvaluationDetail getEvaluationDetailByEd_id(int ed_id);
	List<EvaluationDetail> getEvaluationDatailListByCs_idAndEt_id(int cs_id, int et_id);
	List<EvaluationDetail> getEvaluationDatailListByCont_idAndCs_idAndEt_id(int cont_id, int cs_id, int et_id);
	EvaluationDetail getEvaluationDetailByInf(int cs_id, int et_id, String ed_num);
	
	int addEvaluationDetail(int cont_id, int cs_id, int et_id, 
			String ed_num, double ed_points, double ed_score, double ed_sc_rt);
	int deleteEvaluationDetailByEd_id(int ed_id);
	int updateEvaluationDetailByEd_id(int ed_id, int cont_id, int cs_id, int et_id, 
			String ed_num, double ed_points, double ed_score);
	
	int updateEvaluationDetailEd_scoreAndEd_sc_rtByEd_id(int ed_id);
	
	double getEvaluationScoreByCo_idAndEt_id(int co_id, int et_id);
	double getEvaluationScoreByIp_idAndEt_id(int ip_id, int et_id);
	
	
	double getEvaluationPointsByCo_idAndEt_id(int co_id, int et_id);
	double getEvaluationPointsByIp_idAndEt_id(int ip_id, int et_id);
}
