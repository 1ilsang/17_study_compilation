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
//Model�� "board"��� �̸����� ����Ǵ� �����Ͱ� �ִٸ� �� �����͸� ���ǿ��� �ڵ� �����϶� ��
@SessionAttributes("board")
public class BoardController {
	@Autowired
	//boardDAO�� ���� ��ġ�� �ʵ��� ���ִ� ��. ������ ����(Buisiness Component
	private BoardService boardService;
	
		//������̼� ��� ��Ʈ�ѷ� ����
		//ModelAttribute �� RequestMapping ������̼Ǻ��� ���� ȣ���.
	//conditionMap �� �� Atrbute�� ����. 
	@ModelAttribute("conditionMap")
	public Map<String, String>searchConditionMap(){
		Map<String, String> conditionMap=new HashMap<String, String>();
		conditionMap.put("����","TITLE");
		conditionMap.put("����", "CONTENT");
		return conditionMap;
	}
	
	@RequestMapping("/dataTransform.do")
	//�ڹ� ��ü�� Http ���� ���������� ��ü�� ��ȯ�ϱ����� ResponseBody ���.
	//pre-layer.xml�� mvc:annotation-driven �� MappingJackson2HttpMessageConverter�� ���� �߰�
	//JSON���� ��ȯ�� HTTP���� BODY
	/*@ResponseBody
	public List<BoardVO> dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		return boardList;
	}*/
	//JSON�� �ƴ� XML���� ��ȯ�Ҷ�
	@ResponseBody
	public BoardListVO dataTransform(BoardVO vo){
		vo.setSearchCondition("TITLE");
		vo.setSearchKeyword("");
		List<BoardVO> boardList = boardService.getBoardList(vo);
		BoardListVO boardListVO=new BoardListVO();
		boardListVO.setBoardList(boardList);
		return boardListVO;
	}
		//POJO Ŭ������ ����Ǿ� void inserBoard(requst) �� �� �� �ִ�!!
		//@Controller�� �������Ƿ� @RequestMapping�� ���־�� ��.
		//HandlerMapping ���� �Ͱ� ����. insertBoard.do ��û�� �� �� �޼��� ����
		//��Ʈ�ѷ��� ��� �þ�� ���� �и��ߴ� Ŭ�������� �ٽ� �� �������� �����ϰ� ��
	//(�ڵ� �з��� ª�������ν�.. ���� �� ���� ��������)
		@RequestMapping(value="/insertBoard.do")
		public String insertBoard(BoardVO vo) throws IOException{
			//BoardService Ŭ������ Ȱ���� ���. (���������� �� ������. �Ʒ��� �� �̷���)
			System.out.println("�� ��� ó��");
			//FileUpload process
			MultipartFile uploadFIle=vo.getUploadFile();
			if(!uploadFIle.isEmpty()){
				String fileName=uploadFIle.getOriginalFilename();
				//USB�� ����� ��. �ű��ϴ�!
				uploadFIle.transferTo(new File("D:/"+fileName));
			}
			boardService.insertBoard(vo);
			return "getBoardList.do";
		}
/*		public String insertBoard(BoardVO vo, BoardDAO boardDAO){
			System.out.println("�� ��� ó��");
			boardDAO.insertBoard(vo);
			return "getBoardList.do";
			//�����̷�Ʈ�� ������ �ʹٸ�(URL���� �� req�Ⱥ�����)
			//return "redirect:getBoardList.do";
		}
*/		
		@RequestMapping("/deleteBoard.do")
		public String delteBoardController(BoardVO vo){
			boardService.deleteBoard(vo);
			return "getBoardList.do";
		}
		
		@RequestMapping("/getBoard.do")
		//string ��ȯ���� �ϴ°� �ϰ����� ����. ���� MAV���� model �� �޾� �� ����.
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
		
		//Spring ��� ��Ʈ�ѷ�. �̳� ����������!!
		@RequestMapping("/getBoardList.do")
		public String getBoardListController(Model model, BoardVO vo){
			//�˻� ��� ����
			//Null Check -> Login�� ���İų� ��ϰ��� ��� �˻� Ÿ��Ʋ�� �ʱ�ȭ.
			if(vo.getSearchCondition()==null)vo.setSearchCondition("TITLE");
			if(vo.getSearchKeyword()==null) vo.setSearchKeyword("");

			//Model ���� ����
			model.addAttribute("boardList",boardService.getBoardList(vo));
			return "getBoardList.jsp";
		}
		
		//sessionAttribute ����ϱ�.
		@RequestMapping("/updateBoard.do")
		public String updateBoard(@ModelAttribute("board") BoardVO vo){
			//session�� ����߱� ������ ������ ���� �ٷ� �Ѿ��!
			System.out.println("���� : " + vo.getTitle());
			boardService.updateBoard(vo);
			return "getBoardList.do";
		}
}
