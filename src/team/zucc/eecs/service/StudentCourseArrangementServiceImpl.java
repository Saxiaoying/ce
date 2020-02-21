package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.StudentCourseArrangementDao;
import team.zucc.eecs.model.StudentCourseArrangement;


@Component("StudentCourseArrangementServiceImpl")
public class StudentCourseArrangementServiceImpl implements StudentCourseArrangementService {
	@Autowired
	private StudentCourseArrangementDao studentCourseArrangementDao;
	
	@Override
	public StudentCourseArrangement getStudentCourseArrangementBySca_id(int sca_id) {
		try {
			StudentCourseArrangement studentCourseArrangement = studentCourseArrangementDao.getStudentCourseArrangementBySca_id(sca_id);
			return studentCourseArrangement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public StudentCourseArrangement getStudentCourseArrangementByStu_idAndCag_id(int stu_id, int cag_id) {
		try {
			StudentCourseArrangement studentCourseArrangement = studentCourseArrangementDao.getStudentCourseArrangementByStu_idAndCag_id(stu_id, cag_id);
			return studentCourseArrangement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StudentCourseArrangement> getStudentCourseArrangementListByCag_id(int cag_id) {
		List<StudentCourseArrangement> studentCourseArrangementList = new ArrayList<StudentCourseArrangement>();
		try {
			studentCourseArrangementList = studentCourseArrangementDao.getStudentCourseArrangementListByCag_id(cag_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentCourseArrangementList;
	}

	@Override
	public int addStudentCourseArrangement(int stu_id, int cag_id) {
		try {
			StudentCourseArrangement studentCourseArrangement = 
					studentCourseArrangementDao.getStudentCourseArrangementByStu_idAndCag_id(stu_id, cag_id);
			if(studentCourseArrangement != null) {
				return 1; //已经存在
			}
			
			studentCourseArrangementDao.addStudentCourseArrangement(stu_id, cag_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功
	}

	@Override
	public int deleteStudentCourseArrangement(int sca_id) {
		try {
			studentCourseArrangementDao.deleteStudentCourseArrangement(sca_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功
	}

}
