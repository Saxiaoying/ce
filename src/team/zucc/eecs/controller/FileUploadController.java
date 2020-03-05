package team.zucc.eecs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import team.zucc.eecs.tool.FileUploadTool;

@Controller("FileUploadController")
public class FileUploadController {
	private FileUploadTool fileUploadTool;
	
    private final String SAVE_DIR = "C:/Users/john/Desktop/";
    
    @RequestMapping(value="/upload", method=RequestMethod.POST)
    @ResponseBody  
    public String upload(HttpServletRequest request)
            throws IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        String fileName = null;
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile myfile = entity.getValue();
            fileName = "test"+ myfile.getOriginalFilename();
            byte[] bs = myfile.getBytes();
            File file = new File(SAVE_DIR + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bs);
            fos.close();
            
            
            
            fileName = myfile.getName();
            System.out.println(fileName); 
            File f = new File(fileName);
            System.out.println(f.length()); 
            fileUploadTool.uploadFile(f, f.length(), "/root/Desktop/ceData", null);
        }
        return fileName;
    }
}



