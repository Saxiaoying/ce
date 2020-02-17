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

import team.zucc.eecs.model.EvaluationDetail;

@Component("EvaluationDetailDaoImpl")
public class EvaluationDetailDaoImpl implements EvaluationDetailDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public EvaluationDetail getEvaluationDetailByEd_id(int ed_id) {
		return template.query("select * from tb_eval_dtl where ed_id =" +ed_id, new ResultSetExtractor<EvaluationDetail>() {
			@Override
			public EvaluationDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					EvaluationDetail ed = new EvaluationDetail();
					ed.setEd_id(rs.getInt("ed_id"));
					ed.setCont_id(rs.getInt("cont_id"));
					ed.setCs_id(rs.getInt("cs_id"));
					ed.setEt_id(rs.getInt("et_id"));
					ed.setEd_num(rs.getString("ed_num"));
					ed.setEd_points(rs.getDouble("ed_points"));
					ed.setEd_score(rs.getDouble("ed_score"));
					ed.setEd_sc_rt(rs.getDouble("ed_sc_rt"));
					return ed;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCs_idAndEt_id(int cs_id, int et_id) {
		List<EvaluationDetail> evaluationDetailList = new ArrayList<>();
		String sql = "select * from tb_eval_dtl where cs_id=" + cs_id +" and et_id=" + et_id + " ORDER BY ed_num";
		evaluationDetailList = this.template.query(sql, new RowMapper<EvaluationDetail>() {
			public EvaluationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				EvaluationDetail ed = new EvaluationDetail();
				ed.setEd_id(rs.getInt("ed_id"));
				ed.setCont_id(rs.getInt("cont_id"));
				ed.setCs_id(rs.getInt("cs_id"));
				ed.setEt_id(rs.getInt("et_id"));
				ed.setEd_num(rs.getString("ed_num"));
				ed.setEd_points(rs.getDouble("ed_points"));
				ed.setEd_score(rs.getDouble("ed_score"));
				ed.setEd_sc_rt(rs.getDouble("ed_sc_rt"));
				return ed;
			}
		});
		return evaluationDetailList;
	}

	@Override
	public List<EvaluationDetail> getEvaluationDatailListByCont_idAndCs_idAndEt_id(int cont_id, int cs_id, int et_id) {
		List<EvaluationDetail> evaluationDetailList = new ArrayList<>();
		String sql = "select * from tb_eval_dtl where cs_id=" + cs_id +" and et_id=" + et_id +" and cont_id=" + cont_id + " ORDER BY ed_num";
		evaluationDetailList = this.template.query(sql, new RowMapper<EvaluationDetail>() {
			public EvaluationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				EvaluationDetail ed = new EvaluationDetail();
				ed.setEd_id(rs.getInt("ed_id"));
				ed.setCont_id(rs.getInt("cont_id"));
				ed.setCs_id(rs.getInt("cs_id"));
				ed.setEt_id(rs.getInt("et_id"));
				ed.setEd_num(rs.getString("ed_num"));
				ed.setEd_points(rs.getDouble("ed_points"));
				ed.setEd_score(rs.getDouble("ed_score"));
				ed.setEd_sc_rt(rs.getDouble("ed_sc_rt"));
				return ed;
			}
		});
		return evaluationDetailList;
	}
	
	@Override
	public EvaluationDetail getEvaluationDetailByInf(int cs_id, int et_id, String ed_num) {
		String sql = "select * from tb_eval_dtl where cs_id=" + cs_id +" and et_id=" + et_id 
				+" and ed_num='" + ed_num +"'";
		
		return template.query(sql, new ResultSetExtractor<EvaluationDetail>() {
			@Override
			public EvaluationDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					EvaluationDetail ed = new EvaluationDetail();
					ed.setEd_id(rs.getInt("ed_id"));
					ed.setCont_id(rs.getInt("cont_id"));
					ed.setCs_id(rs.getInt("cs_id"));
					ed.setEt_id(rs.getInt("et_id"));
					ed.setEd_num(rs.getString("ed_num"));
					ed.setEd_points(rs.getDouble("ed_points"));
					ed.setEd_score(rs.getDouble("ed_score"));
					ed.setEd_sc_rt(rs.getDouble("ed_sc_rt"));
					return ed;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addEvaluationDetail(int cont_id, int cs_id, int et_id, String ed_num, double ed_points,
			double ed_score, double ed_sc_rt) {
		template.update("insert into tb_eval_dtl (cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt)  "
				+ "values (?, ?, ?, ?, ?, ?, ?)", cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt);
	}

	@Override
	public void deleteEvaluationDetailByEd_id(int ed_id) {
		template.update("delete from tb_eval_dtl where ed_id=" + ed_id);

	}

	@Override
	public void updateEvaluationByEd_id(int ed_id, int cont_id, int cs_id, int et_id, String ed_num,
			double ed_points, double ed_score, double ed_sc_rt) {
		template.update("update tb_eval_dtl set ed_id = ?, cont_id = ?, cs_id = ?, "
				+ "et_id = ?, ed_num = ?, ed_points = ?, ed_score = ?, ed_sc_rt = ?"
				+ "where ed_id = ? ", 
				ed_id, cont_id, cs_id, et_id, ed_num, ed_points, ed_score, ed_sc_rt, ed_id);

	}

}
