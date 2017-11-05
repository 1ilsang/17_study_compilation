package com.springbook.view.board;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springbook.biz.board.BoardListVO;
import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;

@Controller
//Model에 "board"라는 이름으로 저장되는 데이터가 있다면 그 데이터를 세션에도 자동 저장하란 뜻
@SessionAttributes("board")
public class BoardController {
	@Autowired
	//boardDAO를 직접 거치지 않도록 해주는 것. 의존성 주입(Buisiness Component
	private BoardService boardService;
	
		//어노테이션 기반 컨트롤러 설정
		//ModelAttribute 는 RequestMapping 어노테이션보다 먼저 호출됨.
	//conditionMap 에 모델 Atrbute를 저장. 
	@ModelAttribute("conditionMap")
	public Map<String, String>searchConditionMap(){
		Map<String, String> conditionMap=new HashMap<String, String>();
		conditionMap.put("제목","TITLE");
		conditionMap.put("내용", "CONTENT");
		return conditionMap;
	}
	
	@RequestMapping("/dataTransform.do")
	//자바 객체를 Http 응답 프로토콜의 몸체로 변환하기위해 ResponseBody 사용.
	//pre-layer.xml의 mvc:annotation-driven 이 MappingJackson2HttpMessageConverter을 위해 추가
	//JSON으로 변환해 HTTP응답 BODY
	/*@ResponseBody
	public List<BoardVO> dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		return boardList;
	}*/
	//JSON이 아닌 XML으로 변환할때
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO=new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
		//POJO 클래스로 변경되어 void inserBoard(requst) 로 할 수 있다!!
		//@Controller를 해줬으므로 @RequestMapping을 해주어야 함.
		//HandlerMapping 해준 것과 같음. insertBoard.do 요청일 때 이 메서드 실행
		//컨트롤러가 계속 늘어남에 따라 분리했던 클래스들을 다시 한 페이지에 통합하게 됨
	//(코드 분량이 짧아짐으로써.. 따라서 더 보기 간결해짐)
		@RequestMapping(value="/insertBoard.do")
		public String insertBoard(BoardVO vo) throws IOException{
			//BoardService 클래스를 활용한 모습. (유지보수가 더 쉬워짐. 아래도 다 이렇게)
			System.out.println("글 등록 처리");
			//FileUpload process
			MultipartFile uploadFIle=vo.getUploadFile();
			if(!uploadFIle.isEmpty()){
				String fileName=uploadFIle.getOriginalFilename();
				//USB에 제대로 들어감. 신기하다!
				uploadFIle.transferTo(new File("D:/"+fileName));
			}
			boardService.insertBoard(vo);
			return "getBoardList.do";
		}
/*		public String insertBoard(BoardVO vo, BoardDAO boardDAO){
			System.out.println("글 등록 처리");
			boardDAO.insertBoard(vo);
			return "getBoardList.do";
			//리다이렉트로 보내고 싶다면(URL변경 및 req안보내짐)
			//return "redirect:getBoardList.do";
		}
*/		
		@RequestMapping("/deleteBoard.do")
		public String delteBoardController(BoardVO vo){
			boardService.deleteBoard(vo);
			return "getBoardList.do";
		}
		
		@RequestMapping("/getBoard.do")
		//string 반환으로 하는게 일관성에 좋음. 따라서 MAV에서 model 만 받아 값 전달.
		public String getBoard(BoardVO vo, Model model){
			model.addAttribute("board",boardService.getBoard(vo));
			return "getBoard.jsp";
		}
		/*@RequestMapping("/getBoard.do")
		public ModelAndView getBoardController(ModelAndView mav, BoardVO vo, BoardDAO boardDAO){
			mav.addObject("board",boardDAO.getBoard(vo));
			mav.setViewName("getBoard.jsp");
			return mav;
		}*/
		
		//Spring 기반 컨트롤러. 겁나 간단해졌다!!
		@RequestMapping("/getBoardList.do")
		public String getBoardListController(Model model, BoardVO vo){
			//검색 기능 구현
			//Null Check -> Login한 직후거나 목록가기 등에서 검색 타이틀을 초기화.
			if(vo.getSearchCondition()==null)vo.setSearchCondition("TITLE");
			if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");

			//Model 정보 저장
			model.addAttribute("boardList",boardService.getBoardList(vo));
			return "getBoardList.jsp";
		}
		
		//sessionAttribute 사용하기.
		@RequestMapping("/updateBoard.do")
		public String updateBoard(@ModelAttribute("board") BoardVO vo){
			//session을 사용했기 때문에 수정된 값이 바로 넘어온!
			System.out.println("제목 : " + vo.getTitle());
			boardService.updateBoard(vo);
			return "getBoardList.do";
		}
}
