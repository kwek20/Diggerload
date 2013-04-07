package net.castegaming.game.terrain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import android.gameengine.icadroids.persistence.GamePersistence;
import android.util.Log;

/**
 * @author Jasper Bruhn
 * @version 2.0
 */
public class Terrain {
	
	// current chunk
		private static int[][] chunk = new int[20][20];
		private static int chunkSize = 20;
		private static int x;
		private static int y;
		
	// ore gen percentages ordered by x coordinate of the chunk
	static int[][] genPercent = 
		{
			{-1, 100},
			{ 0, 100, 1, 10, 2, 5},
			{ 0, 100, 1, 12, 2, 8},
			{ 1, 100}
		};
	
	public static void clearEverything() {
		for(int i = 0 ; i < 20; i++) {
			for (int k = 0; k < 20; k++) {
				GamePersistence data = new GamePersistence("" + i + ":" + k + ".txt");
				data.saveData("");
			}
		}
	}
	
	/**
	 * Function used to fill the tilemap based on the player position
	 * 
	 * @param chunkX the variable to identify the chunk the player is currently in
	 * @param chunkY the variable to identify the chunk the player is currently in
	 * @param playerX player position relative to the center chunk
	 * @param playerY player position relative to the center chunk
	 * @return the tilemap used to fill the screen
	 */
	public static int[][] getTileMap(int chunkX, int chunkY, int playerX, int playerY) {
		Log.i("GetTileMap", "");
		return cropTileMap(fillTileMap(chunkX, chunkY), playerX, playerY);
	}
	
