package edu.gmu.swe681.checkers.controller;

import java.io.Serializable;


import org.apache.commons.lang3.builder.*;
import org.slf4j.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
		LOG.debug(piece.toString());
		return piece;
	}

	public static class Piece implements Serializable {
		private static final long serialVersionUID = 1L;
		private int row;
		private int col;

		public int getRow() {
			return row;
		}

		public void setRow(int row) {
			this.row = row;
		}

		public int getCol() {
			return col;
		}

		public void getCol(int col) {
			this.col = col;
		}
		
		@Override
		public String toString() {
		    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}
}