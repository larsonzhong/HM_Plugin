package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.bean.Notice;
import larson.hm.plugin.dao.impl.NoticeDao;
import larson.hm.plugin.util.Constant;

public class PublishNoticeServlet extends HttpServlet {

	/**
	 * tomcat在接受URL的时候是以iso-8859-1的编码方式进行编码的,在服务器端接收到数据后要用  
	 *       new String(name.getBytes(“iso-8859-1),”utf-8”)    这种方式进行转码
	 */
	private static final long serialVersionUID = 3723384058366836368L;
	
	private String PUBLISH_SUCCESS = "succeed";
	private String PUBLISH_FAILED = "failed";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1。获取到数据
		String title = new String(request.getParameter("title").trim().getBytes("iso-8859-1"),"utf-8");
		String content = new String(request.getParameter("content").trim().getBytes("iso-8859-1"),"utf-8");
		String time =  request.getParameter("publishTime").trim();
		Long publishTime = Long.parseLong(time);
		
		System.out.println("PublishNoticeServlet------------"+title+"---"+content+"-----"+publishTime);
		
		//2.拿到输出流
		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		
		//3.验证数据保存到数据库,并将处理结果返回给用户
		String result = publishNotice(title, content,publishTime);
		dos.writeBytes(result);
		dos.flush();
		dos.close();
	}


	private String publishNotice(String title, String content, long publishTime) {
		if(title==null||title.length()<=0||content==null||content.length()<=0)
			return PUBLISH_FAILED ;
		NoticeDao dao = new NoticeDao();
		boolean result = dao.add(new Notice(title, content,publishTime, Constant.TYPE_NOTICE));
		if(result==true)
			return PUBLISH_SUCCESS;
		return PUBLISH_FAILED;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
