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

import team.zucc.eecs.model.GraduationRequire;


@Component("GraduationRequireDaoImpl")
public class GraduationRequireDaoImpl implements GraduationRequireDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<GraduationRequire> getGraduationRequireListFromAtoB(int a, int b) {
		List<GraduationRequire> grList = new ArrayList<>();
		int num = b-a;
		String sql = "select * from tb_grad_req  order by gr_code limit " + a + ", " + num;
		grList = this.template.query(sql, new RowMapper<GraduationRequire>() {
			public GraduationRequire mapRow(ResultSet rs, int rowNum) throws SQLException {
				GraduationRequire gr = new GraduationRequire();
				gr.setGr_code(rs.getInt("gr_code"));
				gr.setGr_content(rs.getString("gr_content"));
				gr.setGr_id(rs.getInt("gr_id"));
				gr.setGr_title(rs.getString("gr_title"));
				return gr;
			}
		});
		return grList;
	}

	@Override
	public int getGraduationRequireListNumber() {
		String sql = "select count(*) from tb_grad_req";
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
	public GraduationRequire getGraduationRequireByGr_id(int gr_id) {
		return template.query("select * from tb_grad_req where gr_id =" +gr_id, new ResultSetExtractor<GraduationRequire>() {
			@Override
			public GraduationRequire extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					GraduationRequire gr = new GraduationRequire();
					gr.setGr_code(rs.getInt("gr_code"));
					gr.setGr_content(rs.getString("gr_content"));
					gr.setGr_id(rs.getInt("gr_id"));
					gr.setGr_title(rs.getString("gr_title"));
					return gr;
				} else {
					return null;
				}
			}
			
		});
	}

}
