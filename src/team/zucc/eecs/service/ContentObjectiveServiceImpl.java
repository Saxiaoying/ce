//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.ContentObjectiveDao;
import team.zucc.eecs.model.ContentObjective;

@Component("ContentObjectiveServiceImpl")
public class ContentObjectiveServiceImpl implements ContentObjectiveService {
	@Autowired
	private  ContentObjectiveDao contentObjectiveDao;
	
	@Override
	public ContentObjective getContentObjectiveByCco_id(int cco_id) {
		try {
			ContentObjective contentObjective=contentObjectiveDao.getContentObjectiveByCco_id(cco_id);
			return contentObjective;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ContentObjective getContentObjectiveByCo_idAndCont_id(int co_id, int cont_id) {
		try {
			ContentObjective contentObjective=contentObjectiveDao.getContentObjectiveByCo_idAndCont_id(co_id, cont_id);
			return contentObjective;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ContentObjective> getContentObjectiveByCo_id(int co_id) {
		try {
			List<ContentObjective> contentObjectiveList = contentObjectiveDao.getContentObjectiveByCo_id(co_id);
			return contentObjectiveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ContentObjective> getContentObjectiveByCont_id(int cont_id) {
		try {
			List<ContentObjective> contentObjectiveList = contentObjectiveDao.getContentObjectiveByCont_id(cont_id);
			return contentObjectiveList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addContentObjective(int co_id, int cont_id) {
		try {
			ContentObjective contentObjective=contentObjectiveDao.getContentObjectiveByCo_idAndCont_id(co_id, cont_id);
			if (contentObjective != null) {
				return 1;
			}
			contentObjectiveDao.addContentObjective(co_id, cont_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int deleteContentObjectiveByCco_id(int cco_id) {
		try {
			contentObjectiveDao.deleteContentObjectiveByCco_id(cco_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	@Override
	public int updateContentObjective(int cco_id, int co_id, int cont_id) {
		try {
			contentObjectiveDao.updateContentObjective(cco_id, co_id, cont_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

}
