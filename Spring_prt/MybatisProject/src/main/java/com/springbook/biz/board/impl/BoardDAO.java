package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.util.SqlSessionFactoryBean;

public class BoardDAO {
	private SqlSession mybatis;
	//MyBatis 에서 SQL등록은 boardMapping.xml 에서 하고 여기서 구현화 한다.
	public BoardDAO(){
		mybatis=SqlSessionFactoryBean.getSqlSessionInstance();
	}
	
	public void insertBoard(BoardVO vo){
		mybatis.insert("BoardDAO.insertBoard",vo);
		mybatis.commit();
	}
	public void updateBoard(BoardVO vo){
		mybatis.update("BoardDAO.updateBoard",vo);
		mybatis.commit();
	}
	public void deleteBoard(BoardVO vo){
		mybatis.delete("BoardDAO.deleteBoard",vo);
		mybatis.commit();
	}
	public BoardVO getBoard(BoardVO vo){
		return mybatis.selectOne("BoardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo){
		return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
}
