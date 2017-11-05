/*package com.springbook.view.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.board.impl.BoardDAO;

@Controller
public class GetBoardListController {
	
	//Spring 기반 컨트롤러. 겁나 간단해졌다!!
	@RequestMapping("/getBoardList.do")
	public ModelAndView getBoardListController(ModelAndView mav, BoardVO vo, BoardDAO boardDAO){
		mav.addObject("boardList",boardDAO.getBoardList(vo));
		mav.setViewName("getBoardList.jsp");
		return mav;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest req, HttpServletResponse response) throws Exception {
		System.out.println("GetBoardListController]");
		
		//1. 사용자 입력 정보 추출(검색 기능은 나증에 구현)
		//2. DB
		BoardVO vo = new BoardVO();
		BoardDAO boardDAO = new BoardDAO();
		List<BoardVO> boardList = boardDAO.getBoardList(vo);
		
		//3. 검색 결과와 화면 정보를 MAV에 저장해 리턴.
		//기존에 session 에 저장하던 것을 mav 에 저장해 리턴!!
		// 이는 세션 메모리 절약용(서버 부하)
		//mav 클래스는 httpServletRequest 객체도 검색가능. JSP로 포워딩
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList",boardList); // Model 정보 저장
		//view page 저장
		mav.setViewName("getBoardList");
		return mav;
	}

	
	
	@Override
	public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("GetBoardListController]");
		
		//1. 사용자 입력 정보 추출(검색 기능은 나증에 구현)
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