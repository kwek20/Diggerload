package net.castegaming.game.entities;

import net.castegaming.game.enums.EntityType;

/**
 * Base class for every enemy in the game
 * @author Brord
 *
 */
public abstract class Enemy extends Entity{
	
	public Enemy(EntityType type, double x, double y) {
		super(type, x, y);
		onSpawn();
	}

	/**
	 * Defines waht todo when the enemy spawns.
	 */
	public abstract void onSpawn();
	
	/**
	 * Defines what todo when the enemy touches the player
	 */
	public abstract void onTouch();
}
