package team.zucc.eecs.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseAppendix;


public interface CourseAppendixDao {
	CourseAppendix getCourseAppendixByCa_id(int ca_id);
	List<CourseAppendix> getCourseAppendixListByCs_id(int cs_id);
	List<CourseAppendix> getCourseAppendixListByCs_idAndCa_typ(int cs_id, String ca_typ);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addCourseAppendix(int cs_id, String ca_typ, String ca_url, String ca_name, Timestamp ca_time);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteCourseAppendix(int ca_id);
}
