//This is maintained by jyl. 
package team.zucc.eecs.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseAppendixBean")
public class CourseAppendix {
	
	@JSONField(ordinal = 1)
	private int ca_id;//ID
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private String ca_typ;//文件类别（0：课程简介；1：课程进度；2：实验卡...）
	
	@JSONField(ordinal = 4)
	private String ca_url;//下载链接
	
	@JSONField(ordinal = 5)
	private String ca_name;//文件显示名字
	
	@JSONField(ordinal = 6)
	private Timestamp ca_time; //更新时间
	
	
	public int getCa_id() {
		return ca_id;
	}


	public void setCa_id(int ca_id) {
		this.ca_id = ca_id;
	}


	public int getCs_id() {
		return cs_id;
	}


	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}


	public String getCa_typ() {
		return ca_typ;
	}


	public void setCa_typ(String ca_typ) {
		this.ca_typ = ca_typ;
	}


	public String getCa_url() {
		return ca_url;
	}


	public void setCa_url(String ca_url) {
		this.ca_url = ca_url;
	}


	public String getCa_name() {
		return ca_name;
	}


	public void setCa_name(String ca_name) {
		this.ca_name = ca_name;
	}


	public Timestamp getCa_time() {
		return ca_time;
	}


	public void setCa_time(Timestamp ca_time) {
		this.ca_time = ca_time;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("ca_id", ca_id);
		j.put("cs_id", cs_id);
		j.put("ca_typ", ca_typ);
		j.put("ca_url", ca_url);
		j.put("ca_name", ca_name);
		j.put("ca_time", ca_time);
		return j;
	}

}

