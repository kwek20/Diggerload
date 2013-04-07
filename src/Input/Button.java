package Input;

import net.castegaming.game.entities.Player;
import android.gameengine.icadroids.input.TouchInput;
import android.gameengine.icadroids.objects.GameObject;

public class Button extends GameObject{
	private int x, y;
	private int size = 50;
	private Player playerInstance;
	private boolean pressing;
	
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
	}
	
	public boolean overButton() {
		return (TouchInput.xPos > x && TouchInput.xPos < x + size) && (TouchInput.yPos > y && TouchInput.yPos < y + size);
 	}
	
	private void action() {
		playerInstance.setMovingMode(!playerInstance.getMovingMode());
	}
}
