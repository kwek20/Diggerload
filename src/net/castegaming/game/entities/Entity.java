package net.castegaming.game.entities;

import java.util.List;

import net.castegaming.game.enums.EntityType;

import android.gameengine.icadroids.objects.MoveableGameObject;
import android.gameengine.icadroids.objects.collisions.ICollision;
import android.gameengine.icadroids.objects.collisions.TileCollision;

/**
 * Base class for all the entities.
 * @author Brord
 *
 */
public abstract class Entity extends MoveableGameObject implements ICollision{
	
	private boolean death;
	private int health;
	private EntityType type;
	
	/**
	 * Makes an entity in the center of the map.</br>
	 * This entity will be in the center of the map.
	 * @param type The {@link EntityType} of this entity
	 */
	public Entity(EntityType type){
		this(type, 5, 5);
	}
	
	/**
	 * Creates an entity with the sprite and the type</br>
	 * This will spawn him at the coordinates defined
	 * @param type The {@link EntityType} of this entity
	 * @param x The starting x position of this entity
	 * @param y The starting y position of this entity
	 */
	public Entity(EntityType type, double x, double y){
		this.type = type;
		setPosition(x, y);
		setSprite(type.getSprite(), type.getFrames());
	}
	
	/**
	 * 
	 */
	@Override
	public void collisionOccurred(List<TileCollision> collidedTiles) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Defines what todo when this entity updates<br/>
	 * Mostly used for movement.
	 */
	public abstract void update();
	
	/**
	 * Sends you the type of entity
	 * @return {@link EntityType} The type
	 */
	public EntityType getType(){
		return type;
	}

	/**
	 * Gets this entity its health
	 * @return The health as an integer
	 */
	public int getHealth(){
		return health;
	}
	
	/**
	 * Sets this entity its health
	 * @param health The new health
	 */
	public void setHealth(int health){
		this.health = health;
	}
	
	/**
	 * Damages this entity</br>
	 * This can not be negative.</br>
	 * You cannot damage if it is allready death
	 * @param damage The damage to do
	 * @return Whether the entity died from this(true) or not(false)
	 */
	public boolean damage(int damage){
		if (damage < 0 || death) damage = 0;
		if (health <= damage){
			health = 0;
			death = true;
			return true;
		}
		health -= damage;
		return false;
	}
	
	/**
	 * Tells you if this entity is death
	 * @return true if it is death, otherwise false
	 */
	public boolean isDeath(){
		return death;
	}
	
	/**
	 * Sets the entity to this death state
	 * @param state true (death), or false (alive)
	 */
	public void setDeath(boolean state){
		death = state;
	}
}
