package application;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Menu extends BlankPane{
	private Text title, playTxt, settingsTxt, quitTxt, loadTxt;

	private Rectangle play, quit, settings, load;
	private Scene scene;
	
	public Menu() {
		super(null);
		super.gameCanvas.setId("Menu");
		super.gameCanvas.getStylesheets().add(String.valueOf(this.getClass().getResource("application.css")));

		title = new Text("Game");
		title.setFont(Font.font("Calibri", 80));
		title.setX(40);
		title.setY(100);

		
		playTxt = new Text("Play");
		playTxt.setFont(Font.font("Calibri", 50));
		playTxt.setX(60);
		playTxt.setY(200);

		play = new Rectangle(55,160,95,50);
		play.setStroke(Color.TRANSPARENT);
		play.setStrokeWidth(1);
		play.setFill(Color.TRANSPARENT);
		play.addEventHandler(MouseEvent.ANY, new PlayHandler());
		
		loadTxt = new Text("Load");
		loadTxt.setFont(Font.font("Calibri", 50));
		loadTxt.setX(60);
		loadTxt.setY(250);
		
		load = new Rectangle(55,210,110,50);
		load.setStroke(Color.TRANSPARENT);
		load.setStrokeWidth(1);
		load.setFill(Color.TRANSPARENT);
		load.addEventHandler(MouseEvent.ANY, new LoadHandler());

		settingsTxt = new Text("Settings");
		settingsTxt.setFont(Font.font("Calibri", 50));
		settingsTxt.setX(60);
		settingsTxt.setY(300);
		
		settings = new Rectangle(55,260,170,50);
		settings.setStroke(Color.TRANSPARENT);
		settings.setStrokeWidth(1);
		settings.setFill(Color.TRANSPARENT);
		settings.addEventHandler(MouseEvent.ANY, new SettingsHandler());

		quitTxt = new Text("Quit");
		quitTxt.setFont(Font.font("Calibri", 50));
		quitTxt.setX(60);
		quitTxt.setY(350);
		
		quit = new Rectangle(55,310,100,50);
		quit.setStroke(Color.TRANSPARENT);
		quit.setStrokeWidth(1);
		quit.setFill(Color.TRANSPARENT);
		quit.addEventHandler(MouseEvent.ANY, new QuitHandler());
		
		gameCanvas.getChildren().addAll(title, playTxt, loadTxt, settingsTxt, quitTxt);
		gameCanvas.getChildren().addAll(play, load, settings, quit);
	}
	
	public void menuScene(Scene scene) {
		this.scene = scene;
	}
	
	private class PlayHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				playTxt.setFill(Color.GREY);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				playTxt.setFill(Color.BLACK);
			}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				setGameCanvas(new LevelSelect(scene));
			}
		}
	}
	
	private class LoadHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				loadTxt.setFill(Color.GREY);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				loadTxt.setFill(Color.BLACK);
			}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			}
		}
	}	
	
	private class SettingsHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				settingsTxt.setFill(Color.GREY);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				settingsTxt.setFill(Color.BLACK);
			}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
//				Pane levelSelect = new Pane(1);
//				setGameCanvas(levelSelect);
			}
		}
	}	
	
	private class QuitHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				quitTxt.setFill(Color.GREY);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				quitTxt.setFill(Color.BLACK);
			}
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED) {
				Platform.exit();
			}
		}
	}
}
