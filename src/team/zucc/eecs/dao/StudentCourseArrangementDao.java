package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.StudentCourseArrangement;

public interface StudentCourseArrangementDao {
	StudentCourseArrangement getStudentCourseArrangementBySca_id(int sca_id);
	StudentCourseArrangement getStudentCourseArrangementByStu_idAndCag_id(int stu_id, int cag_id);
	List<StudentCourseArrangement> getStudentCourseArrangementListByCag_id(int cag_id);

	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addStudentCourseArrangement(int stu_id, int cag_id);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteStudentCourseArrangement(int sca_id);
}
