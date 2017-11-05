<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="my" uri="http://myTags.com" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Custom Tag</title>
</head>
<body>
<%-- <my:first color="red"><%= 1+2 %></my:first><br>  color의 TEI를 blue로 명시했기 때문에 이러면 오류--%>
<my:first color="blue"><%= 1+2 %></my:first>
Custom Tag Test!
</body>
</html>