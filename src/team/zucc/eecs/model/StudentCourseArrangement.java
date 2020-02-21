package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("StudentCourseArrangementBean")
public class StudentCourseArrangement {
	@JSONField(ordinal = 1)
	private int sca_id;//id
	
	@JSONField(ordinal = 2)
	private int stu_id;//学号（外码）
	
	@JSONField(ordinal = 3)
	private int cag_id;//课程安排ID（外码）	
	
	public int getSca_id() {
		return sca_id;
	}

	public void setSca_id(int sca_id) {
		this.sca_id = sca_id;
	}

	public int getStu_id() {
		return stu_id;
	}

	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}

	public int getCag_id() {
		return cag_id;
	}

	public void setCag_id(int cag_id) {
		this.cag_id = cag_id;
	}

	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		j.put("sca_id", sca_id);
		j.put("stu_id", stu_id);
		j.put("cag_id", cag_id);
		return j;
	}

}
