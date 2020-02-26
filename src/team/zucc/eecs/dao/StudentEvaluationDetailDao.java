package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.StudentEvaluationDetail;

public interface StudentEvaluationDetailDao {
	StudentEvaluationDetail getStudentEvaluationDetailBySe_id(int se_id);
	List<StudentEvaluationDetail> getStudentEvaluationDetailListByEd_id(int ed_id);
	int getStudentEvaluationDetailNumberByEd_id(int ed_id);
	StudentEvaluationDetail getStudentEvaluationDetailByStu_idAndEd_id(int stu_id, int ed_id);
	
	double getEd_scoreByEd_idFromTb_stu_ed(int ed_id);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addStudentEvaluationDetail(int stu_id, int ed_id,  double se_score);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteStudentEvaluationDetailBySe_id(int se_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteStudentEvaluationDetailByEd_id(int ed_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteStudentEvaluationDetailByStu_id(int stu_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateStudentEvaluationDetailSe_score(int se_id, double se_score);

}
