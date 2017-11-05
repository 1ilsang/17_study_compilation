package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/errorHandle")
public class ErrorHandleServlet extends HttpServlet{
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html;charset=UTF-8");
	PrintWriter out = resp.getWriter();
	
	Integer code = (Integer) req.getAttribute("javax.servlet.error.status_code");
	String message = (String) req.getAttribute("javax.sevlet.error.message");
	Object type = req.getAttribute("javax.servlet.error.exception_type");
	Throwable exception = (Throwable) req.getAttribute("javax.servlet.error.exception");
	String uri = (String) req.getAttribute("javax.servlet.error.request_uri");
	
	out.println("<h2>Error Code        : " + code + "</h2>");
	out.println("<h2>Error Message : " + message + "</h2>");
	out.println("<h2>Error Type         : " + type + "</h2>");
	out.println("<h2>Error Object     : " + exception + "</h2>");
	out.println("<h2>Error URI           : " + uri + "</h2>");
	
	out.close();
}
}
