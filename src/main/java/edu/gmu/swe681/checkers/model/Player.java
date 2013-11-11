package edu.gmu.swe681.checkers.model;

public enum Player {
	BLACK, RED;
	
	public static Player otherPlayer(Player player) {
		return player == RED ? BLACK : RED;
	}
}
