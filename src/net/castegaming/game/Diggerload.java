package net.castegaming.game;

import shop.ShopHandler;
import net.castegaming.game.screen.IngameScreen;
import net.castegaming.game.screen.Screen;
import net.castegaming.game.screen.StartScreen;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.tiles.GameTiles;

public class Diggerload extends GameEngine {

	public static GameTiles myTiles = IngameScreen.myTiles;
	public static boolean updateTileEnvironment = IngameScreen.updateTileEnvironment;
	private static GameEngine game;
	
	/**
	 * The screen the game is currently on
	 */
	public Screen currentScreen;
	
	@Override
	protected void initialize() {
		super.initialize();
		
		setTitle("Diggerload Alpha");
		TouchInput.use = true;
		
		currentScreen = new StartScreen(this);
		game = this;
		//currentScreen = new IngameScreen(this);
	}
	
	@Override
	public void update() {
		super.update();
		currentScreen.update(this);
	}

	public static GameEngine getGame() {
		// TODO Auto-generated method stub
		return game;
	}
}
