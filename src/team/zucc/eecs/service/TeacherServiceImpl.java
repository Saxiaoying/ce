package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.TeacherDao;
import team.zucc.eecs.model.Teacher;

@Component("TeacherServiceImpl")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public Teacher getTeacherByTch_id(int tch_id) {
		try {
			Teacher teacher = teacherDao.getTeacherByTch_id(tch_id);
			return teacher;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Teacher> getTeacherList() {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			teacherList = teacherDao.getTeacherList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherList;
	}

	@Override
	public int getTeacherNumberByInf(String tch_name) {
		try {
			int num = teacherDao.getTeacherNumberByInf(tch_name);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Teacher> getTeacherListByInfFromAtoB(int a, int b, String tch_name) {
		List<Teacher> teacherList = new ArrayList<Teacher>();
		try {
			teacherList = teacherDao.getTeacherListByInfFromAtoB(a, b, tch_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return teacherList;
	}

}
