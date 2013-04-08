package shop;

import java.util.ArrayList;

import android.util.Log;

import Input.Button;
import Input.ShopButton;

import net.castegaming.game.Diggerload;
import net.castegaming.game.enums.LoadOutType;
import net.castegaming.game.loadout.LoadOut;

public class Shop{
	private int startX = 100;
	private int startY = 100;
	private int height = 50;
	
	private ArrayList<LoadOut> inv = new ArrayList<LoadOut>();
	private ArrayList<Button> buttons = new ArrayList<Button>();
	
	private Diggerload dl;
	
	public Shop(Diggerload dl) {
		this.dl = dl;
	}
	
	public void addInv(LoadOut lo) {
		inv.add(lo);
	
		Log.e("add inv", lo.getType() + "");
		
		if (lo.getType().equals(LoadOutType.FUELTANK)) {
			buttons.add(new ShopButton(startX, startY + (inv.size() * height), "fueltank", lo));
		}else if (lo.getType().equals(LoadOutType.HULLSHIELD)) {
			buttons.add(new ShopButton(startX, startY + (inv.size() * height), "hullshield", lo));
		}
	}
	
	public void createButtons() {
		for (Button b : buttons) {
			dl.addGameObject(b);
		}
	}
	
	public void destroyButtons() {
		for (Button b : buttons) {
			b.remove();
		}
	}
}
