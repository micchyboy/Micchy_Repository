package com.jet.project;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Feeder_App_v1_0Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
