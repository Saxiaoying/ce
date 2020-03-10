//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseAppendixDao;
import team.zucc.eecs.dao.CourseArrangementDao;
import team.zucc.eecs.dao.CourseContentDao;
import team.zucc.eecs.dao.CourseDao;
import team.zucc.eecs.dao.CourseObjectiveDao;
import team.zucc.eecs.dao.CoursePracticeDao;
import team.zucc.eecs.dao.CourseSetDao;
import team.zucc.eecs.dao.EvaluationDao;
import team.zucc.eecs.dao.EvaluationDetailDao;
import team.zucc.eecs.dao.StudentCourseArrangementDao;
import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseSet;

@Component("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
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
	public int getCourseNumber() {
		try {
			int num = courseDao.getCourseNumber();
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Course getCourseByCoz_id(String coz_id) {
		try {
			Course course = courseDao.getCourseByCoz_id(coz_id);
			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> getCourseListFromAtoB(int a, int b){
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListFromAtoB(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature) {
		System.out.println("进入CourseServiceImpl-getCourseListByInf");
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListByInf(coz_id, coz_name_ch, coz_nature);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public int addCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit,
			String coz_hrs_wk, double coz_hours) {
		try {
			Course course = courseDao.getCourseByCoz_id(coz_id);
			if(course != null) {
				return 1; //已经存在
			}
			
			courseDao.addCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功
	}

	@Override
	public int deleteCourseByCoz_id(String coz_id) {
		try {
			courseDao.deleteCourseByCoz_id(coz_id);
			
			List<CourseSet> courseSetList = courseSetDao.getCourseSetListByCoz_id(coz_id);
			for(CourseSet cs: courseSetList) {
				int cs_id = cs.getCs_id();
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
			}
			courseSetDao.deleteCourseSetByCoz_id(coz_id);
			courseDao.deleteCourseByCoz_id(coz_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}	
		return 0;
	}

	@Override
	public int updateCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature,
			double coz_credit, String coz_hrs_wk, double coz_hours) {
		try {
			courseDao.updateCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功
	}

	@Override
	public int getCourseNumberByTch_id(String coz_id, String coz_name_ch, String coz_nature, int tch_id) {
		try {
			int num = courseDao.getCourseNumberByTch_id(coz_id, coz_name_ch, coz_nature, tch_id);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Course> getCourseListByTch_idFromAtoB(int a, int b, String coz_id, String coz_name_ch,
			String coz_nature, int tch_id) {
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListByTch_idFromAtoB(a, b, coz_id, coz_name_ch, coz_nature, tch_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public int getCourseNumberByInf(String coz_id, String coz_name_ch, String coz_nature) {
		try {
			int num = courseDao.getCourseNumberByInf(coz_id, coz_name_ch, coz_nature);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Course> getCourseListByInfFromAtoB(int a, int b, String coz_id, String coz_name_ch, String coz_nature) {
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListByInfFromAtoB(a, b, coz_id, coz_name_ch, coz_nature);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}
}
