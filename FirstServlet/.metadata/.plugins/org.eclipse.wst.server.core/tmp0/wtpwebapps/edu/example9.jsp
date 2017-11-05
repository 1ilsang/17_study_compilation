<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>선언문으로 구현한 덧셈</h3>
	<%!
		public int sum(int a, int b){
		return a+b;
	}
	%>
	덧셈의 결과 : <%=this.sum(20,20) %>
</body>
</html>