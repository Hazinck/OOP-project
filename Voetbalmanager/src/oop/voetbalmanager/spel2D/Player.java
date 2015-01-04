package oop.voetbalmanager.spel2D;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.view.ViewFrame;


public class Player {
	
	private Speler speler;
	private Sprite spriteObj;
	private int playerID;
	double  x , y, speedX, speedY, oldX, oldY, targetX, targetY;
	int spriteHalfWidth = 15;
	int spriteHalfHeight = 15;
	int count;
	int pointIdx = -1;
	private Double[] xA = new Double[4];
	private Double[] yA = new Double[4];
	private boolean ballOwner = false;
	private Ball ball;
	private String richting="";
	private int team12;
	private Rectangle boundsAnchor;
	//private Rectangle bounds = new Rectangle();
	private Ellipse2D.Double circleBounds;
	private boolean collision = false;
	private Controller controlBall;
	private GamePanel gp;
	private Wedstrijdteam wteam;
	private int collisionCount = 0;
	/**
	 * 
	 * @param speler
	 * @param teamNum eerste of tweede team(1e staat links op het veld, 2e rechts)
	 */
	public Player(Speler speler,  int team12, int playerID, Ball ball, Wedstrijdteam wteam, GamePanel gp){
		this.speler = speler;
		if(wteam.getNaam().equals("Feyenoord")){
			this.spriteObj = new Sprite("images/"+wteam.getNaam(), team12);
		}else{
			this.spriteObj = new Sprite("images/general", team12);
		}
		this.playerID = playerID;
	//	position = new Position(this, speler.getType(), playerID);
		this.ball = ball;
		this.team12 = team12;
		this.gp = gp;
		this.wteam = wteam;
		boundsAnchor = new Rectangle();
		setSpeed();
		
		ArrayList<Player> myTeam;
		if(team12==1){
			myTeam = gp.getPlayerListTeam1();
		}else{
			myTeam = gp.getPlayerListTeam2();
		}
		controlBall = new Controller(myTeam, ball);
		xMinGrens =  ViewFrame.getFrameWidth()/2 - 1275;//-600;//bounds.getMinX();
		xMaxGrens =  ViewFrame.getFrameWidth()/2 + 1750;//3000;//bounds.getMaxX();
		yMinGrens = ViewFrame.getFrameHeight()/2 - 300;//-805//bounds.getMinY();
		yMaxGrens = ViewFrame.getFrameHeight()/2 + 1241;//bounds.getMaxY();
		circleBounds = new Ellipse2D.Double(x-15, y-30, 40, 48);
	}
	private double xMinGrens;
	private double xMaxGrens;
	private double yMinGrens;
	private double yMaxGrens;
	
	public void move(Rectangle2D bounds, int targetX, int targetY ) {
//		this.bounds.setRect((int)x-15, (int)y-30, 40, 48);
		circleBounds.setFrame(x-22, y-30, 48, 48);
		
		controlBall.controlBallPerPlayer(this);
//		System.out.println("player before: "+ targetX);
		Dimension target = runTo(targetX, targetY);
		targetX = target.width;
		targetY = target.height;
//		System.out.println("player after: "+ targetX);

		count++;
		checkTired();
		 	String type = speler.getType();
		 //	System.out.println("Speed: " + speedX + ", " + speedY +" " + speler.getNaam()+" Pos: "+x + ", "+y);
			
		//	System.out.println("xMaxGrens" + xMaxGrens);
			oldX = x;
			oldY = y;
			pointIdx++;
			if(pointIdx==4){
				pointIdx=0;
			}
			
			
		//	grenzen(type, playerID);
			//moving			
			if(x > targetX){
				x -= speedX;
				
			}
			if(x < targetX){
				x += speedX;
			}  
			if(y > targetY){
				y -= speedY;
			}
			if(y < targetY){
				y += speedY;
			} 
			
			//bounds
			if (x - spriteHalfWidth<= xMinGrens) {
			      x=oldX;//x += (speedX+1);
			}else  if (x + spriteHalfWidth >= xMaxGrens) {
		    	x=oldX;//x -= (speedX+1);
		    }
		    if (y - spriteHalfHeight <= yMinGrens) {
		    	y=oldY;//y += (speedY+1);
		    }else  if (y + spriteHalfHeight >= yMaxGrens) {
		    	y=oldY;// y -= (speedY+1);
		    }
		    findRichting();
		    xA[pointIdx] = x;
		    yA[pointIdx] = y;
		    
	  }
	
