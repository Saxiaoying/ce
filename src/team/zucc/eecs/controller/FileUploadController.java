package team.zucc.eecs.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.tool.FileUploadTool;

@Controller("FileUploadController")
public class FileUploadController {
	private FileUploadTool fileUploadTool = new FileUploadTool();
	
	 @RequestMapping("/uploadFile")
	    @ResponseBody
	    public JSONObject uploadFile(MultipartFile[] file, HttpSession session) {
	    	System.out.println("进入FileUploadController-uploadFile");
	    	JSONObject obj = new JSONObject();
	    	List<String> fileNameList_new = new ArrayList<String>();
	    	List<String> fileNameList_old = new ArrayList<String>();
	    	List<Timestamp> timeList = new ArrayList<Timestamp>();
	    	try {
	    		for (MultipartFile files : file) {
	    			 String fileName = files.getOriginalFilename();
	    			 String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	    			if(suffix.compareTo("docx") != 0 && suffix.compareTo("doc") != 0 &&suffix.compareTo("xls") != 0 &&suffix.compareTo("xlsx") != 0) {
	    				obj.put("state", "仅支持上传doc/docx/xls/xlsx格式的文件，请重新选择！");
	    				return obj;
	    			}
	    		}
	    		int num = 0;
	            for (MultipartFile files : file){
	                //获取文件名
	                String fileName = files.getOriginalFilename();
	                fileNameList_old.add(fileName);
	                
	                //获取文件的后缀名
	                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
	                
	                //给需要上传的文件起名字
	                String newFileName=String.valueOf(System.currentTimeMillis())+"."+suffix;
	                fileNameList_new.add(newFileName);
	                
	                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	                timeList.add(timestamp);
	                
	                System.out.println("文件名:"+newFileName);
	                System.out.println("文件后缀:"+suffix);
	                System.out.println("文件大小:"+files.getSize()/1024+"KB");
	                
	                //创建要保存文件的路径
	                String dir =  session.getServletContext().getRealPath("/tmp-file"); //获取服务器上传的文件路径
	                System.out.println(dir);
	                File dirFile = new File(dir,newFileName);
	                if (!dirFile.exists()){
	                    dirFile.mkdirs();
	                }
	                try {
	                    //将文件写入创建的路径
	                    files.transferTo(dirFile);
	                    System.out.println("文件保存成功~");
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	                fileUploadTool.uploadFile(dirFile, dirFile.length(), "/root/Desktop/ceData", null);
	                dirFile.delete();
	                num ++;
	            }
	            obj.put("state", "OK");
	            obj.put("fileNameList_new", fileNameList_new);
	            obj.put("fileNameList_old", fileNameList_old);
	            obj.put("timeList", timeList);
	            obj.put("num", num);
			} catch (Exception e) {
				e.printStackTrace();
				obj.put("state", "执行错误！");
			}
	        
	        return obj;
	    }    
	/*
	 * @RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public String fileUpload(HttpServletRequest request) throws
	 * IOException { System.out.println("进入FileUploadController-fileUpload");
	 * 
	 * 
	 * MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)
	 * request; Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
	 * String fileName = null; for (Map.Entry<String, MultipartFile> entity :
	 * fileMap.entrySet()) { MultipartFile myfile = entity.getValue();
	 * 
	 * byte[] bs = myfile.getBytes(); fileName = myfile.getOriginalFilename();
	 * 
	 * 
	 * SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式 String
	 * timeString = df.format(new Date()); System.out.println(timeString);// new
	 * Date()为获取当前系统时间
	 * 
	 * File f = new File(TMP_SAVE_DIR + timeString + fileName);
	 * 
	 * FileOutputStream fos = null; try { fos = new FileOutputStream(f);
	 * fos.write(bs); // 写入文件 } catch (Exception e) { e.printStackTrace(); } finally
	 * { try { fos.close(); } catch (IOException e) { e.printStackTrace(); } }
	 * fileUploadTool.uploadFile(f, f.length(), "/root/Desktop/ceData", null);
	 * f.delete(); } return fileName; }
	 * 
	 * 
	 * 
	 * 
	 * @RequestMapping(value="/fileUpload-CourseIntroduction",
	 * method=RequestMethod.POST)
	 * 
	 * @ResponseBody public JSONObject CourseIntroduction(@RequestBody MultipartFile
	 * files) throws IOException {
	 * System.out.println("进入FileUploadController-fileUpload-CourseIntroduction");
	 * 
	 * JSONObject obj = new JSONObject(); String fileName = null; MultipartFile
	 * myfile = files; System.out.println(myfile); byte[] bs = myfile.getBytes();
	 * fileName = myfile.getOriginalFilename();
	 * 
	 * 
	 * SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式 String
	 * timeString = df.format(new Date()); System.out.println(timeString);// new
	 * Date()为获取当前系统时间
	 * 
	 * File f = new File(TMP_SAVE_DIR + timeString + fileName);
	 * 
	 * FileOutputStream fos = null; try { fos = new FileOutputStream(f);
	 * fos.write(bs); // 写入文件 } catch (Exception e) { e.printStackTrace(); } finally
	 * { try { fos.close(); } catch (IOException e) { e.printStackTrace(); } }
	 * fileUploadTool.uploadFile(f, f.length(), "/root/Desktop/ceData", null);
	 * fileName = f.getName(); f.delete(); obj.put("state", "OK");
	 * obj.put("fileName", fileName); return obj; }
	 */
    
    
}



