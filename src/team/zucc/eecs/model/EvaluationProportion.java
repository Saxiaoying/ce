//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationProportionBean")
public class EvaluationProportion {
	@JSONField(ordinal = 1)
	private int ep_id; //ID
	
	@JSONField(ordinal = 2)
	private int eval_id; //评价依据的ID（外码）
	
	@JSONField(ordinal = 3)
	private int cs_id; //开课流水号（外码）
	
	@JSONField(ordinal = 4)
	private int ep_typ; //评价依据类别
	                           //0：总成绩比例分配； 1：指标达成度比例分配；2：课程目标比例分配
	@JSONField(ordinal = 5)
	private double ep_prop; //评价依据比例
	
	
	public int getEp_id() {
		return ep_id;
	}


	public void setEp_id(int ep_id) {
		this.ep_id = ep_id;
	}


	public int getEval_id() {
		return eval_id;
	}


	public void setEval_id(int eval_id) {
		this.eval_id = eval_id;
	}


	public int getCs_id() {
		return cs_id;
	}


	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}


	public int getEp_typ() {
		return ep_typ;
	}


	public void setEp_typ(int ep_typ) {
		this.ep_typ = ep_typ;
	}


	public double getEp_prop() {
		return ep_prop;
	}


	public void setEp_prop(double ep_prop) {
		this.ep_prop = ep_prop;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("ep_id", ep_id);
		j.put("eval_id", eval_id);
		j.put("cs_id", cs_id);
		j.put("ep_typ", ep_typ);
		j.put("ep_prop", ep_prop);
		return j;
	}
}
