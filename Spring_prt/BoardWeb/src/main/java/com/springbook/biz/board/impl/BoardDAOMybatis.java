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
	//��� 1. extends SqlSessionDaoSupport �ϰ� set~fac~ ������ �� getSqlSession() ���
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	//��� 2. mybatis.~ ���
	/*@Autowired
	private SqlSessionTemplate mybatis;
	*/
	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis�� insertBoard() ��� ó��");
		getSqlSession().insert("BoardDAO.insertBoard",vo);
		//mybatis.insert("BoardDAO.insertBoard",vo);
	}
	public void updateBoard(BoardVO vo){
		System.out.println("===> Mybatis�� updateBoard() ��� ó��");
		getSqlSession().update("BoardDAO.updateBoard",vo);
		//mybatis.update("BoardDAO.updateBoard",vo);
	}
	public void deleteBoard(BoardVO vo){
		System.out.println("===> Mybatis�� deleteBoard() ��� ó��");
		getSqlSession().delete("BoardDAO.deleteBoard",vo);
		//mybatis.delete("BoardDAO.deleteBoard",vo);
	}
	public BoardVO getBoard(BoardVO vo){
		System.out.println("===> Mybatis�� getBoard() ��� ó��");
		return (BoardVO) getSqlSession().selectOne("BoardDAO.getBoard",vo);
		//return (BoardVO) mybatis.selectOne("BoardDAO.getBoard",vo);
	}
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> Mybatis�� getBoardList() ��� ó��");
		return getSqlSession().selectList("BoardDAO.getBoardList",vo);
		//return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
}
