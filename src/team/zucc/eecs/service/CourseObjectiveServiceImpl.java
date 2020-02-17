//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseObjectiveDao;
import team.zucc.eecs.model.CourseObjective;

@Component("CourseObjectiveServiceImpl")
public class CourseObjectiveServiceImpl implements CourseObjectiveService {
	@Autowired
	private  CourseObjectiveDao courseObjectiveDao;

	@Override
	public List<CourseObjective> getCourseObjectiveListByCs_id(int cs_id) {
		try {
			List<CourseObjective> courseObjectiveList = courseObjectiveDao.getCourseObjectiveByCs_id(cs_id);
			return courseObjectiveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateCourseObjective(int cs_id, int co_num, String co_cont) {
		try {
			CourseObjective courseObjective = courseObjectiveDao.getCourseObjectiveByCs_idAndCo_num(cs_id, co_num);
			if(courseObjective == null) courseObjectiveDao.addCourseObjective(cs_id, co_num, co_cont);
			else courseObjectiveDao.updateCourseObjective(cs_id, co_num, co_cont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteCourseObjective(int cs_id, int co_num) {
		try {
			courseObjectiveDao.deleteCourseObjective(cs_id, co_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
