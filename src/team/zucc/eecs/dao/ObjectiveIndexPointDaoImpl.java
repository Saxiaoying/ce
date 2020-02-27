package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.ObjectiveIndexPoint;

@Component("ObjectiveIndexPointDaoImpl")
public class ObjectiveIndexPointDaoImpl implements ObjectiveIndexPointDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public ObjectiveIndexPoint getObjectiveIndexPointByCoi_id(int coi_id) {
		return template.query("select * from tb_co_ip where coi_id =" +coi_id, new ResultSetExtractor<ObjectiveIndexPoint>() {
			@Override
			public ObjectiveIndexPoint extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ObjectiveIndexPoint oip = new ObjectiveIndexPoint();
					oip.setCoi_id(rs.getInt("coi_id"));
					oip.setCo_id(rs.getInt("co_id"));
					oip.setIp_id(rs.getInt("ip_id"));
					oip.setCoi_lev(rs.getString("coi_lev"));
					return oip;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public ObjectiveIndexPoint getObjectiveIndexPointByCo_idAndIp_id(int co_id, int ip_id) {
		return template.query("select * from tb_co_ip where co_id=" +co_id + " and ip_id=" + ip_id, new ResultSetExtractor<ObjectiveIndexPoint>() {
			@Override
			public ObjectiveIndexPoint extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ObjectiveIndexPoint oip = new ObjectiveIndexPoint();
					oip.setCoi_id(rs.getInt("coi_id"));
					oip.setCo_id(rs.getInt("co_id"));
					oip.setIp_id(rs.getInt("ip_id"));
					oip.setCoi_lev(rs.getString("coi_lev"));
					return oip;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addObjectiveIndexPoint(int co_id, int ip_id, String coi_lev) {
		template.update("insert into tb_co_ip (co_id, ip_id, coi_lev) values (?, ?, ?)", co_id, ip_id, coi_lev);
	}

	@Override
	public void deleteObjectiveIndexPoint(int coi_id) {
		template.update("delete from tb_co_ip where coi_id ="  + coi_id);
	}

	@Override
	public void deleteObjectiveIndexPointByCo_id(int co_id) {
		template.update("delete from tb_co_ip where co_id ="  + co_id);
	}

	@Override
	public void updateObjectiveIndexPoint(int coi_id, String coi_lev) {
		template.update("update tb_co_ip set coi_lev='" + coi_lev + "' where coi_id =" + coi_id);
	}

}
