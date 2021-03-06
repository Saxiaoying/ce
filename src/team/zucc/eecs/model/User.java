//This is maintained by jyl. 
package team.zucc.eecs.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("UserBean")
public class User {
	@JSONField(ordinal = 1)
	private int user_id;
	
	@JSONField(ordinal = 2)
	private String user_name;
	
	@JSONField(ordinal = 3)
	private String user_pwd;
	
	@JSONField(ordinal = 4)
	private Timestamp user_log_t;
	
	
	@JSONField(ordinal = 5)
	private int user_typ;
	
	@JSONField(ordinal = 6)
	private String user_tel;
	
	@JSONField(ordinal = 7)
	private int tch_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public Timestamp getUser_log_t() {
		return user_log_t;
	}

	public void setUser_log_t(Timestamp user_log_t) {
		this.user_log_t = user_log_t;
	}

	public int getUser_typ() {
		return user_typ;
	}

	public void setUser_typ(int user_typ) {
		this.user_typ = user_typ;
	}
	
	public String getUser_tel() {
		return user_tel;
	}

	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	
	public int getTch_id() {
		return tch_id;
	}

	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("user_id", user_id);
		j.put("user_name", user_name);
		j.put("user_pwd", user_pwd);
		j.put("user_log_t", user_log_t);
		j.put("user_typ", user_typ);
		j.put("user_tel", user_tel);
		j.put("tch_id", tch_id);
		return j;
	}
}
