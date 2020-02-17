//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseArrangementBean")
public class CourseArrangement {
	
	
	@JSONField(ordinal = 1)
	private int cag_id;//id
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private int tch_id;//教师编号（外码）
	
	@JSONField(ordinal = 4)
	private int cag_num;//课序号
	
	@JSONField(ordinal = 5)
	private String cag_name;//教学班名称
	
	public int getCag_id() {
		return cag_id;
	}

	public void setCag_id(int cag_id) {
		this.cag_id = cag_id;
	}

	public int getCs_id() {
		return cs_id;
	}

	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}

	public int getTch_id() {
		return tch_id;
	}

	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}

	public int getCag_num() {
		return cag_num;
	}

	public void setCag_num(int cag_num) {
		this.cag_num = cag_num;
	}

	public String getCag_name() {
		return cag_name;
	}

	public void setCag_name(String cag_name) {
		this.cag_name = cag_name;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		j.put("cag_id", cag_id);
		j.put("cs_id", cs_id);
		j.put("tch_id", tch_id);
		j.put("cag_num", cag_num);
		j.put("cag_name", cag_name);
		return j;
	}

}
