package terrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import android.util.Log;


/**
 * Generates/loads & saves the terrain
 * 
 * @author Jasper Bruhn
 * @version 1.0
 */
public class Terrain {
	// path definition for the chunks save files
	private static final String CHUNKDATAFILE = "ChunkData.txt";
	private static final String GENERATEDCHUNKLIST = "ChunkList.txt";
	
	// path definition for reserved chunk save files
	private static final String RESERVEDCHUNKDATAFILE = "ProtectedChunks.txt";
	private static final String RESERVEDCHUNKLIST = "ProtectedChunksData.txt";
	
	/**
	 * create the save files for a new level
	 */
	public static void createFiles() {
		try {
			(new File(CHUNKDATAFILE)).createNewFile();
			(new File(GENERATEDCHUNKLIST)).createNewFile();
			(new File(RESERVEDCHUNKDATAFILE)).createNewFile();
			(new File(RESERVEDCHUNKLIST)).createNewFile();
		} catch (IOException e) {
			Log.e("CreateFile", "Failed");
			e.printStackTrace();
		}
		
		Log.e("test", new File(".").getAbsolutePath());
	}
	
	// ore gen percentages ordered by x coordinate of the chunk
	static int[][] genPercent = 
		{
			{-1, 100},
			{ 0, 100, 1, 10, 2, 5, 3, 8},
			{ 0, 100, 1, 12, 2, 8, 3, 9},
			{ 1, 100}
		};
	
	// current chunk
	private static int[][] chunk = new int[20][20];
	private static int x;
	private static int y;
	
	// tile image names
	private static final String[] TILEIMAGENAMES = { "dirt", "stone", "iron", "coal" };
	
	/**
	 * Generates new chunk based on the given coordinates
	 * and loads the date for that chunk into the static memory of the Terrain class.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 */
	public static void generateChunk(int x, int y) {
		if (chunkExists(x, y, RESERVEDCHUNKLIST)) {
			chunk = getChunkFromMem(x, y, RESERVEDCHUNKDATAFILE, RESERVEDCHUNKLIST);
		} else {
			clearChunk(chunk, genPercent[x][0]);
			for (int t = 2; t < genPercent[x].length; t += 2) {
				fillChunk(genPercent[x][x], genPercent[x][x + 1], chunk);
			}
		}
		
		Terrain.x = x;
		Terrain.y = y;
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
		int[][] tileMap = fillTileMap(chunkX, chunkY);
		int[][] croppedTileMap = new int[30][20];
		
		croppedTileMap = cropTileMap(tileMap, playerX, playerY);
		
		return croppedTileMap;
	}
	
	private static int[][] cropTileMap(int[][] tileMap, int pX, int pY) {
		int[][] croppedTileMap = new int[30][20];
		
		for (int i = 0; i < croppedTileMap.length; i++) {
			for (int k = 0; k < croppedTileMap[0].length; i++) {
				croppedTileMap[i][k] = tileMap[pX - (croppedTileMap.length / 2) + i][pY - (croppedTileMap[0].length / 2) + k];
			}
		}
		
		return croppedTileMap;
	}
	
