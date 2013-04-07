package net.castegaming.game.loadout;

import net.castegaming.game.enums.LoadOutType;

/**
 * The LoadOut class is the base class for all loadouts, loadouts are used to determine the fuel, damage and health values of the player.
 * 
 * @author Jasper Bruhn
 * @version 1.0
 */
public class LoadOut {
	protected int maxCapacity;
	protected int currentLevel;
	protected int price;
	protected LoadOutType type;
	
	/**
	 * Function used the get the price of the current loadout.
	 * @return the price of the loadout
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * Constructor used to initialize the LoadOut.
	 * 
	 * @param maxCapacity variable used to store the max value for the selected loadOut
	 * @param price variable used to set the price for the selected loadout
	 */
	public LoadOut(int maxCapacity, int price, LoadOutType type) {
		this.maxCapacity = maxCapacity;
		currentLevel = maxCapacity;
		this.price = price;
		this.type = type;
	}
	
	/**
	 * Function used to get the current value
	 * 
	 * @return the current value of the selected loadout
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * Function used to set the value for the selected loadout
	 * 
	 * @param currentLevel the value to be stored as the current value (note: the currentLevel of the LoadOut class not not be below 0)
	 */
	public void setCurrentLevel(int currentLevel) {
		if ((this.currentLevel + currentLevel) >= 0)
			this.currentLevel = currentLevel;
	}
	
	/**
	 * Function used to set the current level to the max capacity
	 */
	public void fill() {
		currentLevel = maxCapacity;
	}
	
	/**
	 * Function used to reset the current level to 0
	 */
	public void empty() {
		currentLevel = 0;
	}
	
	/**
	 *  Function used to get the maxCapacity of the current LoadOut
	 * 
	 * @return the maxCapacity of the currently selected LoadOut
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}
	
	/**
	 *  Function used to return the type of the loadout
	 * @return the load out type (LoadOutType)
	 */
	public LoadOutType getType() {
		return type;
	}
}
