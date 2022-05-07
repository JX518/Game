package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Lvl1 extends BlankPane{	
	private Player p;
	private AnimationTimer ADAnimations, KAnimations;
	private double yVel, xVel = 0;
	private int jumps;
	private final double yAcc = .6;
	private boolean A, D = false;
	private double nextPlatformL, nextPlatformR; 
	private ArrayList<Rectangle> platforms; 
	
	
	
	public Lvl1(Scene scene) {
		super(scene);
		this.platforms = new ArrayList<Rectangle>();
		this.p = new Player(new PlayerClass(null));
		this.ADAnimations = new LeftRight();
		this.KAnimations = new UpDown();
		this.p.setX(100);
		this.p.setY(100);
		this.p.setFill(Color.WHITE);
		this.p.setOnMouseEntered(new mouseHandler());
		this.p.setOnMouseExited(new mouseHandler());
		
		Rectangle plat1 = new Rectangle(10, 550, 300, 10);
		plat1.setFill(Color.WHITE);
		this.platforms.add(plat1);
		
		scene.addEventHandler(KeyEvent.ANY, new keyHandler());
		
		super.gameCanvas.setStyle("-fx-background-color: #000000");
		super.gameCanvas.getChildren().addAll(p, plat1);

		nextPlatformL = this.calcPlatform(p.getX(), p.getY() + p.getHeight());
		nextPlatformR = this.calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight());
		KAnimations.start();
	}

	//this works for rectangles only
	private double calcPlatform(double x, double y) {
		double max = p.getHeight() + gameCanvas.getMaxHeight();
		for(Rectangle rect : platforms) {
			if(x > rect.getX() && x < rect.getX() + rect.getWidth()) {
				max = max > rect.getY() ? rect.getY() - p.getHeight() : max;
			}
		}
		return max;
	}
	
	private class LeftRight extends AnimationTimer{
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			if(p.getX() > 0 && xVel < 0) {
				p.setX(p.getX() + xVel);
			}

			if(p.getX() < (gameCanvas.getMaxWidth() - p.getWidth()) && xVel > 0) {
				p.setX(p.getX() + xVel);
			}
		}
	}	
	
	private class UpDown extends AnimationTimer{
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			yVel = yVel + yAcc;
			p.setY(p.getY() + yVel);
			
			//something wrong here
			if(yVel >= 0) {
				nextPlatformL = calcPlatform(p.getX(), p.getY() + p.getHeight());
				nextPlatformR = calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight());
			}
			
			double nextPlat = nextPlatformL < nextPlatformR ? nextPlatformL : nextPlatformR;
			
			//it keeps teleporting, idk why?
			if(p.getY() > nextPlat) {
				jumps = 0;
				yVel = 0;
				p.setY(nextPlat);
				KAnimations.stop();
			}
		}
	}
	
	private class mouseHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_ENTERED) {
				p.setFill(Color.FIREBRICK);
			}
			if(event.getEventType() == MouseEvent.MOUSE_EXITED) {
				p.setFill(Color.WHITE);
			}
		}
	}
	
	private class keyHandler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			if(event.getCode() == KeyCode.K && event.getEventType() == KeyEvent.KEY_PRESSED) {
				if(jumps < 2) {
					jumps = jumps + 1;
					yVel = -13.5;
					KAnimations.start();
				}
			}
			
			if(event.getCode() == KeyCode.A && event.getEventType() == KeyEvent.KEY_RELEASED) {
				A = false;
				if(!(A||D)) {
					xVel = 0;
					ADAnimations.stop();
				}
			}	
			
			if(event.getCode() == KeyCode.D && event.getEventType() == KeyEvent.KEY_RELEASED) {
				D = false;
				if(!(A||D)) {
					xVel = 0;
					ADAnimations.stop();
				}
			}
			
			p.setFill(Color.GREEN);
			// TODO Auto-generated method stub
			if(event.getCode() == KeyCode.D && event.getEventType() == KeyEvent.KEY_PRESSED) {
				D = true;
				xVel = 5;
				ADAnimations.start();
			}
			
			if(event.getCode() == KeyCode.A && event.getEventType() == KeyEvent.KEY_PRESSED) {
				A = true;
				xVel = -5;
				ADAnimations.start();
			}
		}
	}
	
}