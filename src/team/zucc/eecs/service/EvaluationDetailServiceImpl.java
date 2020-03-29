package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.StudentEvaluationDetailDao;
import team.zucc.eecs.model.EvaluationDetail;

@Component("EvaluationDetailServiceImpl")
public class EvaluationDetailServiceImpl implements EvaluationDetailService {
	@Autowired
	private  EvaluationDetailDao evaluationDetailDao;
	
	@Autowired
	private  StudentEvaluationDetailDao studentEvaluationDetailDao;
	
	@Override
	public EvaluationDetail getEvaluationDetailByEd_id(int ed_id) {
		try {
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByEd_id(ed_id);
			return ed;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCs_idAndEt_id(int cs_id, int et_id) {
		try {
			List<EvaluationDetail> edList = evaluationDetailDao.getEvaluationDatailListByCs_idAndEt_id(cs_id, et_id);
			return edList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCont_idAndCs_idAndEt_id(int cont_id, int cs_id, int et_id) {
		try {
			List<EvaluationDetail> edList = evaluationDetailDao.getEvaluationDatailListByCont_idAndCs_idAndEt_id(cont_id, cs_id, et_id);
			return edList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public EvaluationDetail getEvaluationDetailByInf(int cs_id, int et_id, String ed_num) {
		try {
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByInf(cs_id, et_id, ed_num);
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
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByInf(cs_id, et_id, ed_num);
			if(ed != null) {
				return 1; 
			} 
			evaluationDetailDao.addEvaluationDetail(cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deleteEvaluationDetailByEd_id(int ed_id) {
		try {
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByEd_id(ed_id);
			if(ed == null) {
				return 1;
			}
			studentEvaluationDetailDao.deleteStudentEvaluationDetailByEd_id(ed_id);
			evaluationDetailDao.deleteEvaluationDetailByEd_id(ed_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updateEvaluationDetailByEd_id(int ed_id, int cont_id, int cs_id, int et_id, String ed_num,
			double ed_points, double ed_score) {
		try {
		    double	ed_sc_rt = 0.0;
		    if (ed_points > 0) {
		    	ed_sc_rt = ed_score / ed_points;
		    }
		    evaluationDetailDao.updateEvaluationDetailByEd_id(ed_id, cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
			
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updateEvaluationDetailEd_scoreAndEd_sc_rtByEd_id(int ed_id) {
		try {
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByEd_id(ed_id);
			if(ed == null) {
				return 1;
			}
			double ed_score = studentEvaluationDetailDao.getEd_scoreByEd_idFromTb_stu_ed(ed_id);
			int cont_id = ed.getCont_id();
			int cs_id = ed.getCs_id();
			int et_id = ed.getEt_id();
			String ed_num = ed.getEd_num();
			double ed_points = ed.getEd_points();
			double ed_sc_rt = 0.0;
			if(ed_points > 0) ed_sc_rt = ed_score / ed_points;
			evaluationDetailDao.updateEvaluationDetailByEd_id(ed_id, cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
		} catch (Exception e) {
			return -1;
		}
		return 0;
	}

	@Override
	public double getEvaluationScoreByCo_idAndEt_id(int co_id, int et_id) {
		double rs = 0.0;
		try {
			rs=evaluationDetailDao.getEvaluationScoreByCo_idAndEt_id(co_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public double getEvaluationScoreByIp_idAndEt_id(int ip_id, int et_id) {
		double rs = 0.0;
		try {
			rs=evaluationDetailDao.getEvaluationScoreByIp_idAndEt_id(ip_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public double getEvaluationPointsByCo_idAndEt_id(int co_id, int et_id) {
		double rs = 0.0;
		try {
			rs=evaluationDetailDao.getEvaluationPointsByCo_idAndEt_id(co_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public double getEvaluationPointsByIp_idAndEt_id(int ip_id, int et_id) {
		double rs = 0.0;
		try {
			rs=evaluationDetailDao.getEvaluationPointsByIp_idAndEt_id(ip_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public int updateEvaluationDetailByCs_idAndEt_id(int cs_id, int et_id) {
		try {
			evaluationDetailDao.updateEvaluationDetailByCs_idAndEt_id(cs_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
