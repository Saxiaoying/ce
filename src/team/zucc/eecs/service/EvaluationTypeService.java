//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;
import team.zucc.eecs.model.EvaluationType;

public interface EvaluationTypeService {
		List<EvaluationType> getEvaluationTypeList();
		EvaluationType getEvaluationTypeByEt_id(int et_id);
		EvaluationType getEvaluationTypeByEt_name(String et_name);
		int addEvaluationType(int et_id, String et_name);
		int updateEvaluationType(int et_id, String et_name);
}
