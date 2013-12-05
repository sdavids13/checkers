package edu.gmu.swe681.checkers.dto;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.NotEmpty;

import edu.gmu.swe681.checkers.model.Piece;

public class GameMove {
	
	@Valid
	@NotEmpty
	private List<Piece> board;

	public List<Piece> getBoard() {
		return board;
	}

	public void setBoard(List<Piece> board) {
		this.board = board;
	}
}
