package net.castegaming.game.screen;

import net.castegaming.game.Diggerload;
import android.R.color;

public abstract class Screen {

	/**
	 * The method which gets called when the game loops
	 * @param game
	 */
	public abstract void update(Diggerload game);
	
}
