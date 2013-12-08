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
		this.redPlayer = firstPlayer;
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
		this.blackPlayer = secondPlayer;
		//this initiates the game
		updateBoard(Board.buildInitialBoard());
	}
	
	public User getSecondPlayer() {
		return getBlackPlayer();
	}
	
	public User getFirstPlayer() {
		return getRedPlayer();
	}
	
	public boolean hasPlayer(User user) {
		if(user == null) {
			return false;
		}
		
		return user.equals(getFirstPlayer()) || user.equals(getSecondPlayer());
	}
	
	/**
	 * Expire the game, give the win to the user that moved last.
	 */
	public void expireGame() {
		if(board == null) {
			throw new IllegalStateException("Unable to expire a game that hasn't started yet.");
		}
		
		this.winner = getUser(getLastPlayer());
	}

	public boolean isUserTurn(User user) {
		return gameStarted() && user.equals(getUser(Player.otherPlayer(getLastPlayer())));
	}
	
	private Player getLastPlayer() {
		if(board == null) {
			return null;
		}
		
		return board.getPrevPlayerMove();
	}
	
	public User getUser(Player player) {
		if(player == null) {
			return null;
		}
		
		return player == Player.RED ? redPlayer : blackPlayer;
	}
	
	private boolean gameStarted() {
		return getSecondPlayer() != null;
	}
}
