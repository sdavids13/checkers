<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="edu.gmu.swe681.checkers.model.Coordinate" %>
<html>
<head>
	<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	
	<div class="container">
		
		<h2>${game.firstPlayer.username} vs ${game.secondPlayer.username}</h2>
		<p class="lead">Winner: ${game.winner.username}, Completed: <fmt:formatDate value="${game.board.prevMoveDate}" pattern="MMM dd YYYY @ hh:mm a" /></p>
		<c:forEach items="${game.history}" var="board">
		<h3>${board.prevPlayerMove} Move</h3>
		<table class="board">
			<c:forEach begin="0" end="7" var="y">
				<tr>
				<c:forEach begin="0" end="7" var="x" >
					<%
						Coordinate coord = new Coordinate((Integer) pageContext.getAttribute("x"), (Integer) pageContext.getAttribute("y"));
						pageContext.setAttribute("coord", coord);
					%>
					<c:set var="cellCls" value="${coord.playable ? 'playable' : ''}"/>
					<td class="${cellCls}" x="${x}" y="${y}">
						<c:set var="piece" value="${board.getPiece(coord)}"/>
						<c:if test="${not empty piece}">
						    <c:set var="pieceCls" value="piece ${piece.player.toString()}"/>
						    <c:if test="${piece.kinged}">
						    	<c:set var="pieceCls" value="kinged ${pieceCls}"/>
						    </c:if>
						    
						    <div class="${pieceCls}" />
						</c:if>
					</td>
				</c:forEach>
				</tr>
			</c:forEach>
		</table>
		</c:forEach>
	</div>
	
	<br/>
</body>
</html>
