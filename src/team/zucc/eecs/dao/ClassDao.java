package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.Class;

public interface ClassDao {
	Class getClassByClass_id(int class_id);
	List<Class> getClassList(); 
	List<Class> getClassListByCag_id(int cag_id);
}
