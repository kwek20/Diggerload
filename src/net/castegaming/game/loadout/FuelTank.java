package net.castegaming.game.loadout;

/**
 * The fuel tank class is used to determine the fuel left variable within the player class
 * 
 * @author Jasper Bruhn
 * @version 1.0
 */
public class FuelTank extends LoadOut {

	/**
	 * Constructor used to initialize the LoadOut.
	 * 
	 * @param maxCapacity variable used to store the max value for the selected loadOut
	 * @param price variable used to set the price for the selected loadout
	 */
	public FuelTank(int maxCapacity, int price) {
		super(maxCapacity, price);
	}

}
