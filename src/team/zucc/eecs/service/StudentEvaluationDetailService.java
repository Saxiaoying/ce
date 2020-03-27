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
	
	double getStudentScoreByStu_idAndCo_idAndEt_id(int stu_id, int co_id, int et_id);
	double getStudentScoreByStu_idAndIp_idAndEt_id(int stu_id, int ip_id, int et_id);
	
	int updateStudentEvaluationDetailListSe_score(List<Integer> stu_idList, List<Integer> ed_idList,  List<Double>se_scoreList);
}
