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
	//SQL ��ɾ�� (SELECT NVL(MAX(SEQ), 0)+1 FROM BOARD)
	private final String BOARD_INSERT ="INSERT INTO BOARD(SEQ, TITLE, WRITER, CONTENT) VALUES((SELECT NVL(MAX(SEQ), 0)+1 FROM BOARD),?,?,?)";
	private final String BOARD_UPDATE ="update board set title=?, content=? where seq=?";
	private final String BOARD_DELETE="delete board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	private final String BOARD_LIST_T = "SELECT * FROM BOARD WHERE TITLE LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
	private final String BOARD_LIST_C = "SELECT * FROM BOARD WHERE CONTENT LIKE '%' ||?|| '%' ORDER BY SEQ DESC";
		
	//spring Bean ����ؼ� �ϴ� ���.(applicationContext)
	//extends JdbcDaoSupport ����
	//�� �޼��帶�� jdbcTemplate.update() �������
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//bean ��Ͼ��ϰ� �ϴ� ù ��° ��� JdbcDaoSupport ���
	//extends JdbcDaoSupport �߰��������.
	//�� �޼��帶�� getJdbcTemplate.update() �������
	@Autowired
	public void setSuperDataSource(DataSource dataSource){
		super.setDataSource(dataSource);
	}
	
	//CRUD Method Implement
	public void insertBoard(BoardVO vo){
		System.out.println("====> Spring JDBC�� insertBoard() ��� ó��");
		//JdbcTemplate ��� ���.
		//getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(),vo.getWriter(),vo.getContent());
		//bean �� Ȱ���� ���
		jdbcTemplate.update(BOARD_INSERT,  vo.getTitle(),vo.getWriter(),vo.getContent());
		}
	
	public void updateBoard(BoardVO vo){
		System.out.println("====> Spring JDBC�� updateBoard() ��� ó��");
		//getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(),vo.getContent(),vo.getSeq());
		jdbcTemplate.update(BOARD_UPDATE, vo.getTitle(),vo.getContent(),vo.getSeq());
	}
	
	public void deleteBoard(BoardVO vo){
		System.out.println("====> Spring JDBC�� deleteBoard() ��� ó��");
		//getJdbcTemplate().update(BOARD_DELETE,vo.getSeq());
		jdbcTemplate.update(BOARD_DELETE,vo.getSeq());
	}
	
	//�� �� ��ȸ
	public BoardVO getBoard(BoardVO vo){
		System.out.println("====> Spring JDBC�� getBoard() ��� ó��");
		Object [] args = {vo.getSeq()};
		//return getJdbcTemplate().queryForObject(BOARD_GET,args,new BoardRowMapper());
		return jdbcTemplate.queryForObject(BOARD_GET,args,new BoardRowMapper());
	}
	//�� ��� ��ȸ
	public List<BoardVO> getBoardList(BoardVO vo){
		System.out.println("====> Spring JDBC�� getBoardList() ��� ó��");
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