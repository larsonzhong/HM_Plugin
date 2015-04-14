package larson.hm.plugin.servlet;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import larson.hm.plugin.service.impl.GetSubjectService;

public class GetSubjectsServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1486232154965762139L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String subjects_str = GetSubjectService.getSubjects();
		System.out.println("GetSubjectsServlet------"+subjects_str);

		DataOutputStream dos = new DataOutputStream(response.getOutputStream());
		dos.write(subjects_str.getBytes("utf-8"));
		dos.flush();
		dos.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
