package edu.gmu.swe681.checkers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

@Entity
public class Game {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToOne
	@JoinColumn
	private User redPlayer;
	
	@OneToOne
	@JoinColumn
	private User blackPlayer;
	
	@OneToOne
	@JoinColumn
	private User winner; //need "DRAW" mechanism?
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn
	private Board board;
	
	@OrderBy("prevMoveDate desc")
	@OneToMany(cascade=CascadeType.ALL, mappedBy="game", fetch = FetchType.LAZY)
	private List<Board> history;

	Game() {
		
	}
	
	public Game(User firstPlayer) {
		this.blackPlayer = firstPlayer;
	}
	
	public void updateBoard(Board board) {
		if(board != null) {
			board.setGame(this);
			this.history.add(board);
		}
		this.board = board;
	}

	public Long getId() {
		return id;
	}

	public User getRedPlayer() {
		return redPlayer;
	}

	public User getBlackPlayer() {
		return blackPlayer;
	}

	public User getWinner() {
		return winner;
	}

	public Board getBoard() {
		return board;
	}

	public List<Board> getHistory() {
		return history;
	}
	
	public void setSecondPlayer(User secondPlayer) {
		if(hasPlayer(secondPlayer)) {
			throw new IllegalArgumentException(String.format("The first user (%s) attempted to join the game as the second user.", secondPlayer.getUsername()));
		}
		this.redPlayer = secondPlayer;
		//this initiates the game
		updateBoard(Board.buildInitialBoard());
	}
	
	public User getSecondPlayer() {
		return getRedPlayer();
	}
	
	public User getFirstPlayer() {
		return getBlackPlayer();
	}
	
	public boolean hasPlayer(User user) {
		if(user == null) {
			return false;
		}
		
		return user.equals(getFirstPlayer()) || user.equals(getSecondPlayer());
	}
}
