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

import team.zucc.eecs.model.CourseObjective;

@Component("CourseObjectiveDaoImpl")
public class CourseObjectiveDaoImpl implements CourseObjectiveDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<CourseObjective> getCourseObjectiveList() {
		List<CourseObjective> courseObjectiveList = new ArrayList<>();
		String sql = "select * from tb_coz_obj";
		courseObjectiveList = this.template.query(sql, new RowMapper<CourseObjective>() {
			public CourseObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseObjective co = new CourseObjective();
				co.setCo_id(rs.getInt("co_id"));
				co.setCs_id(rs.getInt("cs_id"));
				co.setCo_num(rs.getInt("co_num"));
				co.setCo_cont(rs.getString("co_cont"));
				return co;
			}
		});
		return courseObjectiveList;
	}

	@Override
	public CourseObjective getCourseObjectiveByCo_id(int co_id) {
		return template.query("select * from tb_coz_obj where co_id =" +co_id, new ResultSetExtractor<CourseObjective>() {
			@Override
			public CourseObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseObjective co = new CourseObjective();
					co.setCo_id(rs.getInt("co_id"));
					co.setCs_id(rs.getInt("cs_id"));
					co.setCo_num(rs.getInt("co_num"));
					co.setCo_cont(rs.getString("co_cont"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CourseObjective> getCourseObjectiveByCs_id(int cs_id) {
		List<CourseObjective> courseObjectiveList = new ArrayList<>();
		String sql = "select * from tb_coz_obj where cs_id = " + cs_id + " order by co_num";
		courseObjectiveList = this.template.query(sql, new RowMapper<CourseObjective>() {
			public CourseObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseObjective co = new CourseObjective();
				co.setCo_id(rs.getInt("co_id"));
				co.setCs_id(rs.getInt("cs_id"));
				co.setCo_num(rs.getInt("co_num"));
				co.setCo_cont(rs.getString("co_cont"));
				return co;
			}
		});
		return courseObjectiveList;
	}

	@Override
	public CourseObjective getCourseObjectiveByCs_idAndCo_num(int cs_id, int co_num) {
		return template.query("select * from tb_coz_obj where cs_id =" +cs_id + " and co_num = " + co_num, new ResultSetExtractor<CourseObjective>() {
			@Override
			public CourseObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseObjective co = new CourseObjective();
					co.setCo_id(rs.getInt("co_id"));
					co.setCs_id(rs.getInt("cs_id"));
					co.setCo_num(rs.getInt("co_num"));
					co.setCo_cont(rs.getString("co_cont"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addCourseObjective(int cs_id, int co_num, String co_cont) {
		template.update("insert into tb_coz_obj (cs_id, co_num, co_cont)  "
				+ "values (?, ?, ?)", cs_id, co_num, co_cont);
	}

	@Override
	public void deleteCourseObjective(int cs_id, int co_num) {
		template.update("delete from tb_coz_obj where cs_id = ? and co_num = ?", cs_id, co_num);
	}

	@Override
	public void updateCourseObjective(int cs_id, int co_num, String co_cont) {
		template.update("update tb_coz_obj set co_cont = ? where cs_id = ? and co_num = ?", co_cont, cs_id, co_num);
	}

}
