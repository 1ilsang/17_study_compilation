/*package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class InsertBoardController {
	
	//어노테이션 기반 컨트롤러 설정
	//POJO 클래스로 변경되어 void inserBoard(requst) 로 할 수 있다!!
	//@Controller를 해줬으므로 @RequestMapping을 해주어야 함.
	//HandlerMapping 해준 것과 같음. insertBoard.do 요청일 때 이 메서드 실행
	@RequestMapping(value="/insertBoard.do")
	//public void insertBoard(HttpServletRequest request){
	//VO 객체 자체를 매개변수로 해버리면 알아서 객체에 세팅해 보낸다!!
	public String insertBoard(BoardVO vo, BoardDAO boardDAO){
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
		return "getBoardList.do";
		//리다이렉트로 보내고 싶다면(URL변경 및 req안보내짐)
		//return "redirect:getBoardList.do";
	}
	
// imple controller 해줘야함.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
		return "getBoardList.do";
	}

}
*/