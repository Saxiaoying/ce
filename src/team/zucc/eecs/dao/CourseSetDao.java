//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseSet;

public interface CourseSetDao {
		//查询
	    CourseSet getCourseSetByCs_id(int cs_id);
	    CourseSet getCourseSetByCoz_idAndTime(String coz_id, String cs_acad_yr, String cs_sem);
	    
		//"" "" ""可以查询全部
		int getCourseSetNumberByInf(String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature);
		List<CourseSet> getCourseSetListByInfFromAtoB(int a, int b, String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature);
		
		
		int getCourseSetMaxId();
		
		//添加
		@Transactional(propagation = Propagation.REQUIRED)
		void addCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem);
		
		//删除
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCourseSetByCs_id(int cs_id);
		
		//修改
		@Transactional(propagation = Propagation.REQUIRED)
		void updateCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem);
		
		//"" "" ""可以查询全部
		int getCourseSetNumberByTch_id(String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature, int tch_id);
		List<CourseSet> getCourseSetListByTch_idFromAtoB(int a, int b, String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature, int tch_id);
		
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCourseSetByCoz_id(String coz_id);
		
		List<CourseSet> getCourseSetListByCoz_id(String coz_id);
}
