package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.service.impl.GetHomeworkService;

public class GetHomeworkServlet extends HttpServlet {

	/**
	 * @author larson
	 */
	private static final long serialVersionUID = -215061130863249125L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取客户端的最后接受时间
		String lastHomeworkPublishTime = request.getParameter("lastHomeworkPublishTime");
		
		String homework_str ="";
		//2.判断最后时间值，给出不同数据
		if ("0".equalsIgnoreCase(lastHomeworkPublishTime)){
			homework_str = GetHomeworkService.get2WeekHomework();
		}else{
			homework_str = GetHomeworkService.getAfterHomework(lastHomeworkPublishTime);
		}
		
		
//		System.out.println(lastHomeworkPublishTime+"-----"+GetHomeworkServlet.class.getSimpleName()+homework_str);
		
		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		dos.write(homework_str.getBytes("utf-8"));
		dos.flush();
		dos.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
