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
//This is maintained by jyl. 
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.EvaluationType;

@Component("EvaluationTypeDaoImpl")
public class EvaluationTypeDaoImpl implements EvaluationTypeDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public List<EvaluationType> getEvaluationTypeList() {
		List<EvaluationType> evaluationTypeList = new ArrayList<>();
		String sql = "select * from tb_eval_typ";
		evaluationTypeList = this.template.query(sql, new RowMapper<EvaluationType>() {
			public EvaluationType mapRow(ResultSet rs, int rowNum) throws SQLException {
				EvaluationType et = new EvaluationType();
				et.setEt_id(rs.getInt("et_id"));
				et.setEt_name(rs.getString("et_name"));
				return et;
			}
		});
		return evaluationTypeList;
	}

	@Override
	public EvaluationType getEvaluationTypeByEt_id(int et_id) {
		return template.query("select * from tb_eval_typ where et_id =" +et_id, new ResultSetExtractor<EvaluationType>() {
			@Override
			public EvaluationType extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					EvaluationType et = new EvaluationType();
					et.setEt_id(rs.getInt("et_id"));
					et.setEt_name(rs.getString("et_name"));
					return et;
				} else {
					return null;
				}
			}
			
		});
	}
	
	@Override
	public EvaluationType getEvaluationTypeByEt_name(String et_name) {
		return template.query("select * from tb_eval_typ where et_name = '" + et_name +"'", new ResultSetExtractor<EvaluationType>() {
			@Override
			public EvaluationType extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					EvaluationType et = new EvaluationType();
					et.setEt_id(rs.getInt("et_id"));
					et.setEt_name(rs.getString("et_name"));
					return et;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addEvaluationType(int et_id, String et_name) {
		template.update("insert into tb_eval_typ (et_id, et_name)  "
				+ "values (?, ?)", et_id, et_name);
	}

	@Override
	public void updateEvaluationType(int et_id, String et_name) {
		template.update("update tb_eval_typ set et_name = ? "
				+ "where et_id = ? ", 
				et_name, et_id);
	}

}
