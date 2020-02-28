package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.ObjectiveIndexPoint;

public interface ObjectiveIndexPointService {
	ObjectiveIndexPoint getObjectiveIndexPointByCoi_id(int coi_id);
	ObjectiveIndexPoint getObjectiveIndexPointByCo_idAndIp_id(int co_id, int ip_id);
	
	List<ObjectiveIndexPoint> getObjectiveIndexPointListByCo_id(int co_id);
	List<ObjectiveIndexPoint> getObjectiveIndexPointListByCs_id(int cs_id);
	
	int addObjectiveIndexPoint(int co_id, int ip_id, String coi_lev);
	int deleteObjectiveIndexPoint(int coi_id);
	int deleteObjectiveIndexPointByCo_id(int co_id);
	int deleteObjectiveIndexPointByIp_id(int ip_id);
	int updateObjectiveIndexPoint(int coi_id, String coi_lev);
}
