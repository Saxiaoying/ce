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

import team.zucc.eecs.model.StudentCourseArrangement;

@Component("StudentCourseArrangementDaoImpl")
public class StudentCourseArrangementDaoImpl implements StudentCourseArrangementDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public StudentCourseArrangement getStudentCourseArrangementBySca_id(int sca_id) {
		return template.query("select * from tb_stu_cag where sca_id =" +sca_id, new ResultSetExtractor<StudentCourseArrangement>() {
			@Override
			public StudentCourseArrangement extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					StudentCourseArrangement sca = new StudentCourseArrangement();
					sca.setCag_id(rs.getInt("cag_id"));
					sca.setSca_id(rs.getInt("sca_id"));
					sca.setStu_id(rs.getInt("stu_id"));
					return sca;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public StudentCourseArrangement getStudentCourseArrangementByStu_idAndCag_id(int stu_id, int cag_id) {
		return template.query("select * from tb_stu_cag where stu_id =" +stu_id + " and cag_id=" + cag_id, new ResultSetExtractor<StudentCourseArrangement>() {
			@Override
			public StudentCourseArrangement extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					StudentCourseArrangement sca = new StudentCourseArrangement();
					sca.setCag_id(rs.getInt("cag_id"));
					sca.setSca_id(rs.getInt("sca_id"));
					sca.setStu_id(rs.getInt("stu_id"));
					return sca;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<StudentCourseArrangement> getStudentCourseArrangementListByCag_id(int cag_id) {
		List<StudentCourseArrangement> courseList = new ArrayList<>();
		String sql = "select * from tb_stu_cag where cag_id=" + cag_id;
		courseList = this.template.query(sql, new RowMapper<StudentCourseArrangement>() {
			public StudentCourseArrangement mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentCourseArrangement sca = new StudentCourseArrangement();
				sca.setCag_id(rs.getInt("cag_id"));
				sca.setSca_id(rs.getInt("sca_id"));
				sca.setStu_id(rs.getInt("stu_id"));
				return sca;
			}
		});
		return courseList;
	}

	@Override
	public void addStudentCourseArrangement(int stu_id, int cag_id) {
		template.update("insert into tb_stu_cag (stu_id, cag_id)  "
				+ "values (?, ?)", stu_id, cag_id);
	}

	@Override
	public void deleteStudentCourseArrangement(int sca_id) {
		template.update("delete from tb_stu_cag where sca_id = " + sca_id);
	}

	@Override
	public void deleteStudentCourseArrangementByCag_id(int cag_id) {
		template.update("delete from tb_stu_cag where cag_id = " + cag_id);
	}

}
