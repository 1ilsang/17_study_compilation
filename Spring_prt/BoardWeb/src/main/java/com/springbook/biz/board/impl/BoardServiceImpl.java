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
	//1 �⺻���� �ϴ� ���.
	//private BoardDAO boardDAO;
	//2 spring�� DB �÷��� ����Ҷ�
	//private BoardDAOSpring boardDAO;
	//3 Mybatis DB ���
	private BoardDAOMybatis boardDAO;
	
	public void insertBoard(BoardVO vo) {
		/*if(vo.getSeq() == 0){
			throw new IllegalArgumentException("0�� ���� ����� �� �����ϴ�.");
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
