package net.castegaming.game;

import net.castegaming.game.entities.BadGuy;
import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.Direction;
import net.castegaming.game.terrain.T;
import net.castegaming.game.terrain.Terrain;
import android.R.color;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Canvas;
import android.graphics.Color;

public class Diggerload extends GameEngine {
	
	Miner m;
	static GameTiles myTiles;
	
	@Override
	protected void initialize() {
		super.initialize();
		createTileEnvironment();
		TouchInput.use = true;

		addGameObject(new Player());
		addGameObject(new BadGuy(100, 100));
		
	}
	
	@Override
	public void update() {
		super.update();
		setBackgroundColor(color.white);
		setScreenLandscape(true);
	}
	
	 /**
     * Create background with tiles
     */
    private void createTileEnvironment() {
		String[] tileImagesNames = { "unbre", "air", "dirt", "stone" };
		
		// Terrain.clearEverything();
		//T.createFiles();
		T.clearFiles();
		myTiles = new GameTiles(tileImagesNames, T.getTileMap(100, 35), 32);
		setTileMap(myTiles);
		T.breakBlock(Direction.DOWN, 100, 35);
		T.breakBlock(Direction.RIGHT, 100, 35);
		T.breakBlock(Direction.UP, 100, 35);
		myTiles.addTileMap(T.getTileMap(100, 35), 32);
		myTiles.drawTiles(new Canvas());
    }
    
    public static GameTiles getTileInstance() {
    	return myTiles;
    }
}
