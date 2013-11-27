package edu.gmu.swe681.checkers.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
public class Board {

	private static Logger LOG = LoggerFactory.getLogger(Board.class);
	 /** System property - <tt>line.separator</tt>*/
  public static final String NEW_LINE = System.getProperty("line.separator");
	
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional=false) 
	@JoinColumn(nullable=false, updatable=false)
	private Game game;
	
	@NotNull
	@Column(nullable = false)
	private Player prevPlayerMove;
	
	@NotNull
	@Column(nullable = false)
	private Date prevMoveDate = new Date();
	
	@OneToOne
	@JoinColumn(updatable=false)
	private Piece movedPiece;

	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name = "BOARD_PIECE", 
		joinColumns = @JoinColumn(name = "BOARD_ID", referencedColumnName = "ID"), 
		inverseJoinColumns = @JoinColumn(name = "PIECE_ID", referencedColumnName = "ID"))
	private Set<Piece> pieces;
	
	Board() {
	}
	
	public static Board buildInitialBoard() {
		Board board = new Board();
		//Black is the first player of the game
		board.prevPlayerMove = Player.RED;
		
		Set<Piece> pieces = new HashSet<Piece>();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 8; x++) {
				Coordinate coord = new Coordinate(x, y);
				if(coord.isPlayable()) {
					pieces.add(new Piece(Player.RED, coord));
				}
			}
		}
			for (int y = 5; y < 8; y++) {
				for (int x = 0; x < 8; x++) {
				Coordinate coord = new Coordinate(x, y);
				if(coord.isPlayable()) {
					pieces.add(new Piece(Player.BLACK, coord));
				}
			}
		}
		board.setPieces(pieces);
		
		return board;
	}
	
	public Board buildNextBoard(Set<Piece> newBoardLayout) {
		Player currentPlayer = Player.otherPlayer(this.prevPlayerMove);

		Board newBoard = new Board();
		newBoard.game = game;
		newBoard.prevPlayerMove = currentPlayer;
		
		Set<Piece> movedAndRemovedPieces = removeAll(this.pieces, newBoardLayout);
		LOG.debug("Detected the following modified new pieces: " + movedAndRemovedPieces);
		
		Piece movedPiece = null;
		Set<Piece> removedPieces = new HashSet<Piece>();
		for(Piece movedOrRemovedPiece : movedAndRemovedPieces) {
			if(currentPlayer.equals(movedOrRemovedPiece.getPlayer())) {
				if(movedPiece != null) {
					throw new IllegalArgumentException("The new board given has moved multiple current player pieces.");
				}
				movedPiece = movedOrRemovedPiece;
			} else {
				removedPieces.add(movedOrRemovedPiece);
			}
		}
		
		if(movedPiece == null) {
			throw new IllegalArgumentException("The current player didn't perform a move.");
		} else {
			newBoard.movedPiece = movedPiece;
		}
		
		LOG.info("Moved piece: " + movedPiece + " and removed pieces: " + removedPieces);

		//TODO walk chain to see if the movedPiece could jump all removedPieces + verify it landed in the spot that it says it does
		
		return newBoard;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Player getPrevPlayerMove() {
		return prevPlayerMove;
	}

	public void setPrevPlayerMove(Player prevPlayerMove) {
		this.prevPlayerMove = prevPlayerMove;
	}

	public Date getPrevMoveDate() {
		return prevMoveDate;
	}

	public void setPrevMoveDate(Date prevMoveDate) {
		this.prevMoveDate = prevMoveDate;
	}

	public Set<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(Set<Piece> pieces) {
		this.pieces = pieces;
	}

	public Long getId() {
		return id;
	}
	
	public Piece getMovedPiece() {
		return movedPiece;
	}
	
	public Piece getPiece(int x, int y) {
		return getPiece(new Coordinate(x, y));
	}
	
	public Piece getPiece(Coordinate coord) {
		for(Piece piece : getPieces()) {
			if(piece.getCoordinate().equals(coord)) {
				return piece;
			}
		}
		return null;
	}
	
	private static Set<Piece> removeAll(Set<Piece> collection, Set<Piece> remove) {
		Set<Piece> movedAndRemovedPieces = new HashSet<Piece>(collection);
		movedAndRemovedPieces.removeAll(remove);
		return movedAndRemovedPieces;
	}
	
	/**
	 * Visualization of board
	 * @return - visualization of the Board (current state) 
	 */
	public String displayBoard() {
		StringBuilder sB = new StringBuilder(NEW_LINE);

		for (int y = 0; y < 8; y++) {
			for(int x = 0; x < 8; x++) {
				Piece piece = getPiece(new Coordinate(x, y));
				if (piece != null) {
					if (piece.getPlayer().equals(Player.BLACK)) {
						if (piece.getKinged()) {
							sB.append("|B|");
						} else {
							sB.append("|b|");
						}
					} else {
						if (piece.getKinged()) {
							sB.append("|R|");
						} else {
							sB.append("|r|");
						}
					}
				} else {
					sB.append("| |");
				}
			}
			sB.append(NEW_LINE);
		}
		return sB.toString();
	}
}
