//This is maintained by jyl. 
package team.zucc.eecs.dao;

//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.User;

public interface UserDao {
	User getUserById(int user_id);
}
