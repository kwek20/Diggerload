package net.castegaming.game.loadout;

import java.util.ArrayList;

import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.LoadOutType;

/**
 * Class used to handle all player loadouts
 * 
 * @author Jasper Bruhn
 * @version 1.0
 */
public class LoadOutHandler {
	private ArrayList<LoadOut> lo = new ArrayList<LoadOut>();
	private int maxNrOfLoadOuts;
	private Player playerInstance;
	
	/**
	 * The constructor to initialize the load out handler
	 * 
	 * @param maxNrOfLoadOuts the maximum amount of loadout the player can have
	 * @param playerInstance  the player instance this loadout handler belongs to
	 */
	public LoadOutHandler(int maxNrOfLoadOuts, Player playerInstance) {
		this.maxNrOfLoadOuts = maxNrOfLoadOuts;
		this.playerInstance = playerInstance;
	}
	
	/**
	 * Function used to update all load outs
	 */
	public void updateAllLoadOuts() {
		playerInstance.setFuelLevel(getHighestLoadOut(LoadOutType.FUELTANK).getCurrentLevel());
		playerInstance.setHealth(getHighestLoadOut(LoadOutType.HULLSHIELD).getCurrentLevel());
	}
	
	/**
	 * Function to set/ change the max nr of loadouts
	 * @param nr the new maximum nr of loadouts the player can have
	 */
	public void setMaxNrOfLoadOuts(int nr) {
		maxNrOfLoadOuts = nr;
	}
	
	/**
	 * Function the add a new loadout
	 *
	 * @param newLoadOut the new loadout to be added
	 * 
	 * @see LoadOutType
	 */
	public void addNewLoadout(LoadOut newLoadOut) {
		if (lo.size() < maxNrOfLoadOuts)
			lo.add(newLoadOut);
	}
	
	/**
	 * Function used to get the the instance of the best loadout
	 * 
	 * @param type the loadout type to get
	 * @return the best loadout from the loadouttype given
	 */
	public LoadOut getHighestLoadOut(LoadOutType type) {
		LoadOut max = null;
		
		// TODO add more loadout types
		for (LoadOut l : lo) {
			if ((type == LoadOutType.FUELTANK) && l.getType() == LoadOutType.FUELTANK) {
				max = getMax(max, l);
			} else if (type == LoadOutType.HULLSHIELD && l.getType() == LoadOutType.FUELTANK) {
				max = getMax(max, l);
			}
		}
		
		return max;
	}
	
	/**
	 * Function used to get the loadout with the highest max capacity
	 * 
	 * @param lo1 - loadOut 1
	 * @param lo2 - loadOut 2
	 * @return the loadout with the highest max capacity
	 */
	private LoadOut getMax(LoadOut lo1, LoadOut lo2) {
		if (lo1.getMaxCapacity() > lo2.getMaxCapacity()) {
			return lo1;
		} else
			return lo2;
	}
}
