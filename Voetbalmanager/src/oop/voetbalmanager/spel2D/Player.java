package oop.voetbalmanager.spel2D;
import java.awt.Dimension;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import oop.voetbalmanager.model.Speler;
import oop.voetbalmanager.model.Wedstrijdteam;


public class Player {
	
	private Speler speler;
	private Sprite spriteObj;
	private int playerID;
	double  x , y, speedX, speedY, oldX, oldY, targetX, targetY, anchorX, anchorY;
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
	private Ellipse2D.Double boundsAnchor;
	private Ellipse2D.Double circleBounds;
	private Ellipse2D.Double circleBallBounds;
	private boolean collision = false;
	private Controller2D controlBall;
	private GamePanel gp;
	private Wedstrijdteam wteam;
	private int collisionCount = 0;
	private boolean isUpPressed = false;
	private boolean isDownPressed = false;
	private boolean isLeftPressed = false;
	private boolean isRightPressed = false;
	private boolean isQPressed = false;
	private boolean runsByUser = false;
	private boolean randomRun = false;
	/**
	 * 
	 * @param speler
	 * @param teamNum eerste of tweede team(1e staat links op het veld, 2e rechts)
	 */
	public Player(Speler speler,  int team12, int playerID, Ball ball, Wedstrijdteam wteam, GamePanel gp){
		this.speler = speler;
		if(wteam.getNaam().equals("Feyenoord") || wteam.getNaam().equals("ADO Den Haag")  || wteam.getNaam().equals("Ajax")){
			this.spriteObj = new Sprite("images/"+wteam.getNaam(), team12);
		}else{
			this.spriteObj = new Sprite("images/general", team12);
		}
		this.playerID = playerID;
		this.ball = ball;
		this.team12 = team12;
		this.gp = gp;
		this.wteam = wteam;
		boundsAnchor = new Ellipse2D.Double();
		setSpeed();
		
		ArrayList<Player> myTeam;
		if(team12==1){
			myTeam = gp.getPlayerListTeam1();
		}else{
			myTeam = gp.getPlayerListTeam2();
		}
		controlBall = new Controller2D(myTeam, ball);
		xMinGrens =  100;//-600;//bounds.getMinX();
		xMaxGrens =  2300;//3000;//bounds.getMaxX();
		yMinGrens =  350;//-805//bounds.getMinY();
		yMaxGrens = 1400;//bounds.getMaxY();
		circleBounds = new Ellipse2D.Double(x-15, y-30, 40, 48);
		circleBallBounds = new Ellipse2D.Double(x-15, y-15, 40, 33);
	}
	private double xMinGrens;
	private double xMaxGrens;
	private double yMinGrens;
	private double yMaxGrens;
	
