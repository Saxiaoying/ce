package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.PracticeObjective;

public interface PracticeObjectiveService {
	PracticeObjective getPracticeObjectiveByPc_id(int pc_id);
	PracticeObjective getPracticeObjectiveByCo_idAndPra_id(int co_id, int pra_id);
	List<PracticeObjective> getPracticeObjectiveByCo_id(int co_id); 
	List<PracticeObjective> getPracticeObjectiveByPra_id(int pra_id); 
	int addPracticeObjective(int co_id, int pra_id);
	int deletePracticeObjectiveByPc_id(int pc_id);
	int updatePracticeObjective(int pc_id, int co_id, int pra_id);
}
