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

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.service.CourseArrangementService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;
import team.zucc.eecs.service.TeacherService;

@Controller("CourseArrangementController")
public class CourseArrangementController {
	@Autowired
	private CourseArrangementService courseArrangementService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private TeacherService teacherService;
	
	@RequestMapping(value = { "/getCourseArrangementList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseArrangementList(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-getCourseArrangementList");

		JSONObject obj = new JSONObject();
		List<CourseArrangement> courseArrangementList = new ArrayList<CourseArrangement>();
		List<CourseSet> courseSetList = new ArrayList<CourseSet>();
		List<Course> courseList = new ArrayList<Course>();
		List<String> tch_nameList = new ArrayList<String>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			String cs_acad_yr = "", cs_sem = ""; 
			if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
			if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");

			// 处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";

			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if (coz_name_ch.isEmpty()) coz_name_ch = "";

			String tch_name = in.getString("tch_name");
			tch_name = tch_name.replaceAll("\\s", "");
			if (tch_name.isEmpty()) tch_name = "";
			
			courseArrangementList = courseArrangementService.getCourseArrangementListByInfFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
            int total = courseArrangementService.getCourseArrangementNumberByInf(cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
			
            for(CourseArrangement ca: courseArrangementList) {
            	CourseSet cs = courseSetService.getCourseSetByCs_id(ca.getCs_id());
            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
            	Teacher teacher = teacherService.getTeacherByTch_id(ca.getTch_id());
            	if(teacher == null) {
            		tch_nameList.add("教师信息缺失！");
            	} else {
            		tch_nameList.add(teacher.getTch_name());
				}
            	courseSetList.add(cs);
            	courseList.add(c);
            }
			
			obj.put("total", total);
			obj.put("courseArrangementList", courseArrangementList);
			obj.put("courseSetList", courseSetList);
			obj.put("courseList", courseList);
			obj.put("tch_nameList", tch_nameList);
			
			if (courseArrangementList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/updateCourseArrangement" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateCourseArrangement(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-updateCourseArrangement");

		JSONObject obj = new JSONObject();
		CourseSet courseSet = new CourseSet();
		Course course = new Course();
		Teacher teacher = new Teacher();
		try {
			
			int cag_id = in.getIntValue("cag_id");
	
			// 处理字符串中的不可见字符
			String cag_name = in.getString("cag_name");
			cag_name = cag_name.replaceAll("\\s", "");
			if (cag_name.isEmpty()) cag_name = "";

			int cs_id = 0;
	    	try {
	    		cs_id = in.getIntValue("cs_id");
			} catch (Exception e) {
				obj.put("state", "开课流水号必须是数字！");
				return obj;
			}
	    	
	    	int tch_id = 0;
	    	try {
	    		tch_id = in.getIntValue("tch_id");
			} catch (Exception e) {
				obj.put("state", "教师编号必须是数字！");
				return obj;
			}
	    	
	    	int cag_num = 0;
	    	try {
	    		cag_num = in.getIntValue("cag_num");
			} catch (Exception e) {
				obj.put("state", "课序号必须是数字！");
				return obj;
			}
			
	    	//教师问题
	    	teacher = teacherService.getTeacherByTch_id(tch_id);
        	if(teacher == null) {
        		obj.put("state", "该教师编号不存在！");
				return obj;
        	}
	    	
	    	courseSet = courseSetService.getCourseSetByCs_id(cs_id);
        	if(courseSet == null) {
        		obj.put("state", "该开课流水号不存在！");
				return obj;
        	}
        	course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			
        	obj.put("teacher", teacher);
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			
			int f = courseArrangementService.updateCourseArrangement(cag_id, cs_id, tch_id, cag_num, cag_name);
			
			if (f==1) obj.put("state", "该教师已经在该时间段有同一个课程安排！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/addCourseArrangement" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourseArrangement(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-addCourseArrangement");

		JSONObject obj = new JSONObject();
		CourseSet courseSet = new CourseSet();
		Course course = new Course();
		Teacher teacher = new Teacher();
		try {
			
			// 处理字符串中的不可见字符
			String cag_name = in.getString("cag_name");
			cag_name = cag_name.replaceAll("\\s", "");
			if (cag_name.isEmpty()) cag_name = "";

			int cs_id = 0;
	    	try {
	    		cs_id = in.getIntValue("cs_id");
			} catch (Exception e) {
				obj.put("state", "开课流水号必须是数字！");
				return obj;
			}
	    	
	    	int tch_id = 0;
	    	try {
	    		tch_id = in.getIntValue("tch_id");
			} catch (Exception e) {
				obj.put("state", "教师编号必须是数字！");
				return obj;
			}
	    	
	    	int cag_num = 0;
	    	try {
	    		cag_num = in.getIntValue("cag_num");
			} catch (Exception e) {
				obj.put("state", "课序号必须是数字！");
				return obj;
			}
			
	    	//教师问题
	    	teacher = teacherService.getTeacherByTch_id(tch_id);
        	if(teacher == null) {
        		obj.put("state", "该教师编号不存在！");
				return obj;
        	}
	    	
	    	courseSet = courseSetService.getCourseSetByCs_id(cs_id);
        	if(courseSet == null) {
        		obj.put("state", "该开课流水号不存在！");
				return obj;
        	}
        	course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			
        	obj.put("teacher", teacher);
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			
			int f = courseArrangementService.addCourseArrangement(cs_id, tch_id, cag_num, cag_name);
			
			if (f==1) obj.put("state", "该教师已经在该时间段有同一个课程安排！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getCourseArrangement" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseArrangement(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-getCourseArrangement");

		JSONObject obj = new JSONObject();
		try {
			int cag_id = 0;
			try {
				cag_id = in.getIntValue("cag_id");
			} catch (Exception e) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			if(cag_id <= 0) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			
			CourseArrangement courseArrangement = courseArrangementService.getCourseArrangementByCag_id(cag_id);
			if(courseArrangement == null) {
				obj.put("state", "暂无该排课记录！");
				return obj;
			}
			
			int cs_id = courseArrangement.getCs_id();
			int tch_id = courseArrangement.getTch_id();
			
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "发现无效记录，排课流水号为" + cag_id);
				return obj;
			}
			String coz_id = courseSet.getCoz_id();
			
			Course course = courseService.getCourseByCoz_id(coz_id);
			Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
			if(course == null || teacher == null) {
				obj.put("state", "发现无效记录，排课流水号为" + cag_id);
				return obj;
			}
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("teacher", teacher);
			obj.put("courseArrangement", courseArrangement);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/deleteCourseArrangement" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCourseArrangement(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-deleteCourseArrangement");

		JSONObject obj = new JSONObject();
		try {
			int cag_id = in.getIntValue("cag_id");
			int i = courseArrangementService.deleteCourseArrangement(cag_id);
			
			
			if (i != 0) obj.put("state", "数据库错误！");
			else obj.put("state", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	
	
	@RequestMapping(value = { "/getCourseArrangementListByTch_id" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseArrangementListByTch_id(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-getCourseArrangementListByTch_id");

		JSONObject obj = new JSONObject();
		List<CourseArrangement> courseArrangementList = new ArrayList<CourseArrangement>();
		List<CourseSet> courseSetList = new ArrayList<CourseSet>();
		List<Course> courseList = new ArrayList<Course>();
		try {
			int tch_id =  (Integer) request.getSession().getAttribute("TCH_ID");
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			String cs_acad_yr = "", cs_sem = ""; 
			if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
			if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");

			// 处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";

			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if (coz_name_ch.isEmpty()) coz_name_ch = "";

			String coz_nature = in.getString("coz_nature");
			if(coz_nature.compareTo("课程性质（所有）") == 0) {
				coz_nature = "";
			}
			
			courseArrangementList = courseArrangementService.getCourseArrangementByTch_idFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature, tch_id);
            int total = courseArrangementService.getCourseArrangementNumberByTch_id(cs_acad_yr, cs_sem, coz_id, coz_name_ch, coz_nature, tch_id);
			
            for(CourseArrangement ca: courseArrangementList) {
            	CourseSet cs = courseSetService.getCourseSetByCs_id(ca.getCs_id());
            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
            	courseSetList.add(cs);
            	courseList.add(c);
            }
			
			obj.put("courseArrangementList", courseArrangementList);
			obj.put("courseSetList", courseSetList);
			obj.put("courseList", courseList);
			
			obj.put("total", total);
			
			if (courseArrangementList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	
	@RequestMapping(value = { "/getCourseArrangementByTch_id" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseArrangementByTch_id(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-getCourseArrangementByTch_id");

		JSONObject obj = new JSONObject();
		try {
			int cag_id = 0;
			try {
				cag_id = in.getIntValue("cag_id");
			} catch (Exception e) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			if(cag_id <= 0) {
				obj.put("state", "排课流水号必须为正整数！");
				return obj;
			}
			
			CourseArrangement courseArrangement = courseArrangementService.getCourseArrangementByCag_id(cag_id);
			if(courseArrangement == null) {
				obj.put("state", "暂无该排课记录！");
				return obj;
			}
			
			int tch_id1 =  (Integer) request.getSession().getAttribute("TCH_ID");
			
			int cs_id = courseArrangement.getCs_id();
			int tch_id = courseArrangement.getTch_id();
			
			if(tch_id1 != tch_id) {
				obj.put("state", "您没有权限访问该排课记录！");
				return obj;
			}
			
			CourseSet courseSet = courseSetService.getCourseSetByCs_id(cs_id);
			if(courseSet == null) {
				obj.put("state", "发现无效记录，排课流水号为" + cag_id);
				return obj;
			}
			String coz_id = courseSet.getCoz_id();
			
			Course course = courseService.getCourseByCoz_id(coz_id);
			Teacher teacher = teacherService.getTeacherByTch_id(tch_id);
			if(course == null || teacher == null) {
				obj.put("state", "发现无效记录，排课流水号为" + cag_id);
				return obj;
			}
			
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("teacher", teacher);
			obj.put("courseArrangement", courseArrangement);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
