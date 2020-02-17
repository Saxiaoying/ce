//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationTypeDao;
import team.zucc.eecs.model.EvaluationType;

@Component("EvaluationTypeServiceImpl")
public class EvaluationTypeServiceImpl implements EvaluationTypeService {
	@Autowired
	private  EvaluationTypeDao evaluationTypeDao;
	
	@Override
	public List<EvaluationType> getEvaluationTypeList() {
		try {
			List<EvaluationType> evaluationTypeList = evaluationTypeDao.getEvaluationTypeList();
			return evaluationTypeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EvaluationType getEvaluationTypeByEt_id(int et_id) {
		try {
			EvaluationType evaluationType = evaluationTypeDao.getEvaluationTypeByEt_id(et_id);
			return evaluationType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public EvaluationType getEvaluationTypeByEt_name(String et_name) {
		try {
			EvaluationType evaluationType = evaluationTypeDao.getEvaluationTypeByEt_name(et_name);
			return evaluationType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addEvaluationType(int et_id, String et_name) {
		try {
			EvaluationType evaluationType = evaluationTypeDao.getEvaluationTypeByEt_name(et_name);
			if(evaluationType != null) return 1; //数据重复
			evaluationType = evaluationTypeDao.getEvaluationTypeByEt_id(et_id);
			if(evaluationType != null) return 1; //数据重复
			evaluationTypeDao.addEvaluationType(et_id, et_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}

	@Override
	public int updateEvaluationType(int et_id, String et_name) {
		try {
			EvaluationType evaluationType = evaluationTypeDao.getEvaluationTypeByEt_id(et_id);
			if(evaluationType == null) return 2; //数据不存在
			
			EvaluationType evaluationType2 = evaluationTypeDao.getEvaluationTypeByEt_name(et_name);
			if(evaluationType.getEt_id() == evaluationType2.getEt_id()) return 1; //数据重复
			
			evaluationTypeDao.updateEvaluationType(et_id, et_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}
}
