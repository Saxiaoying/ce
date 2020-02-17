//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseContent;;

public interface CourseContentDao {
	//查询
	List<CourseContent> getCourseContentList();
	CourseContent getCourseContentByCont_id(int cont_id);
	List<CourseContent> getCourseContentByCs_id(int cs_id); 
	CourseContent getCourseContentByCs_idAndCont_num(int cs_id, int cont_num);
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addCourseContent(int cs_id, String cont_name,int cont_num, 
			String cont_cont, String cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, String cont_cla_exe, String cont_hw);
	
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteCourseContentByCs_idAndCont_num(int cs_id, int cont_num);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateCourseContent(int cs_id, String cont_name,int cont_num, 
			String cont_cont, String cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, String cont_cla_exe, String cont_hw);
}
