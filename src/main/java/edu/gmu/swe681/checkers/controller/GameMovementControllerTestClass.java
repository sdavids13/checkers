package edu.gmu.swe681.checkers.controller;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.lang3.builder.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import edu.gmu.swe681.checkers.model.*;
import edu.gmu.swe681.checkers.service.GameService;

@Controller
@RequestMapping("/move")
public class GameMovementControllerTestClass {
	private static final transient Logger LOG = LoggerFactory.getLogger(GameMovementControllerTestClass.class);

	@Autowired
	private GameService gameService;
	
	/**
	 * 
	 * @param piece
	 * @return Piece object as JSON
	 */
	@RequestMapping(value = "piece", method = RequestMethod.POST)
	public @ResponseBody 
	Collection<Piece> post(@RequestBody final Collection<Piece> pieces) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.debug("userName: " + user.getUsername());
		
		//List<Game> games = gameService.getMyActiveGames(user.toString());  
		List<Game> games = gameService.getAvailableGames();
		LOG.debug(games.toString());
		
		Collection<Piece> myPieces = new HashSet<Piece>(pieces); 
		
		Board initialStateBoard = Board.buildInitialBoard();  //TODO get the last (good) state of game board
		LOG.debug(initialStateBoard.displayBoard());
		
		Set<edu.gmu.swe681.checkers.model.Piece> modelPieces = new HashSet<edu.gmu.swe681.checkers.model.Piece>();  
		for (Piece piece : myPieces) {
			Coordinate coord = piece.getCoordinate();
			String isKinged = piece.getKinged();
			String player = piece.getPlayer();
			edu.gmu.swe681.checkers.model.Piece modelPiece = new edu.gmu.swe681.checkers.model.Piece(isKinged, player, coord);
			if (!player.equalsIgnoreCase("0")) { 
				//assert: not an empty square b/c it's not owned by either "2" or "3" player
				modelPieces.add(modelPiece);
			}
		}
		
		Board proposedStateBoard = Board.buildInitialBoard();
		proposedStateBoard.setPieces(modelPieces);
		LOG.debug(proposedStateBoard.displayBoard());
		
		//TODO: validate whether move is correct.
		//Boolean isMoveValid = true;
		//TODO: determine if the user *needs* to continue making moves - if you can jump, you must.
		//Boolean multiMove = false;
		//TODO: determine if multimove
		//piece.setMultiMoveState(multiMove);	
		//piece.setIsMoveValid(isMoveValid);
		
		return pieces;
	}

	public static class Piece implements Serializable {  // TODO: remove this inner class, should rely on model.Piece
		private static final long serialVersionUID = -4638399735364352423L;
		private String player;
		private String kinged;
		private Coordinate coordinate;
		
		public Piece() {
			// do nothing
		}
		public Piece(String kinged, String player, Coordinate coordinate) {
			this.player=player;
			this.kinged = kinged;
			this.coordinate = coordinate;
		}

		public String getPlayer() {
			return player;
		}
		public void setPlayer(String player) {
			this.player = player;
		}
		public String getKinged() {
			return kinged;
		}
		public void setKinged(String kinged) {
			this.kinged = kinged;
		}
		public Coordinate getCoordinate() {
			return coordinate;
		}
		public void setCoordinate(Coordinate coordinate) {
			this.coordinate = coordinate;
		}
		
		@Override
		public String toString() {
		    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}
	
	
}