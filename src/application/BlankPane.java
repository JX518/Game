package application;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BlankPane extends BorderPane {

	protected Pane gameCanvas;
	protected Scene scene;

	public BlankPane(Scene scene) {
        gameCanvas = new Pane();
        this.scene = scene;

        //set background colors
        gameCanvas.setStyle("-fx-background-color: #000000;");
        
        gameCanvas.setMaxHeight(600);
        gameCanvas.setMaxWidth(800);
        gameCanvas.setMinHeight(600);
        gameCanvas.setMinWidth(800);
        
        this.setCenter(gameCanvas);
    }
	
	public void setGameCanvas(Pane pane) {
		gameCanvas = pane;
		this.setCenter(pane);
	}
	
	public Scene getGameScene() {
		return this.scene;
	}
}