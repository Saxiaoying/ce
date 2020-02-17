//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("TeacherBean")
public class Teacher {
	
	@JSONField(ordinal = 1)
	private int tch_id;
	
	@JSONField(ordinal = 2)
	private String tch_name;
	
	public int getTch_id() {
		return tch_id;
	}

	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}

	public String getTch_name() {
		return tch_name;
	}

	public void setTch_name(String tch_name) {
		this.tch_name = tch_name;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("tch_id", tch_id);
		j.put("tch_name", tch_name);
		return j;
	}

}

