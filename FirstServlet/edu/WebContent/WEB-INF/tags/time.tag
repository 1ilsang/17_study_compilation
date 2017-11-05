<%@ tag body-content="empty"    pageEncoding="UTF-8"%>
<%@ tag import="java.util.Calendar" %>

<% Calendar date = Calendar.getInstance(); %>

<%= date.get(Calendar.YEAR) %> -
<%= date.get(Calendar.MONTH) + 1 %> -
<%= date.get(Calendar.DATE) %> 
