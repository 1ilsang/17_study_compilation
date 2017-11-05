<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//1. jdbc driver 로딩하기
		Class.forName("oracle.jdbc.driver.OracleDriver");
		//2. DB 서버 접속하기
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		Connection conn = DriverManager.getConnection(NOTOPEN);
		//3. Statement 객체 생성
		Statement stmt = conn.createStatement();
		
		//PreparedStatement 사용
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		PreparedStatement pstmt = conn.prepareStatement("insert into test values(?,?)");
		pstmt.setString(1, id);
		pstmt.setString(2, pwd);
		pstmt.executeUpdate();
		//4. SQL 실행
		//stmt.executeUpdate("create table test(id varchar(2),pwd varchar2(5))");//테이블 생성
		//테이블에 값 입력
		/* stmt.executeUpdate("insert into test values('aa','11')");
		stmt.executeUpdate("insert into test values('bb','22')");
		stmt.executeUpdate("insert into test values('cc','33')"); */
		
		ResultSet rs = stmt.executeQuery("select * from test");
		
		while(rs.next()){
			out.print("<br>" + rs.getString("id") + " : " + rs.getString(2));
		}
		
		//5. 자원해제
		pstmt.close();
		rs.close(); // resultSet 의 해제
		stmt.close();
		conn.close(); //연결 해제
	%>
	OK
</body>
</html>