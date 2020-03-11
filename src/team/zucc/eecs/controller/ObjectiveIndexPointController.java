package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.GraduationRequire;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.ObjectiveIndexPoint;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.GraduationRequireService;
import team.zucc.eecs.service.IndexPointService;
import team.zucc.eecs.service.ObjectiveIndexPointService;

@Controller("ObjectiveIndexPointController")
public class ObjectiveIndexPointController {
	@Autowired
	private ObjectiveIndexPointService objectiveIndexPointService;
	
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@Autowired
	private IndexPointService indexPointService;
	
	@Autowired
	private GraduationRequireService graduationRequireService;
	
	@RequestMapping(value = { "/getObjectiveIndexPointListByCs_id" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getObjectiveIndexPointListByCs_id(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入ObjectiveIndexPointController-getObjectiveIndexPointListByCs_id");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			
			List<CourseObjective> courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			if(courseObjectiveList == null || courseObjectiveList.size() == 0) {
				obj.put("state", "该开课记录暂无课程目标，请前往开课管理-课程目标设置！");
			}
			List<ObjectiveIndexPoint> objectiveIndexPointList = objectiveIndexPointService.getObjectiveIndexPointListByCs_id(cs_id);
			Set<Integer> ip_idSet = new HashSet<Integer>();
			for(ObjectiveIndexPoint oip: objectiveIndexPointList) {
				ip_idSet.add(oip.getIp_id());
			}
			List<Integer> ip_idList = new ArrayList<Integer>();
			ip_idList.addAll(ip_idSet);
			Collections.sort(ip_idList); 
			
			List<IndexPoint> indexPointList = new ArrayList<IndexPoint>();
			List<GraduationRequire> graduationRequireList = new ArrayList<GraduationRequire>();
			String[][] coi_levList = new String[ip_idList.size()][courseObjectiveList.size()];
			
			
			
			for(int i = 0; i < ip_idList.size(); i++) {
				int ip_id = ip_idList.get(i);
				IndexPoint ip = indexPointService.getIndexPointByIp_id(ip_id);
				if(ip == null) {
					objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
				} else {
					GraduationRequire gr = graduationRequireService.getGraduationRequireByGr_id(ip.getGr_id());
					if(gr == null) {
						objectiveIndexPointService.deleteObjectiveIndexPointByIp_id(ip_id);
					} else {
						indexPointList.add(ip);
						graduationRequireList.add(gr);
					}
				}
			}
			
			for(int i = 0; i < indexPointList.size(); i++) {
				int ip_id = indexPointList.get(i).getIp_id();
				for(int j = 0; j < courseObjectiveList.size(); j++) {
					int co_id = courseObjectiveList.get(j).getCo_id();
					ObjectiveIndexPoint tmp = objectiveIndexPointService.getObjectiveIndexPointByCo_idAndIp_id(co_id, ip_id);
					String coi_lev = "";
					if(tmp != null) coi_lev = tmp.getCoi_lev();
					coi_levList[i][j] = coi_lev;
				}
			}
			
			obj.put("indexPointList", indexPointList);
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("coi_levList", coi_levList);
			obj.put("graduationRequireList", graduationRequireList);
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
				String coi_lev = coi_levList.getString(i);
				coi_lev = coi_lev.replaceAll("\\s", "");
				if(coi_lev.compareTo("H")!=0 && coi_lev.compareTo("M")!=0 && coi_lev.compareTo("L")!=0 && coi_lev.compareTo("")!=0) {
					obj.put("state", "注意格式，相关支撑必须填写“H\\M\\L”！");
					return obj;
				}
			}
			for(int i = 0; i < num; i++) {
				int ip_id = ip_idList.getIntValue(i);
				int co_id = co_idList.getIntValue(i);
				String coi_lev = coi_levList.getString(i);
				coi_lev = coi_lev.replaceAll("\\s", "");
				
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
