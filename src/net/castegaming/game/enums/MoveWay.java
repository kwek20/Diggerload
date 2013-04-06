package net.castegaming.game.enums;

/**
 * Splist the movement in 4 directions
 * @author Brord
 *
 */
public enum MoveWay {
	RIGHT(90),
	LEFT(270),
	UP(0),
	DOWN(180);
	
	public double direction;
	
	private MoveWay(double direction) {
		this.direction = direction;
	}
}
