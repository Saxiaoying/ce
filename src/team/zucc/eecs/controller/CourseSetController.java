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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CourseArrangementService;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CoursePracticeService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CourseSetController")
public class CourseSetController {
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseContentService courseContentService;
	
	@Autowired
	private CoursePracticeService coursePracticeService;
	
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@Autowired
	private CourseArrangementService courseArrangementService;
	

	@RequestMapping(value = { "/getCourseSetList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseSetList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseSetController-getCourseSetList");

		JSONObject obj = new JSONObject();
		List<CourseSet> courseSetList = new ArrayList<CourseSet>();
		List<Course> courseList = new ArrayList<Course>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			String cs_acad_yr = "", cs_sem = "", coz_nature = ""; 
			if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
			if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");
			if (in.getString("coz_nature").compareTo("课程性质（所有）") != 0) coz_nature = in.getString("coz_nature");

			// 处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";

			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if (coz_name_ch.isEmpty()) coz_name_ch = "";

			courseSetList = courseSetService.getCourseSetListByInfFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature);
            int total = courseSetService.getCourseSetNumberByInf(cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature);
			
            for(CourseSet cs: courseSetList) {
            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
            	courseList.add(c);
            }
			JSONArray arr = new JSONArray();
			arr.addAll(courseSetList);

			JSONArray arr2 = new JSONArray();
			arr2.addAll(courseList);
			
			obj.put("total", total);
			obj.put("courseSetList", arr);
			obj.put("courseList", arr2);
			
			if (courseSetList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/addCourseSet" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourseSet(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseSetController-addCourseSet");
		
		JSONObject obj = new JSONObject();
		try {
			int cs_id = courseSetService.getCourseSetMaxId() + 1;
			
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";
			
			if (coz_id != "") {
				Course course = courseService.getCourseByCoz_id(coz_id);
				if(course == null) {
					obj.put("state", "该课程号不存在，无法添加！");
					return obj;
				}
			} else {
				obj.put("state", "请输入课程号！");
				return obj;
			}
			
		    String cs_acad_yr = in.getString("cs_acad_yr");
		    String cs_sem = in.getString("cs_sem");
		    
		    int f = courseSetService.addCourseSet(cs_id, coz_id, cs_acad_yr, cs_sem);
		    if(f==1) {
		    	obj.put("state", "该课程在这一时间段已经开过了，请重新选择时间！");
				return obj;
		    }
		  
			obj.put("state", "OK");
			obj.put("cs_id", cs_id);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/updateCourseSet" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCourseSet(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseSetController-updateCourseSet");
		
		JSONObject obj = new JSONObject();
		try {
			int cs_id = courseSetService.getCourseSetMaxId() + 1;
			
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";
			
			if (coz_id != "") {
				Course course = courseService.getCourseByCoz_id(coz_id);
				if(course == null) {
					obj.put("state", "该课程号不存在，无法更改！");
					return obj;
				}
			} else {
				obj.put("state", "请输入课程号！");
				return obj;
			}
			
		    String cs_acad_yr = in.getString("cs_acad_yr");
		    String cs_sem = in.getString("cs_sem");
		    
		    int f = courseSetService.updateCourseSet(cs_id, coz_id, cs_acad_yr, cs_sem);
		    if(f==1) {
		    	obj.put("state", "该课程在这一时间段已经开过了，请重新选择时间！");
				return obj;
		    }
		  
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/getCourseSet" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseSet(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseSetController-getCourseSet");
		
