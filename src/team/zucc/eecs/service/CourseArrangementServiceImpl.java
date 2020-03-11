package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseArrangementDao;
import team.zucc.eecs.dao.StudentCourseArrangementDao;
import team.zucc.eecs.model.CourseArrangement;

@Component("CourseArrangementServiceImpl")
public class CourseArrangementServiceImpl implements CourseArrangementService {
	@Autowired
	private CourseArrangementDao courseArrangementDao;
	
	@Autowired
	private StudentCourseArrangementDao studentCourseArrangementDao;
	
	@Override
	public int getCourseArrangementNumberByInf(String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch,
			String tch_name) {
		try {
			int num = courseArrangementDao.getCourseArrangementNumberByInf(cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CourseArrangement> getCourseArrangementListByInfFromAtoB(int a, int b, String cs_acad_yr, String cs_sem,
			String coz_id, String coz_name_ch, String tch_name) {
		try {
			List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByInfFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
			return courseArrangementList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CourseArrangement getCourseArrangementByCag_id(int cag_id) {
		try {
			CourseArrangement courseArrangement = courseArrangementDao.getCourseArrangementByCag_id(cag_id);
			return courseArrangement;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name) {
		try {
			List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByCs_idAndTch_id(cs_id, tch_id);
			for (CourseArrangement ca: courseArrangementList) {
				if(ca.getCag_num() == cag_num && ca.getCag_id() != cag_id) {
					return 1;
				}
			}
		    courseArrangementDao.updateCourseArrangement(cag_id, cs_id, tch_id, cag_num, cag_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int addCourseArrangement(int cs_id, int tch_id, int cag_num, String cag_name) {
		try {
			List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByCs_idAndTch_id(cs_id, tch_id);
			for (CourseArrangement ca: courseArrangementList) {
				if(ca.getCag_num() == cag_num) {
					return 1;
				}
			}
		    courseArrangementDao.addCourseArrangement(cs_id, tch_id, cag_num, cag_name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCourseArrangement(int cag_id) {
		try {
			studentCourseArrangementDao.deleteStudentCourseArrangementByCag_id(cag_id);
		    courseArrangementDao.deleteCourseArrangementByCag_id(cag_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public List<CourseArrangement> getCourseArrangementByCs_id(int cs_id) {
		try {
			List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByCs_id(cs_id);
			return courseArrangementList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<CourseArrangement> getCourseArrangementByCs_idAndTch_id(int cs_id, int tch_id) {
		try {
			List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByCs_idAndTch_id(cs_id, tch_id);
			return courseArrangementList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseArrangement> getCourseArrangementByTch_idFromAtoB(int a, int b, String cs_acad_yr, String cs_sem,
			String coz_id, String coz_name_ch, String coz_nature, int tch_id) {
		try {
			List<CourseArrangement> courseArrangementList = 
					courseArrangementDao.getCourseArrangementByTch_idFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature, tch_id);
			return courseArrangementList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCourseArrangementNumberByTch_id(String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch,
			String coz_nature, int tch_id) {
		try {
			int num = courseArrangementDao.getCourseArrangementNumberByTch_id(cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature, tch_id);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
