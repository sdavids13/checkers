<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />

	<div class="container">
		<h1>User Profile for ${user.username}</h1>
		<fmt:formatNumber var="total" value="${user.winCount + user.lossCount}" type="NUMBER" />
		<c:set var="winPercentDisplay" value="" />
		<c:if test="${total!=0}">		
			<fmt:formatNumber var="winPercent" value="${(user.winCount * 1.0) / total}" type="PERCENT" />
			<c:set var="winPercentDisplay" value=" (${winPercent})" />
		</c:if>
		<p class="lead">Wins: ${user.winCount}${winPercentDisplay}, Losses: ${user.lossCount}, Total Games: ${total}</p>
		<table class="table table-striped" >
			<tr>
				<th>Date</th><th>Opponent</th><th>Winner</th>
			</tr>
			<c:forEach items="${completedGames}" var="game" >
			<tr>
				<td><fmt:formatDate value="${game.board.prevMoveDate}" pattern="MMM dd YYYY @ hh:mm a" /></td>
				<td>
					<c:choose>
						<c:when test="${game.firstPlayer.username != user.username}">
							<c:set var="opponent" value="${game.firstPlayer.username}" />
						</c:when>
						<c:otherwise>
							<c:set var="opponent" value="${game.secondPlayer.username}" />
						</c:otherwise>
					</c:choose>
					<c:url value="/users/${opponent}" var="opponentLink" />
					<a title="Profile" href="<c:out value="${opponentLink}" />"><c:out value="${opponent}" /></a>
				</td>
				<c:url value="/games/${game.id}/history" var="gameLink" />
				<td>${game.winner.username} - <a title="Profile" href="<c:out value="${gameLink}" />">See Game History</a></td>
			</tr>
			</c:forEach>
		</table>
 	</div>
</body>
</html>