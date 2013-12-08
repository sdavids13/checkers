<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<jsp:include page="header.jsp" flush="true" />
	<spring:url value="/resources/js/jquery-2.0.3.min.js" var="jquery" />
	<spring:url value="/resources/js/board.js" var="board" />
	<spring:url value="/resources/js/uct.js" var="uct" />
	<spring:url value="/resources/js/canvasui.js" var="canvasui" />

	<script src="${jquery}"></script>
	<script>
		function pollMyTurn() {
			if($.data(document.body, 'myTurn') == false) {
				$.ajax({
		 			url : location.pathname + '/myturn',
		 			type : 'GET',
		 			success : function(data) {
		 				if(data == true || data == 'true') {
							window.location.reload();
		 				}
		 			}
	 			});
			}
		}
		
		$( document ).ready(function() {
			$.data(document.body, 'myTurn', ${myTurn});
			setInterval(pollMyTurn, 5000);
		});
	</script>
</head>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	<div class="container">
		<h2>Checkers</h2>
		<p>
			<strong id='red' style='color: red'>Red: ${game.firstPlayer.username}</strong><br />
			<strong id='blue' style='color: blue'>Blue: ${game.secondPlayer.username} </strong><br>
			Rules are <a href="http://en.wikipedia.org/wiki/English_draughts">here</a>.
		</p>
		
		<c:choose>
			<c:when test="${game.board == null}">
				<div class="well">
					The game hasn't started yet, waiting for opponent.
				</div>
			</c:when>
			<c:otherwise>
				<script src="${board}" ></script>
				<script src="${uct}" ></script>
				<script src="${canvasui}"></script>
				
				<canvas id="checkers"></canvas>
				<script>
		  			var canvas = document.getElementById('checkers');
		 			var ui = new CanvasCheckers(canvas, 8, 8);
		 			var game = new Checkers.Game(ui);
		 			game.start(${game.board.getJSON()});
				</script>
			</c:otherwise>
		</c:choose>
		
		<hr>
	</div>
</body>