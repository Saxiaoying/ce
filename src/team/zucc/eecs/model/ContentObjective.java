//This is maintained by jyl. 
package team.zucc.eecs.model;


import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("ContentObjectiveBean")
public class ContentObjective {
	@JSONField(ordinal = 1)
	private int cco_id;

	@JSONField(ordinal = 2)
	private int co_id; 
	
	@JSONField(ordinal = 3)
	private int cont_id; 
	
	
	public int getCco_id() {
		return cco_id;
	}


	public void setCco_id(int cco_id) {
		this.cco_id = cco_id;
	}


	public int getCo_id() {
		return co_id;
	}


	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}


	public int getCont_id() {
		return cont_id;
	}


	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("cco_id", cco_id);
		j.put("co_id", co_id);
		j.put("cont_id", cont_id);
		return j;
	}
}
