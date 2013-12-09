<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	
	<div class="container">
		
		<c:url value="/games" var="newGameLink" />
		<form action="<c:out value="${newGameLink}" />" method="POST">
			<input type="submit" value="Create New Game" class="btn primary pull-right">
		</form>
		
		<h2>My Active Games</h2>
		<table class="table table-striped" >
			<tr>
				<th>Game</th>
			</tr>
			<c:forEach items="${myActiveGames}" var="game" >
				<c:url value="/games/${game.id}" var="gameLink" />
				<tr>
					<td>
						<a href="<c:out value="${gameLink}" />">${game.firstPlayer.username} vs. ${game.secondPlayer.username}</a>
					</td>
				</tr>
			</c:forEach>
		</table>
		
		<h2>Available Games</h2>
		<table class="table table-striped" >
			<tr>
				<th>Game</th>
			</tr>
			<c:forEach items="${availableGames}" var="game" >
				<c:url value="/users/${game.firstPlayer.username}" var="userLink" />
				<c:url value="/games/${game.id}/join" var="joinGameLink" />
				<tr>
					<td>
						<form action="<c:out value="${joinGameLink}" />" method="POST">
							<input type="submit" value="Join" class="btn primary">&nbsp;&nbsp;&nbsp;Opponent: <a title="Profile" href="<c:out value="${userLink}" />"><c:out value="${game.firstPlayer.username}" /></a>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
</body>
</html>