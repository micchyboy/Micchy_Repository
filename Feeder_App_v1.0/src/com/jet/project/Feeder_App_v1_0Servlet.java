package com.jet.project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Feeder_App_v1_0Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		resp.sendRedirect(req.getContextPath() + "/index.html");
//		req.getRequestDispatcher("/index.html").forward(req, resp);;
	}
}
