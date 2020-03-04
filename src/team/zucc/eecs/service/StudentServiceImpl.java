package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.StudentDao;
import team.zucc.eecs.model.Student;

@Component("StudentServiceImpl")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public Student getStudentByStu_id(int stu_id) {
		try {
			Student student = studentDao.getStudentByStu_id(stu_id);
			return student;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> getStudentListByClass_id(int class_id) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentListByClass_id(class_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public int getStudentNumberByInf(String stu_name, String class_name) {
		try {
			int num = studentDao.getStudentNumberByInf(stu_name, class_name);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> getStudentListByInfFromAtoB(int a, int b, String stu_name, String class_name) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentListByInfFromAtoB(a, b, stu_name, class_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public int getStudentNumberByCag_id(int cag_id, String stu_name, String class_name) {
		try {
			int num = studentDao.getStudentNumberByCag_id(cag_id, stu_name, class_name);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> getStudentListByCag_idFromAtoB(int a, int b, int cag_id, String stu_name, String class_name) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentListByCag_idFromAtoB(a, b, cag_id, stu_name, class_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public int getStudentNumberByNotCag_id(int cag_id, String stu_name, String class_name) {
		try {
			int num = studentDao.getStudentNumberByNotCag_id(cag_id, stu_name, class_name);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Student> getStudentListByNotCag_idFromAtoB(int a, int b, int cag_id, String stu_name, String class_name) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentListByNotCag_idFromAtoB(a, b, cag_id, stu_name, class_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

	@Override
	public List<Student> getStudentListByCag_id(int cag_id) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			studentList = studentDao.getStudentListByCag_id(cag_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}

}
