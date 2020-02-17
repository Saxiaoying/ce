//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("PracticeObjectiveBean")
public class PracticeObjective {
	@JSONField(ordinal = 1)
	private int pc_id;

	@JSONField(ordinal = 2)
	private int co_id; 
	
	@JSONField(ordinal = 3)
	private int pra_id;
	
	
	public int getPc_id() {
		return pc_id;
	}


	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}


	public int getCo_id() {
		return co_id;
	}


	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}


	public int getPra_id() {
		return pra_id;
	}


	public void setPra_id(int pra_id) {
		this.pra_id = pra_id;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("pc_id", pc_id);
		j.put("co_id", co_id);
		j.put("pra_id", pra_id);
		return j;
	}

}
