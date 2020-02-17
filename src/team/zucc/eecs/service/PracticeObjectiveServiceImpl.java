//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.PracticeObjectiveDao;
import team.zucc.eecs.model.PracticeObjective;

@Component("PracticeObjectiveServiceImpl")
public class PracticeObjectiveServiceImpl implements PracticeObjectiveService {
	@Autowired
	private  PracticeObjectiveDao practiceObjectiveDao;
	
	@Override
	public PracticeObjective getPracticeObjectiveByPc_id(int pc_id) {
		try {
			PracticeObjective practiceObjective=practiceObjectiveDao.getPracticeObjectiveByPc_id(pc_id);
			return practiceObjective;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PracticeObjective getPracticeObjectiveByCo_idAndPra_id(int co_id, int pra_id) {
		try {
			PracticeObjective practiceObjective=practiceObjectiveDao.getPracticeObjectiveByCo_idAndPra_id(co_id, pra_id);
			return practiceObjective;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PracticeObjective> getPracticeObjectiveByCo_id(int co_id) {
		try {
			List<PracticeObjective> practiceObjectiveList = practiceObjectiveDao.getPracticeObjectiveByCo_id(co_id);
			return practiceObjectiveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PracticeObjective> getPracticeObjectiveByPra_id(int pra_id) {
		try {
			List<PracticeObjective> practiceObjectiveList = practiceObjectiveDao.getPracticeObjectiveByPra_id(pra_id);
			return practiceObjectiveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addPracticeObjective(int co_id, int pra_id) {
		try {
			PracticeObjective practiceObjective=practiceObjectiveDao.getPracticeObjectiveByCo_idAndPra_id(co_id, pra_id);
			if (practiceObjective != null) {
				return 1;
			}
			practiceObjectiveDao.addPracticeObjective(co_id, pra_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deletePracticeObjectiveByPc_id(int pc_id) {
		try {
			practiceObjectiveDao.deletePracticeObjectiveByPc_id(pc_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updatePracticeObjective(int pc_id, int co_id, int pra_id) {
		try {
			practiceObjectiveDao.updatePracticeObjective(pc_id, co_id, pra_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
}
