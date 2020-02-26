package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.StudentEvaluationDetail;

public interface StudentEvaluationDetailService {
	StudentEvaluationDetail getStudentEvaluationDetailBySe_id(int se_id);
	List<StudentEvaluationDetail> getStudentEvaluationDetailListByEd_id(int ed_id);
	StudentEvaluationDetail getStudentEvaluationDetailByStu_idAndEd_id(int stu_id, int ed_id);
	int addStudentEvaluationDetail(int stu_id, int ed_id,  double se_score);
	int deleteStudentEvaluationDetailBySe_id(int se_id);
	int updateStudentEvaluationDetailSe_score(int se_id, double se_score);
}
