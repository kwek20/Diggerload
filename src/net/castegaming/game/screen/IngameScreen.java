package net.castegaming.game.screen;

import shop.ShopHandler;
import android.R.color;
import android.gameengine.icadroids.tiles.GameTiles;
import android.graphics.Canvas;
import android.util.Log;
import net.castegaming.game.Diggerload;
import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.Direction;
import net.castegaming.game.terrain.T;

/**
 * The class for when the game is 'ingame'
 * @author Brord
 *
 */
public class IngameScreen extends Screen{

	public Player player;
	public static boolean updateTileEnvironment = true;
	public static GameTiles myTiles;
	private static final int tileSize = 32;
	
	/**
	 * Constructor to create a new Screen.
	 * @param game The game. update and player needs this
	 */
	public IngameScreen(Diggerload game){
		// player initialisation needs to occur before the calling of setTileEnvironment();
		player = new Player(game);
		game.addGameObject(player);
		update(game);
		// break the initial position of the player
		player.breakBlock(Direction.DOWN);
		//game.addGameObject(new ShopHandler(game));
	}

	@Override
	public void update(Diggerload game) {
		game.setBackgroundColor(color.white);
		game.setScreenLandscape(true);
		
		if (updateTileEnvironment) {
			setTileEnvironment();
			game.setTileMap(myTiles);
			updateTileEnvironment = true;
		}
	}
	
	/**
	  * @author Jasper
     * 
     * Function used to create and update the tile environment
     */
   private void setTileEnvironment() {
		String[] tileImagesNames = { "unbre", "air", "dirt", "stone" };
		
		int pX = player.getPlayerX();
		int pY = player.getPlayerY();
		
		//T.createFiles();
		T.clearFiles();
		myTiles = new GameTiles(tileImagesNames, T.getTileMap(pX, pY), tileSize);
		
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
