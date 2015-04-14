package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.service.impl.GetNoticeService;

public class GetNoticeServlet extends HttpServlet {

	private static final long serialVersionUID = 3552940972123802091L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.获取到客户端的最后一条记录的时间
		String lastNoticePublishTime = request
				.getParameter("lastNoticePublishTime");


		// 2.拿着这条记录比对数据库，如果是两天以外的就把两天以内的数据返回去，否则返回这条记录以后的数据
		//如果是否次运行（lastNoticePublishTime=0），则返回两周内的数据，否则返回时间之后的数据
		String notice_str = "";
		if ("0".equalsIgnoreCase(lastNoticePublishTime)){
			notice_str = GetNoticeService.get2WeekNotice();
		}else{
			notice_str = GetNoticeService.getAfterNotice(lastNoticePublishTime);
		}
		
//		System.out.println(GetNoticeServlet.class.getSimpleName()
//				+ ":&&lastNoticePublishTime:" + lastNoticePublishTime+notice_str);
		
		
		//3.往外面写数据
		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		dos.write(notice_str.getBytes("utf-8"));
		dos.flush();
		dos.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
