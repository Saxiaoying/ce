//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseAppendixBean")
public class CourseAppendix {
	
	@JSONField(ordinal = 1)
	private int ca_id;//ID
	
	@JSONField(ordinal = 2)
	private int cs_id;//开课流水号（外码）
	
	@JSONField(ordinal = 3)
	private int app_id;//附件流水号（外码）
	
	@JSONField(ordinal = 4)
	private int ca_class;//文件类别（0：课程简介；1：课程进度；2：实验卡...）
	
	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("ca_id", ca_id);
		j.put("cs_id", cs_id);
		j.put("app_id", app_id);
		j.put("ca_class", ca_class);
		return j;
	}

}

