// vim: set ts=4 sw=4 tw=99 et:
// Copyright (C) 2010 David Anderson 
// dvander@alliedmods.net
//
// Canvas code is based on the tutorial at http://diveintohtml5.org/canvas.html
// 
// Permission is hereby granted, free of charge, to any person
// obtaining a copy of this software and associated documentation
// files (the "Software"), to deal in the Software without
// restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the
// Software is furnished to do so, subject to the following
// conditions:
// 
// The above copyright notice and this permission notice shall be
// included in all copies or substantial portions of the Software.
// 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
// EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
// OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
// HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
// FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
// OTHER DEALINGS IN THE SOFTWARE.

function CanvasCheckers(canvas, rows, cols) {
	this.rows = rows;
	this.cols = cols;
	var computer = Checkers.PlayerTwo;

	// Initialize canvas variables.
	var PieceWidth = 50;
	var PieceHeight = 50;
	var BoardWidth = 1 + (cols * PieceWidth);
	var BoardHeight = 1 + (rows * PieceHeight);

	canvas.width = BoardWidth;
	canvas.height = BoardHeight;

	var DrawContext = canvas.getContext("2d");

	function Draw(b, selRow, selCol) {
		DrawContext.clearRect(0, 0, BoardWidth, BoardHeight);
		DrawContext.beginPath();

		// Vertical lines.
		for ( var x = 0; x <= BoardWidth; x += PieceWidth) {
			DrawContext.moveTo(0.5 + x, 0);
			DrawContext.lineTo(0.5 + x, BoardHeight);
		}

		// Horizontal lines.
		for ( var y = 0; y <= BoardHeight; y += PieceHeight) {
			DrawContext.moveTo(0, 0.5 + y);
			DrawContext.lineTo(BoardWidth, 0.5 + y);
		}

		// Draw!
		DrawContext.strokeStyle = "#ccc";
		DrawContext.stroke();

		// Now fill in every other square with a light gray.
		var odds = 1;
		for ( var row = 0; row < rows; row++) {
			for ( var col = 0; col < cols; col++) {
				if ((col & 1) != odds)
					DrawContext.fillStyle = "#eeeeee";
				else
					DrawContext.fillStyle = "#dddddd";
				DrawContext.fillRect(row * PieceHeight + 0.5, col * PieceWidth + 0.5, PieceHeight, PieceWidth);
			}
			odds = odds ^ 1;
		}

		function drawCircle(posX, posY, radius, lineWidth, color) {
			DrawContext.beginPath();
			DrawContext.lineWidth = lineWidth;
			DrawContext.arc(posX, posY, radius, 0, Math.PI * 2, false);
			DrawContext.closePath();
			DrawContext.strokeStyle = color;
			DrawContext.stroke();
		}

		// Now, fill in player pieces.
		var radius = (PieceWidth / 2) - (PieceWidth / 10);
		for ( var row = 0; row < rows; row++) {
			for ( var col = 0; col < cols; col++) {
				var piece = b.getPlayer(row, col);
				if (piece == Checkers.EmptyPlayer)
					continue;

				var posX = (col * PieceWidth) + (PieceWidth / 2);
				var posY = (row * PieceHeight) + (PieceHeight / 2);

				var color = (piece == Checkers.PlayerOne) ? "#ed1c24" : "#000000";
				drawCircle(posX, posY, radius, 4, color);
				if (b.isKing(row, col)) {
					// Draw a more different S
					drawCircle(posX, posY, radius - 4, 2, color);
					drawCircle(posX, posY, radius - 8, 2, color);
					drawCircle(posX, posY, radius - 12, 2, color);
					drawCircle(posX, posY, radius - 16, 1, color);
				}

				if (selRow === row && selCol === col) {
					DrawContext.fillStyle = color;
					DrawContext.fill();
				}
			}
		}
	}

	var game;
	var selectedRow = undefined;
	var selectedCol = undefined;
	
	var getBoardState = function()  {
		jsonObj = [];
		
		var resultString ="";
		for (row=0; row < game.board.rows; row++) {
			for (col=0; col < game.board.cols; col++) {
				var pos = row * game.board.cols + col;
				var piece = game.board.grid[pos];  
				var key = (piece >> 8) & 0xFF;
				var player = piece & 0x3;
				var isKing = piece & 0x4;
				var display ="";
				
				if (isKing) {
					if (player == 2) {
						display = "R";
					} else if (player == 3){
						display = "B";
					} else {
						display =" ";
					}
				} else {
					if (player == 2) {
						display = "r";
					} else if (player == 3){
						display = "b";
					} else {
						display = " ";
					}
				}

				//coordinate = {"x" : row, "y" : col};  //the "view" way
				coordinate = {"x" : col, "y" : row};    // the "swe681.checkers.model.Coordinate" way
				if(player == 2 || player == 3) {
					piece ={"player" : player == 2 ? "RED" : "BLACK","kinged" : new Boolean(isKing), "coordinate" : coordinate};
					jsonObj.push(piece);
				}
				  
				resultString += display;
			}
			resultString += "\n";
		}
		console.debug(resultString);
		return jsonObj;
	}

	var onClick = function(e) {
		var x, y;
		if (e.pageX != undefined && e.pageY != undefined) {
			x = e.pageX;
			y = e.pageY;
		} else {
			x = e.clientX + document.body.scrollLeft + document.documentElement.scrollLeft;
			y = e.clientY + document.body.scrollTop + document.documentElement.scrollTop;
		}
		x -= canvas.offsetLeft;
		y -= canvas.offsetTop;
		x = Math.min(x, cols * PieceWidth);
		y = Math.min(y, rows * PieceHeight);

		var row = Math.floor(y / PieceHeight);
		var col = Math.floor(x / PieceWidth);

		//TODO: re-factor to prevent player2 from playing during player3's turn, or vice-versa 
		/*if (game.player() == computer)
			return;*/

		var player = game.playerAt(row, col);
		if (player) {
			
				//TODO: disabled to allow movement of both blue and red pieces
			if (player != game.player())
				return;  

			// If the player is selecting an own piece, just update the display.
			if (!game.inMultiTurn()) {
				selectedRow = row;
				selectedCol = col;
				Draw(game.board, selectedRow, selectedCol);
				return;
			}
		}

		// If the player is selecting an empty square without having done
		// anything yet, just return.
		if (selectedRow === undefined)
			return;

		var fromRow = selectedRow;
		var fromCol = selectedCol;
		if (!game.inMultiTurn()) {
			selectedRow = undefined;
			selectedCol = undefined;
		}

		var pathname = window.location.pathname
		var boardStatePriorToMove = getBoardState();
		var winner = null;
		try {
			 winner = game.move(fromRow, fromCol, row, col);
		} catch (e) {
				alert('Invalid move: ' + e);
				Draw(game.board, selectedRow, selectedCol);
				return;
		}
		var boardStateAfterMove = getBoardState();
    
    // The move was valid. If the player is allowed to move again,
    // re-select the piece and redraw.
    if (game.inMultiTurn()) {
        selectedRow = row;
        selectedCol = col;
        Draw(game.board, row, col);
        return;
    }
		
		$.ajax({
			url : location.pathname + '/move',
			type : 'POST',
			dataType : 'html',
			data : JSON.stringify({'board': boardStateAfterMove}),  
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				//not currently able to use the 'new RedirectView("/games/" + gameId, true);' returned by controller
				Draw(game.board);
				$.data(document.body, 'myTurn', false);
			},
			error : function(data, status, er) {
				console.debug("error: " + data + " status: " + status + " er:" + er);
				Draw(game.board, selectedRow, selectedCol);
			}
		});

		
	}

	this.start = function(game_, pieces) {
		game = game_;
		if (pieces) {
			game.board.populateFromArray(pieces);
		}
		canvas.addEventListener("click", onClick, false);
		
		$.ajax({
			url : location.pathname + '/mycolor',
			type : 'GET',
			success : function(data) {
				game.board.player = data == "RED" ? 2 : 3;
				Draw(game.board);
			},
			error : function(data, status, er) {
				console.error("error: " + data + " status: " + status + " er:" + er);
			}
		});
	}
	
	this.refreshKludge = function(game_, pieces) {
		game = game_;
		console.debug(pieces);
		game.board.populateFromArray(pieces);
		Draw(game.board);
	}

}
