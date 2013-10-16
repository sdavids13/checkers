<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html lang="en">
<head>
<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	
	<div class="container">
		<table class="table table-striped" >
			<tr>
				<th>User</th><th>Wins</th><th>Losses</th>
			</tr>
			<c:forEach items="${users}" var="user" >
				<c:url value="/users/${user.username}" var="userLink" />
				<tr>
					<td><a title="Profile" href="<c:out value="${userLink}" />"><c:out value="${user.username}" /></a></td>
					<td>0</td>
					<td>0</td>
				</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
