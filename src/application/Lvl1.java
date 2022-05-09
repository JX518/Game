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
	private boolean jump1, jump2 = true;
	private final double yAcc = .6;
	private boolean A, D = false;
	double lastDEvent, lastAEvent;
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

		Rectangle bottomCover = new Rectangle(0,this.getGameHeight(),this.getGameWidth(),p.getHeight()+100);
		bottomCover.setFill(new Color(.15, .15, .15, 1));
		
		Rectangle topCover = new Rectangle(0,(-1)*(p.getHeight()+100),this.getGameWidth(),p.getHeight()+100);
		topCover.setFill(new Color(.15, .15, .15, 1));

		Rectangle plat1 = new Rectangle(10, 550, 300, 10);
		plat1.setFill(Color.WHITE);
		this.platforms.add(plat1);

		Rectangle plat2 = new Rectangle(500, 550, 300, 10);
		plat2.setFill(Color.WHITE);
		this.platforms.add(plat2);

		Rectangle plat3 = new Rectangle(250, 300, 300, 10);
		plat3.setFill(Color.WHITE);
		this.platforms.add(plat3);
		
		scene.addEventHandler(KeyEvent.ANY, new keyHandler());
		
		super.gameCanvas.setStyle("-fx-background-color: #000000");
		super.gameCanvas.getChildren().addAll(p, plat1, plat2, plat3, bottomCover, topCover);

		nextPlatformL = this.calcPlatform(p.getX(), p.getY() + p.getHeight());
		nextPlatformR = this.calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight());
		KAnimations.start();
	}

	//this works for rectangles only
	private double calcPlatform(double x, double y) {
		double min = this.getGameHeight();
		for(Rectangle rect : platforms) {
			if(x >= rect.getX() && x <= rect.getX() + rect.getWidth() && y <= rect.getY() + yVel) {
				min = rect.getY() <= min ? rect.getY() - p.getHeight() : min;
			}
		}
		return min;
	}
	
	private class LeftRight extends AnimationTimer{
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			nextPlatformL = calcPlatform(p.getX(), p.getY() + p.getHeight());
			nextPlatformR = calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight());
			
			//left
			if(p.getX() > 0 && xVel < 0) {
				p.setX(p.getX() + xVel);
				if(nextPlatformR > p.getY()) {
					KAnimations.start();
				}
			}

			//right
			if(p.getX() < (gameCanvas.getMaxWidth() - p.getWidth()) && xVel > 0) {
				p.setX(p.getX() + xVel);
				if(nextPlatformL > p.getY() + p.getHeight()) {
					KAnimations.start();
				}
			}
		}
	}	
	
	private class UpDown extends AnimationTimer{
		@Override
		public void handle(long now) {
			double nextPlat = -100;
			// TODO Auto-generated method stub
			yVel = yVel + yAcc;
			p.setY(p.getY() + yVel);
			
			nextPlatformL = calcPlatform(p.getX(), p.getY() + p.getHeight());
			nextPlatformR = calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight());
			
			nextPlat = nextPlatformL < nextPlatformR ? nextPlatformL : nextPlatformR;
			
			if(p.getY() > nextPlat) {
				yVel = 0;
				p.setY(nextPlat);
				jump1 = true;
				jump2 = true;
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
			boolean onGround = 
					(p.getY() - 10 <= calcPlatform(p.getX(), p.getY() + p.getHeight())
					&& p.getY() + 10 >= calcPlatform(p.getX(), p.getY() + p.getHeight())) || 
					(p.getY() + 10 >= calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight())
					&& p.getY() - 10 <= calcPlatform(p.getX() + p.getWidth(), p.getY() + p.getHeight()));
			
			if(event.getCode() == KeyCode.K && event.getEventType() == KeyEvent.KEY_PRESSED) {
				if(jump2 && (!jump1 || !onGround)) {
					jump2 = false;
					yVel = -13.5;
					KAnimations.start();
					
				}
				
				if(jump1 && onGround) {
					jump1 = false;
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
				if(System.currentTimeMillis() - 1000 < lastDEvent && System.currentTimeMillis() - 100 > lastDEvent) {
					xVel = 7;
				} else {
					xVel = 3;
				}
				lastDEvent = System.currentTimeMillis();
				ADAnimations.start();
			}
			
			if(event.getCode() == KeyCode.A && event.getEventType() == KeyEvent.KEY_PRESSED) {
				A = true;
				if(System.currentTimeMillis() - 1000 < lastAEvent && System.currentTimeMillis() - 100 > lastAEvent) {
					xVel = -7;
				} else {
					xVel = -3;
				}
				lastAEvent = System.currentTimeMillis();
				ADAnimations.start();
			}
		}
	}
	
}
