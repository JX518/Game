package application;

import javafx.scene.shape.Rectangle;

public class Player extends Rectangle{
	private PlayerClass pClass;
	private Item[] inventory; 
		//order = [head, chest, legs, feet, hands, trinket, weapon, pet, Rest of Inventory Items] 
		//MaxSize = 20 + 8
	private int[] calculatedStats; //Order = [HP, MP, atk, armor, hRegen, mRegen]
	private int money;
	
	public Player(PlayerClass pClass) {
		this.setWidth(60);
		this.setHeight(100);
		this.pClass = pClass;
		this.inventory = new Item[28];
		for(int i = 0; i < 28; i++) {
			this.inventory[i] = new NoItem();
		}
		this.calcStats();
		money = 0;
	}

	public Player(Player player) {
		this.pClass = player.pClass;
		this.inventory = player.inventory;
		this.calculatedStats = player.calculatedStats;
		this.money = player.money;
	}
	
	protected void calcStats() {
		this.calculatedStats = new int[7];
		for(int x : this.calculatedStats) 
			x = 0;
		
		for(int i = 0; i < 8; i++) {
			if(!inventory[i].getName().equals("")) {
				this.calculatedStats[0] = this.calculatedStats[0] + inventory[i].getHP();
				this.calculatedStats[1] = this.calculatedStats[1] + inventory[i].getMP();
				this.calculatedStats[2] = this.calculatedStats[2] + inventory[i].getAtk();
				this.calculatedStats[3] = this.calculatedStats[3] + inventory[i].getArmor();
				this.calculatedStats[4] = this.calculatedStats[4] + inventory[i].getHRegen();
				this.calculatedStats[5] = this.calculatedStats[5] + inventory[i].getMRegen();
			}
		}
	}
	
	public void changeEquip(int i) {
		
	}
}
