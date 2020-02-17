//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CoursePracticeDao;
import team.zucc.eecs.model.CoursePractice;

@Component("CoursePracticeServiceImpl")
public class CoursePracticeServiceImpl implements CoursePracticeService {
	@Autowired
	private  CoursePracticeDao coursePracticeDao;

	@Override
	public int updateCoursePractice(int cs_id, int pra_num, String pra_name, double pra_hrs, String pra_cont,
			String pra_nature, String pra_typ) {
		try {
			CoursePractice coursePractice = coursePracticeDao.getCoursePracticeByCs_idAndPra_num(cs_id, pra_num);
			if(coursePractice == null) coursePracticeDao.addCoursePractice(cs_id, pra_num, pra_name, pra_hrs, pra_cont, pra_nature, pra_typ);
			else coursePracticeDao.updateCoursePractice(cs_id, pra_num, pra_name, pra_hrs, pra_cont, pra_nature, pra_typ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}

	@Override
	public List<CoursePractice> getCoursePracticeListByCs_id(int cs_id) {
		try {
			List<CoursePractice> coursePracticeList = coursePracticeDao.getCoursePracticeByCs_id(cs_id);
			return coursePracticeList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int deleteCoursePractice(int cs_id, int pra_num) {
		try {
			coursePracticeDao.deleteCoursePracticeByCs_idAndPra_num(cs_id, pra_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
