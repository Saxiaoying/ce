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

import team.zucc.eecs.model.Evaluation;

@Component("EvaluationDaoImpl")
public class EvaluationDaoImpl implements EvaluationDao {
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public List<Evaluation> getEvaluationListByCs_id(int cs_id) {
		List<Evaluation> evaluationList = new ArrayList<>();
		String sql = "select * from tb_eval where cs_id = " + cs_id;
		evaluationList = this.template.query(sql, new RowMapper<Evaluation>() {
			public Evaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Evaluation e = new Evaluation();
				e.setEval_id(rs.getInt("eval_id"));
				e.setCo_id(rs.getInt("co_id"));
				e.setCs_id(rs.getInt("cs_id"));
				e.setEt_id(rs.getInt("et_id"));
				e.setEval_prop(rs.getDouble("eval_prop"));
				e.setEval_points(rs.getDouble("eval_points"));
				e.setEval_score(rs.getDouble("eval_score"));
				e.setEval_sc_rt(rs.getDouble("eval_sc_rt"));
				e.setEval_achv(rs.getDouble("eval_achv"));
				return e;
			}
		});
		return evaluationList;
	}

	@Override
	public List<Evaluation> getEvaluationListByCs_idAndCo_id(int cs_id, int co_id) {
		List<Evaluation> evaluationList = new ArrayList<>();
		String sql = "select * from tb_eval where cs_id = " + cs_id + " and co_id = " + co_id;
		evaluationList = this.template.query(sql, new RowMapper<Evaluation>() {
			public Evaluation mapRow(ResultSet rs, int rowNum) throws SQLException {
				Evaluation e = new Evaluation();
				e.setEval_id(rs.getInt("eval_id"));
				e.setCo_id(rs.getInt("co_id"));
				e.setCs_id(rs.getInt("cs_id"));
				e.setEt_id(rs.getInt("et_id"));
				e.setEval_prop(rs.getDouble("eval_prop"));
				e.setEval_points(rs.getDouble("eval_points"));
				e.setEval_score(rs.getDouble("eval_score"));
				e.setEval_sc_rt(rs.getDouble("eval_sc_rt"));
				e.setEval_achv(rs.getDouble("eval_achv"));
				return e;
			}
		});
		return evaluationList;
	}

	@Override
	public Evaluation getEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id) {
		return template.query("select * from tb_eval where cs_id = " + cs_id + " and co_id = " + co_id + " and et_id = " + et_id,
				new ResultSetExtractor<Evaluation>() {
			@Override
			public Evaluation extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Evaluation e = new Evaluation();
					e.setEval_id(rs.getInt("eval_id"));
					e.setCo_id(rs.getInt("co_id"));
					e.setCs_id(rs.getInt("cs_id"));
					e.setEt_id(rs.getInt("et_id"));
					e.setEval_prop(rs.getDouble("eval_prop"));
					e.setEval_points(rs.getDouble("eval_points"));
					e.setEval_score(rs.getDouble("eval_score"));
					e.setEval_sc_rt(rs.getDouble("eval_sc_rt"));
					e.setEval_achv(rs.getDouble("eval_achv"));
					return e;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addEvaluation(int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score,
			double eval_sc_rt, double eval_achv) {
		template.update("insert into tb_eval (co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv)  "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)", co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);

	}

	@Override
	public void deleteEvaluationByEval_id(int eval_id) {
		template.update("delete from  tb_eval where eval_id = ? ", eval_id);
	}

	@Override
	public void updateEvaluationByEval_id(int eval_id, int co_id, int cs_id, int et_id, double eval_prop, double eval_points,
			double eval_score, double eval_sc_rt, double eval_achv) {
		template.update("update tb_eval set co_id = ?, cs_id = ?, et_id = ?, eval_prop = ?, "
				+ "eval_points = ?, eval_score = ?, eval_sc_rt = ?, eval_achv = ?"
				+ "where eval_id = ? ", 
				co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv, eval_id);
	}
}
