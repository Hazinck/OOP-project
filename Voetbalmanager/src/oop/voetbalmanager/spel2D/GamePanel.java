package oop.voetbalmanager.spel2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import oop.voetbalmanager.view.ViewFrame;


public class GamePanel extends JPanel{

	private ArrayList<Player> playerListTeam1 = new ArrayList<Player>();
	private ArrayList<Player> playerListTeam2 = new ArrayList<Player>();
	private Ball ball;
	
	private int viewX;
	private int viewY;
	
	public GamePanel(Ball ball){
		viewX = -500-1275 + ViewFrame.getFrameWidth()/2;
		viewY = -805 + ViewFrame.getFrameHeight()/2;
		this.ball = ball;
	}
		
	
	
//	public void addSprite(Sprite s){
//		spriteList.add(s);
//	}
	public void addPlayer(Player p, int team12){
		if(team12==1){
			playerListTeam1.add(p);
		}else{
			playerListTeam2.add(p);
		}
	}
	
	public void setBallControllerFalseTimer(Player thisP, long falseTime){
		ArrayList<Player> playerListAll = new ArrayList<Player>();
		playerListAll.addAll(playerListTeam1);
		playerListAll.addAll(playerListTeam2);
		
		for(Player p: playerListAll){
 			if(!p.equals(thisP)){
 				p.getControlBall().setCurrentTimeFalse(falseTime);
 			}else{
 				p.getControlBall().setCurrentTimeFalse(falseTime+5);
 			}
 			
 		}
	}
	
	@Override
    public void paintComponent(Graphics g) {
		
		Image veld = Toolkit.getDefaultToolkit().getImage("veld_big_end.png");//"field_satur=45.png");
		g.drawImage(veld, viewX ,  viewY ,  this);//g.drawImage(veld, -500 + viewX, -500 + viewY,  this);//this.getWidth(), this.getHeight(),
		
		Image ballSprite = Toolkit.getDefaultToolkit().getImage("ball_small.png");//"field_satur=45.png");
		g.drawImage(ballSprite, (int)ball.getX() + viewX, (int)ball.getY() + viewY, this);//
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.addAll(playerListTeam1);
		playerList.addAll(playerListTeam2);
		
		for(Player p: playerList){
			g.drawImage(p.getSpriteObj().getSprite(), (int)p.getX()-30+viewX, (int)p.getY()-30+viewY,  this);//was -30 -30 sprite, x, y, this);
			
			g.setColor(Color.black);
			g.drawRect(p.getBounds().x + viewX, p.getBounds().y + viewY, p.getBounds().width, p.getBounds().height);
			if(p.isBallOwner() && p.getX()==ball.getX() && p.getY()==ball.getY()){
			//	System.out.println(p.getY()+"=="+ball.getY());
				g.setColor(Color.red);
				g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
				g.drawString(p.getSpeler().getType(), (int)p.getX() - 30 + viewX, (int)p.getY() + 25 + viewY);
			} 
		
		}
		
    }

	

	/**
	 * @return the viewX
	 */
	public int getViewX() {
		return viewX;
	}


	/**
	 * @param viewX the viewX to set
	 */
	public void setViewX(int viewX) {
		this.viewX = viewX;
	}


	/**
	 * @return the viewY
	 */
	public int getViewY() {
		return viewY;
	}


	/**
	 * @param viewY the viewY to set
	 */
	public void setViewY(int viewY) {
		this.viewY = viewY;
	}



	/**
	 * @return the ball
	 */
	public Ball getBall() {
		return ball;
	}



	/**
	 * @return the playerListTeam1
	 */
	public ArrayList<Player> getPlayerListTeam1() {
		return playerListTeam1;
	}



	/**
	 * @return the playerListTeam2
	 */
	public ArrayList<Player> getPlayerListTeam2() {
		return playerListTeam2;
	}
}
