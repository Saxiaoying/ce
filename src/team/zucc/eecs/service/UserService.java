package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.User;

public interface UserService {
	User getUserById(int user_id);
	User getUserByTch_id(int tch_id);
	int addUser(String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	int updateUser(int user_id, String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	int deleteUser(int user_id);
	
	List<User> getUserListFromAtoBByUser_nameAndUser_typ(int a, int b, String user_name, int typ1, int typ2);
	int getUserListNumberByUser_nameAndUser_typ(String user_name, int typ1, int typ2);
}
