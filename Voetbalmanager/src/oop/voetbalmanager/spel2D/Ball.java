package oop.voetbalmanager.spel2D;

import java.awt.Dimension;
import java.awt.Rectangle;

import oop.voetbalmanager.model.Wedstrijdteam;

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
	private Player kickedToGoal;
	private Player lastBallOwner;
	private Dimension score = new Dimension();
	private Wedstrijdteam team1;
	private Wedstrijdteam team2;
	private String toVerslag = "";
	private Dimension finalResult;
	
	public Ball(Wedstrijdteam team1, Wedstrijdteam team2){
		this.team1 = team1;
		this.team2 = team2;
		score.setSize(0, 0);
		x = xforP = startX = 1275;
		y = yforP = startY = 805;
		targetX = 1000;
		targetY = 500;

		goalR = new Rectangle(2235, 680, 100, 190);
		goalL = new Rectangle(234, 680, 100, 190);
		
		goalRToKick = new Rectangle(1585, 460, 600, 840);
		goalLToKick = new Rectangle(234, 460, 600, 840);
	}
	
	public void move(){
		if(!ballInGoal){

			if(kick){
				kick();
			}else 
				if (owner!=null){
				xforP = startX = x = owner.getX();
				yforP = startY = y = owner.getY();
			}
				else{
				startX = xforP = x;
				startY = yforP = y;
			}
			
			if(goalR.contains(x, y)){
				score.setSize(score.width + 1, score.height);
				if(kickedToGoal == null){
					kickedToGoal = owner;
					if(kickedToGoal==null){
						kickedToGoal=lastBallOwner;
					}
				}
				System.out.println("Ball: "+kickedToGoal.getSpeler().getNaam()+" heeft gescoord");
				System.out.println("Ball: "+team1.getNaam()+": "+ score.width + "   "+team2.getNaam()+": "+ score.height);
				toVerslag =  "GOAL!" + "\n"+ kickedToGoal.getWteam().getNaam()+" scoort, "+kickedToGoal.getSpeler().getNaam()+" maakt de "+score.width+"-"+score.height+"!\n";
				ballInGoal = true;
			}else if(goalL.contains(x, y)){
				score.setSize(score.width, score.height + 1);
				if(kickedToGoal == null){
					kickedToGoal = owner;
				}
				System.out.println("Ball: "+kickedToGoal.getSpeler().getNaam()+" heeft gescoord");
				System.out.println("Ball: "+team1.getNaam()+": "+ score.width + "   "+team2.getNaam()+": "+ score.height);
				toVerslag = "GOAL!" + "\n"+kickedToGoal.getWteam().getNaam()+" scoort, "+kickedToGoal.getSpeler().getNaam()+" maakt de "+score.width+"-"+score.height+"!\n";
				ballInGoal = true;
			} 
		
		}
	}
	
	public void kick(){
			double g = 9.81;
			//y-snelheid met max tijd = 10
			double Vy0=(-targetY+startY+0.5*g*100)/10;//+-inverse omdat coordinaten systeem voor y omgekeerd is
			//x-snelheid
			double Vx0=(targetX-startX)/10;

			x= startX + Vx0*t;
			y= startY-Vy0*t+0.5*g*t*t;//zelfde als bij Vy0
			
			xforP = x;
			yforP = startY + Vy0*t;

			t+=0.1;
		
			if(targetX+20 > (int)x && targetX-20 < (int)x && targetY+20 > (int)y && targetY-20 < (int)y){
				
				t = 0;
				
				System.out.println("Ball class: Landed "+ targetX + "," + targetY + "  " + (int)x+","+(int)y);
				System.out.println("Ball: goalR " + goalR.toString() + "  goalL " + goalL.toString());
				xforP = startX = x;
				yforP = startY = y;
				owner = null;
				kick = false;
				
				if(x<100){
					x=155;
				}else if(x>2300){
					x = 2250;
				}
				if(y<350){
					y=370;
				}else if(y>1400){
					y = 1350;
				}
				Controller2D.setKicked(false);
			}
	}
	
	public int winner(){
		if(score.getWidth() > score.getHeight()){
			return 1;
		}else if(score.getWidth() < score.getHeight()){
			return 2;
		}else{
			return 0;
		}
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

	/**
	 * @return the t
	 */
	public double getT() {
		return t;
	}

	/**
	 * @param kickedToGoal the kickedToGoal to set
	 */
	public void setKickedToGoal(Player kickedToGoal) {
		this.kickedToGoal = kickedToGoal;
	}

	/**
	 * @return the toVerslag
	 */
	public String getToVerslag() {
		return toVerslag;
	}

	/**
	 * @param finalResult the finalResult to set
	 */
	public void setFinalResult(Dimension finalResult) {
		this.finalResult = finalResult;
		System.out.println("Ball: setFinalResult: " + finalResult.toString());
	}

	/**
	 * @return the finalResult
	 */
	public Dimension getFinalResult() {
		return finalResult;
	}

	/**
	 * @return the score
	 */
	public Dimension getScore() {
		return score;
	}

	/**
	 * @return the team1
	 */
	public Wedstrijdteam getTeam1() {
		return team1;
	}

	/**
	 * @return the team2
	 */
	public Wedstrijdteam getTeam2() {
		return team2;
	}

	/**
	 * @return the owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * @return the lastBallOwner
	 */
	public Player getLastBallOwner() {
		return lastBallOwner;
	}

	/**
	 * @param lastBallOwner the lastBallOwner to set
	 */
	public void setLastBallOwner(Player lastBallOwner) {
		this.lastBallOwner = lastBallOwner;
	}
}
