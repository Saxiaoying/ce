//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CourseObjective;

public interface CourseObjectiveService {
	List<CourseObjective> getCourseObjectiveListByCs_id(int cs_id);
	int updateCourseObjective(int cs_id, int co_num,  String co_cont);
	int deleteCourseObjective(int cs_id, int co_num);
}
