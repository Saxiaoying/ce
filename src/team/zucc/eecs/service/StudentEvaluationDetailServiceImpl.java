package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.StudentEvaluationDetailDao;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetail;

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

}
