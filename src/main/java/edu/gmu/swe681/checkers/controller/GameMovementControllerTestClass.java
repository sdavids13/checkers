package edu.gmu.swe681.checkers.controller;

import java.io.Serializable;

import org.apache.commons.lang3.builder.*;
import org.slf4j.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import edu.gmu.swe681.checkers.model.User;

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
	Piece post(@RequestBody final Piece piece) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName = user.getUsername();	
		LOG.debug(userName + " moved piece from row/col " + piece.getFromRow() +  "," + piece.getFromCol() + " to row/col " + piece.getRow() + "," + piece.getCol()); 
		
		//TODO: validate whether move is correct.
		Boolean isMoveValid = true;
		//TODO: determine if the user *needs* to continue making moves - if you can jump, you must.
		Boolean multiMove = false;
		
		
		piece.setMultiMoveState(multiMove);
		piece.setIsMoveValid(isMoveValid);
		
		return piece;
	}

	public static class Piece implements Serializable {
		private static final long serialVersionUID = 1L;

		private int fromRow;
		private int fromCol;
		private int row;
		private int col;

		private Boolean multiMoveState;
		private Boolean isMoveValid;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}
		
		public void setCol(int col) {
			this.col = col;
		}

		public void getCol(int col) {
			this.col = col;
		}
		public int getFromRow() {
			return fromRow;
		}

		public void setFromRow(int fromRow) {
			this.fromRow = fromRow;
		}

		public int getFromCol() {
			return fromCol;
		}

		public void setFromCol(int fromCol) {
			this.fromCol = fromCol;
		}
		
		public Boolean getMultiMoveState() {
			return multiMoveState;
		}

		public void setMultiMoveState(Boolean multiMoveState) {
			this.multiMoveState = multiMoveState;
		}

		public Boolean getIsMoveValid() {
			return isMoveValid;
		}

		public void setIsMoveValid(Boolean isMoveValid) {
			this.isMoveValid = isMoveValid;
		}


		
		@Override
		public String toString() {
		    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}
}