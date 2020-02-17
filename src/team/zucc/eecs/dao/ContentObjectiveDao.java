//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.ContentObjective;

public interface ContentObjectiveDao {
	ContentObjective getContentObjectiveByCco_id(int cco_id);
	ContentObjective getContentObjectiveByCo_idAndCont_id(int co_id, int cont_id);
	List<ContentObjective> getContentObjectiveByCo_id(int co_id); 
	List<ContentObjective> getContentObjectiveByCont_id(int cont_id); 
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addContentObjective(int co_id, int cont_id);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteContentObjectiveByCco_id(int cco_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updateContentObjective(int cco_id, int co_id, int cont_id);

}
