package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.StudentEvaluationDetailDao;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetailFromView;

@Component("StudentEvaluationDetailServiceImpl")
public class StudentEvaluationDetailServiceImpl implements StudentEvaluationDetailService {
	@Autowired
	private  StudentEvaluationDetailDao studentEvaluationDetailDao;

	@Autowired
	private  EvaluationDetailDao evaluationDetailDao;

	
	@Override
	public StudentEvaluationDetail getStudentEvaluationDetailBySe_id(int se_id) {
		try {
			StudentEvaluationDetail studentEvaluationDetail=studentEvaluationDetailDao.getStudentEvaluationDetailBySe_id(se_id);
			return studentEvaluationDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentEvaluationDetail> getStudentEvaluationDetailListByEd_id(int ed_id) {
		try {
			List<StudentEvaluationDetail> studentEvaluationDetailList = studentEvaluationDetailDao.getStudentEvaluationDetailListByEd_id(ed_id);
			return studentEvaluationDetailList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentEvaluationDetail getStudentEvaluationDetailByStu_idAndEd_id(int stu_id, int ed_id) {
		try {
			StudentEvaluationDetail studentEvaluationDetail=studentEvaluationDetailDao.getStudentEvaluationDetailByStu_idAndEd_id(stu_id, ed_id);
			return studentEvaluationDetail;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addStudentEvaluationDetail(int stu_id, int ed_id, double se_score) {
		try {
			EvaluationDetail ed = evaluationDetailDao.getEvaluationDetailByEd_id(ed_id);
			if(ed == null) {
				return 2; //没有父元素
			}
			
			StudentEvaluationDetail studentEvaluationDetail=studentEvaluationDetailDao.getStudentEvaluationDetailByStu_idAndEd_id(stu_id, ed_id);
			if (studentEvaluationDetail != null) {
				return 1;
			}
			studentEvaluationDetailDao.addStudentEvaluationDetail(stu_id, ed_id, se_score);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deleteStudentEvaluationDetailBySe_id(int se_id) {
		try {
			StudentEvaluationDetail studentEvaluationDetail=studentEvaluationDetailDao.getStudentEvaluationDetailBySe_id(se_id);
			if (studentEvaluationDetail == null) {
				return 1; //没有
			}
			studentEvaluationDetailDao.deleteStudentEvaluationDetailBySe_id(se_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updateStudentEvaluationDetailSe_score(int se_id, double se_score) {
		try {
			StudentEvaluationDetail studentEvaluationDetail=studentEvaluationDetailDao.getStudentEvaluationDetailBySe_id(se_id);
			if (studentEvaluationDetail == null) {
				return 1; //没有
			}
			studentEvaluationDetailDao.updateStudentEvaluationDetailSe_score(se_id, se_score);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public double getStudentScoreByStu_idAndCo_idAndEt_id(int stu_id, int co_id, int et_id) {
		double score = 0.0;
		try {
			score=studentEvaluationDetailDao.getStudentScoreByStu_idAndCo_idAndEt_id(stu_id, co_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}

	@Override
	public double getStudentScoreByStu_idAndIp_idAndEt_id(int stu_id, int ip_id, int et_id) {
		double score = 0.0;
		try {
			score=studentEvaluationDetailDao.getStudentScoreByStu_idAndIp_idAndEt_id(stu_id, ip_id, et_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return score;
	}

	@Override
	public int updateStudentEvaluationDetailListSe_score(List<Integer> stu_idList, List<Integer> ed_idList,
			List<Double> se_scoreList) {
		try {
			studentEvaluationDetailDao.updateStudentEvaluationDetailListSe_score(stu_idList, ed_idList, se_scoreList);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<StudentEvaluationDetailFromView> getStudentEvaluationDetailListByCs_idAndEt_id(int cs_id, int et_id) {
		try {
			List<StudentEvaluationDetailFromView> seList = studentEvaluationDetailDao.getStudentEvaluationDetailListByCs_idAndEt_id(cs_id, et_id);
			return seList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
