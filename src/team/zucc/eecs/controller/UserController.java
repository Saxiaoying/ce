package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.service.TeacherService;
import team.zucc.eecs.service.UserService;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.User;

@Controller("UserController")
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value= {"/checkLogin"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject checkLogin(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-checkLogin");
			String user_name = (String) request.getSession().getAttribute("USER_NAME");
			if(user_name == null) {
				obj.put("state", "NULL");
				return obj;
			} else {
				User user = userService.getUserById((Integer)request.getSession().getAttribute("USER_ID"));
				int user_typ = user.getUser_typ();
				obj.put("user_typ",user_typ);
				obj.put("user_name",user.getUser_name());
				obj.put("state", "OK");
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@RequestMapping(value= {"/userLogout"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject userLogout( HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserLoginController-userLogout");
		JSONObject obj = new JSONObject();
		try {
			request.getSession().setAttribute("USER_ID", null);
			request.getSession().setAttribute("USER_NAME", null);
			request.getSession().setAttribute("USER_PWD", null);
			request.getSession().setAttribute("USER_LOG_T", null);
			request.getSession().setAttribute("USER_TYP", null);
			request.getSession().setAttribute("USER_TEL", null);
			request.getSession().setAttribute("TCH_ID", null);
		} catch (Exception e) {
			obj.put("state", "ERROR");
			e.printStackTrace();
		}
		obj.put("state", "OK");
		return obj;
	}
	
	@RequestMapping(value= {"/userLogin"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject userLogin(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserController-userLogin");
		JSONObject obj = new JSONObject();
		try {
			
			int user_id = 0;
			try {
				user_id = in.getIntValue("user_id");
			} catch (Exception e) {
				obj.put("state", "账号错误！");
				return obj;
			}
			String user_pwd = in.getString("user_pwd");
			
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "用户不存在！");
				return obj;
			}
			
			if(user.getUser_pwd().compareTo(user_pwd) == 0) {
				request.getSession().setAttribute("USER_ID", user.getUser_id());
				request.getSession().setAttribute("USER_NAME", user.getUser_name());
				request.getSession().setAttribute("USER_PWD", user.getUser_pwd());
				request.getSession().setAttribute("USER_LOG_T", user.getUser_log_t());
				request.getSession().setAttribute("USER_TYP", user.getUser_typ());
				request.getSession().setAttribute("USER_TEL", user.getUser_tel());
				request.getSession().setAttribute("TCH_ID", user.getTch_id());
				obj.put("user_typ", user.getUser_typ());
			} else {
				obj.put("state", "密码错误！");
				return obj;
			}
		} catch (Exception e) {
			obj.put("state", "数据库错误！");
			e.printStackTrace();
			return obj;
		}
		obj.put("state", "OK");
		return obj;
	}
	
	@RequestMapping(value= {"/userReg"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject userReg(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserController-userReg");
		JSONObject obj = new JSONObject();
		try {
			String user_name = in.getString("user_name");
			user_name = user_name.replaceAll("\\s", "");
			if(user_name.isEmpty()) {
				obj.put("state", "用户名不能为空，请重新输入！");
				return obj;
			}
			if(user_name.length() > 10) {
				obj.put("state", "用户名不能超过十个字符！");
				return obj;
			}
			String user_pwd = in.getString("user_pwd");
			String user_pwd_t = in.getString("user_pwd_t");
			if(user_pwd.compareTo(user_pwd_t) != 0) {
				obj.put("state", "两次密码不一致，请重新输入！");
				return obj;
			}
			
			if(user_pwd.length() == 0) {
				obj.put("state", "密码不能为空，请重新输入！");
				return obj;
			}
			
			if(user_pwd.length() > 20) {
				obj.put("state", "密码不能超过二十个字符！");
				return obj;
			}
			
			String check_num = in.getString("check_num");
			int user_typ = -1;
			if(check_num.compareTo("admin") == 0) {
				user_typ = 0;
			} else if(check_num.compareTo("user") == 0) {
				user_typ = 1;
			} else {
				obj.put("state", "注册码不正确！");
				return obj;
			}
			
			String user_tel = in.getString("user_tel");
			if (user_tel.length() != 11) {
				obj.put("state", "手机号格式不正确，请输入11位的手机号！");
				return obj;
			}
			for (int i = 0; i < user_tel.length(); i++) {
				char c=user_tel.charAt(i);
				try {
					int k = Integer.valueOf(c);
					if(k<0) {
						obj.put("state", "手机号格式不正确，请输入11位的手机号！");
						return obj;
					}
				} catch (Exception e) {
					obj.put("state", "手机号格式不正确，请输入11位的手机号！");
					return obj;
				}
			}
			
			int tch_id = -1;
			try {
				tch_id = in.getIntValue("tch_id");
			} catch (Exception e) {
				obj.put("state", "教师编号不正确！");
				return obj;
			}
			Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
			if(teacher == null) {
				obj.put("state", "教师编号不存在！");
				return obj;
			}
			
			int f = userService.addUser(user_name, user_pwd, user_typ, user_tel, tch_id);
			if(f == 1) {
				obj.put("state", "该教师编号已经被绑定！");
				return obj;
			} else if (f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("user_id", f);
				obj.put("state", "OK");
			}
		} catch (Exception e) {
			obj.put("state", "数据库错误！");
			e.printStackTrace();
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/getUserList"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getUserList(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserController-getUserList");
		JSONObject obj = new JSONObject();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
			
			String user_name = in.getString("user_name");
			user_name = user_name.replaceAll("\\s", "");
			
			String user_typString = in.getString("user_typ");
			int typ1 = -1, typ2 = -1;
			if(user_typString.compareTo("用户类别（所有）") == 0) {
				typ1 = 0; typ2 = 1;
			} else if(user_typString.compareTo("管理员") == 0) {
				typ1  = typ2 = 0;
			} else {
				typ1  = typ2 = 1;
			}
			
			List<User> userList = userService.getUserListFromAtoBByUser_nameAndUser_typ(a, b, user_name, typ1, typ2);
			int total = userService.getUserListNumberByUser_nameAndUser_typ(user_name, typ1, typ2);
			
			List<String> tch_nameList = new ArrayList<String>();
 			for(User user : userList) {
 				int tch_id = user.getTch_id();
 				Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
 				if(teacher == null) {
 					tch_nameList.add("数据缺失");
 				} else {
 					tch_nameList.add(teacher.getTch_name());
 				}
 			}
			obj.put("userList", userList);
			obj.put("tch_nameList", tch_nameList);
			obj.put("total", total);
			obj.put("state", "OK");
		} catch (Exception e) {
			obj.put("state", "数据库错误！");
			e.printStackTrace();
			return obj;
		}
		return obj;
	}
	
	
	
	@RequestMapping(value= {"/updateUserInf"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject updateUserInf(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-updateUserInf");
			int user_id = (Integer) request.getSession().getAttribute("USER_ID");
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			String user_name = in.getString("user_name");
			user_name = user_name.replaceAll("\\s", "");
			if(user_name.isEmpty()) {
				obj.put("state", "用户名不能为空，请重新输入！");
				return obj;
			}
			if(user_name.length() > 10) {
				obj.put("state", "用户名不能超过十个字符！");
				return obj;
			}
			int tch_id = -1;
			try {
				tch_id = in.getIntValue("tch_id");
			} catch (Exception e) {
				obj.put("state", "教师编号不正确！");
				return obj;
			}
			Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
			if(teacher == null) {
				obj.put("state", "教师编号不存在！");
				return obj;
			}

			String user_tel = in.getString("user_tel");
			if (user_tel.length() != 11) {
				obj.put("state", "手机号格式不正确，请输入11位的手机号！");
				return obj;
			}
			for (int i = 0; i < user_tel.length(); i++) {
				char c=user_tel.charAt(i);
				try {
					int k = Integer.valueOf(c);
					if(k<0) {
						obj.put("state", "手机号格式不正确，请输入11位的手机号！");
						return obj;
					}
				} catch (Exception e) {
					obj.put("state", "手机号格式不正确，请输入11位的手机号！");
					return obj;
				}
			}
			int f = userService.updateUser(user_id, user_name, user.getUser_pwd(), user.getUser_typ(), user_tel, tch_id);
			if(f == 1) {
				obj.put("state", "该教师编号已经被绑定！");
				return obj;
			} else if (f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("user", user);
				obj.put("state", "OK");
				
				
				request.getSession().setAttribute("USER_NAME", user.getUser_name());
				request.getSession().setAttribute("USER_TEL", user.getUser_tel());
				request.getSession().setAttribute("TCH_ID", user.getTch_id());
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@RequestMapping(value= {"/getUserInf"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getUserInf(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-getUserInf");
			int user_id = (Integer) request.getSession().getAttribute("USER_ID");
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			obj.put("user", user);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/pwdChange"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject pwdChange(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-pwdChange");
			int user_id = (Integer) request.getSession().getAttribute("USER_ID");
			
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			String user_pwd_old = in.getString("user_pwd_old");
			String user_pwd_new = in.getString("user_pwd_new");
			String user_pwd_new_t = in.getString("user_pwd_new_t");
			
			if(user_pwd_old.compareTo(user.getUser_pwd()) != 0) {
				obj.put("state", "原密码错误，请重新输入！");
				return obj;
			}
			if(user_pwd_old.length() == 0) {
				obj.put("state", "原密码不能为空，请重新输入！");
				return obj;
			}
			if(user_pwd_new.compareTo(user_pwd_new_t) != 0) {
				obj.put("state", "两次密码不一致，请重新输入！");
				return obj;
			}
			if(user_pwd_new.length() == 0) {
				obj.put("state", "新密码不能为空，请重新输入！");
				return obj;
			}
			if(user_pwd_new.length() > 20) {
				obj.put("state", "密码不能超过二十个字符！");
				return obj;
			}
			int f = userService.updateUser(user_id, user.getUser_name(), user_pwd_new, user.getUser_typ(), user.getUser_tel(), user.getTch_id());
			if(f == 1) {
				obj.put("state", "该教师编号已经被绑定！");
				return obj;
			} else if (f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				request.getSession().setAttribute("USER_PWD", user_pwd_new);
				obj.put("state", "OK");
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/getUser"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getUser(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-getUser");
			int user_id = in.getIntValue("user_id");
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			obj.put("user", user);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@RequestMapping(value= {"/changeUserTyp"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject changeUserTyp(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-changeUserTyp");
			int user_id = (Integer) request.getSession().getAttribute("USER_ID");
			int user_id_change = in.getIntValue("user_id_change");
			String user_pwd = in.getString("user_pwd");
			int user_typ_change = in.getIntValue("user_typ_change");
			
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			if(user_id == user_id_change) {
				obj.put("state", "不能修改自己的用户类型！");
				return obj;
			} 
			if(user_pwd.compareTo(user.getUser_pwd()) != 0) {
				obj.put("state", "密码错误，请重新输入！");
				return obj;
			}
			if(user_pwd.length() == 0) {
				obj.put("state", "密码不能为空，请重新输入！");
				return obj;
			}
			
			User user_change = userService.getUserById(user_id_change);
			if(user_change == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			
			int f = userService.updateUser(user_id_change, user_change.getUser_name(), user_change.getUser_pwd(), 
					user_typ_change, user_change.getUser_tel(), user_change.getTch_id());
			if (f== -1) {
				obj.put("state", "数据库错误！");
				return obj;
			} else {
				obj.put("state", "OK");
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	@RequestMapping(value= {"/checkUserPwd"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject checkUserPwd(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		try {
			System.out.println("进入UserController-checkUserPwd");
			int user_id = (Integer) request.getSession().getAttribute("USER_ID");
			int user_id_check = in.getIntValue("user_id_check");
			String user_pwd = in.getString("user_pwd");
			
			User user = userService.getUserById(user_id);
			if(user == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			
			if(user_pwd.compareTo(user.getUser_pwd()) != 0) {
				obj.put("state", "密码错误，请重新输入！");
				return obj;
			}
			if(user_pwd.length() == 0) {
				obj.put("state", "密码不能为空，请重新输入！");
				return obj;
			}
			
			
			User user_check = userService.getUserById(user_id_check);
			if(user_check == null) {
				obj.put("state", "数据库错误：用户信息缺失！");
				return obj;
			} 
			
			obj.put("user_pwd_check", user_check.getUser_pwd());
			obj.put("state", "OK");
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
