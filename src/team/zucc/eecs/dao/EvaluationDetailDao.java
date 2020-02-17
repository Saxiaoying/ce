//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.EvaluationDetail;

public interface EvaluationDetailDao {
	//查询
	EvaluationDetail getEvaluationDetailByEd_id(int ed_id);
	List<EvaluationDetail> getEvaluationDatailListByCs_idAndEt_id(int cs_id, int et_id);
	List<EvaluationDetail> getEvaluationDatailListByCont_idAndCs_idAndEt_id(int cont_id, int cs_id, int et_id);
	EvaluationDetail getEvaluationDetailByInf(int cs_id, int et_id, String ed_num);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addEvaluationDetail(int cont_id, int cs_id, int et_id, 
			String ed_num, double ed_points, double ed_score, double ed_sc_rt);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteEvaluationDetailByEd_id(int ed_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateEvaluationByEd_id(int ed_id, int cont_id, int cs_id, int et_id, 
			String ed_num, double ed_points, double ed_score, double ed_sc_rt);
}
