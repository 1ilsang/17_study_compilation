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
		ModelAndView mav = new ModelAndView();
		mav.addObject("board",board);
		mav.setViewName("getBoard");
		return mav;
	}

	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
		return "getBoard";
	}

}
*/