	public void findRichting(){
		double dx = x - oldX;
		double dy = y - oldY;
		if(xA[2]!=null && Math.abs(xA[2]-xA[0])<=1 && Math.abs(yA[2]-yA[0])<=1){
			
		//	System.out.println((Math.abs(dy)+Math.abs(dx))/2);
			spriteObj.setIdle(true);
		//	System.out.println(richting);
		}else{
			spriteObj.setIdle(false);
		}
			richting="";
			if(dy>0){
				richting="S";
			}else if(dy<0){
				richting="N";
			}
			
			if(dx>0){
				richting+="E";
			}else if(dx<0){
				richting+="W";
			}
			spriteObj.setRichting(richting);
	//	}
	}
	
	public void setSpeed(){
		speedX = (speler.getOffense() + speler.getDefence())/ 33;//60;
		
		if(speedX<0.5){
			speedX = 0.5;
		}else if(speedX>2){
			speedX = 2;
		}
		speedY =  speedX;
//		if(speedY<0.5){
//			speedY = 0.5;
//		}else if(speedY>3){
//			speedY = 3;
//		}
	}
	
	public void checkTired(){
	//	System.out.println(count);
		if(count>speler.getUithouding()*20){
		//	System.out.println(speler.getNaam() + " is moe");
			speedX -= 1;
			speedY -= 1;
			if(speedX < 1){
				speedX = 1;
			}
			if(speedY < 1){
				speedY = 1;
			}
			
		}
	}
	
	public Dimension runTo(double targetX, double targetY){
		double x;
		double y;
		if(collision){
			x= this.targetX;
			y = this.targetY;
		}else{
			if(ballOwner){// && bounds.contains(ball.getXforP(), ball.getYforP())){//this.x == ball.getX() && this.y ==  ball.getY()){
			//	System.out.println(speler.getNaam() + " got another target");
				if(team12==1){
					x = 2256;
				}else{
					x = 300;
				}
				y = 806;
			}else if((ballAfstand() < anchorAfstand() && anchorAfstand() < boundsAnchor.width*2) || 
				boundsAnchor.contains(ball.getXforP(), ball.getYforP())){//!bounds.contains(this.x, this.y)){//
				x = targetX;
				y = targetY;
			}else{//!boundsAnchor.contains(this.x, this.y)){//
				x = boundsAnchor.x + boundsAnchor.width/2;
				y = boundsAnchor.height/2 + boundsAnchor.y;
			}
			this.targetX = x;
			this.targetY = y;
		}
		return new Dimension((int)x, (int)y);
	}
	
//	public void randomRun(){
//		
//	}
	
	public void setGrenzen(int anchorX, int anchorY){
		
		boundsAnchor.x =  anchorX - 100;
		boundsAnchor.width = 300;
		boundsAnchor.y = anchorY - 200;
		boundsAnchor.height = 400;
		
		if(speler.getType().equals("doelman") && team12 == 1){
			boundsAnchor.x =  anchorX - 500;
			boundsAnchor.width = 600;
			boundsAnchor.y = anchorY - 400;
			boundsAnchor.height = 800;
		}else if(speler.getType().equals("doelman") && team12 == 2){
			boundsAnchor.x =  anchorX - 50;
			boundsAnchor.width = 600;
			boundsAnchor.y = anchorY - 400;
			boundsAnchor.height = 800;
		}
		
	//	System.out.println(boundsAnchor.x + " " + boundsAnchor.width + " " + speler.getNaam());
		
	}
	
	public double ballAfstand(){
		double a = Math.pow((ball.getXforP() - x), 2);
		double b = Math.pow((ball.getYforP() - y), 2);
		double afst = Math.sqrt(a + b);
//		System.out.println("to ball: " + afst + " " + speler.getNaam());
		return afst;
	}
	
