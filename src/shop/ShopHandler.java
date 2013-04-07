package shop;

import java.util.ArrayList;
import Input.Button;

public class ShopHandler {
	private static ArrayList<Button> repairShop = new ArrayList<Button>();
	static private boolean repairShopOpen = false;
	
	private static ArrayList<Button> upgradeShop = new ArrayList<Button>();
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
	
	
}
