package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.ClassDao;
import team.zucc.eecs.model.Class;

@Component("ClassServiceImpl")
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassDao classDao;
	
	@Override
	public Class getClassByClass_id(int class_id) {
		try {
			Class c = classDao.getClassByClass_id(class_id);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Class> getClassList() {
		List<Class> classList = new ArrayList<Class>();
		try {
			classList = classDao.getClassList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

	@Override
	public List<Class> getClassListByCag_id(int cag_id) {
		List<Class> classList = new ArrayList<Class>();
		try {
			classList = classDao.getClassListByCag_id(cag_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return classList;
	}

}