	public double anchorAfstand(){
//		boundsCenterX = bounds.x + bounds.width/2;
//		boundsCenterY = bounds.y + bounds.height/2;
		double a = Math.pow((boundsAnchor.getCenterX() - x), 2);
		double b = Math.pow((boundsAnchor.getCenterY() - y), 2);
		double afst = Math.sqrt(a + b);
//		System.out.println("to anchor: " + afst + " " + speler.getNaam());
		return afst;
	}
	/**
	 * @return the x
	 */
	public double getX() {
	
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the spriteObj
	 */
	public Sprite getSpriteObj() {
		return spriteObj;
	}

	/**
	 * @return the speler
	 */
	public Speler getSpeler() {
		return speler;
	}
	
	
	
	 public void grenzen(String type, int playerID){
		  if(type.equals("doelman")){
			  speedX = speedY = 2;//voorbeeld van versnellen per player
				if(playerID<11){
					xMinGrens = 44;
					xMaxGrens = 192;
				}else{
					xMinGrens = 646;
					xMaxGrens = 795;
				}
				yMinGrens = 82;
				yMaxGrens = 442;
			}
		  if(type.equals("verdediger")){
				if(playerID<11){
					xMinGrens = 44;
					xMaxGrens = 288;
				}else{
					xMinGrens = 552;
					xMaxGrens = 795;
				}
				if(playerID == 1 || playerID == 14){
				//	System.out.println(x +" " +y);
					yMinGrens = 180;
					yMaxGrens = 360;
				}else if(playerID == 2 || playerID == 12){
				//	System.out.println(x +" " +y);
					yMinGrens = 320;
					yMaxGrens = 443;
				}else if(playerID == 3 || playerID == 13){
				//	System.out.println(x +" " +y);
					yMinGrens = 11;
					yMaxGrens = 140;
				}else if(playerID == 4 || playerID == 15){
//					System.out.println(x +" " +y);
					yMinGrens = 110;
					yMaxGrens = 220;
				}
				
			}
		  if(type.equals("middenvelder")){
				if(playerID<11){
					xMinGrens = 400;
					xMaxGrens = 470;
				}else{
					xMinGrens = 370;
					xMaxGrens = 740;
				}
//				yMinGrens = 82;
//				yMaxGrens = 177;
			}
	  }

	/**
	 * @return the speedX
	 */
	public double getSpeedX() {
		return speedX;
	}

	/**
	 * @return the speedY
	 */
	public double getSpeedY() {
		return speedY;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Player [speler=" + speler + "]";
	}

	/**
	 * @return the ballOwner
	 */
	public boolean isBallOwner() {
		return ballOwner;
	}

	/**
	 * @param ballOwner the ballOwner to set
	 */
	public void setBallOwner(boolean ballOwner) {
		this.ballOwner = ballOwner;
	}

	/**
	 * @return the boundsAnchor
	 */
	public Rectangle getBoundsAnchor() {
		return boundsAnchor;
	}

	

	/**
	 * @return the targetX
	 */
	public double getTargetX() {
		return targetX;
	}

	/**
	 * @return the targetY
	 */
	public double getTargetY() {
		return targetY;
	}

	/**
	 * @param targetX the targetX to set
	 */
	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	/**
	 * @param targetY the targetY to set
	 */
	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}

	/**
	 * @return the collision
	 */
	public boolean isCollision() {
		return collision;
	}

	/**
	 * @param collision the collision to set
	 */
	public void setCollision(boolean collision) {
		this.collision = collision;
	}

	/**
	 * @return the controlBall
	 */
	public Controller getControlBall() {
		return controlBall;
	}

	/**
	 * @return the gp
	 */
	public GamePanel getGp() {
		return gp;
	}

	/**
	 * @return the ball
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * @return the wteam
	 */
	public Wedstrijdteam getWteam() {
		return wteam;
	}

	/**
	 * @return the team12
	 */
	public int getTeam12() {
		return team12;
	}

	/**
	 * @return the circleBounds
	 */
	public Ellipse2D.Double getCircleBounds() {
		return circleBounds;
	}

	/**
	 * @return the collisionCount
	 */
	public int getCollisionCount() {
		return collisionCount;
	}

	/**
	 * @param collisionCount the collisionCount to set
	 */
	public void setCollisionCount(int collisionCount) {
		this.collisionCount = collisionCount;
	}

	
}
