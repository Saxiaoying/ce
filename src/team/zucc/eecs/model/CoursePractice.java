//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CoursePracticeBean")
public class CoursePractice {
	@JSONField(ordinal = 1)
	private int pra_id;//ID
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private int pra_num;//序号
	
	@JSONField(ordinal = 4)
	private String pra_name;//实践环节
	
	@JSONField(ordinal = 5)
	private Double pra_hrs;//学时数
	
	@JSONField(ordinal = 6)
	private String pra_cont;//内容及基本要求

	@JSONField(ordinal = 7)
	private String pra_nature;//实验性质
	
	@JSONField(ordinal = 8)
	private String pra_typ;//类别
	
	
	public int getPra_id() {
		return pra_id;
	}


	public void setPra_id(int pra_id) {
		this.pra_id = pra_id;
	}


	public int getCs_id() {
		return cs_id;
	}


	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}


	public int getPra_num() {
		return pra_num;
	}


	public void setPra_num(int pra_num) {
		this.pra_num = pra_num;
	}


	public String getPra_name() {
		return pra_name;
	}


	public void setPra_name(String pra_name) {
		this.pra_name = pra_name;
	}


	public Double getPra_hrs() {
		return pra_hrs;
	}


	public void setPra_hrs(Double pra_hrs) {
		this.pra_hrs = pra_hrs;
	}


	public String getPra_cont() {
		return pra_cont;
	}


	public void setPra_cont(String pra_cont) {
		this.pra_cont = pra_cont;
	}


	public String getPra_nature() {
		return pra_nature;
	}


	public void setPra_nature(String pra_nature) {
		this.pra_nature = pra_nature;
	}


	public String getPra_typ() {
		return pra_typ;
	}


	public void setPra_typ(String pra_typ) {
		this.pra_typ = pra_typ;
	}


	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("pra_id", pra_id);
		j.put("cs_id", cs_id);
		j.put("pra_num", pra_num);
		j.put("pra_name", pra_name);
		j.put("pra_hrs", pra_hrs);
		j.put("pra_cont", pra_cont);
		j.put("pra_nature", pra_nature);
		j.put("pra_typ", pra_typ);
		return j;
	}
}
