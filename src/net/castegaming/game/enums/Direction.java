package net.castegaming.game.enums;

/**
 * Enum used in the Terrain class to determine with side of the player to use.
 * 
 * @author Jasper, Brord
 * @version 1.0
 */
public enum Direction {
	UP(-1),
	DOWN(1),
	RIGHT(1),
	LEFT(-1);
	
	public int move;
	
	private Direction(int move){
		this.move = move;
	}
}
