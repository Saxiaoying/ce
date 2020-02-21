package team.zucc.eecs.service;

import team.zucc.eecs.model.User;

public interface UserService {
	User getUserById(int user_id);
	User getUserByTch_id(int tch_id);
	int addUser(String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	int updateUser(int user_id, String user_name, String user_pwd, int user_typ, String user_tel, int tch_id);
	int deleteUser(int user_id);
}
