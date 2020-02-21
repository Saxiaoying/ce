//This is maintained by jyl. 
package team.zucc.eecs.dao;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.User;

public interface UserDao {
	User getUserById(int user_id);
	User getUserByTch_id(int tch_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addUser(String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void updateUser(int user_id, String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deleteUser(int user_id);
}
