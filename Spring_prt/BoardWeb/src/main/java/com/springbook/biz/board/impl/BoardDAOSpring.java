/*package com.springbook.biz.board.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;

@Repository
public class BoardDAOSpring{
	//SQL 명령어들 (SELECT NVL(MAX(SEQ), 0)+1 FROM BOARD)
	private final String BOARD_INSERT ="INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ), 0)+1 FROM BOARD),?,?,?)";
	private final String BOARD_UPDATE ="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	private final String BOARD_LIST_T = "SELECT * FROM BOARD WHERE TITLE LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
	private final String BOARD_LIST_C = "SELECT * FROM BOARD WHERE CONTENT LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
		
	//spring Bean 등록해서 하는 방법.(applicationContext)
	//extends JdbcDaoSupport 제거
	//각 메서드마다 jdbcTemplate.update() 해줘야함
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//bean 등록안하고 하는 첫 번째 방법 JdbcDaoSupport 상속
	//extends JdbcDaoSupport 추가해줘야함.
	//각 메서드마다 getJdbcTemplate.update() 해줘야함
	@Autowired
	public void setSuperDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	
	//CRUD Method Implement
	public void insertBoard(BoardVO vo){
		System.out.println("====> Spring JDBC로 insertBoard() 기능 처리");
		//JdbcTemplate 상속 방법.
		//getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(),vo.getWriter(),vo.getContent());
		//bean 을 활용한 방법
		jdbcTemplate.update(BOARD_INSERT,  vo.getTitle(),vo.getWriter(),vo.getContent());
		}
	
	public void updateBoard(BoardVO vo){
		System.out.println("====> Spring JDBC로 updateBoard() 기능 처리");
		//getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(),vo.getContent(),vo.getSeq());
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(),vo.getContent(),vo.getSeq());
	}
	
	public void deleteBoard(BoardVO vo){
		System.out.println("====> Spring JDBC로 deleteBoard() 기능 처리");
		//getJdbcTemplate().update(BOARD_DELETE,vo.getSeq());
		jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	}
	
	//글 상세 조회
	public BoardVO getBoard(BoardVO vo){
		System.out.println("====> Spring JDBC로 getBoard() 기능 처리");
		Object [] args = {vo.getSeq()};
		//return getJdbcTemplate().queryForObject(BOARD_GET,args,new BoardRowMapper());
		return jdbcTemplate.queryForObject(BOARD_GET,args,new BoardRowMapper());
	}
	//글 목록 조회
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("====> Spring JDBC로 getBoardList() 기능 처리");
		Object[] args={vo.getSearchKeyword()};
		if(vo.getSearchCondition().equals("TITLE")){
			return jdbcTemplate.query(BOARD_LIST_T, args,new BoardRowMapper());
		}else if(vo.getSearchCondition().equals("CONTENT")){
			return jdbcTemplate.query(BOARD_LIST_C, args,new BoardRowMapper());
		}
		//return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
		return jdbcTemplate.query(BOARD_LIST, new BoardRowMapper());
		return null;
	}
}

class BoardRowMapper implements RowMapper<BoardVO>{
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException{
		BoardVO board=new BoardVO();
		board.setSeq(rs.getInt("SEQ"));
		board.setTitle(rs.getString("TITLE"));
		board.setWriter(rs.getString("WRITER"));
		board.setContent(rs.getString("CONTENT"));
		board.setRegDate(rs.getDate("REGDATE"));
		board.setCnt(rs.getInt("CNT"));
		return board;
	}
}*/