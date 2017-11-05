/*package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class GetBoardListController {
	
	//Spring ��� ��Ʈ�ѷ�. �̳� ����������!!
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardListController(ModelAndView mav, BoardVO vo, BoardDAO boardDAO){
		mav.addObject("boardList",boardDAO.getBoardList(vo));
		mav.setViewName("getBoardList.jsp");
		return mav;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse response) throws Exception {
		System.out.println("GetBoardListController]");
		
		//1. ����� �Է� ���� ����(�˻� ����� ������ ����)
		//2. DB
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. �˻� ����� ȭ�� ������ MAV�� ������ ����.
		//������ session �� �����ϴ� ���� mav �� ������ ����!!
		// �̴� ���� �޸� �����(���� ����)
		//mav Ŭ������ httpServletRequest ��ü�� �˻�����. JSP�� ������
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",boardList); // Model ���� ����
		//view page ����
		mav.setViewName("getBoardList");
		return mav;
	}

	
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("GetBoardListController]");
		
		//1. ����� �Է� ���� ����(�˻� ����� ������ ����)
		//2. DB
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. return
		HttpSession session = req.getSession();
		session.setAttribute("boardList", boardList);
		return "getBoardList";
		
	}

}
*/