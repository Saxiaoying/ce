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

import team.zucc.eecs.model.StudentEvaluationDetail;
import team.zucc.eecs.model.StudentEvaluationDetailFromView;


@Component("StudentEvaluationDetailDaoImpl")
public class StudentEvaluationDetailDaoImpl implements StudentEvaluationDetailDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public StudentEvaluationDetail getStudentEvaluationDetailBySe_id(int se_id) {
		return template.query("select * from tb_stu_ed where se_id =" +se_id, new ResultSetExtractor<StudentEvaluationDetail>() {
			@Override
			public StudentEvaluationDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					StudentEvaluationDetail se = new StudentEvaluationDetail();
					se.setSe_id(rs.getInt("se_id"));
					se.setStu_id(rs.getInt("stu_id"));
					se.setEd_id(rs.getInt("ed_id"));
					se.setSe_score(rs.getDouble("se_score"));
					return se;
				} else {
					return null;
				}
			}
			
		});
	}
	
	@Override
	public List<StudentEvaluationDetail> getStudentEvaluationDetailListByEd_id(int ed_id) {
		List<StudentEvaluationDetail> courseList = new ArrayList<>();
		String sql = "select * from tb_stu_ed where ed_id=" + ed_id;
		courseList = this.template.query(sql, new RowMapper<StudentEvaluationDetail>() {
			public StudentEvaluationDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentEvaluationDetail se = new StudentEvaluationDetail();
				se.setSe_id(rs.getInt("se_id"));
				se.setStu_id(rs.getInt("stu_id"));
				se.setEd_id(rs.getInt("ed_id"));
				se.setSe_score(rs.getDouble("se_score"));
				return se;
			}
		});
		return courseList;
	}
	
	@Override
	public int getStudentEvaluationDetailNumberByEd_id(int ed_id) {
		String sql = "select count(*) from tb_stu_ed where ed_id=" + ed_id;
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
	public StudentEvaluationDetail getStudentEvaluationDetailByStu_idAndEd_id(int stu_id, int ed_id) {
		return template.query("select * from tb_stu_ed where stu_id =" +stu_id+" and ed_id="+ed_id, new ResultSetExtractor<StudentEvaluationDetail>() {
			@Override
			public StudentEvaluationDetail extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					StudentEvaluationDetail se = new StudentEvaluationDetail();
					se.setSe_id(rs.getInt("se_id"));
					se.setStu_id(rs.getInt("stu_id"));
					se.setEd_id(rs.getInt("ed_id"));
					se.setSe_score(rs.getDouble("se_score"));
					return se;
				} else {
					return null;
				}
			}
			
		});
	}
	
	
	@Override
	public double getEd_scoreByEd_idFromTb_stu_ed(int ed_id) {
		String sql = "select AVG(se_score) from tb_stu_ed where ed_id=" + ed_id;
		return template.query(sql, new ResultSetExtractor<Double>() {
			@Override
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getDouble("AVG(se_score)");
				} else {
					return 0.0;
				}
			}
			
		});
	}

	@Override
	public void addStudentEvaluationDetail(int stu_id, int ed_id, double se_score) {
		template.update("insert into tb_stu_ed (stu_id, ed_id, se_score)  "
				+ "values (?, ?, ?)", stu_id, ed_id, se_score);
	}

	@Override
	public void deleteStudentEvaluationDetailBySe_id(int se_id) {
		template.update("delete from tb_stu_ed where se_id = " + se_id);
	}

	
	@Override
	public void deleteStudentEvaluationDetailByEd_id(int ed_id) {
		template.update("delete from tb_stu_ed where ed_id = " + ed_id);
	}

	@Override
	public void deleteStudentEvaluationDetailByStu_id(int stu_id) {
		template.update("delete from tb_stu_ed where stu_id = " + stu_id);		
	}
	
	@Override
	public void updateStudentEvaluationDetailSe_score(int se_id, double se_score) {
		template.update("update tb_stu_ed set se_score = ?  where se_id = ?", 
				se_score, se_id);
	}
	
	
	@Override
	public double getStudentScoreByStu_idAndCo_idAndEt_id(int stu_id, int co_id, int et_id) {
		String tb = ""; 
		String td = "";
		if(et_id == 1) {
			tb = "tb_pra_co";
			td = "pra_id";
		}
		else if(et_id == 2) {
			tb = "tb_cont_co";
			td = "cont_id";
		}
		
		
		String sql = "select sum(se_score) from tb_stu_ed where stu_id=" + stu_id + " and ed_id in("
				+ "select ed_id from tb_eval_dtl where et_id=" + et_id + " and cont_id in("
				+ "select " + td + " from " + tb + " where co_id=" + co_id
				+ ")"
				+ ")";
		return template.query(sql, new ResultSetExtractor<Double>() {
			@Override
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getDouble("sum(se_score)");
				} else {
					return 0.0;
				}
			}
			
		});
	}
	
	@Override
	public double getStudentScoreByStu_idAndIp_idAndEt_id(int stu_id, int ip_id, int et_id) {
		String tb = ""; 
		String td = "";
		if(et_id == 1) {
			tb = "tb_pra_co";
			td = "pra_id";
		}
		else if(et_id == 2) {
			tb = "tb_cont_co";
			td = "cont_id";
		}
		
		
		String sql = "select sum(se_score) from tb_stu_ed where stu_id=" + stu_id + " and ed_id in("
				+ "select ed_id from tb_eval_dtl where et_id=" + et_id + " and cont_id in("
				+ "select " + td + " from " + tb + " where co_id in("
				+ "select co_id from tb_co_ip where ip_id=" + ip_id
				+ ")"
				+ ")"
				+ ")";
		return template.query(sql, new ResultSetExtractor<Double>() {
			@Override
			public Double extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getDouble("sum(se_score)");
				} else {
					return 0.0;
				}
			}
			
		});
	}

	@Override
	public void updateStudentEvaluationDetailListSe_score(List<Integer> stu_idList, List<Integer> ed_idList,
			List<Double> se_scoreList) {
		String sql = 
				"UPDATE tb_stu_ed" +
				" SET se_score =" + 
				" CASE";
		for (int i = 0; i < stu_idList.size(); i++) {
			sql += " WHEN  stu_id = " + stu_idList.get(i) + " AND ed_id = " + ed_idList.get(i) + " THEN  " + se_scoreList.get(i);
		}
		sql += " ELSE se_score END;" ;
				
		template.update(sql);
	}

	@Override
	public List<StudentEvaluationDetailFromView> getStudentEvaluationDetailListByCs_idAndEt_id(int cs_id, int et_id) {
		List<StudentEvaluationDetailFromView> seList = new ArrayList<>();
		String sql = "select * from view_stu_ed where cs_id=" + cs_id + " and et_id=" + et_id;
		
		seList = this.template.query(sql, new RowMapper<StudentEvaluationDetailFromView>() {
			public StudentEvaluationDetailFromView mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentEvaluationDetailFromView se = new StudentEvaluationDetailFromView();
				se.setStu_id(rs.getInt("stu_id"));
				se.setStu_name(rs.getString("stu_name"));
				se.setClass_name(rs.getString("class_name"));
				se.setSe_score(rs.getDouble("se_score"));
				se.setCs_id(rs.getInt("cs_id"));
				se.setEd_id(rs.getInt("ed_id"));
				se.setEd_num(rs.getString("ed_num"));
				return se;
			}
		});
		return seList;
	}

}
