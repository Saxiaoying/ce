package team.zucc.eecs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.tool.FileUploadTool;

@Controller("FileDownloadController")
public class FileDownloadController {
	private FileUploadTool fileUploadTool = new FileUploadTool();	 
	
	@RequestMapping(value = { "/fileDownload" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject fileDownload(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入FileDownloadController-fileDownload");
		
		JSONObject obj = new JSONObject();
		try {
			String fileName = in.getString("fileName");
			fileUploadTool.downloadFile(fileName, "/root/Desktop/ceData", "C:\\Users\\john\\Desktop\\");
			
			
			System.out.println("进入FileDownloadController-fileDownload");
			
			obj.put("state", "OK");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return obj;
	}
	
}
