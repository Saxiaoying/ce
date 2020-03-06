package team.zucc.eecs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.hslf.dev.SlideAndNotesAtomListing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import team.zucc.eecs.tool.FileUploadTool;

@Controller("FileUploadController")
public class FileUploadController {
	private FileUploadTool fileUploadTool = new FileUploadTool();
	
    private final String TMP_SAVE_DIR = "D:\\";
    
    @RequestMapping(value="/fileUpload", method=RequestMethod.POST)
    @ResponseBody  
    public String fileUpload(HttpServletRequest request) throws IOException {
    	System.out.println("进入FileUploadController-fileUpload");
    	
    	
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile myfile = entity.getValue();
            
            byte[] bs = myfile.getBytes();
            fileName = myfile.getOriginalFilename();
            
            
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String timeString = df.format(new Date());
            System.out.println(timeString);// new Date()为获取当前系统时间
            
            File f = new File(TMP_SAVE_DIR + timeString + fileName);
            
            FileOutputStream fos = null;
    		try {
    			fos = new FileOutputStream(f); 
    			fos.write(bs); // 写入文件
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				fos.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
            fileUploadTool.uploadFile(f, f.length(), "/root/Desktop/ceData", null);
            f.delete();
        }
        return fileName;
    }
    
    
    
    
}



