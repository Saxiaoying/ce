//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Course;

public interface CourseDao {
	//查询
	List<Course> getCourseList();
	int getCourseNumber();
	List<Course> getCourseListFromAtoB(int a, int b);
	Course getCourseByCoz_id(String coz_id);//精确查询
	List<Course> getCourseListByCoz_id(String coz_id);//模糊查询
	List<Course> getCourseListByCoz_name_ch(String coz_name_ch); //模糊查询
	List<Course> getCourseListByCoz_nature(String coz_nature); //模糊查询
	
	List<Course> getCourseListByCoz_idAndCoz_name_ch(String coz_id, String coz_name_ch);//模糊查询
	List<Course> getCourseListByCoz_name_chAndCoz_nature(String coz_name_ch, String coz_nature); //模糊查询
	List<Course> getCourseListByCoz_natureAndCoz_id(String coz_nature, String coz_id); //模糊查询
	
	List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature);//模糊查询
	
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, String coz_hrs_wk, double coz_hours);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteCourseByCoz_id(String coz_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, String coz_hrs_wk, double coz_hours);
}