	/**
	 * Function used to fill the initial tile map 
	 * 
	 * @param centerChunkX the coordinates of the center chunk
	 * @param centerChunkY the coordinates of the center chunk
	 * @return the initial tile map
	 */
	private static int[][] fillTileMap(int centerChunkX, int centerChunkY) {
		Log.i("FilleTileMap", "");
		int[][] tileMap = new int[60][60];
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
			
				getChunk((centerChunkX - 1) + x, (centerChunkY - 1) + y);
				
				for (int i = (x * 20); i < ((20 * x) + 20); i++) {
					for (int k = (y * 20); k < ((20 * y) + 20); k++) {
						tileMap[i][k] = chunk[i - (x * 20)][k - (y * 20)];
					}
				}
				
			}
		}
		
		return tileMap;
	}
	
	/**
	 * Function used to crop the tile map to fit the standard screen size
	 * 
	 * @param tileMap the tilemap to crop
	 * @param pX the player position within the center chunk
	 * @param pY the player position within the center chunk
	 * @return the cropped tile map
	 */
	private static int[][] cropTileMap(int[][] tileMap, int pX, int pY) {
		int[][] croppedTileMap = new int[20][20];
		
		for (int i = 0; i < croppedTileMap[0].length; i++) {
			for (int k = 0; k < croppedTileMap.length; k++) {
				croppedTileMap[k][i] = tileMap[((pX + 20) - (croppedTileMap.length / 2)) + i][((pY + 20) - (croppedTileMap[0].length / 2)) + k];
			}
		}
		
		croppedTileMap[pX + 10][pY + 10] = -1;
		
		return croppedTileMap;
	}
	
	/**
	 * Returns the chunk variable from the current class.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 * @return the chunk data for the currently saved chunk within the Terrain class
	 */
	public static int[][] getChunk(int x, int y) {
		Log.i("GetChunk", "");
		if (!(x == Terrain.x && y == Terrain.y)) {
			if (chunkExists(x, y)) {
				getChunkFromMem(x, y);
			} else {
				// generate the chunk
				// after generation the chunk is automatically saved as chunk
				generateChunk(x, y);
			}
		}
		
		saveChunk();
		
		return chunk;
	}
	
	/**
	 * Generates new chunk based on the given coordinates
	 * and loads the date for that chunk into the static memory of the Terrain class.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 */
	public static void generateChunk(int x, int y) {
		Log.i("GenerateChunk", "");
		// clearChunk(genPercent[x][0]);
		
		for (int t = 0; t <= genPercent[y].length - 2; t += 2) {
			fillChunk(genPercent[y][t], genPercent[y][t + 1]);
		}
		
		Terrain.x = x;
		Terrain.y = y;
	}
	
	/**
	 * Function used to fill the current chunk with block data
	 * 
	 * @param tileType the ore type to be generated
	 * @param chanceOfGen the chance of gen for this block
	 */
	private static void fillChunk(int tileType, float chanceOfGen){
		Log.i("FillChunk", "");
		Random rn = new Random();
		double rndmNr;
		
		for (int i = 0; i < chunk.length; i ++) {
			for (int k = 0; k < chunk[0].length; k++) {
				rndmNr = rn.nextDouble() * 100;
				
				if (rndmNr < chanceOfGen) {
					chunk[i][k] = tileType;
				}
			}
		}
	}
	
	/**
	 * Looks for the chunk id within the save file.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 * @return true if the chunk exists within the save file
	 */
	public static boolean chunkExists(int x, int y) {
		Log.i("ChunkExists", "");
		GamePersistence data = new GamePersistence("" + x + ":" + y + ".txt");
		return !((data.loadData()).equals(""));
	}
	
	/**
	 * Function used to pull a specific chunk from the save file.
	 * 
	 * @param x coordinate used to identify the chunk
	 * @param y coordinate used to identify the chunk
	 * @param dataFile the file to pull the chunk data from usually Terrain.CHUNKDATAFILE
	 * @param chunkListFile the file to pull the data position from usually Terrain.GENERATEDCHUNKLIST
	 * @return the newly loaded chunk
	 */
	public static int[][] getChunkFromMem(int x, int y) {
		Log.i("GetChunkFromMem", "");
		
		GamePersistence data = new GamePersistence("" + x + ":" + y + ".txt");
		List<String> list = Arrays.asList((data.loadData()).split(","));
		
		for (int i = 0; i < chunkSize; i++) {
			for (int k = 0; k < chunkSize; k++) {
				chunk[i][k] = Integer.parseInt(list.get(i + k));
			}
		}
		
		String temp = "";
		for (int i = 0; i < chunk.length; i++) {
			for (int k = 0; k < chunk[0].length; k++) {
				temp = temp + chunk[k][i] + ",";
			}
		}
		
		Terrain.x = x;
		Terrain.y = y;
		
		Log.e("loading:  " + Terrain.x + ":" + Terrain.y, temp);
		
		return chunk;
	}
	
	/**
	 * Removes a specific block from the current chunk.
	 * 
	 * @param direction the direction the player is facing (1 - up, 2 - right, 3 - down, 4 - left)
	 * @param playerX the current player position in relation to the current chunk
	 * @param playerY the current player position in relation to the current chunk
	 */
	public static void removeBlock(int direction, int playerX, int playerY) {
		switch(direction) {
		case 1:
			setBlock(playerX, playerY - 1, -1);
			break;
		case 2:
			setBlock(playerX + 1, playerY, -1);
			break;
		case 3:
			setBlock(playerX, playerY + 1, -1);
			break;
		case 4:
			setBlock(playerX - 1, playerY, -1);
			break;
		}
	}
	
	private static void setBlock(int x, int y, int value) {
		Terrain.x -= 1;
		Terrain.y -= 1;
		
		chunk = getChunkFromMem(Terrain.x, Terrain.y);
		
		chunk[x][y] = value; 
		
		saveChunk();
	}
	
	private static void saveChunk() {
		GamePersistence data = new GamePersistence("" + Terrain.x + ":" + Terrain.y + ".txt");
		data.saveData("");
		
		String temp = "";
		for (int i = 0; i < chunk.length; i++) {
			for (int k = 0; k < chunk[0].length; k++) {
				temp = temp + chunk[i][k] + ",";
			}
		}
		
		Log.e("Saving:    " + Terrain.x + ":" + Terrain.y, temp);
		
		data.saveData(temp);
	}
}
