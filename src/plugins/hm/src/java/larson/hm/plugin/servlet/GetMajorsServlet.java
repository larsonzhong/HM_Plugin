package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.service.impl.GetMajorService;

public class GetMajorsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String majors_str = GetMajorService.getMajors();
		System.out.println("GetMajorsServlet -------" +majors_str);

		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		dos.write(majors_str.getBytes("utf-8"));
		dos.flush();
		dos.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
