package com.springbook.view.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springbook.biz.user.UserVO;
import com.springbook.biz.user.Impl.UserDAO;

//import com.springbook.view.controller.Controller;

//컨트롤러가 자동적으로 있다. 준나신기
@Controller
public class LoginController {
	
	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginView(UserVO vo){
		System.out.println("로그인 화면으로 이동");
		vo.setId("test");
		vo.setPassword("test123");
		return "login.jsp";
	}
	//로그인 성공시 세션에 사용자 이름을 저장.->getBoardList
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(UserVO vo, UserDAO userDAO, HttpSession session){
		if(vo.getId()==null||vo.getId().equals("")){
			throw new IllegalArgumentException("아이디는 반드시 입력해야 합니다.");
		}
		UserVO user = userDAO.getUser(vo);
		if(user!=null){
			session.setAttribute("userName", user.getName());
			return "getBoardList.do";
		}else{
			return "login.jsp";
		}
	}
	/*@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginController(UserVO vo, UserDAO userDAO){
		System.out.println("로그인 인증 처리...");
		if(userDAO.getUser(vo) != null)return "getBoardList.do";
		else return "login.jsp";
	}*/
	
	/*@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse response) throws Exception {
	System.out.println("Login Process");
		
		//1. user input data extration
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//2. DB
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		//3. return
		ModelAndView mav = new ModelAndView();
		if(user != null){
			System.out.println(user.getId() + "로그인");
			mav.setViewName("redirect:getBoardList.do");
		}else{
			System.out.println("Login Fail...");
			mav.setViewName("redirect:login.jsp");
		}
		return mav;
	}*/

	
	/*//spring 을 사용하지 않을 때. 기본 MVC2
	 * @Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("Login Process");
		
		//1. user input data extration
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		
		//2. DB
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPassword(password);
		
		UserDAO userDAO = new UserDAO();
		UserVO user = userDAO.getUser(vo);
		
		//3. return
		if(user != null){
			System.out.println(user.getId() + "로그인");
			return "getBoardList.do";
		}else{
			System.out.println("Login Fail...");
			return "login";
		}
	}*/
	
	
}
