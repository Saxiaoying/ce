package team.zucc.eecs.service;

import java.sql.Timestamp;
import java.util.List;


import team.zucc.eecs.model.CourseAppendix;

public interface CourseAppendixService {
	CourseAppendix getCourseAppendixByCa_id(int ca_id);
	List<CourseAppendix> getCourseAppendixListByCs_id(int cs_id);
	List<CourseAppendix> getCourseAppendixListByCs_idAndCa_typ(int cs_id, String ca_typ);
	
	int addCourseAppendix(int cs_id, String ca_typ, String ca_url, String ca_name, Timestamp ca_time);
	
	int deleteCourseAppendix(int ca_id);
}
