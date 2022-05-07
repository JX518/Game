package application;

public class Item {
	String type, name;
	protected int hp, mp, attack, armor, hRegen, mRegen;

	public Item(String type, String name, int hp, int mp, int atk, int armor, int hRegen, int mRegen){
		this.type = type;
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.attack = atk;
		this.armor = armor;
		this.hRegen = hRegen;
		this.mRegen = mRegen;
	}
	
	public Item(String type, String name, int lvl){
		this.type = type;
		this.name = name;
		this.hp = 0;
		this.mp = 0;
		this.attack = 0;
		this.armor = 0;
		this.hRegen = 0;
		this.mRegen = 0;
	}

	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public int getHP() {
		return this.hp;
	}
	
	public int getMP() {
		return this.mp;
	}
	
	public int getAtk() {
		return this.attack;
	}
	
	public int getArmor() {
		return this.armor;
	}
	
	public int getHRegen() {
		return this.hRegen;
	}
	
	public int getMRegen() {
		return this.mRegen;
	}
}
