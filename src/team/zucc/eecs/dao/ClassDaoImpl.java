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

import team.zucc.eecs.model.Class;

@Component("ClassDaoImpl")
public class ClassDaoImpl implements ClassDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public Class getClassByClass_id(int class_id) {
		return template.query("select * from tb_class where class_id =" +class_id, new ResultSetExtractor<Class>() {
			@Override
			public Class extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Class c = new Class();
					c.setClass_id(rs.getInt("class_id"));
					c.setClass_name(rs.getString("class_name"));
					return c;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<Class> getClassList() {
		List<Class> evaluationTypeList = new ArrayList<>();
		String sql = "select * from tb_class";
		evaluationTypeList = this.template.query(sql, new RowMapper<Class>() {
			public Class mapRow(ResultSet rs, int rowNum) throws SQLException {
				Class c = new Class();
				c.setClass_id(rs.getInt("class_id"));
				c.setClass_name(rs.getString("class_name"));
				return c;
			}
		});
		return evaluationTypeList;
	}

}
