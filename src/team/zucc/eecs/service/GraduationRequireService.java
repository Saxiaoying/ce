package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.GraduationRequire;

public interface GraduationRequireService {
	List<GraduationRequire> getGraduationRequireListFromAtoB(int a, int b);
	int getGraduationRequireListNumber();
	GraduationRequire getGraduationRequireByGr_id(int gr_id);
}
