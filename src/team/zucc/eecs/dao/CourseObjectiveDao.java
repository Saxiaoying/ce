//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseObjective;

public interface CourseObjectiveDao {
	    //查询
		List<CourseObjective> getCourseObjectiveList();
		CourseObjective getCourseObjectiveByCo_id(int co_id);
		List<CourseObjective> getCourseObjectiveByCs_id(int cs_id);
		CourseObjective getCourseObjectiveByCs_idAndCo_num(int cs_id, int co_num);
		
		//添加
		@Transactional(propagation = Propagation.REQUIRED)
		void addCourseObjective(int cs_id,  int co_num,  String co_cont);
		
		//删除
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCourseObjective(int cs_id, int co_num);
		
		//修改
		@Transactional(propagation = Propagation.REQUIRED)
		void updateCourseObjective(int cs_id,  int co_num,  String co_cont);
}
