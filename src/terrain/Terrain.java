package terrain;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
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
	
	// current chunk
	private static int[][] chunk = new int[20][20];
	private static int x;
	private static int y;
	
	/**
	 * Generates new chunk based on the given coordinates
	 * and loads the date for that chunk into the static memory of the current class.
	 * 
	 * @param x coordinate used to identify the chunk position
	 * @param y coordinate used to identify the chunk position
	 */
	public static void generateChunk(int x, int y) {
		// TODO add chunk generation
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
				chunk = getChunkFromMem(x, y);
			} else {
				// generate the chunk
				// after generation the chunk is automatically saved as chunk
				generateChunk(x, y);
			}
		}
		
		return chunk;
	}
	
	private static int[][] getChunkFromMem(int x, int y) {
		int[][] data = new int[20][20];
		String singleLineData = "";
		
		BufferedReader r;
		try {
			r = new BufferedReader(new FileReader(CHUNKDATAFILE));
			
			for (int i = 0; i < getChunkDataRow(x, y) - 1; i++) {
				singleLineData = r.readLine();
			}
			
			List<String> parts = Arrays.asList(singleLineData.split(","));
			
			for (int i = 0; i < chunk.length; i++) {
				for (int k = 0; k < chunk[0].length; k++) {
					data[i][k] = Integer.parseInt(parts.get(i + k));
				}
			}
			
		} catch (FileNotFoundException e) {
			Log.e("Terrain", "Error: file " + GENERATEDCHUNKLIST + " not found.");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("Terrain", "Error: " + e.getMessage());
			e.printStackTrace();
		}
		
		return data;
	}
	
	private static int getChunkDataRow(int x, int y) {
		java.io.File file = new java.io.File(GENERATEDCHUNKLIST);  
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
			Log.e("Terrain", "Error: file " + GENERATEDCHUNKLIST + " not found.");
			e.printStackTrace();
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
		return (getChunkDataRow(x, y) != -1);
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