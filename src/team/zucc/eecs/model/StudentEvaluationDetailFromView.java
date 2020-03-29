package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;


@Component("StudentEvaluationDetailFromViewBean")
public class StudentEvaluationDetailFromView {
	@JSONField(ordinal = 1)
	private int cs_id;
	
	@JSONField(ordinal = 2)
	private int et_id;
	
	@JSONField(ordinal = 3)
	private int ed_id;
	
	@JSONField(ordinal = 4)
	private String ed_num;
	
	@JSONField(ordinal = 5)
	private Double se_score;
	
	@JSONField(ordinal = 6)
	private int stu_id;
	
	@JSONField(ordinal = 7)
	private String stu_name;
	
	@JSONField(ordinal = 8)
	private String class_name;
	
	

	public int getCs_id() {
		return cs_id;
	}



	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}



	public int getEt_id() {
		return et_id;
	}



	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}



	public int getEd_id() {
		return ed_id;
	}



	public void setEd_id(int ed_id) {
		this.ed_id = ed_id;
	}



	public String getEd_num() {
		return ed_num;
	}



	public void setEd_num(String ed_num) {
		this.ed_num = ed_num;
	}



	public Double getSe_score() {
		return se_score;
	}



	public void setSe_score(Double se_score) {
		this.se_score = se_score;
	}



	public int getStu_id() {
		return stu_id;
	}



	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}



	public String getStu_name() {
		return stu_name;
	}



	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	
	public String getClass_name() {
		return class_name;
	}



	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}



	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		j.put("cs_id", cs_id);
		j.put("et_id", et_id);
		j.put("ed_id", ed_id);
		j.put("ed_num", ed_num);
		j.put("se_score", se_score);
		j.put("stu_id", stu_id);
		j.put("stu_name", stu_name);
		j.put("class_name", class_name);
		return j;
	}

}
