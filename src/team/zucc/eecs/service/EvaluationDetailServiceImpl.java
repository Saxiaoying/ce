package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.model.EvaluationDetail;

@Component("EvaluationDetailServiceImpl")
public class EvaluationDetailServiceImpl implements EvaluationDetailService {
	@Autowired
	private  EvaluationDetailDao EvaluationDetailDao;
	
	@Override
	public EvaluationDetail getEvaluationDetailByEd_id(int ed_id) {
		try {
			EvaluationDetail ed = EvaluationDetailDao.getEvaluationDetailByEd_id(ed_id);
			return ed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCs_idAndEt_id(int cs_id, int et_id) {
		try {
			List<EvaluationDetail> edList = EvaluationDetailDao.getEvaluationDatailListByCs_idAndEt_id(cs_id, et_id);
			return edList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCont_idAndCs_idAndEt_id(int cont_id, int cs_id, int et_id) {
		try {
			List<EvaluationDetail> edList = EvaluationDetailDao.getEvaluationDatailListByCont_idAndCs_idAndEt_id(cont_id, cs_id, et_id);
			return edList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EvaluationDetail getEvaluationDetailByInf(int cs_id, int et_id, String ed_num) {
		try {
			EvaluationDetail ed = EvaluationDetailDao.getEvaluationDetailByInf(cs_id, et_id, ed_num);
			return ed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addEvaluationDetail(int cont_id, int cs_id, int et_id, String ed_num, double ed_points,
			double ed_score, double ed_sc_rt) {
		try {
			EvaluationDetail ed = EvaluationDetailDao.getEvaluationDetailByInf(cs_id, et_id, ed_num);
			if(ed != null) {
				return 1; 
			} else {
				EvaluationDetailDao.addEvaluationDetail(cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deleteEvaluationDetailByEd_id(int ed_id) {
		try {
			EvaluationDetailDao.deleteEvaluationDetailByEd_id(ed_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updateEvaluationByEd_id(int ed_id, int cont_id, int cs_id, int et_id, String ed_num,
			double ed_points, double ed_score, double ed_sc_rt) {
		try {
			EvaluationDetailDao.updateEvaluationByEd_id(ed_id, cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
