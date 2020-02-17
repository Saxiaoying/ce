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

import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.Teacher;

@Component("TeacherDaoImpl")
public class TeacherDaoImpl implements TeacherDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public Teacher getTeacherByTch_id(int tch_id) {
		return template.query("select * from tb_class where tch_id =" +tch_id, new ResultSetExtractor<Teacher>() {
			@Override
			public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Teacher c = new Teacher();
					c.setTch_id(rs.getInt("tch_id"));
					c.setTch_name(rs.getString("tch_name"));
					return c;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<Teacher> getTeacherList() {
		List<Teacher> evaluationTypeList = new ArrayList<>();
		String sql = "select * from tb_class";
		evaluationTypeList = this.template.query(sql, new RowMapper<Teacher>() {
			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
				Teacher c = new Teacher();
				c.setTch_id(rs.getInt("tch_id"));
				c.setTch_name(rs.getString("tch_name"));
				return c;
			}
		});
		return evaluationTypeList;
	}

}
