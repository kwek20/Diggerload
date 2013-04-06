package net.castegaming.game;

import net.castegaming.game.entities.BadGuy;
import net.castegaming.game.entities.Player;
import net.castegaming.game.terrain.Terrain;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.tiles.GameTiles;
import android.util.Log;

public class Diggerload extends GameEngine {
	
	Miner m;
	 
	@Override
	protected void initialize() {
		super.initialize();
		createTileEnvironment();

		addGameObject(new Player());
		addGameObject(new BadGuy(100, 100));
		
	}
	
	@Override
	public void update() {
		Log.i("Diggerload", "HELLO!?!!");
	}
	
	 /**
     * Create background with tiles
     */
    private void createTileEnvironment() {
		String[] tileImagesNames = { "dirt", "stone", "iron", "grass" };
		
		
		Terrain.createFiles();
		GameTiles myTiles = new GameTiles(tileImagesNames, Terrain.getTileMap(1, 1, 10, 10), 32);
		setTileMap(myTiles);
    }
}
