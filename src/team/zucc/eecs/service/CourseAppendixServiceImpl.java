package team.zucc.eecs.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseAppendixDao;
import team.zucc.eecs.model.CourseAppendix;

@Component("CourseAppendixServiceImpl")
public class CourseAppendixServiceImpl implements CourseAppendixService {
	@Autowired
	private CourseAppendixDao courseAppendixDao;
	
	@Override
	public CourseAppendix getCourseAppendixByCa_id(int ca_id) {
		try {
			CourseAppendix courseAppendix = courseAppendixDao.getCourseAppendixByCa_id(ca_id);
			return courseAppendix;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseAppendix> getCourseAppendixListByCs_id(int cs_id) {
		try {
			List<CourseAppendix> courseAppendixList = courseAppendixDao.getCourseAppendixListByCs_id(cs_id);
			return courseAppendixList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseAppendix> getCourseAppendixListByCs_idAndCa_typ(int cs_id, String ca_typ) {
		try {
			List<CourseAppendix> courseAppendixList = courseAppendixDao.getCourseAppendixListByCs_idAndCa_typ(cs_id, ca_typ);
			return courseAppendixList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addCourseAppendix(int cs_id, String ca_typ, String ca_url, String ca_name, Timestamp ca_time) {
		try {
		    courseAppendixDao.addCourseAppendix(cs_id, ca_typ, ca_url, ca_name, ca_time);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deleteCourseAppendix(int ca_id) {
		try {
		    courseAppendixDao.deleteCourseAppendix(ca_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
