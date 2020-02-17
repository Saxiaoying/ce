//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseBean")
public class Course {
	
	@JSONField(ordinal = 1)
	private String coz_id;//ID，即课程号
	
	@JSONField(ordinal = 2)
	private String coz_name_ch;//课程名称-中文
	
	@JSONField(ordinal = 3)
	private String coz_name_eng;//课程名称-英文
	
	@JSONField(ordinal = 4)
	private String coz_nature;//课程性质
	
	@JSONField(ordinal = 5)
	private Double coz_credit;//学分
	
	@JSONField(ordinal = 6)
	private String coz_hrs_wk;//周学时

	@JSONField(ordinal = 7)
	private Double coz_hours;//总学时
	
	
	public String getCoz_id() {
		return coz_id;
	}


	public void setCoz_id(String coz_id) {
		this.coz_id = coz_id;
	}


	public String getCoz_name_ch() {
		return coz_name_ch;
	}


	public void setCoz_name_ch(String coz_name_ch) {
		this.coz_name_ch = coz_name_ch;
	}


	public String getCoz_name_eng() {
		return coz_name_eng;
	}


	public void setCoz_name_eng(String coz_name_eng) {
		this.coz_name_eng = coz_name_eng;
	}


	public String getCoz_nature() {
		return coz_nature;
	}


	public void setCoz_nature(String coz_nature) {
		this.coz_nature = coz_nature;
	}


	public Double getCoz_credit() {
		return coz_credit;
	}


	public void setCoz_credit(Double coz_credit) {
		this.coz_credit = coz_credit;
	}


	public String getCoz_hrs_wk() {
		return coz_hrs_wk;
	}


	public void setCoz_hrs_wk(String coz_hrs_wk) {
		this.coz_hrs_wk = coz_hrs_wk;
	}


	public Double getCoz_hours() {
		return coz_hours;
	}


	public void setCoz_hours(Double coz_hours) {
		this.coz_hours = coz_hours;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("coz_id", coz_id);
		j.put("coz_name_ch", coz_name_ch);
		j.put("coz_name_eng", coz_name_eng);
		j.put("coz_nature", coz_nature);
		j.put("coz_credit", coz_credit);
		j.put("coz_hrs_wk", coz_hrs_wk);
		j.put("coz_hours", coz_hours);
		return j;
	}
}

