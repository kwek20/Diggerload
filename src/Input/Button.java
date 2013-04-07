package Input;

import net.castegaming.game.entities.Player;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;

/**
 * Class used to draw the button to switch between mining and moving mode.
 * 
 * @author Jasper
 *
 */
public class Button extends GameObject{
	private int x, y;
	private int size;
	private Player playerInstance;
	private boolean pressing;
	
	/**
	 * Constructor for the button class.
	 * 
	 * @param x - the x coordinate of the button
	 * @param y - the y coordinate of the button
	 * @param playerInstance - the class the button responds to.
	 */
	public Button(int x, int y, Player playerInstance) {
		this.x = x;
		this.y = y;
		this.playerInstance = playerInstance;
	
		TouchInput.use = true;
		drawButton();
	}
	
	@ Override
	public void update() {
		super.update();
		if (TouchInput.onPress)
			pressing = true;
			
		if (pressing) {
			if (overButton()) 
				action();
		}
		
		if (TouchInput.onRelease)
			pressing = false;
	}
	
	private void drawButton() {
		super.setPosition(x, y);
		      setSprite("button1");
		      
		size = super.getFrameWidth();
	}
	
	/**
	 * Function used to check whether the touch input is above/on the button or not.
	 * @return true if the touch input is on the button.
	 */
	public boolean overButton() {
		return (TouchInput.xPos > x && TouchInput.xPos < x + size) && (TouchInput.yPos > y && TouchInput.yPos < y + size);
 	}
	
	private void action() {
		playerInstance.setMovingMode(!playerInstance.getMovingMode());
	}
}
