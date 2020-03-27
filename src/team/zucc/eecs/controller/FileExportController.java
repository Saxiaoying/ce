package team.zucc.eecs.controller;


import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.tool.FileExportTool;
import team.zucc.eecs.tool.FileUploadTool;

@Controller("FileExportController")
public class FileExportController {
//	private FileExportTool fileExportTool = new FileExportTool();
	//static String dirPlace = "/root/Desktop/ceData/tmpData/";
	//static String dirPlace = "C:\\Users\\john\\Desktop\\";
	
	private String remoteTargetDirectory = "/root/Desktop/ceData/tmpData/";
	private String URL = "http://115.29.227.78:8080/ceData/tmpData/";
	
	@RequestMapping(value = { "/exportFile" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject exportFile(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		System.out.println("进入FileExportController-exportFile");
		
		JSONObject obj = new JSONObject();
		try {
			String sheetName = in.getString("sheetName");
			JSONArray titleJsonArray = in.getJSONArray("title");
			JSONArray valuesJsonArray = in.getJSONArray("values");
			
			
			String fileName = String.valueOf(System.currentTimeMillis()) + ".xls";
			
			String[] title = new String[titleJsonArray.size()];
			for(int i = 0; i <titleJsonArray.size(); i++) {
				title[i] = titleJsonArray.getString(i);
			}
			
			
			String[][] values = new String[valuesJsonArray.size()][];
			for(int i = 0; i <valuesJsonArray.size(); i++) {
				String dataString = valuesJsonArray.getString(i);
				String[] tmp = dataString.split(";");
				values[i] = tmp;
			}
			
			String dirPath = session.getServletContext().getRealPath("/tmp");
			File dir = new File(dirPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			
			String filePath =  session.getServletContext().getRealPath("/tmp") + "/" + fileName; //获取服务器上传的文件路径
			
			File file = new File(filePath);
			if (!file.exists()){
				file.createNewFile();
            }
			HSSFWorkbook wb = FileExportTool.getHssfWorkbook(sheetName, title, values);
            FileOutputStream fout = new FileOutputStream(file);
            wb.write(fout);
            fout.close();
            wb.close();
            
            FileUploadTool.uploadFile(file, file.length(), remoteTargetDirectory, null);
            file.delete();
            
            
            obj.put("state", "OK");
			obj.put("fileUrl", URL+fileName);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "后台错误！");
		}
		return obj;
	}
	
	
	
	@RequestMapping(value = { "/exportFileOfTwoSheet" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject exportFileOfTwoSheet(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		System.out.println("进入FileExportController-exportFileOfTwoSheet");
		
		JSONObject obj = new JSONObject();
		try {
			String sheetName1 = in.getString("sheetName1");
			JSONArray titleJsonArray1 = in.getJSONArray("title1");
			JSONArray valuesJsonArray1 = in.getJSONArray("values1");
			
			
			String sheetName2 = in.getString("sheetName2");
			JSONArray titleJsonArray2 = in.getJSONArray("title2");
			JSONArray valuesJsonArray2 = in.getJSONArray("values2");
			
			String fileName = String.valueOf(System.currentTimeMillis()) + ".xls";
			
			String[] title1 = new String[titleJsonArray1.size()];
			for(int i = 0; i <titleJsonArray1.size(); i++) {
				title1[i] = titleJsonArray1.getString(i);
			}
			String[][] values1 = new String[valuesJsonArray1.size()][];
			for(int i = 0; i <valuesJsonArray1.size(); i++) {
				String dataString = valuesJsonArray1.getString(i);
				String[] tmp = dataString.split(";");
				values1[i] = tmp;
			}
			
			
			
			String[] title2 = new String[titleJsonArray2.size()];
			for(int i = 0; i <titleJsonArray2.size(); i++) {
				title2[i] = titleJsonArray2.getString(i);
			}
			String[][] values2 = new String[valuesJsonArray2.size()][];
			for(int i = 0; i <valuesJsonArray2.size(); i++) {
				String dataString = valuesJsonArray2.getString(i);
				String[] tmp = dataString.split(";");
				values2[i] = tmp;
			}
			
			String dirPath = session.getServletContext().getRealPath("/tmp");
			File dir = new File(dirPath);
			if(!dir.exists()) {
				dir.mkdirs();
			}
			
			
			String filePath =  session.getServletContext().getRealPath("/tmp") + "/" + fileName; //获取服务器上传的文件路径
			
			File file = new File(filePath);
			if (!file.exists()){
				file.createNewFile();
            }
			HSSFWorkbook wb = FileExportTool.getHssfWorkbookOfTwoSheet(sheetName1, sheetName2, title1, values1, title2, values2);
            FileOutputStream fout = new FileOutputStream(file);
            wb.write(fout);
            fout.close();
            wb.close();
            
            FileUploadTool.uploadFile(file, file.length(), remoteTargetDirectory, null);
            file.delete();
            
            
            obj.put("state", "OK");
			obj.put("fileUrl", URL+fileName);
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "后台错误！");
		}
		return obj;
	}
}
