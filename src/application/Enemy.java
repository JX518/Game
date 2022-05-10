package application;

public class Enemy extends Entity{
	public Enemy() {
		super();
		this.setWidth(60);
		this.setHeight(100);
	}
	
	public Enemy(int[] stats){
		super();
		super.calculatedStats = new int[7];
		if(stats.length != calculatedStats.length) {
			throw new IllegalArgumentException();
		} else {
			super.calculatedStats = stats;
		}
		this.setWidth(60);
		this.setHeight(100);
	}

}	
