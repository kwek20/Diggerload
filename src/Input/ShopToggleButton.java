package Input;

import android.util.Log;
import shop.ShopHandler;
import net.castegaming.game.enums.ShopType;

public class ShopToggleButton extends Button {
	private ShopType st;
	
	public ShopToggleButton(int x, int y, String url, ShopType st) {
		super(x, y, url);
		
		this.st = st;
	}

	@Override
	public void action() {
		Log.e("shop toggle button", "toggle: " + st);
		
		if (st.equals(ShopType.REPAIRSHOP)) {
			ShopHandler.setRepairShopOpen(!ShopHandler.getRepairShopOpen());
		} else if (st.equals(ShopType.UPGRADESHOP)) {
			ShopHandler.setUpgradeShopOpen(!ShopHandler.getUpgradeShopOpen());
		}
	}

}
