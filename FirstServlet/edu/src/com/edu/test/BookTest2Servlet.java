package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookOutput")
public class BookTest2Servlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		Book book = (Book)req.getAttribute("book");
		
		out.println("<h3>책 제목 : " + book.getTitle() + "</h3>");
		out.println("<h3>책 저자 : " + book.getAuthor() + "</h3>");
		out.println("<h3>출 판사 : " + book.getPublisher() + "</h3>");
		
		out.close();
	}
}
