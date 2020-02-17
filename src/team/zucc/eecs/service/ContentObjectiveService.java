//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;


import team.zucc.eecs.model.ContentObjective;

public interface ContentObjectiveService {
	ContentObjective getContentObjectiveByCco_id(int cco_id);
	ContentObjective getContentObjectiveByCo_idAndCont_id(int co_id, int cont_id);
	List<ContentObjective> getContentObjectiveByCo_id(int co_id); 
	List<ContentObjective> getContentObjectiveByCont_id(int cont_id); 
	int addContentObjective(int co_id, int cont_id);
	int deleteContentObjectiveByCco_id(int cco_id);
	int updateContentObjective(int cco_id, int co_id, int cont_id);
}
