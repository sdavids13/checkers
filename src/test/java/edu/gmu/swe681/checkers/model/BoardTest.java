package edu.gmu.swe681.checkers.model;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class BoardTest {

	
	@Test
	public void testDetermineMovedPiece() {
		Board board = Board.buildInitialBoard();
		Coordinate redPieceCoord = new Coordinate(1, 2);
		Piece redPiece = board.getPiece(redPieceCoord);
		
		assertEquals(Player.RED, redPiece.getPlayer());
		
		Set<Piece> nextBoardPieces = new HashSet<Piece>(board.getPieces());
		nextBoardPieces.remove(redPiece);
		nextBoardPieces.add(new Piece(redPiece.getPlayer(), new Coordinate(0, 3)));
		
		Board newBoard = board.buildNextBoard(nextBoardPieces);
		assertEquals(redPiece, newBoard.getMovedPiece());
	}
	
	@Test
	public void testRemovePieces() {
		Board board = new Board();
		board.setPrevPlayerMove(Player.RED);
		
		Piece jumpedPiece = new Piece(Player.RED, new Coordinate(1, 1));
		Piece movedPiece = new Piece(Player.BLACK, new Coordinate(2, 2));
		board.setPieces(new HashSet<Piece>(Arrays.asList(jumpedPiece, movedPiece)));
		
		Set<Piece> nextBoardPieces = new HashSet<Piece>(Arrays.asList(new Piece(Player.BLACK, new Coordinate(0, 0))));
		
		Board newBoard = board.buildNextBoard(nextBoardPieces);
		assertEquals(movedPiece, newBoard.getMovedPiece());
	}
	
	@Test
	public void testDisplayBoard() {
		Board gameBoard =  Board.buildInitialBoard();
		String results = gameBoard.displayBoard();
		System.out.println(results);
		assertTrue(StringUtils.contains(results, "r"));		
	}
	
	@Test
	public void testMoveValidation() {
		assertTrue(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(1, 1)), 
			Collections.<Piece> emptyList()
		));
		
		assertFalse(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			Collections.<Piece> emptyList()
		));
		
		assertFalse(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(0, 1)), 
			Collections.<Piece> emptyList()
		));
		
		assertTrue(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(2, 2)), 
			Arrays.asList(
				new Piece(Player.RED, new Coordinate(1, 1))
			)
		));
		
		assertFalse(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(3, 3)), 
			Arrays.asList(
				new Piece(Player.RED, new Coordinate(1, 1))
			)
		));
		
		assertTrue(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(0, 4)), 
			Arrays.asList(
				new Piece(Player.RED, new Coordinate(1, 1)),
				new Piece(Player.RED, new Coordinate(1, 3))
			)
		));
		
		assertFalse(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(2, 2)), 
			Arrays.asList(
				new Piece(Player.RED, new Coordinate(1, 1)),
				new Piece(Player.RED, new Coordinate(2, 2))
			)
		));
		
		assertFalse(Board.isValidMove(
			new Piece(Player.BLACK, new Coordinate(0, 0)), 
			new Piece(Player.BLACK, new Coordinate(6, 6)), 
			Arrays.asList(
				new Piece(Player.RED, new Coordinate(1, 1)),
				new Piece(Player.RED, new Coordinate(2, 2))
			)
		));
	}

}
