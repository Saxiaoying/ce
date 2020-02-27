package team.zucc.eecs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.ObjectiveIndexPointDao;
import team.zucc.eecs.model.ObjectiveIndexPoint;

@Component("ObjectiveIndexPointServiceImpl")
public class ObjectiveIndexPointServiceImpl implements ObjectiveIndexPointService {
	@Autowired
	private  ObjectiveIndexPointDao objectiveIndexPointDao;
	
	@Override
	public ObjectiveIndexPoint getObjectiveIndexPointByCoi_id(int coi_id) {
		try {
			ObjectiveIndexPoint objectiveIndexPoint = objectiveIndexPointDao.getObjectiveIndexPointByCoi_id(coi_id);
			return objectiveIndexPoint;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ObjectiveIndexPoint getObjectiveIndexPointByCo_idAndIp_id(int co_id, int ip_id) {
		try {
			ObjectiveIndexPoint objectiveIndexPoint = objectiveIndexPointDao.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
			return objectiveIndexPoint;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addObjectiveIndexPoint(int co_id, int ip_id, String coi_lev) {
		try {
			ObjectiveIndexPoint objectiveIndexPoint = objectiveIndexPointDao.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
			if (objectiveIndexPoint != null) {
				return 1;
			}
			objectiveIndexPointDao.addObjectiveIndexPoint(co_id, ip_id, coi_lev);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteObjectiveIndexPoint(int coi_id) {
		try {
			objectiveIndexPointDao.deleteObjectiveIndexPoint(coi_id);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int deleteObjectiveIndexPointByCo_id(int co_id) {
		try {
			objectiveIndexPointDao.deleteObjectiveIndexPointByCo_id(co_id);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int updateObjectiveIndexPoint(int coi_id, String coi_lev) {
		try {
			ObjectiveIndexPoint objectiveIndexPoint = objectiveIndexPointDao.getObjectiveIndexPointByCoi_id(coi_id);
			if (objectiveIndexPoint != null) {
				return 1; //没有
			}
			objectiveIndexPointDao.updateObjectiveIndexPoint(coi_id, coi_lev);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

}
