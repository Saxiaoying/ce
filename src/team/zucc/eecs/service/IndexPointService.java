package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.IndexPoint;

public interface IndexPointService {
	IndexPoint getIndexPointByIp_id(int ip_id);
	List<IndexPoint> getIndexPointListByGr_id(int gr_id);
}
