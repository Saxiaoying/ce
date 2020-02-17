//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseContentBean")
public class CourseContent {
	@JSONField(ordinal = 1)
	private int cont_id;//ID
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private String cont_name;//教学内容的名称
	
	@JSONField(ordinal = 4)
	private int cont_num;//教学内容序号
	
	@JSONField(ordinal = 5)
	private String cont_cont;//教学主要内容
	
	@JSONField(ordinal = 6)
	private String cont_method;//教学方法与要求
	
	@JSONField(ordinal = 7)
	private String cont_key;//重点
	
	@JSONField(ordinal = 8)
	private String cont_diff;//难点
	
	@JSONField(ordinal = 9)
	private double cont_hrs_tch;//讲课时数
	
	@JSONField(ordinal = 10)
	private double cont_hrs_pr;//实验时数
	
	@JSONField(ordinal = 11)
	private String cont_cla_exe;//课堂练习
	
	@JSONField(ordinal = 12)
	private String cont_hw;//课后作业
	
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

	public String getCont_name() {
		return cont_name;
	}

	public void setCont_name(String cont_name) {
		this.cont_name = cont_name;
	}

	public int getCont_num() {
		return cont_num;
	}

	public void setCont_num(int cont_num) {
		this.cont_num = cont_num;
	}

	public String getCont_cont() {
		return cont_cont;
	}

	public void setCont_cont(String cont_cont) {
		this.cont_cont = cont_cont;
	}

	public String getCont_method() {
		return cont_method;
	}

	public void setCont_method(String cont_method) {
		this.cont_method = cont_method;
	}

	public String getCont_key() {
		return cont_key;
	}

	public void setCont_key(String cont_key) {
		this.cont_key = cont_key;
	}

	public String getCont_diff() {
		return cont_diff;
	}

	public void setCont_diff(String cont_diff) {
		this.cont_diff = cont_diff;
	}

	public double getCont_hrs_tch() {
		return cont_hrs_tch;
	}

	public void setCont_hrs_tch(double cont_hrs_tch) {
		this.cont_hrs_tch = cont_hrs_tch;
	}

	public double getCont_hrs_pr() {
		return cont_hrs_pr;
	}

	public void setCont_hrs_pr(double cont_hrs_pr) {
		this.cont_hrs_pr = cont_hrs_pr;
	}

	public String getCont_cla_exe() {
		return cont_cla_exe;
	}

	public void setCont_cla_exe(String cont_cla_exe) {
		this.cont_cla_exe = cont_cla_exe;
	}

	public String getCont_hw() {
		return cont_hw;
	}

	public void setCont_hw(String cont_hw) {
		this.cont_hw = cont_hw;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("cont_id", cont_id);
		j.put("cs_id", cs_id);
		j.put("cont_name", cont_name);
		j.put("cont_num", cont_num);
		
		j.put("cont_cont", cont_cont);
		j.put("cont_method", cont_method);
		j.put("cont_key", cont_key);
		j.put("cont_diff", cont_diff);
		j.put("cont_hrs_tch", cont_hrs_tch);
		
		j.put("cont_hrs_pr", cont_hrs_pr);
		j.put("cont_cla_exe", cont_cla_exe);
		j.put("cont_hw", cont_hw);
		
		return j;
	}

}
