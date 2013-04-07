/**
 * 
 */
package net.castegaming.game.enums;

/**
 * @author Brord
 *
 */
public enum Block {
	VOID(0,0, "Unbreakble"),
	AIR(1,0, "Air"),
	DIRT(2, 5, "Dirt"),
	STONE(3,10, "Stone");
	
	public int ID;
	public int points;
	public String name;
	
	private Block(int ID, int points, String name) {
		this.ID = ID;
		this.points = points;
		this.name = name;
	}
	
	/**
	 * Prints the friendly name of this block.
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Method to get the block from its ID
	 * @param blockType The ID of the block
	 * @return The block, or null if the ID doesnt exist
	 */
	public static Block fromID(int blockType) {
		for (Block b : values()){
			if (b.ID == blockType){
				return b;
			}
		}
		
		return null;
	}
}
