package team.zucc.eecs.controller;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.EvaluationDetailService;
import team.zucc.eecs.service.EvaluationService;
import team.zucc.eecs.service.StudentEvaluationDetailService;

@Controller("FileReadController")
public class FileReadController {
	//private FileUploadTool fileUploadTool = new FileUploadTool();
		@Autowired
		private StudentEvaluationDetailService studentEvaluationDetailService;
	
		@Autowired
		private EvaluationDetailService evaluationDetailService;
		
		@Autowired
		private CourseObjectiveService courseObjectiveService;
		
		@Autowired
		private EvaluationService evaluationService;

		
	    @RequestMapping("/readFile")
	    @ResponseBody
	    public JSONObject readFile(MultipartFile file, HttpSession session) {
	    	System.out.println("进入FileReadController-readFile");
	    	JSONObject obj = new JSONObject();
	    	try {
	    		//for (MultipartFile file : files) {
	    			String fileName = file.getOriginalFilename();
	    			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    			if(suffix.compareTo("xls") != 0 &&suffix.compareTo("xlsx") != 0) {
	    				obj.put("state", "仅支持上传xls/xlsx格式的文件，请重新选择！");
	    				return obj;
	    			}
	    			InputStream is = file.getInputStream();
	    	        Workbook excel = WorkbookFactory.create(is);
	    	        is.close();
	    	        // 遍历所有表格
	    	        List<Integer>ed_idList = new ArrayList<Integer>();
	    	        List<Integer>stu_idList = new ArrayList<Integer>();
	    	        List<Double>scoreList = new ArrayList<Double>();
	    	        for (int numSheet = 0; numSheet < excel.getNumberOfSheets(); numSheet++) {
	    	            Sheet sheet = excel.getSheetAt(numSheet);
	    	            //遍历所有行
	    	            Row titleRow = sheet.getRow(0);
	    	            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
	    	            	Row row = sheet.getRow(rowNum);
	    	                if (row == null) {
	    	                    continue;
	    	                }
	    	                Cell cell2 = row.getCell(1);
	    	                System.out.println(cell2);
	    	                int stu_id = -1;
	    	                try {
	    	                	String tString = cell2.getStringCellValue();
	    	                	stu_id = Integer.valueOf(tString);
							} catch (Exception e) {
								e.printStackTrace();
								obj.put("state", "文件格式不正确！");
								return obj;
							}
	    	            	for(int i = 4; i < titleRow.getLastCellNum(); i++) {
		    	            	Cell tmpCell = titleRow.getCell(i);
		    	            	String tmp = tmpCell.getStringCellValue();
		    	            	try {
		    	            		Integer ed_id = Integer.valueOf((tmp.substring(tmp.indexOf(":") + 1)));
		    	            		ed_idList.add(ed_id);
		    	            		stu_idList.add(stu_id);
			    	                Cell cell = row.getCell(i);
			    	                double score = -1.0;
			    	                
			    	                try {
			    	                	String tString = cell.getStringCellValue();
			    	            		score=Double.valueOf(tString);
									} catch (Exception e) {
										score = (double) cell.getNumericCellValue();
									}
			    	                if(score < 0) {
			    	                	obj.put("state", "分数不为负数或者文件格式不正确！");
										return obj;
			    	                }
		    	            		scoreList.add(score);
								} catch (Exception e) {
									obj.put("state", "文件格式不正确！");
									return obj;
								}
		    	            }
	    	            }
	    	        }
	    	        int f = studentEvaluationDetailService.updateStudentEvaluationDetailListSe_score(stu_idList, ed_idList, scoreList);
	    	        if(f != 0) {
	    	        	 obj.put("state", "文件有数据不符合数据库存储！");
    	        		 return obj;
	    	        }
	    	        int ed_id = ed_idList.get(0);
					EvaluationDetail ed = evaluationDetailService.getEvaluationDetailByEd_id(ed_id);
					int et_id = ed.getEt_id();
					int cs_id = ed.getCs_id();
					
					
					int ff = evaluationDetailService.updateEvaluationDetailByCs_idAndEt_id(cs_id, et_id);
					if(ff != 0) {
	    	        	 obj.put("state", "数据库出错！");
   	        		 return obj;
	    	        }
					
					List<CourseObjective> coList = courseObjectiveService.getCourseObjectiveListByCs_id(cs_id);
					for (CourseObjective co: coList) {
						evaluationService.updateEvaluationByCs_idAndCo_idAndEt_id(co.getCo_id(), cs_id, et_id);
					}
		            obj.put("state", "OK");
	    		//}
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("state", "执行错误！");
			}
	        
	        return obj;
	    }    
}



