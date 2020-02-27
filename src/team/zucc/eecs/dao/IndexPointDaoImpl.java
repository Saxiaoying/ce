package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.IndexPoint;


@Component("IndexPointDaoImpl")
public class IndexPointDaoImpl implements IndexPointDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public IndexPoint getIndexPointByIp_id(int ip_id) {
		return template.query("select * from tb_idx_pt where ip_id =" +ip_id, new ResultSetExtractor<IndexPoint>() {
			@Override
			public IndexPoint extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					IndexPoint ip = new IndexPoint();
					ip.setIp_id(rs.getInt("ip_id"));
					ip.setGr_id(rs.getInt("gr_id"));
					ip.setIp_code(rs.getInt("ip_code"));
					ip.setIp_content(rs.getString("ip_content"));
					return ip;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<IndexPoint> getIndexPointListByGr_id(int gr_id) {
		List<IndexPoint> evaluationTypeList = new ArrayList<>();
		String sql = "select * from tb_idx_pt where gr_id = " + gr_id;
		evaluationTypeList = this.template.query(sql, new RowMapper<IndexPoint>() {
			public IndexPoint mapRow(ResultSet rs, int rowNum) throws SQLException {
				IndexPoint ip = new IndexPoint();
				ip.setIp_id(rs.getInt("ip_id"));
				ip.setGr_id(rs.getInt("gr_id"));
				ip.setIp_code(rs.getInt("ip_code"));
				ip.setIp_content(rs.getString("ip_content"));
				return ip;
			}
		});
		return evaluationTypeList;
	}

}
