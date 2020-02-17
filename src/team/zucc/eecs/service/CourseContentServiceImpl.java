//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseContentDao;
import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;

@Component("CourseContentServiceImpl")
public class CourseContentServiceImpl implements CourseContentService {
	@Autowired
	private  CourseContentDao courseContentDao;
	
	@Override
	public int updateCourseContent(int cs_id, String cont_name, int cont_num, String cont_cont,
			String cont_method, String cont_key, String cont_diff, double cont_hrs_tch, double cont_hrs_pr,
			String cont_cla_exe, String cont_hw) {
		try {
			CourseContent courseContent = courseContentDao.getCourseContentByCs_idAndCont_num(cs_id, cont_num);
			if(courseContent == null) courseContentDao.addCourseContent(cs_id, cont_name, cont_num, cont_cont, cont_method, cont_key, cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, cont_hw);
			else courseContentDao.updateCourseContent(cs_id, cont_name, cont_num, cont_cont, cont_method, cont_key, cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, cont_hw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}

	@Override
	public List<CourseContent> getCourseContentListByCs_id(int cs_id) {
		try {
			List<CourseContent> courseContentList = courseContentDao.getCourseContentByCs_id(cs_id);
			return courseContentList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteCourseContent(int cs_id, int cont_num) {
		try {
			courseContentDao.deleteCourseContentByCs_idAndCont_num(cs_id, cont_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
