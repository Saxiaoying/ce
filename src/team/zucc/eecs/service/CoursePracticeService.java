//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.CoursePractice;

public interface CoursePracticeService {
	int updateCoursePractice(int cs_id, int pra_num, String pra_name, double pra_hrs, String pra_cont,
			String pra_nature, String pra_typ);
	List<CoursePractice> getCoursePracticeListByCs_id(int cs_id);
	int deleteCoursePractice(int cs_id, int pra_num);
}
