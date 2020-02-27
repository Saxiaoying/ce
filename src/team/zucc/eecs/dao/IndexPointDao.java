package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.IndexPoint;

public interface IndexPointDao {
	IndexPoint getIndexPointByIp_id(int ip_id);
	List<IndexPoint> getIndexPointListByGr_id(int gr_id);
}
