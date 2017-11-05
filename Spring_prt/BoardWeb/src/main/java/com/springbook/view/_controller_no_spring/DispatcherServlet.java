/*package com.springbook.view._controller_no_spring;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.startup.ContextConfig;

*//**
 * Servlet implementation class DispatcherServlet
 *//*
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HandlerMapping handlerMapping;
	private ViewResolver viewResolver;
	private String contextConfigLocation;
	
	public void init(ServletConfig config) throws ServletException{
		//Spring 컨테이너 사용 
		//초기화 파일 설정. init-param
		contextConfigLocation = config.getInitParameter("contextConfigLocation");
		
		//MVC2 사용
		handlerMapping = new HandlerMapping();
		viewResolver = new ViewResolver();
		viewResolver.setPrefix("./");
		viewResolver.setSuffix(".jsp");
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("EUC-KR");
		process(request, response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//MVC2 Dispatcher 분리
		//1. 클라이언트의 요청 path 정보를 추출
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		//2. HandlerMapping을 통해 path에 해당하는 컨트롤러 검색
		Controller ctrl = handlerMapping.getController(path);
		
		//3. 검색된 ctrl를 실행
		String viewName = ctrl.handleRequest(request, response);
		
		//4. ViewResolver를 통해 viewName에 해당하는 화면을 검색
		String view = null;
		if(!viewName.contains(".do")){
			view = viewResolver.getview(viewName);
		}else{
			view = viewName;
		}
		
		//5 검색된 화면으로 이동
		response.sendRedirect(view);
		/////////////////////////////////////////////////////////////////////////////////////////
		//MVC 2 Pattern
		//1 클라이언트의 요청 path 정보를 추출
		String uri=request.getRequestURI();
		String path=uri.substring(uri.lastIndexOf("/"));
		System.out.println(path);
		
		//2. 클라이언트의 요청 path에 따라 적절히 분기처리
		if(path.equals("/login.do")){
			System.out.println("로그인 처리");
			
			//1. 사용자 입력 정보 추출
			String id=request.getParameter("id");
			String password=request.getParameter("passowrd");
			
			//2. DB연동
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			
			//3. 화면 네비게이션
			if(user != null){
				response.sendRedirect("getBoardList.do");
			}else{
				response.sendRedirect("login.jsp");
			}
		}else if(path.equals("/logout.do")){
			System.out.println("로그아웃 처리");
			
			//1. 브라우저와 연결된 세션 객체를 강제 종료
			HttpSession session = request.getSession();
			session.invalidate();
			
			//2 세션 종료후, 메인 화면으로 이동.
			response.sendRedirect("login.jsp");
		}else if(path.equals("/insertBoard.do")){
			System.out.println("글 등록 처리");
			
			//1. 사용자 입력 정보 추출
			//request.setCharacterEncoding("EUC-KR");
			String title=request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			//2. DB
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAO boardDAO= new BoardDAO();
			boardDAO.insertBoard(vo);
			
			//3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/updateBoard.do")){
			System.out.println("글 수정 처리");
			
			//1. 사용자 입력 정보 추출
			String title=request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			
			//2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.updateBoard(vo);
			
			//3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/deleteBoard.do")){
			System.out.println("글 삭제 처리");
			
			//1. 사용자 입력 정보 추출
			String seq = request.getParameter("seq");
			System.out.println(request.getParameter("seq"));
			System.out.println(seq);
			//2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAO boardDAO = new BoardDAO();
			boardDAO.deleteBoard(vo);
			
			//3. 화면 네비게이션
			response.sendRedirect("getBoardList.do");
		}else if(path.equals("/getBoard.do")){
			System.out.println("글 상세 조회 처리");
			
			//1. 검색할 게시글 번호 추출
			String seq=request.getParameter("seq");

			//2. DB연동 처리
			BoardVO vo=new BoardVO(	);
			vo.setSeq(Integer.parseInt(seq));
			System.out.println(Integer.parseInt(seq));
			BoardDAO boardDAO = new BoardDAO();
			BoardVO board = boardDAO.getBoard(vo);
			
			//3. 응답 화면 구성
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");
			
		}else if(path.equals("/getBoardList.do")){
			System.out.println("글 목록 검색 처리");
			
			//1. 사용자 입력 정보 추출(검색 기능은 나중에 구현)
			//2. DB 연동 처리
			BoardVO vo = new BoardVO();
			BoardDAO boardDAO = new BoardDAO();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			//3. 검색 결과를 세션에 저장하고 목록 화면으로 이동.
			//세션은 데이터 많으면 부담됨. 이 부분은 springMVC할때 고쳐질 내용.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			response.sendRedirect("getBoardList.jsp");
		}
	
	}
}
*/