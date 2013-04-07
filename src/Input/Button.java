package Input;

import net.castegaming.game.entities.Player;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;

/**
 * Class used to draw a simple button.
 * 
 * @author Jasper
 *
 */
public abstract class Button extends GameObject{
	private int x, y;
	private int size;
	private String url;
	private boolean pressing;
	
	/**
	 * Constructor for the button class.
	 * 
	 * @param x - the x coordinate of the button
	 * @param y - the y coordinate of the button
	 * @param url - the url of the button texture
	 */
	public Button(int x, int y, String url) {
		this.x = x;
		this.y = y;
		this.url = url;
	
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
		      setSprite(url);
		      
		size = super.getFrameWidth();
	}
	
	/**
	 * Function used to check whether the touch input is above/on the button or not.
	 * @return true if the touch input is on the button.
	 */
	public boolean overButton() {
		return (TouchInput.xPos > x && TouchInput.xPos < x + size) && (TouchInput.yPos > y && TouchInput.yPos < y + size);
 	}
	
	/**
	 * Function to be called on button press.
	 */
	public abstract void action();
}
