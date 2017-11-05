<%@ tag body-content="empty" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ tag dynamic-attributes="options" %>
<%@ attribute name="location" required="true" %>

<select name="${location }">
	<c:forEach var="item" items="${options }">
		<option value="${item.key }">${item.value }</option>
	</c:forEach>
</select>

<%@ tag dynamic-attributes="options" %>