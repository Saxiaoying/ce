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

import team.zucc.eecs.model.ContentObjective;

@Component("ContentObjectiveDaoImpl")
public class ContentObjectiveDaoImpl implements ContentObjectiveDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public ContentObjective getContentObjectiveByCco_id(int cco_id) {
		return template.query("select * from tb_cont_co where cco_id =" +cco_id, new ResultSetExtractor<ContentObjective>() {
			@Override
			public ContentObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ContentObjective co = new ContentObjective();
					co.setCco_id(rs.getInt("cco_id"));
					co.setCo_id(rs.getInt("co_id"));
					co.setCont_id(rs.getInt("cont_id"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public ContentObjective getContentObjectiveByCo_idAndCont_id(int co_id, int cont_id) {
		return template.query("select * from tb_cont_co where co_id =" +co_id + " and cont_id=" + cont_id, new ResultSetExtractor<ContentObjective>() {
			@Override
			public ContentObjective extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					ContentObjective co = new ContentObjective();
					co.setCco_id(rs.getInt("cco_id"));
					co.setCo_id(rs.getInt("co_id"));
					co.setCont_id(rs.getInt("cont_id"));
					return co;
				} else {
					return null;
				}
			}
			
		});
	}
	

	@Override
	public List<ContentObjective> getContentObjectiveByCo_id(int co_id) {
		List<ContentObjective> courseList = new ArrayList<>();
		String sql = "select * from tb_cont_co where co_id=" + co_id;
		courseList = this.template.query(sql, new RowMapper<ContentObjective>() {
			public ContentObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContentObjective co = new ContentObjective();
				co.setCco_id(rs.getInt("cco_id"));
				co.setCo_id(rs.getInt("co_id"));
				co.setCont_id(rs.getInt("cont_id"));
				return co;
			}
		});
		return courseList;
	}

	@Override
	public List<ContentObjective> getContentObjectiveByCont_id(int cont_id) {
		List<ContentObjective> courseList = new ArrayList<>();
		String sql = "select * from tb_cont_co where cont_id=" + cont_id;
		courseList = this.template.query(sql, new RowMapper<ContentObjective>() {
			public ContentObjective mapRow(ResultSet rs, int rowNum) throws SQLException {
				ContentObjective co = new ContentObjective();
				co.setCco_id(rs.getInt("cco_id"));
				co.setCo_id(rs.getInt("co_id"));
				co.setCont_id(rs.getInt("cont_id"));
				return co;
			}
		});
		return courseList;
	}

	@Override
	public void addContentObjective(int co_id, int cont_id) {
		template.update("insert into tb_cont_co (co_id, cont_id)  "
				+ "values (?, ?)", co_id, cont_id);

	}

	@Override
	public void deleteContentObjectiveByCco_id(int cco_id) {
		template.update("delete from tb_cont_co where cco_id = " + cco_id);
	}

	@Override
	public void updateContentObjective(int cco_id, int co_id, int cont_id) {
		template.update("update tb_cont_co set co_id = ?, cont_id = ?  where cco_id = ?", 
				co_id, cont_id, cco_id);

	}

}
