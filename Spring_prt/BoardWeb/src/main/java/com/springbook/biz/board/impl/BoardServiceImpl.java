package com.springbook.biz.board.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbook.biz.board.BoardService;
import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.LogAdvice;

@Service("boardService")
public class BoardServiceImpl implements BoardService{
	@Autowired
	//1 기본으로 하는 방법.
	//private BoardDAO boardDAO;
	//2 spring에 DB 올려서 사용할때
	//private BoardDAOSpring boardDAO;
	//3 Mybatis DB 사용
	private BoardDAOMybatis boardDAO;
	
	public void insertBoard(BoardVO vo) {
		/*if(vo.getSeq() == 0){
			throw new IllegalArgumentException("0번 글은 등록할 수 없습니다.");
		}*/
		boardDAO.insertBoard(vo);
	}

	public void updateBoard(BoardVO vo) {
		boardDAO.updateBoard(vo);
	}

	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	public BoardVO getBoard(BoardVO vo) {
		return boardDAO.getBoard(vo);
	}

	@Override
	public List<BoardVO> getBoardList(BoardVO vo) {
		return boardDAO.getBoardList(vo);
	}
	
	
}
