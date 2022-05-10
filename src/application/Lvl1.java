package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Lvl1 extends BlankPane{	
	private Player player;
	private Text pText;
	private AnimationTimer ADAnimations, KAnimations;
	private double yVel, xVel = 0;
	private boolean jump1, jump2 = true;
	private final double yAcc = .6;
	private boolean A, D = false;
	double lastDEvent, lastAEvent;
	private double nextPlatformL, nextPlatformR; 
	private ArrayList<Rectangle> platforms;
	private ArrayList<Enemy> enemiesW1;
	
	public Lvl1(Scene scene) {
		super(scene);
		this.platforms = new ArrayList<Rectangle>();
		this.player = new Player(new PlayerClass(null));
		this.ADAnimations = new LeftRight();
		this.KAnimations = new UpDown();
		this.player.setX(100);
		this.player.setY(100);
		this.player.setFill(Color.WHITE);
		pText = new Text("player");
		pText.setFill(Color.BLUE);
		pText.setX(player.getX() + player.getWidth()/4);
		pText.setY(player.getY() + player.getHeight()/2);
		
		Rectangle bottomCover = new Rectangle(0,this.getGameHeight(),this.getGameWidth(),player.getHeight()+100);
		bottomCover.setFill(new Color(.15, .15, .15, 1));
		
		Rectangle topCover = new Rectangle(0,(-1)*(player.getHeight()+100),this.getGameWidth(),player.getHeight()+100);
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
		super.gameCanvas.getChildren().addAll(plat1, plat2, plat3);
		super.gameCanvas.getChildren().addAll(player, pText);
		super.gameCanvas.getChildren().addAll( bottomCover, topCover);

		nextPlatformL = this.calcPlatform(player.getX(), player.getY() + player.getHeight());
		nextPlatformR = this.calcPlatform(player.getX() + player.getWidth(), player.getY() + player.getHeight());
		KAnimations.start();
	}

	private void startWave1() {
		
	}
	//this works for rectangles only
	private double calcPlatform(double x, double y) {
		double min = this.getGameHeight();
		for(Rectangle rect : platforms) {
			if(x >= rect.getX() && x <= rect.getX() + rect.getWidth() && y <= rect.getY() + yVel) {
				min = rect.getY() <= min ? rect.getY() - player.getHeight() : min;
			}
		}
		return min;
	}
	
	private class LeftRight extends AnimationTimer{
		@Override
		public void handle(long now) {
			// TODO Auto-generated method stub
			nextPlatformL = calcPlatform(player.getX(), player.getY() + player.getHeight());
			nextPlatformR = calcPlatform(player.getX() + player.getWidth(), player.getY() + player.getHeight());
			
			//left
			if(player.getX() > 0 && xVel < 0) {
				player.setX(player.getX() + xVel);
				pText.setX(player.getX() + player.getWidth()/4);
				if(nextPlatformR > player.getY()) {
					KAnimations.start();
				}
			}

			//right
			if(player.getX() < (gameCanvas.getMaxWidth() - player.getWidth()) && xVel > 0) {
				player.setX(player.getX() + xVel);
				pText.setX(player.getX() + player.getWidth()/4);
				if(nextPlatformL > player.getY() + player.getHeight()) {
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
			player.setY(player.getY() + yVel);
			pText.setY(player.getY() + player.getHeight()/2);
			
			nextPlatformL = calcPlatform(player.getX(), player.getY() + player.getHeight());
			nextPlatformR = calcPlatform(player.getX() + player.getWidth(), player.getY() + player.getHeight());
			
			nextPlat = nextPlatformL < nextPlatformR ? nextPlatformL : nextPlatformR;
			
			if(player.getY() > nextPlat) {
				yVel = 0;
				player.setY(nextPlat);
				pText.setY(player.getY() + player.getHeight()/2);
				jump1 = true;
				jump2 = true;
				KAnimations.stop();
				startWave1();
			}
			
		}
	}
	
	private class keyHandler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent event) {
			boolean onGround = 
					(player.getY() - 10 <= calcPlatform(player.getX(), player.getY() + player.getHeight())
					&& player.getY() + 10 >= calcPlatform(player.getX(), player.getY() + player.getHeight())) || 
					(player.getY() + 10 >= calcPlatform(player.getX() + player.getWidth(), player.getY() + player.getHeight())
					&& player.getY() - 10 <= calcPlatform(player.getX() + player.getWidth(), player.getY() + player.getHeight()));
			
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
