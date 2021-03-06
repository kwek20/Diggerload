package net.castegaming.game.screen;

import android.gameengine.icadroids.input.TouchInput;
import net.castegaming.game.Diggerload;

public class StartScreen extends Screen{

	/**
	 * Creates a new Startscreen. <br/>
	 * This gets called on the launch of the game
	 * @param game
	 */
	public StartScreen(Diggerload game) {
		game.setBackground("main_screen");
	}
	
	@Override
	public void update(Diggerload game) {
		if (TouchInput.onPress){
			game.currentScreen = new IngameScreen(game);
		}
	}
}
