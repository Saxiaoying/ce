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
	int updateEvaluationByEd_id(int ed_id, int cont_id, int cs_id, int et_id, 
			String ed_num, double ed_points, double ed_score, double ed_sc_rt);
}
