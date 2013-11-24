package edu.gmu.swe681.checkers.controller;

import java.io.Serializable;
import java.util.*;

import org.apache.commons.lang3.builder.*;
import org.slf4j.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import edu.gmu.swe681.checkers.model.*;



@Controller
@RequestMapping("/move")
public class GameMovementControllerTestClass {
	private static final transient Logger LOG = LoggerFactory.getLogger(GameMovementControllerTestClass.class);

	/**
	 * 
	 * @param piece
	 * @return Piece object as JSON
	 */
	@RequestMapping(value = "piece", method = RequestMethod.POST)
	public @ResponseBody 
	MyPieces post(@RequestBody MyPieces pieces) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.debug("userName: " + user.getUsername());
		MyPieces myPieces = pieces;
		
		//KLUDGE to convert from edu.gmu.swe681.checkers.controller.Piece to edu.gmu.swe681.checkers.model.Piece
		
		
		//TODO: validate whether move is correct.
		//Boolean isMoveValid = true;
		//TODO: determine if the user *needs* to continue making moves - if you can jump, you must.
		//Boolean multiMove = false;
		//TODO: determine if multimove
		//piece.setMultiMoveState(multiMove);	
		//piece.setIsMoveValid(isMoveValid);
		
		return pieces;
	}

	public static class Piece implements Serializable {
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
	
	public static class MyPieces extends ArrayList<Piece> {
		private static final long serialVersionUID = 5512940473318587536L;

		@Override
		public String toString() {
		    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
		
	}
	
}