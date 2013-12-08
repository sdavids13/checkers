<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
	<jsp:include page="header.jsp" flush="true" />
	<spring:url value="/resources/js/board.js" var="board" />
	<spring:url value="/resources/js/uct.js" var="uct" />
	<spring:url value="/resources/js/canvasui.js" var="canvasui" />
	<spring:url value="/resources/js/jquery-2.0.3.min.js" var="jquery" />

</head>

<script src="${board}" ></script>
<script src="${uct}" ></script>
<script src="${canvasui}"></script>
<script src="${jquery}"></script>

<body>
	<jsp:include page="menu.jsp" flush="true" />
	<div class="container">
		<h2>Checkers</h2>
		<p>
			<strong id='red' style='color: red'>Red: ${game.firstPlayer.username}</strong><br />
			<strong id='black' style='color: black'>Black: ${game.secondPlayer.username} </strong><br>
			Rules are <a href="http://en.wikipedia.org/wiki/English_draughts">here</a>.
		</p>
		<canvas id="checkers"></canvas>
		<script>

			var canvas = document.getElementById('checkers');
			var ui = new CanvasCheckers(canvas, 8, 8);
			var game = new Checkers.Game(ui);
			
			 var timerId = setInterval(function () {
         window.location.reload();
     }, 10000);
			 
 			game.start(${game.board.getJSON()});
 
		</script>
		<hr>
	</div>
</body>