package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BlankPane extends BorderPane {

	protected Pane gameCanvas;
	protected Scene scene;
	private final double gameHeight = 600;
	private final double gameWidth = 800;

	public BlankPane(Scene scene) {
        gameCanvas = new Pane();
        this.scene = scene;

        //set background colors
        gameCanvas.setStyle("-fx-background-color: #000000;");
        
        gameCanvas.setMaxHeight(gameHeight);
        gameCanvas.setMaxWidth(gameWidth);
        gameCanvas.setMinHeight(gameHeight);
        gameCanvas.setMinWidth(gameWidth);
        
        this.setCenter(gameCanvas);
    }

	public double getGameHeight() {
		return this.gameHeight;
	}
	
	public double getGameWidth() {
		return this.gameWidth;
	}
	
	public void setGameCanvas(Pane pane) {
		gameCanvas = pane;
		this.setCenter(pane);
	}
	
	public Scene getGameScene() {
		return this.scene;
	}
}