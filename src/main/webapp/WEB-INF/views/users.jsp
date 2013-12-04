<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
				<th>User</th><th>Wins</th><th>Losses</th><th>Total</th><th>Win %</th>
			</tr>
			<c:forEach items="${users}" var="user" >
				<c:url value="/users/${user.username}" var="userLink" />
				<tr>
					<td><a title="Profile" href="<c:out value="${userLink}" />"><c:out value="${user.username}" /></a></td>
					<td>${user.winCount}</td>
					<td>${user.lossCount}</td>
					<fmt:formatNumber var="total" value="${user.winCount + user.lossCount}" type="NUMBER" />
					<td>${total}</td>
					<c:choose>
						<c:when test="${total==0}">
							<td>N/A</td>
						</c:when>
						<c:otherwise>
							<fmt:formatNumber var="winPercentage" value="${(user.winCount * 1.0) / total}" type="PERCENT" />
							<td>${winPercentage}</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
  </body>
</html>
