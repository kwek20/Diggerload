package net.castegaming.game.terrain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.castegaming.game.Diggerload;
import net.castegaming.game.enums.Direction;

import android.gameengine.icadroids.persistence.GamePersistence;
import android.util.Log;

/**
 * Class used to handle all terrain generation/removal
 * 
 * @author Jasper
 * @version 2.2
 */
public class T {
	private static long seed = 9373;
	private static Random rn;
	
	private static int windowWidth = 33;
	private static int windowHeight = 18;
	
	private static int[][] chunk = new int[20][200];
	
	/**
	 * Variable that holds the block id for the VOID block
	 */
	public static int VOID = 0;
	/**
	 * Variable that holds the block id for the AIR block
	 */
	public static int AIR = 1;
	/**
	 * Variable that holds the block id for the DIRT block
	 */
	public static int DIRT = 2;
	/**
	 * Variable that holds the block id for the STONE block
	 */
	public static int STONE = 3;
	
	private static ArrayList<Integer> blockBreakingBlackList = new ArrayList<Integer>(VOID);
	
	private static int[][] genPercent = 
		{
			{VOID, 100},
			{AIR, 100},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20},
			{DIRT, 100, STONE, 20}
		};

	/**
	 *  Function used to set the seed for the current level. <br /> The seed defaults to 9373. 
	 * @param seed - the variable used as the new seed.
	 */
	public static void setSeed(long seed) {
		T.seed = seed;
	}
	
	/**
	 * Function used to create the save files.
	 */
	public static void createFiles() {
		for (int i = 0; i < 10; i++) {
			GamePersistence file = new GamePersistence(i + ".txt");
			if (file.loadData().equals(""))
				file.saveData("");
		}
	}
	
	/**
	 * Function used to clear all save files.
	 */
	public static void clearFiles() {
		for (int y = 0; y < genPercent.length; y++) {
			GamePersistence file = new GamePersistence(y + ".txt");
			file.saveData("");
		}
	}
	
	/**
	 * Function used to get the tilemap. <br /> The tilemap is centered around the player.
	 * 
	 * @param pX - the absolute player position (x)
	 * @param pY - the absolute player position (y)
	 * @return the tilemap. The size of the tilmap is depended on the window height and window width variables declared in T
	 */
	public static int[][] getTileMap(int pX, int pY) {
		return cropTileMap(fillTileMap(pY), pX, pY);
	}

	private static int[][] cropTileMap(int[][] tileMap, int pX, int pY) {
		int[][] newTileMap = new int[windowHeight][windowWidth];
		
		for (int y = 0; y < windowHeight; y++) {
			for (int x = 0; x < windowWidth; x++) {
				// Log.i("y", ((pY - (((pY % chunk.length) - 1) * chunk.length)) - (height / 2) + y) + "");
				// Log.i("x", (pX - (width / 2) + x) + "");
				newTileMap[y][x] = tileMap[(pY - (((pY / chunk.length) - 1) * chunk.length)) - (windowHeight / 2) + y][pX - (windowWidth / 2) + x];
			}
		}
		
		return newTileMap;
	}
	
	private static int[][] fillTileMap(int pY) {
		int[][] tileMap = new int[60][200];
		int topChunk = (pY / chunk.length) - 1;
		
		for (int i = 0; i < 3; i++) {
			getChunk(topChunk + i);
			
			for (int y = 0; y < chunk.length; y++) {
				for (int x = 0; x < chunk[y].length; x++) {
					tileMap[y + (chunk.length * i)][x] = chunk[y][x];
				}
			}
		}
		
		return tileMap;
	}

	private static void getChunk(int y) {
		generateChunk(y);
		checkChunkAgainstFile(y);
	}

	private static void checkChunkAgainstFile(int y) {
		GamePersistence file = new GamePersistence(y + ".txt");
		
		//Log.e("file", file.loadData());
		
		if (!(file.loadData()).equals("")) {
			List<String> list = Arrays.asList((file.loadData()).split(":"));
			for (String st : list) {
				List<String> index = Arrays.asList(st.split(","));
				
				//Log.e("file checking", "removing block");
				//Log.e("file checking", "x: " + Integer.parseInt(index.get(1)));
				//Log.e("file checking", "y: " + Integer.parseInt(index.get(0)));
				
				for (int i = 0; i <= 0; i++) {
					//for (int k = -40; k < 50; k++) {
						chunk[Integer.parseInt(index.get(0)) + i][Integer.parseInt(index.get(1)) - (windowWidth / 3)] = AIR;
					//}
				}
				// chunk[Integer.parseInt(index.get(0))][Integer.parseInt(index.get(1))] = AIR;
			}
		}
	}

	private static void generateChunk(int y) {
		rn = new Random(seed);
		
		//Log.i("gen", y +"");
		//Log.i("gen2", genPercent[y].length + "");
		
		for (int i = 0; i <= genPercent[y].length - 2; i += 2) {
			fillChunk(genPercent[y][i], genPercent[y][i + 1]);
		}
		
	}

	private static void fillChunk(int type, int percentage) {
		for (int y = 0; y < chunk.length; y++) {
			for (int x = 0; x < chunk[y].length; x++) {
				if (rn.nextDouble() * 100 < percentage) {
					chunk[y][x] = type;
					// Log.i("fill", "" + type);
					// Log.i("fill nr", rn.nextDouble() * 100 + "");
					// Log.i("fill per", percentage + "");
				}
			}
		}
	}
	
	/**
	 * Function used to break a specific block around the player.
	 *  
	 * @param dir - the direction of the block that should be broken
	 * @param pX - the absolute player position (x)
	 * @param pY - the absolute player position (y)
	 * @return true if the block has successfully been broken/ mined.
	 * 
	 * @see Direction
	 */
	public static boolean breakBlock(Direction dir, int pX, int pY) {
		pX += 11;
		if (dir == Direction.UP) {
			if (canBeBroken(pY - 1, pX))
				save(pY - 1, pX);
			return canBeBroken(pY - 1, pX);
		} else if (dir == Direction.RIGHT) {
			if (canBeBroken(pY, pX + 1))
				save(pY, pX + 1);
			return canBeBroken(pY, pX + 1);
		} else if (dir == Direction.DOWN) {
			if (canBeBroken(pY + 1, pX))
				save(pY + 1, pX);
			return canBeBroken(pY + 1, pX);
		} else if (dir == Direction.LEFT) {
			if (canBeBroken(pY, pX - 1))
				save(pY, pX - 1);
			return canBeBroken(pY, pX - 1);
		}
		
		return false;
	}
	
	/**
	 * Function used to determine whether or not a block can be broken/mined. 
	 * 
	 * @param x - the absolute x position of the block to be checked.
	 * @param y - the absolute y position of the block to be checked.
	 * @return true if the block can be broken/ mined.
	 */
	public static boolean canBeBroken(int x, int y) {
		int chunkY = (y % 20) == 0 ? ((y - 1) / chunk.length) : (y / chunk.length);
		getChunk(chunkY);
		
		for (Integer i : blockBreakingBlackList) {
			if (chunk[y - ((y / chunk.length) * chunk.length)][x] == i) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void save(int y, int x) {
		//Log.e("save", y - (((y / chunk.length)) * chunk.length) - (windowHeight / 2) + "");
		//Log.e("save2", x - (windowWidth / 2) + "");
		
		//Log.e("save3", Diggerload.getTileInstance().getMapHeigth() + "");
		//Log.e("save4", Diggerload.getTileInstance().getMapWidth() + "");
		// Diggerload.getTileInstance().changeTile(x - (windowWidth / 2), y - (((y / chunk.length) - 1) * chunk.length) - (windowHeight / 2), AIR);
		
		GamePersistence file = new GamePersistence((y / chunk.length) + ".txt");
		String data = "";
		
		if (file.loadData().equals("")) {
			data = (y - ((y / chunk.length) * chunk.length)) + "," + x;
		} else {
			data = file.loadData() + ":" + (y - ((y / chunk.length) * chunk.length)) + "," + x;
		}
		
		file.saveData(data);
	}

	public static int getTileType(int x, int y) {
		getChunk(y / chunk.length);
		
		return chunk[y - ((y / chunk.length) * chunk.length)][x];
	}
}
