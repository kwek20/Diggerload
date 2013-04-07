package net.castegaming.game;

import net.castegaming.game.entities.BadGuy;
import net.castegaming.game.entities.Entity;
import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.Direction;
import net.castegaming.game.terrain.T;
import android.R.color;
import android.gameengine.icadroids.engine.GameEngine;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Canvas;
import android.util.Log;

public class Diggerload extends GameEngine {
	
	Miner m;
	static GameTiles myTiles;
	private Player player;
	private int tileSize = 32;
	public static boolean updateTileEnvironment = true;
	
	@Override
	protected void initialize() {
		super.initialize();
		// player initialisation needs to occur before the calling of setTileEnvironment();
		player = new Player();
		//setTileEnvironment();
		TouchInput.use = true;

		player = new Player();
		Log.e("player", player.getPlayerX() + "");
		Log.e("player", player.getPlayerY() + "");
		Log.e("width", super.getScreenWidth() + "");
		
		addGameObject(player);
		addGameObject(new BadGuy(100, 100));
		
	}
	
	@Override
	public void update() {
		super.update();
		setBackgroundColor(color.white);
		setScreenLandscape(true);
		
		if (updateTileEnvironment) {
			setTileEnvironment();
			
			updateTileEnvironment = false;
		}
	}
	
	 /**
	  * @author Jasper
      * 
      * Function used to create and update the tile environment
      */
    private void setTileEnvironment() {
		String[] tileImagesNames = { "unbre", "air", "dirt", "stone" };
		Log.e("tile", "setting tile environment");
		
		int pX = player.getPlayerX();
		int pY = player.getPlayerY();
		
		//T.createFiles();
		//T.clearFiles();
		myTiles = new GameTiles(tileImagesNames, T.getTileMap(pX, pY), tileSize);
		setTileMap(myTiles);
		//T.breakBlock(Direction.DOWN, pX, pY);
		myTiles.addTileMap(T.getTileMap(pX, pY), tileSize);
		myTiles.drawTiles(new Canvas());
    }
    
    /**
     * @author Jasper
     * Function used to get the game tile instance used in setting the tilemap.
     * 
     * @return - the GameTile instance
     */
    public static GameTiles getTileInstance() {
    	return myTiles;
    }
}
