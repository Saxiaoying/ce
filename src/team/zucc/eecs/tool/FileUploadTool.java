package team.zucc.eecs.tool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SCPInputStream;
import ch.ethz.ssh2.SCPOutputStream;

@Component
public class FileUploadTool {
	public static Connection conn = null;

	String ip = "115.29.227.78";
	String name = "root";
	String password = "ZUCCbysj223~";
	public boolean login() {
		// 创建远程连接，默认连接端口为22，如果不使用默认，可以使用方法
		// new Connection(ip, port)创建对象
		conn = new Connection(ip);
		try {
			// 连接远程服务器
			conn.connect();
			// 使用用户名和密码登录
			return conn.authenticateWithPassword("root", "ZUCCbysj223~");
		} catch (IOException e) {
			System.err.printf("用户%s密码%s登录服务器%s失败！", "root", "ZUCCbysj223~", "115.29.227.78");
			e.printStackTrace();
		}
		return false;
	}

	 /**
     * download
     * @param remoteFile 服务器上的文件名
     * @param remoteTargetDirectory 服务器上文件的所在路径
     * @param newPath 下载文件的路径
     */
    public void downloadFile(String remoteFile, String remoteTargetDirectory,String newPath){
        Connection connection = new Connection(ip);
 
        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(name,password);
            if(isAuthenticated){
                SCPClient scpClient = connection.createSCPClient();
                SCPInputStream sis = scpClient.get(remoteTargetDirectory + "/" + remoteFile);
                File f = new File(newPath);
                if(!f.exists()){
                    f.mkdirs();
                }
                File newFile = new File(newPath + remoteFile);
                FileOutputStream fos = new FileOutputStream(newFile);
                byte[] b = new byte[4096];
                int i;
                while ((i = sis.read(b)) != -1){
                    fos.write(b,0, i);
                }
                fos.flush();
                fos.close();
                sis.close();
                connection.close();
                System.out.println("download ok");
            }else{
                System.out.println("连接建立失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    
    /**
     * 上传文件到服务器
     * @param f 文件对象
     * @param length 文件大小
     * @param remoteTargetDirectory 上传路径
     * @param mode 默认为null
     */
    public void uploadFile(File f, long length, String remoteTargetDirectory, String mode) {
        Connection connection = new Connection(ip);
 
        try {
            connection.connect();
            boolean isAuthenticated = connection.authenticateWithPassword(name,password);
            if(!isAuthenticated){
                System.out.println("连接建立失败");
                return ;
            }
            SCPClient scpClient = new SCPClient(connection);
            
            SCPOutputStream os = scpClient.put(f.getName(),length,remoteTargetDirectory,mode);
            byte[] b = new byte[4096];
            FileInputStream fis = new FileInputStream(f);
            int i;
            while ((i = fis.read(b)) != -1) {
                os.write(b, 0, i);
            }
            os.flush();
            fis.close();
            os.close();
            connection.close();
            System.out.println("upload ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String args[]) {
		FileUploadTool log = new FileUploadTool();
		//log.downloadFile("test1.txt", "/root/Desktop/ceData", "C:\\Users\\john\\Desktop\\");
		File f = new File("C:\\Users\\john\\Desktop\\test1.txt");
		log.uploadFile(f, f.length(), "/root/Desktop/ceData", null);
	}
}
