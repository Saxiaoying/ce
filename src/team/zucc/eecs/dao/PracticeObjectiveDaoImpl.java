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

import team.zucc.eecs.model.PracticeObjective;

@Component("PracticeObjectiveDaoImpl")
public class PracticeObjectiveDaoImpl implements PracticeObjectiveDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public PracticeObjective getPracticeObjectiveByPc_id(int pc_id) {
		return template.query("select * from tb_pra_co where pc_id =" +pc_id, new ResultSetExtractor<PracticeObjective>() {
			@Override
			public PracticeObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PracticeObjective co = new PracticeObjective();
					co.setPc_id(rs.getInt("pc_id"));
					co.setCo_id(rs.getInt("co_id"));
					co.setPra_id(rs.getInt("pra_id"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public PracticeObjective getPracticeObjectiveByCo_idAndPra_id(int co_id, int pra_id) {
		return template.query("select * from tb_pra_co where co_id =" +co_id +" and pra_id=" + pra_id, new ResultSetExtractor<PracticeObjective>() {
			@Override
			public PracticeObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					PracticeObjective co = new PracticeObjective();
					co.setPc_id(rs.getInt("pc_id"));
					co.setCo_id(rs.getInt("co_id"));
					co.setPra_id(rs.getInt("pra_id"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}
	

	@Override
	public List<PracticeObjective> getPracticeObjectiveByCo_id(int co_id) {
		List<PracticeObjective> courseList = new ArrayList<>();
		String sql = "select * from tb_pra_co where co_id=" + co_id;
		courseList = this.template.query(sql, new RowMapper<PracticeObjective>() {
			public PracticeObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				PracticeObjective co = new PracticeObjective();
				co.setPc_id(rs.getInt("pc_id"));
				co.setCo_id(rs.getInt("co_id"));
				co.setPra_id(rs.getInt("pra_id"));
				return co;
			}
		});
		return courseList;
	}

	@Override
	public List<PracticeObjective> getPracticeObjectiveByPra_id(int pra_id) {
		List<PracticeObjective> courseList = new ArrayList<>();
		String sql = "select * from tb_pra_co where pra_id=" + pra_id;
		courseList = this.template.query(sql, new RowMapper<PracticeObjective>() {
			public PracticeObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				PracticeObjective co = new PracticeObjective();
				co.setPc_id(rs.getInt("pc_id"));
				co.setCo_id(rs.getInt("co_id"));
				co.setPra_id(rs.getInt("pra_id"));
				return co;
			}
		});
		return courseList;
	}

	@Override
	public void addPracticeObjective(int co_id, int pra_id) {
		template.update("insert into tb_pra_co (co_id, pra_id)  "
				+ "values (?, ?)", co_id, pra_id);

	}

	@Override
	public void deletePracticeObjectiveByPc_id(int pc_id) {
		template.update("delete from tb_pra_co where pc_id = " + pc_id);
	}

	@Override
	public void updatePracticeObjective(int pc_id, int co_id, int pra_id) {
		template.update("update tb_pra_co set co_id = ?, pra_id = ?  where pc_id = ?", 
				co_id, pra_id, pc_id);

	}

}
