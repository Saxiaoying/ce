package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Teacher;

public interface TeacherService {
	Teacher getTeacherByTch_id(int tch_id);
	List<Teacher> getTeacherList(); 
	
	int getTeacherNumberByInf(String tch_name);
	List<Teacher> getTeacherListByInfFromAtoB(int a, int b, String tch_name);
}
