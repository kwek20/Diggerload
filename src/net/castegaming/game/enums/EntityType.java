package net.castegaming.game.enums;

/**
 * An enum containing all the different entity types
 * @author Brord
 *
 */
public enum EntityType {
	ENEMY("Enemy", "default_enemy", 1),
	PLAYER("Player", "player", 1), 
	BADGUY("Bad guy", "bad_guy", 1);
	
	String name;
	String sprite;
	int frames;
	
	private EntityType(String name, String sprite, int frames) {
		this.name = name;
		this.sprite = sprite;
		this.frames = frames;
	}
	
	/**
	 * Sends you an easy to read string containing all the enums
	 * @return a string in this format: "Type"
	 */
	public static String getAll(){
		EntityType[] values = values();
		String readableString = "";
		
		for (int i=0; i<values.length; i++){
			readableString += values[i].toString();
			if (i < values.length) {
				readableString += ", ";
			} else {
				readableString += ".";
			}
		}
		return readableString;
	}
	
	/**
	 * Returns a better copy of this enum its name
	 */
	@Override
	public String toString() {
		return name;
	}

	public String getSprite() {
		return sprite;
	}
	
	public int getFrames() {
		return frames;
	}
}
