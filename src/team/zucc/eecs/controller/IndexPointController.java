//This is maintained by jyl. 
package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.service.IndexPointService;

@Controller("IndexPointController")
public class IndexPointController {
	@Autowired
	private IndexPointService indexPointService;
	
	@RequestMapping(value = { "/getIndexPointList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getIndexPointList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入IndexPointController-getIndexPointList");

		JSONObject obj = new JSONObject();
		List<IndexPoint> indexPointList = new ArrayList<IndexPoint>();
		try {
			int gr_id = in.getIntValue("gr_id");
			indexPointList = indexPointService.getIndexPointListByGr_id(gr_id);
			obj.put("indexPointList", indexPointList);
			
			if (indexPointList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getIndexPoint" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getIndexPoint(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入IndexPointController-getIndexPoint");

		JSONObject obj = new JSONObject();
		try {
			int ip_id = in.getIntValue("ip_id");
			IndexPoint indexPoint = indexPointService.getIndexPointByIp_id(ip_id);
			
			if (indexPoint == null) {
				obj.put("state", "数据库内没有该数据！");
			} else {
				obj.put("indexPoint", indexPoint);
				obj.put("state", "OK");
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
}
