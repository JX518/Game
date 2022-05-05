package application;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class BlankPane extends BorderPane {

	protected Pane gameCanvas;

	public BlankPane() {
        gameCanvas = new Pane();

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
	
}