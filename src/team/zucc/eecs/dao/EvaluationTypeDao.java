//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.EvaluationType;

public interface EvaluationTypeDao {
	//查询
	List<EvaluationType> getEvaluationTypeList();
	EvaluationType getEvaluationTypeByEt_id(int et_id);
	EvaluationType getEvaluationTypeByEt_name(String et_name);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addEvaluationType(int et_id, String et_name);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateEvaluationType(int et_id, String et_name);
}
