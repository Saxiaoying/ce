//This is maintained by jyl. 
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

import team.zucc.eecs.model.CourseSet;

@Component("CourseSetDaoImpl")
public class CourseSetDaoImpl implements CourseSetDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public CourseSet getCourseSetByCs_id(int cs_id) {
		return template.query("select * from tb_coz_set where cs_id =" +cs_id, new ResultSetExtractor<CourseSet>() {
			@Override
			public CourseSet extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseSet cs = new CourseSet();
					cs.setCs_id(rs.getInt("cs_id"));
					cs.setCoz_id(rs.getString("coz_id"));
					cs.setCs_acad_yr(rs.getString("cs_acad_yr"));
					cs.setCs_sem(rs.getString("cs_sem"));
					return cs;
				} else {
					return null;
				}
			}
			
		});
	}
	@Override
	public CourseSet getCourseSetByCoz_idAndTime(String coz_id, String cs_acad_yr, String cs_sem) {
		return template.query("select * from tb_coz_set where coz_id = '" +coz_id +
				"' and cs_acad_yr = '" + cs_acad_yr + "' and cs_sem = '" +  cs_sem +"'", new ResultSetExtractor<CourseSet>() {
			@Override
			public CourseSet extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseSet cs = new CourseSet();
					cs.setCs_id(rs.getInt("cs_id"));
					cs.setCoz_id(rs.getString("coz_id"));
					cs.setCs_acad_yr(rs.getString("cs_acad_yr"));
					cs.setCs_sem(rs.getString("cs_sem"));
					return cs;
				} else {
					return null;
				}
			}
			
		});
	}
	
	@Override
	public int getCourseSetNumberByInf(String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature) {
		String sql = "select count(*) from tb_coz_set where cs_acad_yr like '%" + cs_acad_yr + "%' and cs_sem like '%" + cs_sem + "%' and coz_id in"
        		+ "(select coz_id from tb_coz where coz_id like '%" + coz_id + "%' "
        				+ "and coz_name_ch like '%" + coz_name_ch + "%' "
        				+ "and coz_nature like '%" + coz_nature + "%')";
        
		return template.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("count(*)");
				} else {
					return 0;
				}
			}
			
		});
	}
	
	@Override
	public List<CourseSet> getCourseSetListByInfFromAtoB(int a, int b, String coz_id, String cs_acad_yr, String cs_sem, String coz_name_ch, String coz_nature) {
		List<CourseSet> courseSetList = new ArrayList<>();
		
		// "select * from tb_coz_set where cs_acad_yr=2019 and cs_sem=1 and 
		// coz_id in(select coz_id from tb_coz where coz_id like '%1%' and coz_name_ch LIKE '%测%' and coz_nature like '%通识%')";
		String sql = "select * from tb_coz_set where cs_acad_yr like '%" + cs_acad_yr + "%' and cs_sem like '%" + cs_sem + "%' and coz_id in"
        		+ "(select coz_id from tb_coz where coz_id like '%" + coz_id + "%' "
        				+ "and coz_name_ch like '%" + coz_name_ch + "%' "
        				+ "and coz_nature like '%" + coz_nature + "%')";
		
		int num = b-a;
		String tmp = " limit " + a + ", " + num;
		sql = sql + tmp;
		
		courseSetList = this.template.query(sql, new RowMapper<CourseSet>() {
			public CourseSet mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseSet cs = new CourseSet();
				cs.setCs_id(rs.getInt("cs_id"));
				cs.setCoz_id(rs.getString("coz_id"));
				cs.setCs_acad_yr(rs.getString("cs_acad_yr"));
				cs.setCs_sem(rs.getString("cs_sem"));
				return cs;
			}
		});
		return courseSetList;
	}
	
	@Override
	public int getCourseSetMaxId() {
		String sql = "select max(cs_id) from tb_coz_set";
        
		return template.query(sql, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getInt("max(cs_id)");
				} else {
					return 0;
				}
			}
			
		});
	}
	
	@Override
	public void addCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem) {
		template.update("insert into tb_coz_set (cs_id, coz_id, cs_acad_yr, cs_sem)  "
				+ "values (?, ?, ?, ?)", cs_id, coz_id, cs_acad_yr, cs_sem);
	}

	@Override
	public void deleteCourseSetByCoz_id(int cs_id) {
		template.update("delete from tb_coz_set where cs_id = " + cs_id);
		
	}

	@Override
	public void updateCourseSet(int cs_id, String coz_id, String cs_acad_yr, String cs_sem) {
		template.update("update tb_coz_set set coz_id = ?, cs_acad_yr = ?, cs_sem = ?  "
				+ "where cs_id = ?", coz_id, cs_acad_yr, cs_sem, cs_id);
	}
}
