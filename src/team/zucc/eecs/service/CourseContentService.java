//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CourseContent;

public interface CourseContentService {
	int updateCourseContent(int cs_id, String cont_name,int cont_num, 
			String cont_cont, String cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, String cont_cla_exe, String cont_hw);
	List<CourseContent> getCourseContentListByCs_id(int cs_id);
	int deleteCourseContent(int cs_id, int cont_num);
	
}
