package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("GraduationRequireBean")
public class GraduationRequire {
	@JSONField(ordinal = 1)
	private int gr_id; //毕业要求ID
	
	@JSONField(ordinal = 2)
	private int gr_code; //毕业要求编码
	
	@JSONField(ordinal = 3)
	private String gr_title; //毕业要求标题
	
	@JSONField(ordinal = 4)
	private String gr_content; //毕业要求内容

	public int getGr_id() {
		return gr_id;
	}

	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}

	public int getGr_code() {
		return gr_code;
	}

	public void setGr_code(int gr_code) {
		this.gr_code = gr_code;
	}

	public String getGr_title() {
		return gr_title;
	}

	public void setGr_title(String gr_title) {
		this.gr_title = gr_title;
	}

	public String getGr_content() {
		return gr_content;
	}

	public void setGr_content(String gr_content) {
		this.gr_content = gr_content;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("gr_id", gr_id);
		j.put("gr_code", gr_code);
		j.put("gr_title", gr_title);
		j.put("gr_content", gr_content);
		return j;
	}
}