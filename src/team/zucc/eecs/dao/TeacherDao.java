package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.Teacher;

public interface TeacherDao {
	Teacher getTeacherByTch_id(int tch_id);
	List<Teacher> getTeacherList(); 
}
