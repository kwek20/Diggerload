package Input;

import net.castegaming.game.entities.Player;

/**
 * Button extension used to switch between the mining and moving mode of the player
 * @author Jasper
 * @version 1.0
 */
public class MoveModeChangingButton extends Button {
	private Player playerInstance;

	/**
	 * Constructor for the MoveModeChangingButton class
	 * @param x - the x position of the button
	 * @param y - the y position of the button
	 * @param url - the texture url of the button
	 * @param playerInstance - the player isntance this button responds to
	 */
	public MoveModeChangingButton(int x, int y, String url, Player playerInstance) {
		super(x, y, url);
		
		this.playerInstance = playerInstance;
	}
	
	/**
	 * @see Button.action()
	 */
	@ Override
	public void action() {
		playerInstance.setMovingMode(!playerInstance.getMovingMode());
	}
}
