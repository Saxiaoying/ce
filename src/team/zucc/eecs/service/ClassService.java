package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Class;

public interface ClassService {
	Class getClassByClass_id(int class_id);
	List<Class> getClassList(); 

}
