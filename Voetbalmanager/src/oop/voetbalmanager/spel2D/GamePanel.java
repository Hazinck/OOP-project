package oop.voetbalmanager.spel2D;
import java.awt.Color;
import java.awt.Font;
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
	private Controller controller;
	
	private int viewX;
	private int viewY;
	
	public GamePanel(Ball ball){
		viewX = -1275 + ViewFrame.getFrameWidth()/2;
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
		super.paintComponent(g);
		Image veld = Toolkit.getDefaultToolkit().getImage("images/veld_big_end.png");//"field_satur=45.png");
		g.drawImage(veld, viewX ,  viewY ,  this);//g.drawImage(veld, -500 + viewX, -500 + viewY,  this);//this.getWidth(), this.getHeight(),
		
//		ball.getGoal2().setLocation(ball.getGoal2().x+viewX, ball.getGoal2().y + viewY);
//		g.drawRect(ball.getGoal2().x, ball.getGoal2().y, ball.getGoal2().width, ball.getGoal2().height);
//		
//		ball.getGoal1().setLocation(ball.getGoal1().x+viewX, ball.getGoal1().y + viewY);
//		g.drawRect(ball.getGoal1().x, ball.getGoal1().y, ball.getGoal1().width, ball.getGoal1().height);
		
		
		
//		System.out.println("GamePanel:  "+(int)ball.getX() +" "+ (int)ball.getY());
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.addAll(playerListTeam1);
		playerList.addAll(playerListTeam2);
		
		for(Player p: playerList){
			g.drawImage(p.getSpriteObj().getSprite(), (int)p.getX()-30+viewX, (int)p.getY()-30+viewY,  this);//was -30 -30 sprite, x, y, this);
//			System.out.println(p.getTargetY()+"=="+ball.getTargetY());
//			g.setColor(Color.black);
//			g.drawRect(p.getBoundsAnchor().x + viewX, p.getBoundsAnchor().y + viewY, p.getBoundsAnchor().width, p.getBoundsAnchor().height);
//			g.drawOval((int)p.getCircleBounds().x + viewX, (int)p.getCircleBounds().y + viewY, (int)p.getCircleBounds().width, (int)p.getCircleBounds().height);
			if(p.isBallOwner() && p.getX()==ball.getX() && p.getY()==ball.getY()){
				if(p.getTeam12()==1){
					g.setColor(Color.blue);
				}else{
					g.setColor(Color.red);
				}
				g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
				g.drawString(p.getSpeler().getType(), (int)p.getX() - 30 + viewX, (int)p.getY() + 25 + viewY);
			} 
		
		}
		Image ballSprite = Toolkit.getDefaultToolkit().getImage("images/ball_small.png");//"field_satur=45.png");
		g.drawImage(ballSprite, (int)ball.getX() + viewX, (int)ball.getY() + viewY, this);//
		
		
		Image scoreBalk = Toolkit.getDefaultToolkit().getImage("images/logo-score.png");//"field_satur=45.png");
		g.drawImage(scoreBalk, 120, 50, this);//
		g.setColor(Color.black);
		
		String team1 = ball.getTeam1().getNaam();
		team1 = team1.replaceAll(" ", "");
		team1 = team1.substring(0, Math.min(team1.length(), 3));
		String team2 = ball.getTeam2().getNaam();
		team2 = team2.replaceAll(" ", "");
		team2 = team2.substring(0, Math.min(team2.length(), 3));
	    g.setFont (new Font ("Arial", Font.BOLD , 14));
		g.drawString(team1, 235, 75);
		g.drawString(team2, 330, 75);
		
		g.setColor(Color.white);
		g.drawString(ball.getScore().width+" - "+ball.getScore().height, 280, 75);
		
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



	/**
	 * @return the controll
	 */
	public Controller getController() {
		return controller;
	}



	/**
	 * @param controll the controll to set
	 */
	public void setController(Controller controller) {
		this.controller = controller;
	}



}
