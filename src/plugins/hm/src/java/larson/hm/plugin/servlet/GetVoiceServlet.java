package larson.hm.plugin.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class GetVoiceServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9016351138224650161L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String path = request.getSession().getServletContext().getRealPath("/upload/audio");
		factory.setRepository(new File(path));
		factory.setSizeThreshold(1024*1024);
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try{
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
			for(FileItem item:list){
				String name = item.getFieldName();
				if(item.isFormField()){
					String value = item.getString();
					request.setAttribute(name, value);
				}
				else{ 
					String value = item.getName();
					int start = value.lastIndexOf("\\");
					String filename = value.substring(start+1);
					request.setAttribute(name, filename);
					
					OutputStream out = new FileOutputStream(new File(path,filename));
					InputStream in = item.getInputStream();
					
					int length = 0;
					byte[] buf = new byte[1024];
					System.out.println("文件大小为:"+ item.getSize());
					
					while((length = in.read(buf))!=-1){
						out.write(buf,0,length);
					}
					in.close();
					out.close();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
