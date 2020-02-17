//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseObjectiveBean")
public class CourseObjective {
	@JSONField(ordinal = 1)
	private int co_id;//ID
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private int co_num;//课程目标序号
	
	@JSONField(ordinal = 4)
	private String co_cont;//内容
	
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

	public int getCo_num() {
		return co_num;
	}

	public void setCo_num(int co_num) {
		this.co_num = co_num;
	}

	public String getCo_cont() {
		return co_cont;
	}

	public void setCo_cont(String co_cont) {
		this.co_cont = co_cont;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("co_id", co_id);
		j.put("cs_id", cs_id);
		j.put("co_num", co_num);
		j.put("co_cont", co_cont);
		
		return j;
	}

}

