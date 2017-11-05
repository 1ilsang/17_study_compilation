/*package com.springbook.view.board;

import org.springframework.stereotype.Controller;

@Controller
public class DeleteBoardController {

	@RequestMapping("/deleteBoard.do")
	public String dleteBoardController(BoardVO vo, BoardDAO boardDAO){
		boardDAO.deleteBoard(vo);
		return "getBoardList.do";
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:getBoardList.do");
		return mav;
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
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
		return "getBoardList.do";
	}

}
*/