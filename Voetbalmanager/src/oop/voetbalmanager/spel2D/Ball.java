package oop.voetbalmanager.spel2D;

public class Ball {
	
	private double x, y, startX, startY, targetX, targetY, xforP, yforP;
	private double t = 0;
	private boolean kick = false;
	private Player owner;
	
	public Ball(){
		xforP = startX = 1275;
		yforP = startY = 805;
		targetX = 1000;
		targetY = 500;
		move();
//		x = startX;
//		y = startY;
	}
	
	public void move(){
//		if(startX <= targetX){
//			x+=5;;
//		}else{
//			x-=5;
//		}
//		if(kick){
//			kick();
//		}else 
			if (owner!=null){
			xforP = startX = x = owner.getX();
			yforP = startY = y = owner.getY();
		}else{
			x = xforP = startX;
			y = yforP = startY;
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
		if(kick){	
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
			
			owner.runTo((int)targetX, (int)targetY);
		//	System.out.println(targetX + ";"+ targetY);

			t+=0.1;
			System.out.println("Ball class: "+ xforP + "," + yforP + "  " + x+","+y);
			
		}
//		}
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
}
