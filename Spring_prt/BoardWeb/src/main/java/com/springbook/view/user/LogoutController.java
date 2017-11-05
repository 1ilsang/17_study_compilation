package com.springbook.view.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	//Spring 컨테이너엔 없는게 없는듯 하다.
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "login.jsp";
	}
	
	/*@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("로그아웃 처리");
		
		//1. 브라우저와 연결된 세션 객체를 강제 종료
		HttpSession session = request.getSession();
		session.invalidate();
		
		//2 세션 종료후, 메인 화면으로 이동.
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:login.jsp");
		return mav;
	}
*/
	/*@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("로그아웃 처리");
		
		//1. 브라우저와 연결된 세션 객체를 강제 종료
		HttpSession session = request.getSession();
		session.invalidate();
		
		//2 세션 종료후, 메인 화면으로 이동.
		return "login";
	}
*/
}
