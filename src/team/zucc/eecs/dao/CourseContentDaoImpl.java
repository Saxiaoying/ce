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


import team.zucc.eecs.model.CourseContent;

@Component("CourseContentImpl")
public class CourseContentDaoImpl implements CourseContentDao {
	@Autowired
	JdbcTemplate template;

	@Override
	public List<CourseContent> getCourseContentList() {
		List<CourseContent> courseContentList = new ArrayList<>();
		String sql = "select * from tb_cont";
		courseContentList = this.template.query(sql, new RowMapper<CourseContent>() {
			public CourseContent mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseContent cc = new CourseContent();
				cc.setCont_id(rs.getInt("cont_id"));
				cc.setCs_id(rs.getInt("cs_id"));
				cc.setCont_name(rs.getString("cont_name"));
				cc.setCont_num(rs.getInt("cont_num"));
				cc.setCont_cont(rs.getString("cont_cont"));
				cc.setCont_method(rs.getString("cont_method"));
				cc.setCont_key(rs.getString("cont_key"));
				cc.setCont_diff(rs.getString("cont_diff"));
				cc.setCont_hrs_pr(rs.getDouble("cont_hrs_pr"));
				cc.setCont_hrs_tch(rs.getDouble("cont_hrs_tch"));
				cc.setCont_cla_exe(rs.getString("cont_cla_exe"));
				cc.setCont_hw(rs.getString("cont_hw"));
				return cc;
			}
		});
		return courseContentList;
	}

	@Override
	public CourseContent getCourseContentByCont_id(int cont_id) {
		return template.query("select * from tb_cont where cont_id ='" +cont_id + "'", new ResultSetExtractor<CourseContent>() {
			@Override
			public CourseContent extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseContent cc = new CourseContent();
					cc.setCont_id(rs.getInt("cont_id"));
					cc.setCs_id(rs.getInt("cs_id"));
					cc.setCont_name(rs.getString("cont_name"));
					cc.setCont_num(rs.getInt("cont_num"));
					cc.setCont_cont(rs.getString("cont_cont"));
					cc.setCont_method(rs.getString("cont_method"));
					cc.setCont_key(rs.getString("cont_key"));
					cc.setCont_diff(rs.getString("cont_diff"));
					cc.setCont_hrs_pr(rs.getDouble("cont_hrs_pr"));
					cc.setCont_hrs_tch(rs.getDouble("cont_hrs_tch"));
					cc.setCont_cla_exe(rs.getString("cont_cla_exe"));
					cc.setCont_hw(rs.getString("cont_hw"));
					return cc;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CourseContent> getCourseContentByCs_id(int cs_id) {
		List<CourseContent> courseContentList = new ArrayList<>();
		String sql = "select * from tb_cont where cs_id =" + cs_id + " ORDER BY cont_num";
		courseContentList = this.template.query(sql, new RowMapper<CourseContent>() {
			public CourseContent mapRow(ResultSet rs, int rowNum) throws SQLException {
				CourseContent cc = new CourseContent();
				cc.setCont_id(rs.getInt("cont_id"));
				cc.setCs_id(rs.getInt("cs_id"));
				cc.setCont_name(rs.getString("cont_name"));
				cc.setCont_num(rs.getInt("cont_num"));
				cc.setCont_cont(rs.getString("cont_cont"));
				cc.setCont_method(rs.getString("cont_method"));
				cc.setCont_key(rs.getString("cont_key"));
				cc.setCont_diff(rs.getString("cont_diff"));
				cc.setCont_hrs_pr(rs.getDouble("cont_hrs_pr"));
				cc.setCont_hrs_tch(rs.getDouble("cont_hrs_tch"));
				cc.setCont_cla_exe(rs.getString("cont_cla_exe"));
				cc.setCont_hw(rs.getString("cont_hw"));
				return cc;
			}
		});
		return courseContentList;
	}

	@Override
	public CourseContent getCourseContentByCs_idAndCont_num(int cs_id, int cont_num) {
		return template.query("select * from tb_cont where cs_id =" +cs_id + " and cont_num = " + cont_num, new ResultSetExtractor<CourseContent>() {
			@Override
			public CourseContent extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CourseContent cc = new CourseContent();
					cc.setCont_id(rs.getInt("cont_id"));
					cc.setCs_id(rs.getInt("cs_id"));
					cc.setCont_name(rs.getString("cont_name"));
					cc.setCont_num(rs.getInt("cont_num"));
					cc.setCont_cont(rs.getString("cont_cont"));
					cc.setCont_method(rs.getString("cont_method"));
					cc.setCont_key(rs.getString("cont_key"));
					cc.setCont_diff(rs.getString("cont_diff"));
					cc.setCont_hrs_pr(rs.getDouble("cont_hrs_pr"));
					cc.setCont_hrs_tch(rs.getDouble("cont_hrs_tch"));
					cc.setCont_cla_exe(rs.getString("cont_cla_exe"));
					cc.setCont_hw(rs.getString("cont_hw"));
					return cc;
				} else {
					return null;
				}
			}
			
		});
	}
	
	@Override
	public void addCourseContent(int cs_id, String cont_name, int cont_num, String cont_cont,
			String cont_method, String cont_key, String cont_diff, double cont_hrs_tch, double cont_hrs_pr,
			String cont_cla_exe, String cont_hw) {
		template.update("insert into tb_cont (cs_id, cont_name, cont_num, cont_cont, cont_method, "
				+ "cont_key, cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, cont_hw)  "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", cs_id, cont_name, cont_num, cont_cont,
				cont_method, cont_key, cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, cont_hw);
	}

	@Override
	public void deleteCourseContentByCs_idAndCont_num(int cs_id, int cont_num) {
		template.update("delete from tb_cont where cs_id ="  + cs_id + " and cont_num = " + cont_num);

	}

	@Override
	public void updateCourseContent(int cs_id, String cont_name, int cont_num,
			String cont_cont, String cont_method, String cont_key, String cont_diff, double cont_hrs_tch,
			double cont_hrs_pr, String cont_cla_exe, String cont_hw) {
		template.update("update tb_cont set cont_name = ?, cont_cont = ?, cont_method = ?, cont_key = ?, "
				+ "cont_diff = ?, cont_hrs_tch = ?, cont_hrs_pr = ?, cont_cla_exe = ?, cont_hw = ? "
				+ "where cs_id = ? and cont_num = ?", 
			    cont_name, cont_cont, cont_method, cont_key, cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, 
				cont_hw, cs_id, cont_num);
	}

}
