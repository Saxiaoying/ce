//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CourseSet;

public interface CourseSetService {
	int getCourseSetNumberByInf(String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch, String coz_nature);
	List<CourseSet> getCourseSetListByInfFromAtoB(int a, int b, String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch, String coz_nature);

	int getCourseSetMaxId();
	int addCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem);
	int updateCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem);
	CourseSet getCourseSetByCs_id(int cs_id);
	CourseSet getCourseSetByCoz_idAndTime(String coz_id, String cs_acad_yr, String cs_sem);
	
	int getCourseSetNumberByTch_id(String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature, int tch_id);
	List<CourseSet> getCourseSetListByTch_idFromAtoB(int a, int b, String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature, int tch_id);
	
	int deleteCourseSet(int cs_id);
}
