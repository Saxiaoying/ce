package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.GraduationRequire;

public interface GraduationRequireDao {
	List<GraduationRequire> getGraduationRequireListFromAtoB(int a, int b);
	int getGraduationRequireListNumber();
	GraduationRequire getGraduationRequireByGr_id(int gr_id);
}
