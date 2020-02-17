//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationBean")
public class Evaluation {
	
	@JSONField(ordinal = 1)
	private int eval_id;
	
	@JSONField(ordinal = 2)
	private int co_id; //课程目标ID（外码）

	@JSONField(ordinal = 3)
	private int cs_id; //开课流水号（外码）
	
	@JSONField(ordinal = 4)
	private int et_id; //类型表ID （外码）

	@JSONField(ordinal = 5)
	private double eval_prop; //比例
	
	@JSONField(ordinal = 6)
	private double eval_points; //设定的分数（总分）
	
	@JSONField(ordinal = 7)
	private double eval_score; //评价得分
	
	@JSONField(ordinal = 8)
	private double eval_sc_rt; //得分率
	
	@JSONField(ordinal = 9)
	private double eval_achv; //达成度
	
	public int getEval_id() {
		return eval_id;
	}


	public void setEval_id(int eval_id) {
		this.eval_id = eval_id;
	}


	public int getCo_id() {
		return co_id;
	}


	public void setCo_id(int co_id) {
		this.co_id = co_id;
	}


	public int getCs_id() {
		return cs_id;
	}


	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}


	public int setEt_id() {
		return et_id;
	}


	public void setEt_id(int et_id) {
		this.et_id = et_id;
	}


	public double getEval_prop() {
		return eval_prop;
	}


	public void setEval_prop(double eval_prop) {
		this.eval_prop = eval_prop;
	}


	public double getEval_points() {
		return eval_points;
	}


	public void setEval_points(double eval_points) {
		this.eval_points = eval_points;
	}


	public double getEval_score() {
		return eval_score;
	}


	public void setEval_score(double eval_score) {
		this.eval_score = eval_score;
	}


	public double getEval_sc_rt() {
		return eval_sc_rt;
	}


	public void setEval_sc_rt(double eval_sc_rt) {
		this.eval_sc_rt = eval_sc_rt;
	}
	
	public double getEval_achv() {
		return eval_achv;
	}


	public void setEval_achv(double eval_achv) {
		this.eval_achv = eval_achv;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("eval_id", eval_id);
		j.put("co_id", co_id);
		j.put("cs_id", cs_id);
		j.put("et_id", et_id);
		j.put("eval_prop", eval_prop);
		j.put("eval_points", eval_points);
		j.put("eval_score", eval_score);
		j.put("eval_sc_rt", eval_sc_rt);
		j.put("eval_achv", eval_achv);
		return j;
	}
}
