<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
	<jsp:include page="header.jsp" flush="true" />
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	
	<div class="container">
		
		<h2>Game</h2>
		<table class="board">
			<c:forEach begin="0" end="7" var="yOffset">
				<c:set var="y" value="${7 - yOffset}"/> 
				<tr>
				<c:forEach begin="0" end="7" var="x" >
				
					
					<c:set var="cellCls" value="${(x + y) % 2 == 0 ? 'playable' : ''}"/>
					<td class="${cellCls}" x="${x}" y="${y}">
						<c:set var="piece" value="${game.board.getPiece(x,y)}"/>
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
	</div>
	
	<br/>
</body>
</html>