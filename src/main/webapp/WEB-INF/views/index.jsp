<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!-- TODO: cite https://github.com/dvander/checkers --->
<head>
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
	<h2>Checkers</h2>
	<p>
		<strong id='red' style='color: red'>Red: Human (you!)</strong><br />
		<strong id='blue' style='color: blue'>Blue: Your Browser</strong><br />
		Rules are <a href="http://en.wikipedia.org/wiki/English_draughts">here</a>.
		Or, <a href="#" onClick="switchSides();">switch sides</a>.
	</p>
	<canvas id="checkers"></canvas>
	<p>
		<em>Predictions made last turn: <span id="predictor">0</span>.
			The AI will be effective if your browser can pull off at least
			10,000.
		</em>
	</p>
	<script>
		var canvas = document.getElementById('checkers');
		var predictor = document.getElementById('predictor');
		var ui = new CanvasCheckers(canvas, predictor, 8, 8);
		var game = new Checkers.Game(ui);
		game.start();

		function switchSides() {
			document.getElementById('red').innerHTML = 'Red: Your Browser';
			document.getElementById('blue').innerHTML = 'Human: (you!)';
			ui.switchSides();
		}
	</script>
	<hr>
	<p>
		<em><strong>About the demo.</strong> The goal of this demo is to
			test the feasibility of using JavaScript to implement AI in board
			games. Specifically, here we use the <a
			href="http://senseis.xmp.net/?UCT">UCT</a> algorithm. While mainly
			popular for <a href="http://en.wikipedia.org/wiki/Go_%28game%29">Go</a>,
			it can be adapted for many situations. Checkers had a good balance of
			familiarity, ease of play, and ease of implementation.</em>
</body>