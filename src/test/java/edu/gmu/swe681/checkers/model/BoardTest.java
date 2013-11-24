package edu.gmu.swe681.checkers.model;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class BoardTest {

	
	@Test
	public void testDetermineMovedPiece() {
		Board board = Board.buildInitialBoard();
		Coordinate blackPieceCoord = new Coordinate(1, 6);
		Piece blackPiece = board.getPiece(blackPieceCoord);
		
		assertEquals(Player.BLACK, blackPiece.getPlayer());
		
		Set<Piece> nextBoardPieces = new HashSet<Piece>(board.getPieces());
		nextBoardPieces.remove(blackPiece);
		nextBoardPieces.add(new Piece(blackPiece.getPlayer(), new Coordinate(0, 4)));
		
		Board newBoard = board.buildNextBoard(nextBoardPieces);
		assertEquals(blackPiece, newBoard.getMovedPiece());
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

}
