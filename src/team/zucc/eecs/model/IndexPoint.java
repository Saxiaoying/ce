package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("IndexPointBean")
public class IndexPoint {
	@JSONField(ordinal = 1)
	private int ip_id; //指标点ID
	
	@JSONField(ordinal = 2)
	private int gr_id; //毕业要求ID（外码）
	
	@JSONField(ordinal = 3)
	private int ip_code; //指标点编码
	
	@JSONField(ordinal = 4)
	private String ip_content; //指标点内容

	public int getIp_id() {
		return ip_id;
	}

	public void setIp_id(int ip_id) {
		this.ip_id = ip_id;
	}

	public int getGr_id() {
		return gr_id;
	}

	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}

	public int getIp_code() {
		return ip_code;
	}

	public void setIp_code(int ip_code) {
		this.ip_code = ip_code;
	}

	public String getIp_content() {
		return ip_content;
	}

	public void setIp_content(String ip_content) {
		this.ip_content = ip_content;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("ip_id", ip_id);
		j.put("gr_id", gr_id);
		j.put("ip_code", ip_code);
		j.put("ip_content", ip_content);
		return j;
	}
}