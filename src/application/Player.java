package application;

public class Player extends Entity{
	private PlayerClass pClass;
	private Item[] inventory; 
	//order = [head, chest, legs, feet, hands, trinket, weapon, pet, Rest of Inventory Items] 
	//MaxSize = 20 + 8
	private int money;	
	
	public Player(PlayerClass pClass) {
		super();
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
		super();
		this.pClass = player.pClass;
		this.inventory = player.inventory;
		super.calculatedStats = player.calculatedStats;
		this.money = player.money;
	}
	
	protected void calcStats() {
		this.calculatedStats = new int[7];
		
		for(int i = 0; i < 8; i++) {
			if(!inventory[i].getName().equals("")) {
				super.calculatedStats[0] = super.calculatedStats[0] + inventory[i].getHP();
				super.calculatedStats[1] = super.calculatedStats[1] + inventory[i].getMP();
				super.calculatedStats[2] = super.calculatedStats[2] + inventory[i].getAtk();
				super.calculatedStats[3] = super.calculatedStats[3] + inventory[i].getArmor();
				super.calculatedStats[4] = super.calculatedStats[4] + inventory[i].getHRegen();
				super.calculatedStats[5] = super.calculatedStats[5] + inventory[i].getMRegen();
			}
		}
	}
	
	public void changeEquip(int i) {
		
	}
}