	private static int[][] fillTileMap(int centerChunkX, int centerChunkY) {
		int[][] tileMap = new int[60][60];
		
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
			
				chunk = getChunk((centerChunkX - 1) + x, (centerChunkY - 1) + y);
				
				for (int i = (x * 20); i < ((20 * x) + 20); i++) {
					for (int k = (y * 20); k < ((20 * y) + 20); k++) {
						tileMap[i][k] = chunk[i - (x * 20)][k - (y * 20)];
					}
				}
				
			}
		}
		
		return tileMap;
	}
	
	// fill the chunk given in the param 'chunkToBeFilled' with the number from 'tileType'
	// based on the given ChanceOfGen
	private static void fillChunk(int tileType, float chanceOfGen, int[][] chunkToFill){
		Random rn = new Random();
		double rndmNr;
		
		for (int i = 0; i < chunkToFill.length; i ++) {
			for (int k = 0; k < chunkToFill[0].length; k++) {
				rndmNr = rn.nextDouble() * 100;
				
				if (rndmNr < chanceOfGen) {
					chunkToFill[i][k] = tileType;
				}
			}
		}
	}
	
	public static String[] getTileimagenames() {
		return TILEIMAGENAMES;
	}


	// fill the given chunk with the given nr
	private static void clearChunk(int[][] chunkToBeCleared, int nr) {
		for (int i = 0; i < chunkToBeCleared.length; i ++) {
			for (int k = 0; k < chunkToBeCleared[0].length; k++) {
				chunkToBeCleared[i][k] = nr;
			}
		}
	}
	
	/**
	 * Returns the chunk variable from the current class.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 * @return the chunk data for the currently saved chunk within the Terrain class
	 */
	public static int[][] getChunk(int x, int y) {
		// check to see if the variable chunk holds the requested chunk data
		if (!(x == Terrain.x && y == Terrain.y)) {
			// check to see if the requested chunk is already generated/ saved
			if (chunkExists(x, y)) {
				chunk = getChunkFromMem(x, y, CHUNKDATAFILE, GENERATEDCHUNKLIST);
			} else {
				// generate the chunk
				// after generation the chunk is automatically saved as chunk
				generateChunk(x, y);
			}
		}
		
		return chunk;
	}
	
	private static int[][] getChunkFromMem(int x, int y, String dataFile, String chunkListFile) {
		int[][] data = new int[20][20];
		String singleLineData = "";
		
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(dataFile));
			
			for (int i = 0; i < getChunkDataRow(x, y, chunkListFile) - 1; i++) {
				singleLineData = r.readLine();
			}
			
			List<String> parts = Arrays.asList(singleLineData.split(","));
			
			for (int i = 0; i < chunk.length; i++) {
				for (int k = 0; k < chunk[0].length; k++) {
					data[i][k] = Integer.parseInt(parts.get(i + k));
				}
			}
			
		} catch (FileNotFoundException e) {
			Log.e("Terrain", "Error: file not found.");
			// // e.printStackTrace();
		} catch (IOException e) {
			Log.e("Terrain", "Error: " + e.getMessage());
			// // e.printStackTrace();
		}
		
		return data;
	}
	
	private static int getChunkDataRow(int x, int y, String fileName) {
		java.io.File file = new java.io.File(fileName);  
		Scanner content;
		int count = -1;
		
		try {
			content = new Scanner(file);
			
			while (content.hasNext()) {
				if ((content.nextLine()).equals(x + ":" + y)) {
					count ++;
				}
			}
		} catch (FileNotFoundException e) {
			Log.e("Terrain", "Error: file " + fileName + " not found.");
			// e.printStackTrace();
		}
		
		return count;
	}
	
	
	/**
	 * Looks for the chunk id within the save file.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 * @return true if the chunk exists within the save file
	 */
	public static boolean chunkExists(int x, int y) {
		return (getChunkDataRow(x, y, GENERATEDCHUNKLIST) != -1);
	}
	
	private static boolean chunkExists(int x, int y, String chunkListFile) {
		return (getChunkDataRow(x, y, chunkListFile) != -1);
	}
	
	/**
	 *  Saves the chunk variable to the save file
	 */
	public static void saveChunk() {
		saveData();
		saveList();
	}
	
	private static void saveList() {
		try{
			FileWriter file = new FileWriter(GENERATEDCHUNKLIST ,true);
	
			file.write(x + ":" + y);
			
			file.close();
		}catch (Exception e){
			Log.e("Terrain", "Error: " + e.getMessage());
		}
	}

	private static void saveData() {
		try{
			FileWriter file = new FileWriter(CHUNKDATAFILE ,true);
			
			String toFile = "";
			
			for (int i = 0; i < chunk.length; i++) {
				for (int k = 0; k < chunk[0].length; k++) {
					toFile += Integer.toString(chunk[i][k]) + ",";
				}
			}
			
			file.write(toFile);
			  
			file.close();
		}catch (Exception e){
			Log.e("Terrain", "Error: " + e.getMessage());
		}
	}
}