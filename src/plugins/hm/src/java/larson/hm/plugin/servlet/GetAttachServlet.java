package larson.hm.plugin.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAttachServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545523250008573721L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取到服务器文件地址
		String attachFilePath = request.getParameter("attachFilePath");
		
		//
		File file = new File(attachFilePath);
		if(!file.exists()){
			System.out.println("客户端请求的文件不存在");
			return;
		}
//			response.getOutputStream().write("文件不存在".getBytes());
		
		FileInputStream fis = new FileInputStream(file);
		ServletOutputStream sos = response.getOutputStream();
		int len;
		byte[] buf = new byte[2048];
		while((len = fis.read(buf))!=-1){
			sos.write(buf, 0, len);
		}
		fis.close();
		sos.close();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