		JSONObject obj = new JSONObject();
		try {
			int cs_id = 0;
			try {
				cs_id = in.getIntValue("cs_id");
			} catch (Exception e) {
				obj.put("state", "开课流水号必须是正整数！");
				return obj;
			}
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "暂无该开课信息！");
				return obj;
			}
			Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}

	@RequestMapping(value= {"/deleteCourseSet"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCourseSet(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-deleteCourseSet");

		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			int f = courseSetService.deleteCourseSet(cs_id);
			if (f== 0) obj.put("state", "OK");
			else obj.put("state", "数据库出错");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "后台出错");
		}
		return obj;
	}
	@RequestMapping(value= {"/deleteCourseSetList"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCourseSetList(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-deleteCourseSetList");

		JSONObject obj = new JSONObject();
		try {
			int num = in.getIntValue("num");
			JSONArray cs_idList = in.getJSONArray("cs_idList");
		    String inf ="";
			for(int i =0; i < num; i++) {
				int cs_id = cs_idList.getIntValue(i);
				int f = courseSetService.deleteCourseSet(cs_id);
				if (f != 0) {
					inf += "删除课程号为" + cs_id +"时数据库出错！\n";
				}
		    }
			if (inf.length() == 0) obj.put("state", "OK");
			else obj.put("state", inf);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "后台出错");
		}
		return obj;
	}
	@RequestMapping(value = { "/getCourseSetDetail" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseSetDetail(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseContentController-getCourseSetDetail");
		
		List<CourseContent> courseContentList = new ArrayList<CourseContent>();
		List<CoursePractice> coursePracticeList = new ArrayList<CoursePractice>();
		List<CourseObjective> courseObjectiveList = new ArrayList<CourseObjective>();
		JSONObject obj = new JSONObject();
		try {
			int tch_id = (Integer) request.getSession().getAttribute("TCH_ID");
			int cs_id = -1;
			try {
				cs_id = in.getIntValue("cs_id");
				if(cs_id <= 0) {
					obj.put("state", "开课流水号为正整数！");
					return obj;
				}
			} catch (Exception e) {
				obj.put("state", "开课流水号为正整数！");
				return obj;
			}
			
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "暂无开课流水号为" + cs_id+ "的开课情况，请重新查询！");
				return obj;
			}
			
			
			List<CourseArrangement> courseArrangementList = courseArrangementService.getCourseArrangementByCs_idAndTch_id(cs_id, tch_id);
			if(courseArrangementList == null || courseArrangementList.size() == 0) {
				obj.put("state", "您没有该开课记录，无法查看详情！");
				return obj;
			}
			
			String coz_id = courseSet.getCoz_id();
			Course course = courseService.getCourseByCoz_id(coz_id);
			
			courseContentList = courseContentService.getCourseContentListByCs_id(cs_id);
			if(courseContentList == null) {
				courseContentList = new ArrayList<CourseContent>();
			}
			
			coursePracticeList = coursePracticeService.getCoursePracticeListByCs_id(cs_id);
			if(coursePracticeList == null) {
				coursePracticeList = new ArrayList<CoursePractice>();
			}
			
			courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
			if(courseObjectiveList == null) {
				courseObjectiveList = new ArrayList<CourseObjective>();
			}
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);

			obj.put("courseContentList", courseContentList);
			obj.put("courseContentList_num", courseContentList.size());
			
			obj.put("coursePracticeList", coursePracticeList);
			obj.put("coursePracticeList_num", coursePracticeList.size());
			
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("courseObjectiveList_num", courseObjectiveList.size());
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	
		//普通用户界面-开课记录
		@RequestMapping(value = { "/getCourseSetListByTch_id" }, method = RequestMethod.POST)
		@ResponseBody
		public JSONObject getCourseSetListByTch_id(@RequestBody JSONObject in, HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println("进入CourseSetController-getCourseSetListByTch_id");

			JSONObject obj = new JSONObject();
			List<CourseSet> courseSetList = new ArrayList<CourseSet>();
			List<Course> courseList = new ArrayList<Course>();
			try {
				int a = in.getIntValue("a");
				int b = in.getIntValue("b");
		
				int tch_id = (Integer) request.getSession().getAttribute("TCH_ID");
				String cs_acad_yr = "", cs_sem = "", coz_nature = ""; 
				if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
				if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");
				if (in.getString("coz_nature").compareTo("课程性质（所有）") != 0) coz_nature = in.getString("coz_nature");

				// 处理字符串中的不可见字符
				String coz_id = in.getString("coz_id");
				coz_id = coz_id.replaceAll("\\s", "");
				if (coz_id.isEmpty()) coz_id = "";

				String coz_name_ch = in.getString("coz_name_ch");
				coz_name_ch = coz_name_ch.replaceAll("\\s", "");
				if (coz_name_ch.isEmpty()) coz_name_ch = "";

				courseSetList = courseSetService.getCourseSetListByTch_idFromAtoB(a, b, coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
	            int total = courseSetService.getCourseSetNumberByTch_id(coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
				
	            for(CourseSet cs: courseSetList) {
	            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
	            	courseList.add(c);
	            }
				
				obj.put("total", total);
				obj.put("courseSetList", courseSetList);
				obj.put("courseList", courseList);
				
				if (courseSetList.size() == 0) obj.put("state", "暂无符合条件的记录！");
				else obj.put("state", "OK");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("state", "数据库错误！");
			}
			return obj;
		}
		@RequestMapping(value = { "/getCourseSetListByTch_id2" }, method = RequestMethod.POST)
		@ResponseBody
		public JSONObject getCourseSetListByTch_id2(@RequestBody JSONObject in, HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println("进入CourseSetController-getCourseSetListByTch_id2");

			JSONObject obj = new JSONObject();
			try {
				int cs_id = -1;
				try {
					cs_id = in.getIntValue("cs_id");
					if(cs_id <= 0) {
						obj.put("state", "开课流水号为正整数！");
						return obj;
					}
				} catch (Exception e) {
					obj.put("state", "开课流水号为正整数！");
					return obj;
				}
				
				CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
				if(courseSet == null) {
					obj.put("state", "暂无该开课信息！");
					return obj;
				}
				
				Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
				if(course == null) {
					obj.put("state", "该开课流水号的课程不在数据库内！");
					return obj;
				}
				int tch_id = (Integer) request.getSession().getAttribute("TCH_ID");
				
				List<CourseArrangement> courseArrangementList = courseArrangementService.getCourseArrangementByCs_idAndTch_id(cs_id, tch_id);
				if(courseArrangementList == null || courseArrangementList.size() == 0) {
					obj.put("state", "您没有查看这门课的权限！");
				}
				else obj.put("state", "OK");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("state", "数据库错误！");
			}
			return obj;
		}
		
		
		
		
		
		@RequestMapping(value = { "/getCourseSetListByTch_idOfHistory" }, method = RequestMethod.POST)
		@ResponseBody
		public JSONObject getCourseSetListByTch_idOfHistory(@RequestBody JSONObject in, HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println("进入CourseSetController-getCourseSetListByTch_idOfHistory");

			JSONObject obj = new JSONObject();
			List<CourseSet> courseSetList = new ArrayList<CourseSet>();
			List<Course> courseList = new ArrayList<Course>();
			try {
				int a = in.getIntValue("a");
				int b = in.getIntValue("b");
		
				int tch_id = in.getIntValue("tch_id");
				String cs_acad_yr = "", cs_sem = "", coz_nature = ""; 
				if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
				if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");
				if (in.getString("coz_nature").compareTo("课程性质（所有）") != 0) coz_nature = in.getString("coz_nature");

				// 处理字符串中的不可见字符
				String coz_id = in.getString("coz_id");
				coz_id = coz_id.replaceAll("\\s", "");
				if (coz_id.isEmpty()) coz_id = "";

				String coz_name_ch = in.getString("coz_name_ch");
				coz_name_ch = coz_name_ch.replaceAll("\\s", "");
				if (coz_name_ch.isEmpty()) coz_name_ch = "";

				courseSetList = courseSetService.getCourseSetListByTch_idFromAtoB(a, b, coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
	            int total = courseSetService.getCourseSetNumberByTch_id(coz_id, cs_acad_yr, cs_sem, coz_name_ch, coz_nature, tch_id);
				
	            for(CourseSet cs: courseSetList) {
	            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
	            	courseList.add(c);
	            }
				
				obj.put("total", total);
				obj.put("courseSetList", courseSetList);
				obj.put("courseList", courseList);
				
				if (courseSetList.size() == 0) obj.put("state", "暂无符合条件的记录！");
				else obj.put("state", "OK");
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("state", "数据库错误！");
			}
			return obj;
		}
}
