/*package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
	//Mybatis 연동시 자바코드는 한두줄로 끝나버림!(Data Mapper)
	//심지어 여기엔 SQL문이 없음. java코드와 별개
	//board-mapping.xml에 쿼리문 작성.
	public List<BoardVO> getBoardList(BoardVO vo){
		SqlSession mybatis = SqlSessionFactoryBean.getSqlSessionInstance();
		return mybatis.selectList("BoardDAO.getBoardList",vo);
	}
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content) values((select nvl(max(seq),0)+1 from board),?,?,?)";
	private final String BOARD_UPDATE = "update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE = "delete board where seq=?";
	private final String BOARD_GET = "select * from board where seq=?";
	private final String BOARD_LIST = "select * from board order by seq desc";
	private final String BOARD_LIST_T = "SELECT * FROM BOARD WHERE TITLE LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
	private final String BOARD_LIST_C = "SELECT * FROM BOARD WHERE CONTENT LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
	
	public void insertBoard(BoardVO vo){
		System.out.println("===> JDBC로 insertBoard() 처리");
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeQuery();
		}catch(Exception e){
			System.out.println("insertBoard Er : " + e);
		}finally{
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void updateBoard(BoardVO vo){
		System.out.println("===> JDBC로 updateBoard() 처리");
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeQuery();
		}catch(Exception e){
			System.out.println("updateBoard Er : " + e);
		}finally{
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public void deleteBoard(BoardVO vo){
		System.out.println("===> JDBC로 deleteBoard() 처리");
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeQuery();
		}catch(Exception e){
			System.out.println("deleteBoard Er : " + e);
		}finally{
			JDBCUtil.close(stmt, conn);
		}
	}
	
	public BoardVO getBoard(BoardVO vo){
		System.out.println("===> JDBC로 getBoard() 처리");
		BoardVO board = null;
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			if(rs.next()){
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		}catch(Exception e){
			System.out.println("getBoard Er : " + e);
		}finally{
			JDBCUtil.close(rs, stmt, conn);
		}return board;
	}
	
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("===> JDBC로 getBoardList() 처리");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try{
			conn = JDBCUtil.getConnection();
			//검색 기능을 추가함. 없다면 그냥 BOARD_LIST 로 하면 됨.
			if(vo.getSearchCondition().equals("TITLE")){
				stmt = conn.prepareStatement(BOARD_LIST_T);
			}else if(vo.getSearchCondition().equals("CONTENT")){
				stmt = conn.prepareStatement(BOARD_LIST_C);
			}
			stmt.setString(1, vo.getSearchKeyword());
			rs = stmt.executeQuery();
			while(rs.next()){
				BoardVO board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		}catch(Exception e){
			System.out.println("deleteBoard Er : " + e);
		}finally{
			JDBCUtil.close(rs, stmt, conn);
		}return boardList;
	}
}
*/