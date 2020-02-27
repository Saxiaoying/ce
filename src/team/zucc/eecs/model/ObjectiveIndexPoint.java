package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("ObjectiveIndexPointBean")
public class ObjectiveIndexPoint {
	@JSONField(ordinal = 1)
	private int coi_id; //ID
	
	@JSONField(ordinal = 2)
	private int co_id; //课程目标ID
	
	@JSONField(ordinal = 3)
	private int ip_id; //指标点ID
	
	@JSONField(ordinal = 4)
	private String coi_lev; //关系等级
	
	public int getCoi_id() {
		return coi_id;
	}

	public void setCoi_id(int coi_id) {
		this.coi_id = coi_id;
	}

	public int getCo_id() {
		return co_id;
	}

	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}

	public int getIp_id() {
		return ip_id;
	}

	public void setIp_id(int ip_id) {
		this.ip_id = ip_id;
	}

	public String getCoi_lev() {
		return coi_lev;
	}

	public void setCoi_lev(String coi_lev) {
		this.coi_lev = coi_lev;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("coi_id", coi_id);
		j.put("co_id", co_id);
		j.put("ip_id", ip_id);
		j.put("coi_lev", coi_lev);
		return j;
	}

}
