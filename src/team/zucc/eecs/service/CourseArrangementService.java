//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CourseArrangement;

public interface CourseArrangementService {
	int getCourseArrangementNumberByInf(String cs_acad_yr, String cs_sem, String coz_id, String coz_name_ch, String tch_name);
	List<CourseArrangement> getCourseArrangementListByInfFromAtoB(int a, int b, String cs_acad_yr, String cs_sem, String coz_id,
			String coz_name_ch, String tch_name);
	CourseArrangement getCourseArrangementByCag_id(int cag_id);
	
	int updateCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name);
	int addCourseArrangement(int cs_id, int tch_id, int cag_num, String cag_name);
	int deleteCourseArrangement(int cag_id);
}
