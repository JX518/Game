package application;

import javafx.scene.shape.Rectangle;

public abstract class Entity extends Rectangle{
	protected int[] calculatedStats; //Order = [HP, MP, atk, armor, hRegen, mRegen]

	public Entity() {
		calculatedStats = new int[7];
	}
}
