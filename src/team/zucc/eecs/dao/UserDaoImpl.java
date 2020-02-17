//This is maintained by jyl. 
package team.zucc.eecs.dao;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
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
					return u;
				} else {
					return null;
				}
			}
			
		});
	}


}
