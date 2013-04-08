package shop;

import java.util.ArrayList;

import net.castegaming.game.Diggerload;
import net.castegaming.game.entities.Player;
import net.castegaming.game.enums.ShopType;
import net.castegaming.game.loadout.LoadOut;

import android.gameengine.icadroids.objects.GameObject;
import Input.Button;
import Input.ShopToggleButton;

public class ShopHandler extends GameObject{
	private static Shop repairShop;
	private static Button repairShopToggle;
	static private boolean repairShopOpen = false;
	
	private static Diggerload dl;
	
	public ShopHandler(Diggerload dl) {
		this.dl = dl;
		
		initialize();
	}
	
	private static Shop upgradeShop;
	static private boolean upgradeShopOpen = false;
	
	public static boolean getUpgradeShopOpen() {
		return upgradeShopOpen;
	}
	public static void setUpgradeShopOpen(boolean upgradeShopOpen) {
		ShopHandler.upgradeShopOpen = upgradeShopOpen;
	}
	public static boolean getRepairShopOpen() {
		return repairShopOpen;
	}
	public static void setRepairShopOpen(boolean repairShopOpen) {
		ShopHandler.repairShopOpen = repairShopOpen;
	}
	
	private void initialize() {
		repairShop = new Shop(dl);
		repairShopToggle = new ShopToggleButton(50, 10, "repairtoggle", ShopType.REPAIRSHOP);
		dl.addGameObject(repairShopToggle);
		
		for (LoadOut lo : Player.getLoadOuts()) {
			repairShop.addInv(lo);
		}
		
		//upgradeShop = new Shop(dl);
	}
	
	@ Override
	public void update() {
		if (repairShopOpen) {
			repairShop.createButtons();
		} else {
			repairShop.destroyButtons();
		}
		
		/*
		if (upgradeShopOpen) {
			upgradeShop.createButtons();
		}else {
			upgradeShop.destroyButtons();
		}
		*/
	}
}
