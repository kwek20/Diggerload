package net.castegaming.game.screen;

import android.R.color;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Canvas;
import android.util.Log;
import net.castegaming.game.Diggerload;
import net.castegaming.game.entities.Player;
import net.castegaming.game.terrain.T;

public class IngameScreen extends Screen{

	public static boolean updateTileEnvironment = true;
	public static GameTiles myTiles;
	private static final int tileSize = 32;

	@Override
	public void update(Diggerload game) {
		game.setBackgroundColor(color.white);
		game.setScreenLandscape(true);
		
		if (updateTileEnvironment) {
			setTileEnvironment(game.player);
			game.setTileMap(myTiles);
			updateTileEnvironment = true;
			Log.i("INGAMESCREEN", "HELLO?!!");
		}
	}
	
	/**
	  * @author Jasper
     * 
     * Function used to create and update the tile environment
     */
   private void setTileEnvironment(Player p) {
		String[] tileImagesNames = { "unbre", "air", "dirt", "stone" };
		Log.e("tile", "setting tile environment");
		
		int pX = p.getPlayerX();
		int pY = p.getPlayerY();
		
		//T.createFiles();
		//T.clearFiles();
		myTiles = new GameTiles(tileImagesNames, T.getTileMap(pX, pY), tileSize);
		
		// break the initial position of the player
		p.breakblock();
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
