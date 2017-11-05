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
	//Mybatis ������ �ڹ��ڵ�� �ѵ��ٷ� ��������!(Data Mapper)
	//������ ���⿣ SQL���� ����. java�ڵ�� ����
	//board-mapping.xml�� ������ �ۼ�.
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
		System.out.println("===> JDBC�� insertBoard() ó��");
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
		System.out.println("===> JDBC�� updateBoard() ó��");
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
		System.out.println("===> JDBC�� deleteBoard() ó��");
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
		System.out.println("===> JDBC�� getBoard() ó��");
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
		System.out.println("===> JDBC�� getBoardList() ó��");
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try{
			conn = JDBCUtil.getConnection();
			//�˻� ����� �߰���. ���ٸ� �׳� BOARD_LIST �� �ϸ� ��.
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