package team.zucc.eecs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.ObjectiveIndexPoint;
import team.zucc.eecs.service.ObjectiveIndexPointService;

@Controller("ObjectiveIndexPointController")
public class ObjectiveIndexPointController {
	@Autowired
	private ObjectiveIndexPointService objectiveIndexPointService;
	
	@RequestMapping(value = { "/getObjectiveIndexPointList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getObjectiveIndexPointList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入ObjectiveIndexPointController-getObjectiveIndexPointList");

		JSONObject obj = new JSONObject();
		try {
			JSONArray co_idList = in.getJSONArray("co_idList");
			JSONArray ip_idList = in.getJSONArray("ip_idList");
			String[][] coi_levList = new String[ip_idList.size()][co_idList.size()];
			
			for(int i = 0; i < ip_idList.size(); i++) {
				int ip_id = ip_idList.getIntValue(i);
				for(int j = 0; j < co_idList.size(); j++) {
					int co_id = co_idList.getIntValue(j);
					ObjectiveIndexPoint tmp = objectiveIndexPointService.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
					String coi_lev = "";
					if(tmp != null) coi_lev = tmp.getCoi_lev();
					coi_levList[i][j] = coi_lev;
				}
			}
			obj.put("coi_levList", coi_levList);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/updateObjectiveIndexPointList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateObjectiveIndexPointList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入ObjectiveIndexPointController-updateObjectiveIndexPointList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			JSONArray co_idList = in.getJSONArray("co_idList");
			JSONArray ip_idList = in.getJSONArray("ip_idList");
			JSONArray coi_levList = in.getJSONArray("coi_levList");
			
			for(int i = 0; i < num; i++) {
				int ip_id = ip_idList.getIntValue(i);
				int co_id = co_idList.getIntValue(i);
				String coi_lev = coi_levList.getString(i);
				ObjectiveIndexPoint tmp = objectiveIndexPointService.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
				
				if(coi_lev.length() == 0 && tmp != null) {
					int f = objectiveIndexPointService.deleteObjectiveIndexPoint(tmp.getCoi_id());
					if(f != 0) {
						obj.put("state", "数据库错误！");
						return obj;
					}
				}
				
				if(coi_lev.length() != 0 && tmp != null) {
					int f = objectiveIndexPointService.updateObjectiveIndexPoint(tmp.getCoi_id(), coi_lev);
					if(f != 0) {
						obj.put("state", "数据库错误！");
						return obj;
					}
				}
				
				if (tmp == null) {
					int f = objectiveIndexPointService.addObjectiveIndexPoint(co_id, ip_id, coi_lev);
					if(f != 0) {
						obj.put("state", "数据库错误！");
						return obj;
					}
				}
			}
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
