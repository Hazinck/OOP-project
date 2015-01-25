package oop.voetbalmanager.spel2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

import oop.voetbalmanager.view.ViewFrame;


public class GamePanel extends JPanel{

	private ArrayList<Player> playerListTeam1 = new ArrayList<Player>();
	private ArrayList<Player> playerListTeam2 = new ArrayList<Player>();
	private Ball ball;
	private Controller2D controller;
	
	private int viewX;
	private int viewY;
	
	private boolean goal = false;
	private boolean start = true;
	private boolean end = false;
	private int tijd = 45;
	private boolean manualPlay = false;
	Polygon pol;
	private Image gameEndImg, t1, t2, gameStartImg, goalImg, scoreBalk, ballSprite, veld;
	
	public GamePanel(Ball ball){
		viewX = -1275 + ViewFrame.getFrameWidth()/2;
		viewY = -805 + ViewFrame.getFrameHeight()/2;
		this.ball = ball;
		
		int xpoints[] = {388, 2165, 2444, 123, 388};
	    int ypoints[] = {353, 353, 1412, 1412, 353};
	    int npoints = 5;
	    
	    pol = new Polygon(xpoints, ypoints, npoints);
		
	    gameEndImg = Toolkit.getDefaultToolkit().getImage("images/game_end.png");
	    gameStartImg = Toolkit.getDefaultToolkit().getImage("images/game_start.png");
	    goalImg = Toolkit.getDefaultToolkit().getImage("images/goal.png");
	    scoreBalk = Toolkit.getDefaultToolkit().getImage("images/logo-score.png");
	    ballSprite = Toolkit.getDefaultToolkit().getImage("images/ball_small.png");
	    veld = Toolkit.getDefaultToolkit().getImage("images/veld_big_end.png");
	    t1 = Toolkit.getDefaultToolkit().getImage("images/logos/"+ ball.getTeam1().getNaam()+".png");//"field_satur=45.png");
		t2 = Toolkit.getDefaultToolkit().getImage("images/logos/"+ ball.getTeam2().getNaam()+".png");//"field_satur=45.png");
	}
	
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
 				p.getController2DBall().setCurrentTimeFalse(falseTime);
 			}else{
 				p.getController2DBall().setCurrentTimeFalse(falseTime+5);
 			}
 			
 		}
	}
	
	public void clearImg(){
		gameEndImg.flush();
	    gameStartImg.flush();
	    goalImg.flush();
	    scoreBalk.flush();
	    ballSprite.flush();
	    veld.flush();
	    t1.flush();
		t2.flush();
		gameEndImg = null;
	    gameStartImg = null;
	    goalImg = null;
	    scoreBalk = null;
	    ballSprite = null;
	    veld = null;
	    t1 = null;
		t2 = null;
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(veld, viewX ,  viewY ,  this);//g.drawImage(veld, -500 + viewX, -500 + viewY,  this);//this.getWidth(), this.getHeight(),
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.addAll(playerListTeam1);
		playerList.addAll(playerListTeam2);
		
		for(Player p: playerList){
			g.drawImage(p.getSpriteObj().getSprite(), (int)p.getX()-30+viewX, (int)p.getY()-30+viewY,  this);//was -30 -30 sprite, x, y, this);
		if(p.isBallOwner() && p.getX()==ball.getX() && p.getY()==ball.getY()){
				if(p.getTeam12()==1 && !manualPlay){
					g.setColor(Color.blue);
					g.drawString(p.getSpeler().getType(), (int)p.getX() - 30 + viewX, (int)p.getY() + 25 + viewY);
					g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
				}else if(p.getTeam12()==2){
					g.setColor(Color.red);
					g.drawString(p.getSpeler().getType(), (int)p.getX() - 30 + viewX, (int)p.getY() + 25 + viewY);
					g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
				}
			} 
			if(manualPlay && p.isRunsByUser()){
				Font old = g.getFont();
				g.setFont (new Font ("Arial", Font.BOLD , 14));
				g.setColor(Color.blue);
				g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
				g.drawString(p.getSpeler().getType(), (int)p.getX() - 30 + viewX, (int)p.getY() + 25 + viewY);
				g.setFont (old);
			}
		
		}
		g.drawImage(ballSprite, (int)ball.getX() + viewX, (int)ball.getY() + viewY, this);//
		
		
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
		g.drawString(tijd+":00", 375, 75);
		
		if(goal){
		
			g.drawImage(goalImg, VeldPanel.getFrameWidth()/2-500, VeldPanel.getFrameHeight()/2-150, this);//
		}
		
		if(start){
		
			g.drawImage(gameStartImg, VeldPanel.getFrameWidth()/2-273, VeldPanel.getFrameHeight()/2-120, this);//
			g.setFont (new Font ("Arial", Font.BOLD , 20));
			
			g.setColor(Color.decode("#464e5a"));
			g.drawString( ball.getTeam1().getNaam(), VeldPanel.getFrameWidth()/2-200, VeldPanel.getFrameHeight()/2-30);//75*75
			g.drawString( ball.getTeam2().getNaam(), VeldPanel.getFrameWidth()/2+94, VeldPanel.getFrameHeight()/2-30);//75*75
			
			g.drawImage(t1, VeldPanel.getFrameWidth()/2-190, VeldPanel.getFrameHeight()/2-10, this);//h=120
			g.drawImage(t2, VeldPanel.getFrameWidth()/2+80, VeldPanel.getFrameHeight()/2-10, this);//
			
		}
		
		if(end){
			g.drawImage(gameEndImg, VeldPanel.getFrameWidth()/2-273, VeldPanel.getFrameHeight()/2-120, this);//
			g.setFont (new Font ("Arial", Font.BOLD , 20));
			
			g.setColor(Color.decode("#464e5a"));
			g.drawString( ball.getTeam1().getNaam(), VeldPanel.getFrameWidth()/2-200, VeldPanel.getFrameHeight()/2+5);//75*75
			g.drawString( ball.getTeam2().getNaam(), VeldPanel.getFrameWidth()/2+94, VeldPanel.getFrameHeight()/2+5);//75*75
			
			g.drawImage(t1, VeldPanel.getFrameWidth()/2-190, VeldPanel.getFrameHeight()/2+25, this);//h=120
			g.drawImage(t2, VeldPanel.getFrameWidth()/2+80, VeldPanel.getFrameHeight()/2+25, this);//
			
			g.setColor(Color.white);
			g.setFont (new Font ("Arial", Font.BOLD , 40));
			g.drawString( ball.getScore().width+"", VeldPanel.getFrameWidth()/2-145, VeldPanel.getFrameHeight()/2+192);//75*75
			g.drawString( ball.getScore().height+"", VeldPanel.getFrameWidth()/2+125, VeldPanel.getFrameHeight()/2+192);//75*75
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



	/**
	 * @return the controll
	 */
	public Controller2D getController2D() {
		return controller;
	}



	/**
	 * @param controll the controll to set
	 */
	public void setController2D(Controller2D controller) {
		this.controller = controller;
	}


	/**
	 * @param goal the goal to set
	 */
	public void setGoal(boolean goal) {
		this.goal = goal;
	}


	/**
	 * @param start the start to set
	 */
	public void setStart(boolean start) {
		this.start = start;
	}


	/**
	 * @param tijd the tijd to set
	 */
	public void setTijd(int tijd) {
		this.tijd = tijd;
	}


	/**
	 * @return the tijd
	 */
	public int getTijd() {
		return tijd;
	}


	/**
	 * @return the boolSpeelZelf
	 */
	public boolean isManualPlay() {
		return manualPlay;
	}


	/**
	 * @param boolSpeelZelf the boolSpeelZelf to set
	 */
	public void setManualPlay(boolean manualPlay) {
		this.manualPlay = manualPlay;
	}


	/**
	 * @param end the end to set
	 */
	public void setEnd(boolean end) {
		this.end = end;
	}



}
