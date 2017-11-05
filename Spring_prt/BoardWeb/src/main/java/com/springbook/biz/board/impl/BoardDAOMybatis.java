package com.springbook.biz.board.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOMybatis extends SqlSessionDaoSupport{
	//방법 1. extends SqlSessionDaoSupport 하고 set~fac~ 재정의 및 getSqlSession() 사용
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	//방법 2. mybatis.~ 사용
	/*@Autowired
	private SqlSessionTemplate mybatis;
	*/
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 기능 처리");
		getSqlSession().insert("BoardDAO.insertBoard",vo);
		//mybatis.insert("BoardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo){
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		getSqlSession().update("BoardDAO.updateBoard",vo);
		//mybatis.update("BoardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo){
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		getSqlSession().delete("BoardDAO.deleteBoard",vo);
		//mybatis.delete("BoardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo){
		System.out.println("===> Mybatis로 getBoard() 기능 처리");
		return (BoardVO) getSqlSession().selectOne("BoardDAO.getBoard",vo);
		//return (BoardVO) mybatis.selectOne("BoardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> Mybatis로 getBoardList() 기능 처리");
		return getSqlSession().selectList("BoardDAO.getBoardList",vo);
		//return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
}
