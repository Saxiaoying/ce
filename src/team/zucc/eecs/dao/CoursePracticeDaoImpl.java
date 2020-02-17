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


import team.zucc.eecs.model.CoursePractice;

@Component("CoursePracticeDaoImpl")
public class CoursePracticeDaoImpl implements CoursePracticeDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<CoursePractice> getCoursePracticeList() {
		List<CoursePractice> coursePracticeList = new ArrayList<>();
		String sql = "select * from tb_pra";
		coursePracticeList = this.template.query(sql, new RowMapper<CoursePractice>() {
			public CoursePractice mapRow(ResultSet rs, int rowNum) throws SQLException {
				CoursePractice cp = new CoursePractice();
				cp.setPra_id(rs.getInt("pra_id"));
				cp.setCs_id(rs.getInt("cs_id"));
				cp.setPra_num(rs.getInt("pra_num"));
				cp.setPra_name(rs.getString("pra_name"));
				cp.setPra_hrs(rs.getDouble("pra_hrs"));
				cp.setPra_cont(rs.getString("pra_cont"));
				cp.setPra_nature(rs.getString("pra_nature"));
				cp.setPra_typ(rs.getString("pra_typ"));
				return cp;
			}
		});
		return coursePracticeList;
	}

	@Override
	public CoursePractice getCoursePracticeByPra_id(int pra_id) {
		return template.query("select * from tb_pra where pra_id ='" +pra_id + "'", new ResultSetExtractor<CoursePractice>() {
			@Override
			public CoursePractice extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CoursePractice cp = new CoursePractice();
					cp.setPra_id(rs.getInt("pra_id"));
					cp.setCs_id(rs.getInt("cs_id"));
					cp.setPra_num(rs.getInt("pra_num"));
					cp.setPra_name(rs.getString("pra_name"));
					cp.setPra_hrs(rs.getDouble("pra_hrs"));
					cp.setPra_cont(rs.getString("pra_cont"));
					cp.setPra_nature(rs.getString("pra_nature"));
					cp.setPra_typ(rs.getString("pra_typ"));
					return cp;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<CoursePractice> getCoursePracticeByCs_id(int cs_id) {
		List<CoursePractice> coursePracticeList = new ArrayList<>();
		String sql = "select * from tb_pra where cs_id =" + cs_id + " ORDER BY pra_num" ;
		coursePracticeList = this.template.query(sql, new RowMapper<CoursePractice>() {
			public CoursePractice mapRow(ResultSet rs, int rowNum) throws SQLException {
				CoursePractice cp = new CoursePractice();
				cp.setPra_id(rs.getInt("pra_id"));
				cp.setCs_id(rs.getInt("cs_id"));
				cp.setPra_num(rs.getInt("pra_num"));
				cp.setPra_name(rs.getString("pra_name"));
				cp.setPra_hrs(rs.getDouble("pra_hrs"));
				cp.setPra_cont(rs.getString("pra_cont"));
				cp.setPra_nature(rs.getString("pra_nature"));
				cp.setPra_typ(rs.getString("pra_typ"));
				return cp;
			}
		});
		return coursePracticeList;
	}

	@Override
	public CoursePractice getCoursePracticeByCs_idAndPra_num(int cs_id, int pra_num) {
		return template.query("select * from tb_pra where cs_id =" +cs_id + " and pra_num = " + pra_num, new ResultSetExtractor<CoursePractice>() {
			@Override
			public CoursePractice extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					CoursePractice cp = new CoursePractice();
					cp.setPra_id(rs.getInt("pra_id"));
					cp.setCs_id(rs.getInt("cs_id"));
					cp.setPra_num(rs.getInt("pra_num"));
					cp.setPra_name(rs.getString("pra_name"));
					cp.setPra_hrs(rs.getDouble("pra_hrs"));
					cp.setPra_cont(rs.getString("pra_cont"));
					cp.setPra_nature(rs.getString("pra_nature"));
					cp.setPra_typ(rs.getString("pra_typ"));
					return cp;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addCoursePractice(int cs_id, int pra_num, String pra_name, double pra_hrs, String pra_cont,
			String pra_nature, String pra_typ) {
		template.update("insert into tb_pra (cs_id, pra_num, pra_name, pra_hrs, pra_cont, pra_nature, pra_typ)  "
				+ "values (?, ?, ?, ?, ?, ?, ?)", cs_id, pra_num, pra_name, pra_hrs, pra_cont, pra_nature, pra_typ);

	}

	@Override
	public void deleteCoursePracticeByCs_idAndPra_num(int cs_id, int pra_num) {
		template.update("delete from tb_pra where cs_id ="  + cs_id + " and pra_num = " + pra_num);
	}

	@Override
	public void updateCoursePractice(int cs_id, int pra_num, String pra_name, double pra_hrs, String pra_cont,
			String pra_nature, String pra_typ) {
		template.update("update tb_pra set pra_name = ?, pra_hrs = ?, pra_cont = ?, pra_nature = ?, pra_typ = ? "
				+ "where cs_id = ? and pra_num = ?", 
				pra_name, pra_hrs, pra_cont, pra_nature, pra_typ, cs_id, pra_num);
	}

}
