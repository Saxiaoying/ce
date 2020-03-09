//This is maintained by jyl. 
package team.zucc.eecs.dao;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.User;


@Component("UserDaoImpl")
public class UserDaoImpl implements UserDao {
	@Autowired
	JdbcTemplate template;
	

	@Override
	public User getUserById(int user_id) {
		return template.query("select * from `tb_user` where `user_id`=" + user_id, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User u = new User();
					u.setUser_id(rs.getInt("user_id"));
					u.setUser_name(rs.getString("user_name"));
					u.setUser_pwd(rs.getString("user_pwd"));
					u.setUser_log_t(rs.getTimestamp("user_log_t"));
					u.setUser_typ(rs.getInt("user_typ"));
					u.setUser_tel(rs.getString("user_tel"));
					u.setTch_id(rs.getInt("tch_id"));
					return u;
				} else {
					return null;
				}
			}
			
		});
	}
	@Override
	public User getUserByTch_id(int tch_id) {
		return template.query("select * from `tb_user` where `tch_id`=" + tch_id, new ResultSetExtractor<User>() {
			@Override
			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					User u = new User();
					u.setUser_id(rs.getInt("user_id"));
					u.setUser_name(rs.getString("user_name"));
					u.setUser_pwd(rs.getString("user_pwd"));
					u.setUser_log_t(rs.getTimestamp("user_log_t"));
					u.setUser_typ(rs.getInt("user_typ"));
					u.setUser_tel(rs.getString("user_tel"));
					u.setTch_id(rs.getInt("tch_id"));
					return u;
				} else {
					return null;
				}
			}
			
		});
	}

	@Override
	public void addUser(String user_name, String user_pwd, int user_typ, String user_tel, int tch_id) {
		Timestamp user_log_t = new Timestamp(System.currentTimeMillis());
		template.update("insert into tb_user (user_name, user_pwd, user_log_t, user_typ, user_tel, tch_id)  "
				+ "values (?, ?, ?, ?, ?, ?)", user_name, user_pwd, user_log_t, user_typ, user_tel, tch_id);
	}


	@Override
	public void updateUser(int user_id, String user_name, String user_pwd, int user_typ, String user_tel, int tch_id) {
		template.update("update tb_user set user_name = ?, user_pwd = ?, user_typ=?, user_tel=?,tch_id=? where user_id = ?", 
				user_name, user_pwd, user_typ, user_tel, tch_id, user_id);
	}


	@Override
	public void deleteUser(int user_id) {
		template.update("delete from tb_user where user_id = " + user_id);
	}
	
	
	@Override
	public List<User> getUserListFromAtoBByUser_nameAndUser_typ(int a, int b, String user_name, int typ1, int typ2) {
		List<User> userList = new ArrayList<>();
		int num = b-a;
		
		String sql = "select * from tb_user where user_name like '%" + user_name + "%' ";
		if(typ1 == typ2) sql += " and user_typ=" + typ1;
		sql += " limit "  + a + ", " + num;

		userList = this.template.query(sql, new RowMapper<User>() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User u = new User();
				u.setUser_id(rs.getInt("user_id"));
				u.setUser_name(rs.getString("user_name"));
				u.setUser_pwd(rs.getString("user_pwd"));
				u.setUser_log_t(rs.getTimestamp("user_log_t"));
				u.setUser_typ(rs.getInt("user_typ"));
				u.setUser_tel(rs.getString("user_tel"));
				u.setTch_id(rs.getInt("tch_id"));
				return u;
			}
		});
		return userList;
	}
	@Override
	public int getUserListNumberByUser_nameAndUser_typ(String user_name, int typ1, int typ2) {
		String sql = "select count(*) from tb_user where user_name like '%" + user_name + "%' ";
		if(typ1 == typ2) sql += " and user_typ=" + typ1;

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
}
