package net.castegaming.game.enums;

/**
 * Enum used in the Terrain class to determine with side of the player to use.
 * 
 * @author Jasper, Brord
 * @version 1.0
 */
public enum Direction {
	UP(0, -1),
	DOWN(0, 1),
	RIGHT(1, 0),
	LEFT(-1, 0);
	
	public int x;
	public int y;
	
	private Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
}
