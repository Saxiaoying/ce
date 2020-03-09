package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.UserDao;
import team.zucc.eecs.model.User;

@Component("UserServiceImpl")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User getUserById(int user_id) {
		try {
			User user = userDao.getUserById(user_id);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public User getUserByTch_id(int tch_id) {
		try {
			User user = userDao.getUserByTch_id(tch_id);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addUser(String user_name, String user_pwd, int user_typ, String user_tel, int tch_id) {
		try {
			User user = null;
			if(tch_id != -1) {
				user = userDao.getUserByTch_id(tch_id);
			}
			if(user != null) {
				return 1; //教师编号已经存在
			}
			
			userDao.addUser(user_name, user_pwd, user_typ, user_tel, tch_id);
			user = userDao.getUserByTch_id(tch_id);
			return user.getUser_id();//成功
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
	}

	@Override
	public int updateUser(int user_id, String user_name, String user_pwd, int user_typ, String user_tel, int tch_id) {
		try {
			User user = null;
			if(tch_id != -1) {
				user = userDao.getUserByTch_id(tch_id);
			}
			if(user != null && user.getUser_id() != user_id) {
				return 1; //教师编号已经存在
			}
			
			userDao.updateUser(user_id, user_name, user_pwd, user_typ, user_tel, tch_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功
	}

	@Override
	public int deleteUser(int user_id) {
		try {
			User user = userDao.getUserById(user_id);
			if(user == null) {
				return 1; //不存在
			}
			
			userDao.deleteUser(user_id);
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //失败
		}
		return 0;//成功

	}

	@Override
	public List<User> getUserListFromAtoBByUser_nameAndUser_typ(int a, int b, String user_name, int typ1, int typ2) {
		try {
			List<User> userList = userDao.getUserListFromAtoBByUser_nameAndUser_typ(a, b, user_name, typ1, typ2);
			return userList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getUserListNumberByUser_nameAndUser_typ(String user_name, int typ1, int typ2) {
		try {
			int num = userDao.getUserListNumberByUser_nameAndUser_typ(user_name, typ1, typ2);
			return num;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
