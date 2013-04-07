package net.castegaming.game;

import net.castegaming.game.entities.BadGuy;
import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.Direction;
import net.castegaming.game.screen.IngameScreen;
import net.castegaming.game.screen.Screen;
import net.castegaming.game.terrain.T;
import android.R.color;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Canvas;
import android.util.Log;

public class Diggerload extends GameEngine {

	public static GameTiles myTiles = IngameScreen.myTiles;
	public Player player;
	public static boolean updateTileEnvironment = IngameScreen.updateTileEnvironment;
	
	/**
	 * The screen the game is currently on
	 */
	public Screen currentScreen;
	
	@Override
	protected void initialize() {
		super.initialize();
		
		// player initialisation needs to occur before the calling of setTileEnvironment();
		player = new Player(this);
		
		setTitle("Diggerload Alpha");
		currentScreen = new IngameScreen();
		
		addGameObject(player);
		addGameObject(new BadGuy(100, 100));
	}
	
	@Override
	public void update() {
		super.update();
		currentScreen.update(this);
	}
}
