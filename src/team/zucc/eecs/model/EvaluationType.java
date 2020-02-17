//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationTypeBean")
public class EvaluationType {
	@JSONField(ordinal = 1)
	private int et_id; //类型表ID （外码）
	
	@JSONField(ordinal = 2)
	private String et_name; //名字
	
	public int getEt_id() {
		return et_id;
	}

	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}

	public String getEt_name() {
		return et_name;
	}

	public void setEt_name(String et_name) {
		this.et_name = et_name;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("et_id", et_id);
		j.put("et_name", et_name);
		return j;
	}
}
