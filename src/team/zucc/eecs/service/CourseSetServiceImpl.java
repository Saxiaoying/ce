//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseAppendixDao;
import team.zucc.eecs.dao.CourseArrangementDao;
import team.zucc.eecs.dao.CourseContentDao;
import team.zucc.eecs.dao.CourseObjectiveDao;
import team.zucc.eecs.dao.CoursePracticeDao;
import team.zucc.eecs.dao.CourseSetDao;
import team.zucc.eecs.dao.EvaluationDao;
import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.StudentCourseArrangementDao;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseSet;

@Component("CourseSetServiceImpl")
public class CourseSetServiceImpl implements CourseSetService {
	@Autowired
	private CourseSetDao courseSetDao;
	
	@Autowired
	private CourseArrangementDao courseArrangementDao;
	
	@Autowired
	private CourseAppendixDao courseAppendixDao;
	
	@Autowired
	private CourseContentDao courseContentDao;
	
	@Autowired
	private CoursePracticeDao coursePracticeDao;
	
	@Autowired
	private CourseObjectiveDao courseObjectiveDao;
	
	@Autowired
	private EvaluationDao evaluationDao;
	
	@Autowired
	private EvaluationDetailDao evaluationDetailDao;
	
	@Autowired
	private StudentCourseArrangementDao studentCourseArrangementDao;
	
	@Override
	public int getCourseSetNumberByInf(String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch,
			String coz_nature) {
		try {
			int num = courseSetDao.getCourseSetNumberByInf(coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public List<CourseSet> getCourseSetListByInfFromAtoB(int a, int b, String cs_acad_yr, String cs_sem, String coz_id,
			String coz_name_ch, String coz_nature) {
		try {
			List<CourseSet> courseSetList = courseSetDao.getCourseSetListByInfFromAtoB(a, b, coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature);
			return courseSetList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCourseSetMaxId() {
		try {
			int id = courseSetDao.getCourseSetMaxId();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int addCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem) {
		try {
			List<CourseSet> courseSetList = courseSetDao.getCourseSetListByInfFromAtoB(0, Integer.MAX_VALUE, coz_id, cs_acad_yr, cs_sem, "", "");
			for (CourseSet cs: courseSetList) {
				if(cs.getCoz_id().compareTo(coz_id) == 0) {
					return 1;
				}
			}
		    courseSetDao.addCourseSet(cs_id, coz_id, cs_acad_yr, cs_sem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem) {
		try {
			List<CourseSet> courseSetList = courseSetDao.getCourseSetListByInfFromAtoB(0, Integer.MAX_VALUE, coz_id, cs_acad_yr, cs_sem, "", "");
			for (CourseSet cs: courseSetList) {
				if(cs.getCoz_id().compareTo(coz_id) == 0) {
					return 1;
				}
			}
		    courseSetDao.updateCourseSet(cs_id, coz_id, cs_acad_yr, cs_sem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public CourseSet getCourseSetByCs_id(int cs_id) {
		CourseSet courseSet = null;
		try {
		    courseSet = courseSetDao.getCourseSetByCs_id(cs_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseSet;
	}

	@Override
	public CourseSet getCourseSetByCoz_idAndTime(String coz_id, String cs_acad_yr, String cs_sem) {
		CourseSet courseSet = null;
		try {
		    courseSet = courseSetDao.getCourseSetByCoz_idAndTime(coz_id, cs_acad_yr, cs_sem);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseSet;
	}

	@Override
	public int getCourseSetNumberByTch_id(String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch,
			String coz_nature, int tch_id) {
		try {
			int num = courseSetDao.getCourseSetNumberByTch_id(coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<CourseSet> getCourseSetListByTch_idFromAtoB(int a, int b, String coz_id, String cs_acad_yr,
			String cs_sem, String coz_name_ch, String coz_nature, int tch_id) {
		try {
			List<CourseSet> courseSetList = courseSetDao.getCourseSetListByTch_idFromAtoB(a, b, coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
			return courseSetList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteCourseSet(int cs_id) {
		try {
			courseAppendixDao.deleteCourseAppendixByCs_id(cs_id);
			courseContentDao.deleteCourseContentByCs_id(cs_id);
			coursePracticeDao.deleteCoursePracticeByCs_id(cs_id);
			courseObjectiveDao.deleteCourseObjectiveByCs_id(cs_id);
			evaluationDao.deleteEvaluationByCs_id(cs_id);
			evaluationDetailDao.deleteEvaluationDetailByCs_id(cs_id);
            List<CourseArrangement> courseArrangementList = courseArrangementDao.getCourseArrangementByCs_id(cs_id);
            for (CourseArrangement cag: courseArrangementList) {
				int cag_id = cag.getCag_id();
				studentCourseArrangementDao.deleteStudentCourseArrangementByCag_id(cag_id);
			}
			courseArrangementDao.deleteCourseArrangementByCs_id(cs_id);
			courseSetDao.deleteCourseSetByCs_id(cs_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}	
		return 0;
	}

}
