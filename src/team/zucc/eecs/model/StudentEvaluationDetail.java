package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;


@Component("StudentEvaluationDetailBean")
public class StudentEvaluationDetail {
	@JSONField(ordinal = 1)
	private int se_id;//id
	
	@JSONField(ordinal = 2)
	private int stu_id;//学号（外码）
	
	@JSONField(ordinal = 3)
	private int ed_id;//评价依据明细（外码）	
	
	@JSONField(ordinal = 4)
	private Double se_score;//获得的分数
	
	public int getSe_id() {
		return se_id;
	}

	public void setSe_id(int se_id) {
		this.se_id = se_id;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getEd_id() {
		return ed_id;
	}

	public void setEd_id(int ed_id) {
		this.ed_id = ed_id;
	}


	public Double getSe_score() {
		return se_score;
	}


	public void setSe_score(Double se_score) {
		this.se_score = se_score;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		j.put("se_id", se_id);
		j.put("stu_id", stu_id);
		j.put("ed_id", ed_id);
		j.put("se_score", se_score);
		return j;
	}

}
