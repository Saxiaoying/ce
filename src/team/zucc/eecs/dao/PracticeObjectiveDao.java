//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.PracticeObjective;

public interface PracticeObjectiveDao {
	PracticeObjective getPracticeObjectiveByPc_id(int pc_id);
	PracticeObjective getPracticeObjectiveByCo_idAndPra_id(int co_id, int pra_id);
	List<PracticeObjective> getPracticeObjectiveByCo_id(int co_id); 
	List<PracticeObjective> getPracticeObjectiveByPra_id(int pra_id); 
	
	//添加
	@Transactional(propagation = Propagation.REQUIRED)
	void addPracticeObjective(int co_id, int pra_id);
	
	//删除
	@Transactional(propagation = Propagation.REQUIRED)
	void deletePracticeObjectiveByPc_id(int pc_id);
	
	//修改
	@Transactional(propagation = Propagation.REQUIRED)
	void updatePracticeObjective(int pc_id, int co_id, int pra_id);
}
