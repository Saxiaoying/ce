package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.IndexPointDao;
import team.zucc.eecs.model.IndexPoint;

@Component("IndexPointServiceImpl")
public class IndexPointServiceImpl implements IndexPointService {
	@Autowired
	private IndexPointDao indexPointDao;
	
	@Override
	public IndexPoint getIndexPointByIp_id(int ip_id) {
		try {
			IndexPoint ip = indexPointDao.getIndexPointByIp_id(ip_id);
			return ip;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<IndexPoint> getIndexPointListByGr_id(int gr_id) {
		List<IndexPoint> ipList = new ArrayList<IndexPoint>();
		try {
			ipList = indexPointDao.getIndexPointListByGr_id(gr_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipList;
	}

}
