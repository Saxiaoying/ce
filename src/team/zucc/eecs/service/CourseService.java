//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Course;

public interface CourseService {
	int getCourseNumber();
	Course getCourseByCoz_id(String coz_id);
	List<Course> getCourseListFromAtoB(int a, int b);
	List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature);
	
	int addCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, String coz_hrs_wk, double coz_hours);
	int deleteCourseByCoz_id(String coz_id);
	int updateCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, String coz_hrs_wk, double coz_hours);
}
