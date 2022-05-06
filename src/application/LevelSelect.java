package application;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LevelSelect extends BlankPane{
	
	private Rectangle lvl1;
	
	public LevelSelect(Scene scene) {
		super(scene);
		super.gameCanvas.setId("LevelSelect");
		super.gameCanvas.getStylesheets().add(String.valueOf(this.getClass().getResource("application.css")));
		lvl1 = new Rectangle(570,420,50,80);
		lvl1.setFill(Color.TRANSPARENT);
		
		lvl1.setOnMouseClicked(new Lvl1Handler());
		lvl1.setOnMouseEntered(new Lvl1Handler());
		lvl1.setOnMouseExited(new Lvl1Handler());
		
		gameCanvas.getChildren().add(lvl1);
//		super.gameCanvas.setId("pane");
//		super.gameCanvas.getStylesheets().add(String.valueOf(this.getClass().getResource("application.css")));
	}

	private class Lvl1Handler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				setGameCanvas(new Lvl1(scene));
			}
			
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				lvl1.setStroke(Color.BLACK);
			}
			
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				lvl1.setStroke(Color.TRANSPARENT);
			}
		}
		
	}
}
