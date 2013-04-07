package Input;

import shop.ShopHandler;
import net.castegaming.game.enums.ShopType;

public class ShopOpenStateChangingButton extends Button {
	private ShopType st;

	public ShopOpenStateChangingButton(int x, int y, String url, ShopType st) {
		super(x, y, url);
		
		this.st = st;
	}

	@Override
	public void action() {
		if (st.equals(ShopType.REPAIRSHOP)) {
			ShopHandler.setRepairShopOpen(!ShopHandler.getRepairShopOpen());
		} else if (st.equals(ShopType.UPGRADESHOP)) {
			ShopHandler.setUpgradeShopOpen(!ShopHandler.getUpgradeShopOpen());
		}
	}

}
