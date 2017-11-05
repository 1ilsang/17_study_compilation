/*package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class GetBoardController {
	
	@RequestMapping("/getBoard.do")
	public ModelAndView getBoardController(ModelAndView mav, BoardVO vo, BoardDAO boardDAO){
		mav.addObject("board",boardDAO.getBoard(vo));
		mav.setViewName("getBoard.jsp");
		return mav;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("�� �� ��ȸ ó��");
		
		//1. �˻��� �Խñ� ��ȣ ����
		String seq=request.getParameter("seq");

		//2. DB���� ó��
		BoardVO vo=new BoardVO(	);
		vo.setSeq(Integer.parseInt(seq));
		System.out.println(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
		//3. ���� ȭ�� ����
		ModelAndView mav = new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("getBoard");
		return mav;
	}

	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("�� �� ��ȸ ó��");
		
		//1. �˻��� �Խñ� ��ȣ ����
		String seq=request.getParameter("seq");

		//2. DB���� ó��
		BoardVO vo=new BoardVO(	);
		vo.setSeq(Integer.parseInt(seq));
		System.out.println(Integer.parseInt(seq));
		
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = boardDAO.getBoard(vo);
		
		//3. ���� ȭ�� ����
		HttpSession session = request.getSession();
		session.setAttribute("board", board);
		return "getBoard";
	}

}
*/