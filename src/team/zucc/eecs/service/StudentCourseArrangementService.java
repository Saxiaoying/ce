package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.StudentCourseArrangement;

public interface StudentCourseArrangementService {
	StudentCourseArrangement getStudentCourseArrangementBySca_id(int sca_id);
	StudentCourseArrangement getStudentCourseArrangementByStu_idAndCag_id(int stu_id, int cag_id);
	List<StudentCourseArrangement> getStudentCourseArrangementListByCag_id(int cag_id);

	int addStudentCourseArrangement(int stu_id, int cag_id);
	int deleteStudentCourseArrangement(int sca_id);
}
