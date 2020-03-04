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

import team.zucc.eecs.model.Student;

@Component("StudentDaoImpl")
public class StudentDaoImpl implements StudentDao {
	@Autowired
	JdbcTemplate template;
	
	@Override
	public Student getStudentByStu_id(int stu_id) {
		return template.query("select * from tb_stu where stu_id =" +stu_id, new ResultSetExtractor<Student>() {
			@Override
			public Student extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Student st = new Student();
					st.setStu_id(rs.getInt("stu_id"));
					st.setStu_name(rs.getString("stu_name"));
					st.setClass_id(rs.getInt("class_id"));
					return st;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public List<Student> getStudentListByClass_id(int class_id) {
		List<Student> studentList = new ArrayList<>();
		String sql = "select * from tb_stu where class_id="+ class_id;
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

	@Override
	public List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<>();
		String sql = "select * from tb_stu";
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

	@Override
	public int getStudentNumberByInf(String stu_name, String class_name) {
		String sql = "select count(*) from tb_stu where stu_name like '%" + stu_name + "%' and class_id in"
				+ "(select class_id from tb_class where class_name like '%" + class_name + "%')";
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
	public List<Student> getStudentListByInfFromAtoB(int a, int b, String stu_name, String class_name) {
		List<Student> studentList = new ArrayList<>();
		int num = b-a;
		String sql = "select * from tb_stu where stu_name like '%" + stu_name + "%' and class_id in"
				+ "(select class_id from tb_class where class_name like '%" + class_name + "%') limit "  + a + ", " + num;
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

	@Override
	public int getStudentNumberByCag_id(int cag_id, String stu_name, String class_name) {
		String sql = "select count(*) from tb_stu where stu_name like '%" + stu_name + "%' "
				+ "and stu_id in (select stu_id from tb_stu_cag where cag_id=" + cag_id + ")"
				+ "and class_id in (select class_id from tb_class where class_name like '%" + class_name + "%')";
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
	public List<Student> getStudentListByCag_idFromAtoB(int a, int b, int cag_id, String stu_name, String class_name) {
		String sql = "select * from tb_stu where stu_name like '%" + stu_name + "%' "
				+ "and stu_id in (select stu_id from tb_stu_cag where cag_id=" + cag_id + ")"
				+ "and class_id in (select class_id from tb_class where class_name like '%" + class_name + "%')";
		int num = b-a;
		sql +=  " limit "  + a + ", " + num;
		List<Student> studentList = new ArrayList<>();
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

	@Override
	public int getStudentNumberByNotCag_id(int cag_id, String stu_name, String class_name) {
		String sql = "select count(*) from tb_stu where stu_name like '%" + stu_name + "%' "
				+ "and stu_id not in (select stu_id from tb_stu_cag where cag_id=" + cag_id + ")"
				+ "and class_id in (select class_id from tb_class where class_name like '%" + class_name + "%')";
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
	public List<Student> getStudentListByNotCag_idFromAtoB(int a, int b, int cag_id, String stu_name, String class_name) {
		String sql = "select * from tb_stu where stu_name like '%" + stu_name + "%' "
				+ "and stu_id not in (select stu_id from tb_stu_cag where cag_id=" + cag_id + ")"
				+ "and class_id in (select class_id from tb_class where class_name like '%" + class_name + "%')";
		int num = b-a;
		sql +=  " limit "  + a + ", " + num;
		List<Student> studentList = new ArrayList<>();
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

	@Override
	public List<Student> getStudentListByCag_id(int cag_id) {
		String sql = "select * from tb_stu where stu_id in (select stu_id from tb_stu_cag where cag_id=" + cag_id + ")";
		List<Student> studentList = new ArrayList<>();
		studentList = this.template.query(sql, new RowMapper<Student>() {
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student st = new Student();
				st.setStu_id(rs.getInt("stu_id"));
				st.setStu_name(rs.getString("stu_name"));
				st.setClass_id(rs.getInt("class_id"));
				return st;
			}
		});
		return studentList;
	}

}
