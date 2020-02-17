//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import team.zucc.eecs.model.CoursePractice;

public interface CoursePracticeDao {
	    //查询
		List<CoursePractice> getCoursePracticeList();
		CoursePractice getCoursePracticeByPra_id(int pra_id);
		List<CoursePractice> getCoursePracticeByCs_id(int cs_id); 
		CoursePractice getCoursePracticeByCs_idAndPra_num(int cs_id, int pra_num);
		
		//添加
		@Transactional(propagation = Propagation.REQUIRED)
		void addCoursePractice(int cs_id, int pra_num, String pra_name, 
				double pra_hrs, String pra_cont, String pra_nature, String pra_typ);
		
		//删除
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCoursePracticeByCs_idAndPra_num(int cs_id, int pra_num);
		
		//修改
		@Transactional(propagation = Propagation.REQUIRED)
		void updateCoursePractice(int cs_id, int pra_num, String pra_name, 
				double pra_hrs, String pra_cont, String pra_nature, String pra_typ);

}
