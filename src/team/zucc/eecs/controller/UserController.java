package team.zucc.eecs.controller;

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
		System.out.println("进入UserController-checkLogin");
		JSONObject obj = new JSONObject();
		String user_name = (String) request.getSession().getAttribute("USER_NAME");
		if(user_name == null) {
			obj.put("state", "NULL");
			return obj;
		} else {
			int user_typ = (Integer) request.getSession().getAttribute("USER_TYP");
			obj.put("user_typ",user_typ);
			obj.put("user_name",user_name);
			obj.put("state", "OK");
			return obj;
		}
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
			
			String user_pwd = in.getString("user_pwd");
			String user_pwd_t = in.getString("user_pwd_t");
			if(user_pwd.compareTo(user_pwd_t) != 0) {
				obj.put("state", "两次密码不一致，请重新输入！");
				return obj;
			}
			
			String check_num = in.getString("check_num");
			int user_typ = -1;
			if(check_num.compareTo("147258") == 0) {
				user_typ = 0;
			} else if(check_num.compareTo("258369") == 0) {
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
	
	@RequestMapping(value= {"/userUpdate"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject userUpdate(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserController-userUpdate");
		JSONObject obj = new JSONObject();
		try {
			String user_name = in.getString("user_name");
			user_name = user_name.replaceAll("\\s", "");
			if(user_name.isEmpty()) {
				obj.put("state", "用户名不能为空，请重新输入！");
				return obj;
			}
			
			String user_pwd = in.getString("user_pwd");
			String user_pwd_t = in.getString("user_pwd_t");
			if(user_pwd.compareTo(user_pwd_t) != 0) {
				obj.put("state", "两次密码不一致，请重新输入！");
				return obj;
			}
			
			String check_num = in.getString("check_num");
			int user_typ = -1;
			if(check_num.compareTo("147258") == 0) {
				user_typ = 0;
			} else if(check_num.compareTo("258369") == 0) {
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
				obj.put("state", "OK");
			}
		} catch (Exception e) {
			obj.put("state", "数据库错误！");
			e.printStackTrace();
			return obj;
		}
		return obj;
	}
	
}
