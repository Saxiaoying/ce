package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.GraduationRequireDao;
import team.zucc.eecs.model.GraduationRequire;

@Component("GraduationRequireServiceImpl")
public class GraduationRequireServiceImpl implements GraduationRequireService {
	@Autowired
	private GraduationRequireDao graduationRequireDao;
	
	@Override
	public List<GraduationRequire> getGraduationRequireListFromAtoB(int a, int b) {
		List<GraduationRequire> grList = new ArrayList<GraduationRequire>();
		try {
			grList = graduationRequireDao.getGraduationRequireListFromAtoB(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return grList;
	}

	@Override
	public int getGraduationRequireListNumber() {
		try {
			return graduationRequireDao.getGraduationRequireListNumber();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public GraduationRequire getGraduationRequireByGr_id(int gr_id) {
		try {
			GraduationRequire gr = graduationRequireDao.getGraduationRequireByGr_id(gr_id);
			return gr;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
