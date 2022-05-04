package application;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends BlankPane{
	private Text title;
	private Text playTxt;
	private Text loadTxt;
//	private Text settingsTxt;
//	private Text quitTxt;
//
	private Rectangle play;
//	private Rectangle quit;
//	private Rectangle settings;
	private Rectangle load;

	public Menu() {
		super.gameCanvas.setStyle("-fx-background-color: #87CEEB;");

		title = new Text("Game");
		title.setFont(Font.font("Calibri", 80));
		title.setX(40);
		title.setY(100);

		play = new Rectangle(55,160,100,50);
		play.setStroke(Color.RED);
		play.setStrokeWidth(1);
		play.setFill(Color.TRANSPARENT);
		play.setOnMouseEntered(new PlayHandler());
		play.setOnMouseExited(new PlayHandler());

		playTxt = new Text("Play");
		playTxt.setFont(Font.font("Calibri", 50));
		playTxt.setX(60);
		playTxt.setY(200);

		loadTxt = new Text("Load");
		loadTxt.setFont(Font.font("Calibri", 50));
		loadTxt.setX(60);
		loadTxt.setY(200);

		playTxt = new Text("Play");
		playTxt.setFont(Font.font("Calibri", 50));
		playTxt.setX(60);
		playTxt.setY(200);



		gameCanvas.getChildren().addAll(title, playTxt);
		gameCanvas.getChildren().addAll(play);
//		play.setOnMouseClicked();
	}
	
	private class PlayHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				play.setStroke(Color.ORANGE);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				play.setStroke(Color.BLACK);
			}
		}
	}
}
