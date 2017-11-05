/*package com.springbook.view.board;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class InsertBoardController {
	
	//������̼� ��� ��Ʈ�ѷ� ����
	//POJO Ŭ������ ����Ǿ� void inserBoard(requst) �� �� �� �ִ�!!
	//@Controller�� �������Ƿ� @RequestMapping�� ���־�� ��.
	//HandlerMapping ���� �Ͱ� ����. insertBoard.do ��û�� �� �� �޼��� ����
	@RequestMapping(value="/insertBoard.do")
	//public void insertBoard(HttpServletRequest request){
	//VO ��ü ��ü�� �Ű������� �ع����� �˾Ƽ� ��ü�� ������ ������!!
	public String insertBoard(BoardVO vo, BoardDAO boardDAO){
		System.out.println("�� ��� ó��");
		
		//1. ����� �Է� ���� ����
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
		//�����̷�Ʈ�� ������ �ʹٸ�(URL���� �� req�Ⱥ�����)
		//return "redirect:getBoardList.do";
	}
	
// imple controller �������.
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("�� ��� ó��");
		
		//1. ����� �Է� ���� ����
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
		
		//3. ȭ�� �׺���̼�
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�� ��� ó��");
		
		//1. ����� �Է� ���� ����
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
		
		//3. ȭ�� �׺���̼�
		return "getBoardList.do";
	}

}
*/