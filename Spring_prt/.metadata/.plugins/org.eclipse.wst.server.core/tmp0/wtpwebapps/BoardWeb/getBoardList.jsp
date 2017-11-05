<%@ page import="java.util.List" %>
<%@page import="com.springbook.biz.board.BoardVO" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!-- JSTL 을 위한 주소 -->
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%-- JSTL 을 사용하기 때문에 필요없는 구문
    <% 
   @SuppressWarnings("unchecked")
   List<BoardVO> boardList = (List<BoardVO>) session.getAttribute("boardList");
   %> --%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1><spring:message code="message.board.list.mainTitle"/></h1>
	<h3>${userName }<spring:message code="message.board.list.welcomeMsg"/>...
	<a href="logout.do">Log-out</a></h3>
	
	<!-- 검색 시작 -->
	<form action="getBoardList.do" method="post">
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<td align="right">
				<select name="searchCondition">
					<!-- <option value="TITLE">제목
					<option value="CONTENT">내용 -->
					<c:forEach items="${conditionMap }" var="option">
						<option value="${option.value }">${option.key }
					</c:forEach>
				</select>
				<input name="searchKeyword" type="text"/>
				<input type="submit" value="<spring:message code="message.board.list.search.condition.btn"/>"/>
			</td>
		</tr>
	</table></form>
	<!-- 검색 종료 -->
	<table border="1" cellpadding="0" cellspacing="0" width="700">
		<tr>
			<th bgcolor="orange" width="100">
 				<spring:message code="message.board.list.table.head.seq"/>
			</th>
			<th bgcolor="orange" width="200"><spring:message code="message.board.list.table.head.title"/></th>
			<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.writer"/></th>
			<th bgcolor="orange" width="150"><spring:message code="message.board.list.table.head.regDate"/></th>
			<th bgcolor="orange" width="100"><spring:message code="message.board.list.table.head.cnt"/></th>
		</tr><!-- BoardVO board : boardList -->
		<%-- <% for(BoardVO board : boardList) { %>
		<tr>
			<td><%= board.getSeq() %></td>
			<td align="left"><a href="getBoard.do?seq=<%= board.getSeq() %>">
										 <%= board.getTitle() %></a></td>
			<td><%= board.getWriter() %></td>
			<td><%= board.getRegDate() %></td>
			<td><%= board.getCnt() %></td>
		</tr>
		<%} %> --%>
		<c:forEach items="${boardList }" var="board">
		<tr>
			<td>${board.seq }</td>
			<td align="left"><a href="getBoard.do?seq=${board.seq }">${board.title }</a></td>
			<td>${board.writer }</td>
			<td>${board.regDate }</td>
			<td>${board.cnt} </td>
		</tr>
		</c:forEach>
	</table>
	<br>
	<a href="insertBoard.jsp"><spring:message code="message.board.list.link.insertBoard"/></a>
	</center>
</body>
</html>