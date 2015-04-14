package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.service.impl.LoginService;

public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取到用户名和密码
		String username = new String(request.getParameter("username").trim().getBytes("iso-8859-1"),"utf-8");
		String password = new String(request.getParameter("password").trim().getBytes("iso-8859-1"),"utf-8");
		String majorName = new String(request.getParameter("majorName").trim().getBytes("iso-8859-1"),"utf-8");
		
		System.out.println(username + "----------------------" + password+"---------------"+majorName);
		//2.获取输出流
		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		
		//3.开始验证，并把结果写回去(如果成功就返回user数据，否则返回错误信息)
		String result = LoginService.Validate(username, password,majorName);
		
		dos.write(result.getBytes("utf-8"));
		dos.flush();
		dos.close();
		
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
