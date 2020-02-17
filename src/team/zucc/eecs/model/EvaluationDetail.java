//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationDetailBean")
public class EvaluationDetail {
	
	@JSONField(ordinal = 1)
	private int ed_id; // ID
	
	@JSONField(ordinal = 2)
	private int cont_id; //教学内容ID（外码）
	
	@JSONField(ordinal = 3)
	private int cs_id; //开课流水号（外码）
	
	@JSONField(ordinal = 4)
	private int et_id; //类型表ID （外码）
	
	@JSONField(ordinal = 5)
	private String ed_num; //题号或者实验编码 1-1
	
	@JSONField(ordinal = 6)
	private double ed_points; //设定的分数
	
	@JSONField(ordinal = 7)
	private double ed_score; //得分 
	
	@JSONField(ordinal = 8)
	private double ed_sc_rt; //得分率
	
	

	public int getEd_id() {
		return ed_id;
	}



	public void setEd_id(int ed_id) {
		this.ed_id = ed_id;
	}

 
	public int getCont_id() {
		return cont_id;
	}



	public void setCont_id(int cont_id) {
		this.cont_id = cont_id;
	}



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



	public String getEd_num() {
		return ed_num;
	}



	public void setEd_num(String ed_num) {
		this.ed_num = ed_num;
	}



	public double getEd_points() {
		return ed_points;
	}



	public void setEd_points(double ed_points) {
		this.ed_points = ed_points;
	}



	public double getEd_score() {
		return ed_score;
	}



	public void setEd_score(double ed_score) {
		this.ed_score = ed_score;
	}



	public double getEd_sc_rt() {
		return ed_sc_rt;
	}



	public void setEd_sc_rt(double ed_sc_rt) {
		this.ed_sc_rt = ed_sc_rt;
	}



	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("ed_id", ed_id);
		j.put("cont_id", cont_id);
		j.put("cs_id", cs_id);
		j.put("et_id", et_id);
		j.put("ed_num", ed_num);
		j.put("ed_points", ed_points);
		j.put("ed_score", ed_score);
		j.put("ed_sc_rt", ed_sc_rt);
		return j;
	}
}