	public void move(int targetX, int targetY ) {
		circleBounds.setFrame(x, y-30, 18, 48);
		circleBallBounds.setFrame(x-10, y-10, 38, 28);
		
		controlBall.controlBallPerPlayer(this);
		Dimension target;
		if(gp.isManualPlay() && team12==1 && ballOwner){
			ball.getLastBallOwner().setRunsByUser(false);
			runsByUser = true;
			target = runByUser();
		}else if(gp.isManualPlay() && team12==1 && 
				ball.getLastBallOwner()!=null && 
				ball.getLastBallOwner().getSpeler().getNaam().equals(speler.getNaam()) &&
				(ball.getOwner()==null || ball.getOwner().getTeam12()==2)){
			
				runsByUser = true;
				target = runByUser();
		}else{
			runsByUser = false;
			target = runTo(targetX, targetY);
			setSpeed();
		}
		
		targetX = target.width;
		targetY = target.height;

		count++;
		checkTired();
		 	String type = speler.getType();
		 	oldX = x;
			oldY = y;
			pointIdx++;
			if(pointIdx==4){
				pointIdx=0;
			}
			
			
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
			      x=oldX + 2;
			}else  if (x + spriteHalfWidth >= xMaxGrens) {
		    	x=oldX - 2;
		    }
		    if (y - spriteHalfHeight <= yMinGrens) {
		    	y=oldY + 2;
		    }else  if (y + spriteHalfHeight > yMaxGrens) {
		    	y=oldY - 2;
		    }
		    findRichting();
		    xA[pointIdx] = x;
		    yA[pointIdx] = y;

		    
	  }
	
	
	public void findRichting(){
		double dx = x - oldX;
		double dy = y - oldY;
		if(xA[2]!=null && Math.abs(xA[2]-xA[0])<=1 && Math.abs(yA[2]-yA[0])<=1){
			spriteObj.setIdle(true);
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
	}
	
	public void setSpeed(){
		speedX = (speler.getOffense() + speler.getDefence())/ 33;
		
		if(speedX<0.5){
			speedX = 0.5;
		}else if(speedX>2){
			speedX = 2;
		}
		speedY =  speedX;
	}
	
	public void checkTired(){
		if(count>speler.getUithouding()*20){
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
		double x=0;
		double y=0;
		if(collision){
			x= this.targetX;
			y = this.targetY;
			randomRun = false;
		}else{
			if(ballOwner){
				if(team12==1){
					x = 2256;
				}else{
					x = 300;
				}
				y = 806;
				randomRun = false;
			}else if(
					(ball.getOwner()==null && anchorToBallAfstand() < 300) ||
					(boundsAnchor.contains(this.x, this.y) &&
				boundsAnchor.contains(ball.getXforP(), ball.getYforP()))){
				x = targetX;
				y = targetY;
				randomRun = false;
			}

			else{
				x = anchorX;
				y = anchorY;
			}
		}
		if(x == 0 || y == 0){
			x= this.targetX;
			y = this.targetY;
		}
		return new Dimension((int)x, (int)y);
	}
	
	public Dimension runByUser(){
		keyListener();
		//run left
		if(isLeftPressed){
        	targetX = x - 5;
        }
          //run right
        if(isRightPressed){
        	targetX = x + 5;
        }
          //run up
        if(isUpPressed){
        	targetY =  y - 5;
        }
          //run down
        if(isDownPressed){
        	targetY = y + 5;
        }
        if(isDownPressed){
        	targetY = y + 5;
        }
        if(isQPressed){

    		System.out.println("Player: " + Controller2D.isKicked());
        	if(Controller2D.isKicked()==false && ballOwner==true){
        		System.out.println("Player: kicked");
        		Controller2D.kickBal(this);
        	}
        }
		return new Dimension((int)targetX, (int)targetY);
		
	}
	
	public void keyListener(){
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			@Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
            	if(ke.getID() == KeyEvent.KEY_PRESSED){
            		switch(ke.getKeyCode()) {
		            case KeyEvent.VK_A: isLeftPressed = true; break;
		            case KeyEvent.VK_D: isRightPressed = true; break;
		            case KeyEvent.VK_W: isUpPressed = true; break;
		            case KeyEvent.VK_S: isDownPressed = true; break;
		            case KeyEvent.VK_Q: isQPressed = true; break;
            		}
            	}
            	if(ke.getID() == KeyEvent.KEY_RELEASED){
            		switch(ke.getKeyCode()) {
		            case KeyEvent.VK_A: isLeftPressed = false; break;
		            case KeyEvent.VK_D: isRightPressed = false; break;
		            case KeyEvent.VK_W: isUpPressed = false; break;
		            case KeyEvent.VK_S: isDownPressed = false; break;
		            case KeyEvent.VK_Q: isQPressed = false; break;
            		}
	                    
	                }
                   return false;
            
            }
            
        });
		
	}
	
	public void setGrenzen(int anchorX, int anchorY){
		this.anchorY = anchorY;
		
		
		if(speler.getType().equals("doelman") && team12 == 1){
			boundsAnchor.x =  anchorX - 700;
			boundsAnchor.width = 1000;
			boundsAnchor.y = anchorY - 400;
			boundsAnchor.height = 800;
			this.anchorX = boundsAnchor.getCenterX();
		
		}else if(speler.getType().equals("doelman") && team12 == 2){

			boundsAnchor.x =  anchorX - 50;
			boundsAnchor.width = 600;
			boundsAnchor.y = anchorY - 400;
			boundsAnchor.height = 800;
			this.anchorX = boundsAnchor.getCenterX();
		}else{
			this.anchorX = anchorX;
			boundsAnchor.x =  anchorX - 100;
			boundsAnchor.width = 300;
			boundsAnchor.y = anchorY - 400;
			boundsAnchor.height = 800;
			
		}
		
		if(boundsAnchor.y + boundsAnchor.height > yMaxGrens){
			boundsAnchor.y = yMaxGrens - boundsAnchor.height;
		}else if(boundsAnchor.y < yMinGrens){
			boundsAnchor.y = yMinGrens;
		}
		
	}
	
	public double ballAfstand(){
		double a = Math.pow((ball.getXforP() - x), 2);
		double b = Math.pow((ball.getYforP() - y), 2);
		double afst = Math.sqrt(a + b);
		return afst;
	}
	
	public double anchorAfstand(){
		double a = Math.pow((anchorX - x), 2);
		double b = Math.pow((anchorY - y), 2);
		double afst = Math.sqrt(a + b);
		return afst;
	}
	
	public double anchorToBallAfstand(){
		double a = Math.pow((anchorX - ball.getXforP()), 2);
		double b = Math.pow((anchorY - ball.getYforP()), 2);
		double afst = Math.sqrt(a + b);
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
	public Ellipse2D.Double getBoundsAnchor() {
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
	public Controller2D getController2DBall() {
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


	/**
	 * @return the runsByUser
	 */
	public boolean isRunsByUser() {
		return runsByUser;
	}


	/**
	 * @param runsByUser the runsByUser to set
	 */
	public void setRunsByUser(boolean runsByUser) {
		this.runsByUser = runsByUser;
	}


	/**
	 * @return the richting
	 */
	public String getRichting() {
		return richting;
	}


	/**
	 * @return the circleBallBounds
	 */
	public Ellipse2D.Double getCircleBallBounds() {
		return circleBallBounds;
	}

	
}
