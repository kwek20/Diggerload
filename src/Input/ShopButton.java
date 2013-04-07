package Input;

import net.castegaming.game.entities.Player;
import net.castegaming.game.loadout.LoadOut;

public class ShopButton extends Button {
	private LoadOut lo;

	public ShopButton(int x, int y, String url, LoadOut lo) {
		super(x, y, url);
		
		this.lo = lo;
	}

	@Override
	public void action() {
		if (lo.getCurrentLevel() != lo.getMaxCapacity()) {
			if (Player.money > lo.getPrice()) {
				lo.setCurrentLevel(lo.getCurrentLevel() + 1);
				Player.money -= lo.getPrice();
			}
		}
	}

}
