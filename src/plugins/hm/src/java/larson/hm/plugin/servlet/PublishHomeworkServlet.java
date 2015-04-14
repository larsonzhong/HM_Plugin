package larson.hm.plugin.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.util.PublishResultTool;

/**
 * 一次请求包括，客户端先发作业信息，判断作业信息是否含有图片，
 * 有的话就请求客户端发送图片，客户端图片发送完毕后，服务端接收到重新存储并命名，继续问客户端是不是
 * 还要发送音频文件，是的话继续接受，接收完毕就告诉客户端接收成功，并把这条记录插入到数据库
 * 
 * @author larson
 * 
 */
public class PublishHomeworkServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5724603896083991619L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String upload_path = this.getServletContext().getRealPath("/upload/");
		PublishResultTool tool = new PublishResultTool();
		String result = tool.readHomework(request,upload_path);

		// 1.获取输出流
		PrintWriter out = response.getWriter();
		InputStream in = request.getInputStream();
		// 2.处理客户端发送的数据

		System.out.println(result + "-------------publish servlet");
		// 3.向客户端写相应数据
		out.write(result);
		out.flush();

		out.close();
		in.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
