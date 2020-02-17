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

import team.zucc.eecs.model.Course;


@Component("CourseDaoImpl")
public class CourseDaoImpl implements CourseDao {
	@Autowired
	JdbcTemplate template;

	@Override
	public List<Course> getCourseList() {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}
	
	@Override
	public int getCourseNumber() {
		return template.query("select count(*) from tb_coz", new ResultSetExtractor<Integer>() {
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
	public List<Course> getCourseListFromAtoB(int a, int b){
		List<Course> courseList = new ArrayList<>();
		int num = b-a;
		String sql = "select * from tb_coz limit " + a + ", " + num;
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}
	
	@Override
	public Course getCourseByCoz_id(String coz_id) {
		return template.query("select * from tb_coz where coz_id ='" +coz_id + "'", new ResultSetExtractor<Course>() {
			@Override
			public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Course c = new Course();
					c.setCoz_id(rs.getString("coz_id"));
					c.setCoz_name_ch(rs.getString("coz_name_ch"));
					c.setCoz_name_eng(rs.getString("coz_name_eng"));
					c.setCoz_nature(rs.getString("coz_nature"));
					c.setCoz_credit(rs.getDouble("coz_credit"));
					c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
					c.setCoz_hours(rs.getDouble("coz_hours"));
					return c;
				} else {
					return null;
				}
			}
			
		});
	}
	
	@Override
	public List<Course> getCourseListByCoz_id(String coz_id) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_id like '%" +coz_id + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}

	@Override
	public List<Course> getCourseListByCoz_name_ch(String coz_name_ch) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_name_ch like '%" + coz_name_ch + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}

	@Override
	public List<Course> getCourseListByCoz_nature(String coz_nature) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_nature like '%" + coz_nature + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}
	
	@Override
	public List<Course> getCourseListByCoz_idAndCoz_name_ch(String coz_id, String coz_name_ch) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_id like '%" + coz_id + "%' and coz_name_ch like '%" + coz_name_ch + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}


	@Override
	public List<Course> getCourseListByCoz_name_chAndCoz_nature(String coz_name_ch, String coz_nature) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_nature like '%" + coz_nature + "%' and coz_name_ch like '%" + coz_name_ch + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}


	@Override
	public List<Course> getCourseListByCoz_natureAndCoz_id(String coz_nature, String coz_id) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_id like '%" + coz_id + "%' and coz_nature like '%" + coz_nature + "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}

	@Override
	public List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature) {
		List<Course> courseList = new ArrayList<>();
		String sql = "select * from tb_coz where coz_id like '%" + coz_id + "%' and coz_nature like '%" + coz_nature +
				"%' and coz_name_ch like '%" + coz_name_ch +  "%'";
		courseList = this.template.query(sql, new RowMapper<Course>() {
			public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
				Course c = new Course();
				c.setCoz_id(rs.getString("coz_id"));
				c.setCoz_name_ch(rs.getString("coz_name_ch"));
				c.setCoz_name_eng(rs.getString("coz_name_eng"));
				c.setCoz_nature(rs.getString("coz_nature"));
				c.setCoz_credit(rs.getDouble("coz_credit"));
				c.setCoz_hrs_wk(rs.getString("coz_hrs_wk"));
				c.setCoz_hours(rs.getDouble("coz_hours"));
				return c;
			}
		});
		return courseList;
	}
	
	@Override
	public void addCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit,
			String coz_hrs_wk, double coz_hours) {
		template.update("insert into tb_coz (coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours)  "
				+ "values (?, ?, ?, ?, ?, ?, ?)", coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
	}

	@Override
	public void deleteCourseByCoz_id(String coz_id) {
		template.update("delete from tb_coz where coz_id = '" + coz_id + "'");
	}

	@Override
	public void updateCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit,
			String coz_hrs_wk, double coz_hours) {
		template.update("update tb_coz set coz_name_ch = ?, coz_name_eng = ?, coz_nature = ?, coz_credit = ?, coz_hrs_wk = ?, coz_hours = ? "
				+ "where coz_id = ?", coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours, coz_id);
	}
}
