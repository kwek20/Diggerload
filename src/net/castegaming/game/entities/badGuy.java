package net.castegaming.game.entities;

import net.castegaming.game.enums.EntityType;

public class BadGuy extends Enemy{

	public BadGuy(double x, double y) {
		super(EntityType.BADGUY, x, y);
		onSpawn();
	}

	@Override
	public void onSpawn() {
		
	}

	@Override
	public void onTouch() {
		
	}
	
	@Override
	public void update() {
		
	}
}
