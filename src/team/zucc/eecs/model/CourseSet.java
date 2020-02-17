//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseSetBean")
public class CourseSet {
	@JSONField(ordinal = 1)
	private int cs_id;//开课流水号
	
	@JSONField(ordinal = 2)
	private String coz_id;//课程号（外码）
	
	@JSONField(ordinal = 3)
	private String cs_acad_yr;//学年
	
	@JSONField(ordinal = 4)
	private String cs_sem;//学期
	
	
	public int getCs_id() {
		return cs_id;
	}


	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}


	public String getCoz_id() {
		return coz_id;
	}


	public void setCoz_id(String coz_id) {
		this.coz_id = coz_id;
	}


	public String getCs_acad_yr() {
		return cs_acad_yr;
	}


	public void setCs_acad_yr(String cs_acad_yr) {
		this.cs_acad_yr = cs_acad_yr;
	}


	public String getCs_sem() {
		return cs_sem;
	}


	public void setCs_sem(String cs_sem) {
		this.cs_sem = cs_sem;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("cs_id", cs_id);
		j.put("coz_id", coz_id);
		j.put("cs_acad_yr", cs_acad_yr);
		j.put("cs_sem", cs_sem);
		return j;
	}

}
