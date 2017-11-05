package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
public static void main(String[] args) {
	//1.spring container start
	AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");
	
	//2. Spring �����̳ʷκ��� BoardServiceImple ��ü�� Lookup
	BoardService boardService = (BoardService) container.getBean("boardService");
	
	//3. �� ��� ��� �׽�Ʈ
	BoardVO vo = new BoardVO();
	//vo.setSeq(100);
	vo.setTitle("Ʈ������ �ȵ�");
	vo.setWriter("�Ƕ�");
	vo.setContent("1ilsang.blog.me");
	boardService.insertBoard(vo);
	
	//4. TEST[p
	List<BoardVO> boardList = boardService.getBoardList(vo);
	for(BoardVO board : boardList){
		System.out.println("---> "+ board.toString());
	}
	
	//5. CLOSE the Spring Container
	container.close();
}
}
