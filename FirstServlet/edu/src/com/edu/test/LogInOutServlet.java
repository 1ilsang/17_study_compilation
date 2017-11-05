package com.edu.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logProc")
public class LogInOutServlet extends HttpServlet{
	
	//LogIn
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		if(id.isEmpty() || pwd.isEmpty()){
			out.println("ID or Pwd 를 넣");
			return;
		}
		HttpSession session = req.getSession();
		if(session.isNew()||session.getAttribute("id")==null){
			session.setAttribute("id", id);
			out.println("로그인을 완료하였습니다.");
		}else{
			out.println("현재 로그인 상태입니다.");
		}
	}
	
	//LogOut
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("id")!=null){
			session.invalidate();
			out.println("로그아웃 작업 완료");
		}else{
			out.println("현재 로그인 상태가 아님.");
		}
		out.close();
	}
}
