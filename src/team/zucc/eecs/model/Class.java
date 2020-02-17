//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("ClassBean")
public class Class {
	
	@JSONField(ordinal = 1)
	private int class_id;
	
	@JSONField(ordinal = 2)
	private String class_name;
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}

	public String getClass_name() {
		return class_name;
	}

	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("class_id", class_id);
		j.put("class_name", class_name);
		return j;
	}

}

