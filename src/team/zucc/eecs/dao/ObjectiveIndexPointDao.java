package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.ObjectiveIndexPoint;

public interface ObjectiveIndexPointDao {
		ObjectiveIndexPoint getObjectiveIndexPointByCoi_id(int coi_id);
		ObjectiveIndexPoint getObjectiveIndexPointByCo_idAndIp_id(int co_id, int ip_id);
		
		List<ObjectiveIndexPoint> getObjectiveIndexPointListByCo_id(int co_id);
		List<ObjectiveIndexPoint> getObjectiveIndexPointListByCs_id(int cs_id);

		@Transactional(propagation = Propagation.REQUIRED)
		void addObjectiveIndexPoint(int co_id, int ip_id, String coi_lev);
		
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteObjectiveIndexPoint(int coi_id);
		
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteObjectiveIndexPointByCo_id(int co_id);
		
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteObjectiveIndexPointByIp_id(int ip_id);
		
		@Transactional(propagation = Propagation.REQUIRED)
		void updateObjectiveIndexPoint(int coi_id, String coi_lev);
}
