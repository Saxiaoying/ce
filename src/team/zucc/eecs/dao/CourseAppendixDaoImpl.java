package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.CourseAppendix;

@Component("CourseAppendixDaoImpl")
public class CourseAppendixDaoImpl implements CourseAppendixDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public CourseAppendix getCourseAppendixByCa_id(int ca_id) {
		return template.query("select * from tb_coz_app where ca_id ='" +ca_id + "'", new ResultSetExtractor<CourseAppendix>() {
			@Override
			public CourseAppendix extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseAppendix ca = new CourseAppendix();
					ca.setCa_id(rs.getInt("ca_id"));
					ca.setCs_id(rs.getInt("cs_id"));
					ca.setCa_typ(rs.getString("ca_typ"));
					ca.setCa_url(rs.getString("ca_url"));
					ca.setCa_name(rs.getString("ca_name"));
					ca.setCa_time(rs.getTimestamp("ca_time"));
					return ca;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CourseAppendix> getCourseAppendixListByCs_id(int cs_id) {
		List<CourseAppendix> courseContentList = new ArrayList<>();
		String sql = "select * from tb_coz_app where cs_id=" + cs_id;
		courseContentList = this.template.query(sql, new RowMapper<CourseAppendix>() {
			public CourseAppendix mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseAppendix ca = new CourseAppendix();
				ca.setCa_id(rs.getInt("ca_id"));
				ca.setCs_id(rs.getInt("cs_id"));
				ca.setCa_typ(rs.getString("ca_typ"));
				ca.setCa_url(rs.getString("ca_url"));
				ca.setCa_name(rs.getString("ca_name"));
				ca.setCa_time(rs.getTimestamp("ca_time"));
				return ca;
			}
		});
		return courseContentList;
	}

	@Override
	public List<CourseAppendix> getCourseAppendixListByCs_idAndCa_typ(int cs_id, String ca_typ) {
		List<CourseAppendix> courseContentList = new ArrayList<>();
		String sql = "select * from tb_coz_app where cs_id=" + cs_id + " and ca_typ='" + ca_typ + "'";
		courseContentList = this.template.query(sql, new RowMapper<CourseAppendix>() {
			public CourseAppendix mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseAppendix ca = new CourseAppendix();
				ca.setCa_id(rs.getInt("ca_id"));
				ca.setCs_id(rs.getInt("cs_id"));
				ca.setCa_typ(rs.getString("ca_typ"));
				ca.setCa_url(rs.getString("ca_url"));
				ca.setCa_name(rs.getString("ca_name"));
				ca.setCa_time(rs.getTimestamp("ca_time"));
				return ca;
			}
		});
		return courseContentList;
	}

	@Override
	public void addCourseAppendix(int cs_id, String ca_typ, String ca_url, String ca_name, Timestamp ca_time) {
		template.update("insert into tb_coz_app (cs_id, ca_typ, ca_url, ca_name, ca_time)  "
				+ "values (?, ?, ?, ?, ?)", cs_id, ca_typ, ca_url, ca_name, ca_time);
	}

	@Override
	public void deleteCourseAppendix(int ca_id) {
		template.update("delete from tb_coz_app where ca_id = ?", ca_id);
	}

}
