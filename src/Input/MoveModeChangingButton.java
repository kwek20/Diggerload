package Input;

import net.castegaming.game.entities.Player;

public class MoveModeChangingButton extends Button {
	private Player playerInstance;

	public MoveModeChangingButton(int x, int y, String url, Player playerInstance) {
		super(x, y, url);
		
		this.playerInstance = playerInstance;
	}
	
	private void action() {
		playerInstance.setMovingMode(!playerInstance.getMovingMode());
	}
}
