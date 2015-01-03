package oop.voetbalmanager.spel2D;

import java.awt.Rectangle;

public class Ball {
	
	private double x, y, startX, startY, targetX, targetY, xforP, yforP;
	private double t = 0;
	private boolean kick = false;
	private Player owner;
	private boolean ballInGoal = false;
	private Rectangle goalL;
	private Rectangle goalR;
	private Rectangle goalLToKick;
	private Rectangle goalRToKick;
	
	public Ball(){
		x = xforP = startX = 1275;
		y = yforP = startY = 805;
		targetX = 1000;
		targetY = 500;

		goalR = new Rectangle(2235, 690, 100, 190);
		goalL = new Rectangle(234, 690, 100, 190);
		
		goalRToKick = new Rectangle(1585, 460, 600, 840);
		goalLToKick = new Rectangle(234, 460, 600, 840);
	//	move();
//		x = startX;
//		y = startY;
	}
	
	public void move(){
//		if(startX <= targetX){
//			x+=5;;
//		}else{
//			x-=5;
//		}
		if(kick){
			kick();
		}else 
			if (owner!=null){
			xforP = startX = x = owner.getX();
			yforP = startY = y = owner.getY();
		//		System.out.println("Ball class move: Owner "+ x+","+y);
		}
			else{
			startX = xforP = x;
			startY = yforP = y;
		//	System.out.println("Ball start: " + x+","+y);
		}
		
		if(goalR.contains(x, y)){
			System.out.println("Ball: Team 1 heeft gescoord");
			ballInGoal = true;
		}else if(goalL.contains(x, y)){
			System.out.println("Ball: Team 2 heeft gescoord");
			ballInGoal = true;
		} 
		
	//	System.out.println(x);
	}
	
	public void kick(){
//		if((x>targetX-1 && x<targetX+1) && (y>targetY-1 && y<targetY+1)){
//			x = targetX;
//			y = targetY;
//			startX = x;
//			startY = y;
//			t=0;
//			kick = false;
//			if (owner!=null){
//				owner.setBallOwner(true);
//     			
//			}
//		}else{
	//	if(kick){	
			double g = 9.81;
			//y-snelheid met max tijd = 10
			double Vy0=(-targetY+startY+0.5*g*100)/10;//+-inverse omdat voordinaten systeem voor y omgekeerd is
			//x-snelheid
			double Vx0=(targetX-startX)/10;
//			
			x= startX + Vx0*t;
			y= startY-Vy0*t+0.5*g*t*t;//zelfde als bij Vy0
			//System.out.println(x+ " " +y + " " +t);
			
			xforP = x;
			yforP = startY + Vy0*t;
			
		//	owner.runTo((int)targetX, (int)targetY);
		//	System.out.println(targetX + ";"+ targetY);

			t+=0.1;
//			System.out.println("Ball class: "+ targetX + "," + targetY + "  " + (int)x+","+(int)y);
			if(targetX+20 > (int)x && targetX-20 < (int)x && targetY+20 > (int)y && targetY-20 < (int)y){
				
				t = 0;
				System.out.println("Ball class: Landed "+ targetX + "," + targetY + "  " + (int)x+","+(int)y);
				System.out.println("Ball: goalR " + goalR.toString() + "  goalL " + goalL.toString());
				xforP = startX = x;
				yforP = startY = y;
				owner = null;
				kick = false;
			}
	//	}
//		}
	}
	
	public void reset(){
		targetX = x = xforP = startX = 1275;
		targetY = y = yforP = startY = 805;
		owner = null;
		t = 0;
		ballInGoal = false;
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
	 * @return the startX
	 */
	public double getStartX() {
		return startX;
	}

	/**
	 * @return the startY
	 */
	public double getStartY() {
		return startY;
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
	 * @return the kick
	 */
	public boolean isKick() {
		return kick;
	}

	/**
	 * @param kick the kick to set
	 */
	public void setKick(boolean kick) {
		this.kick = kick;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * @return the xforP
	 */
	public double getXforP() {
		return xforP;
	}

	/**
	 * @return the yforP
	 */
	public double getYforP() {
		return yforP;
	}

	/**
	 * @return the ballInGoal
	 */
	public boolean isBallInGoal() {
		return ballInGoal;
	}

	/**
	 * @param ballInGoal the ballInGoal to set
	 */
	public void setBallInGoal(boolean ballInGoal) {
		this.ballInGoal = ballInGoal;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(double t) {
		this.t = t;
	}

	/**
	 * @return the goal1
	 */
	public Rectangle getGoalR() {
		return goalR;
	}

	/**
	 * @return the goal2
	 */
	public Rectangle getGoalL() {
		return goalL;
	}

	/**
	 * @return the goal1ToKick
	 */
	public Rectangle getGoalRToKick() {
		return goalRToKick;
	}

	/**
	 * @return the goal2ToKick
	 */
	public Rectangle getGoalLToKick() {
		return goalLToKick;
	}
}
