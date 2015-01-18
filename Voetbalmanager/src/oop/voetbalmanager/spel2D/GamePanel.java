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
//		Image veld = Toolkit.getDefaultToolkit().getImage("images/veld_big_end.png");//"field_satur=45.png");
		g.drawImage(veld, viewX ,  viewY ,  this);//g.drawImage(veld, -500 + viewX, -500 + viewY,  this);//this.getWidth(), this.getHeight(),
		
//		int xpoints[] = {388+viewX, 2165+viewX, 2444+viewX, 123+viewX, 388+viewX};
//	    int ypoints[] = {353+viewY, 353+viewY, 1412+viewY, 1412+viewY, 353+viewY};
//		g.drawPolygon(pol);
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
//			if(p.getSpeler().getType().equals("doelman"))
			if(p.getSpeler().getType().equals("doelman"))
			g.drawRect((int)p.getBoundsAnchor().x + viewX, (int)p.getBoundsAnchor().y + viewY, (int)p.getBoundsAnchor().width, (int)p.getBoundsAnchor().height);
//			g.drawString(p.getSpeler().getNaam(), (int)p.getX() - 30 + viewX, (int)p.getY() - 25 + viewY);
//			g.setColor(Color.black);
//			g.drawOval((int)p.getCircleBounds().x + viewX, (int)p.getCircleBounds().y + viewY, (int)p.getCircleBounds().width, (int)p.getCircleBounds().height);
//			g.drawOval((int)p.getBoundsAnchor().x + viewX, (int)p.getBoundsAnchor().y + viewY, (int)p.getBoundsAnchor().width, (int)p.getBoundsAnchor().height);
//			g.setColor(Color.magenta);
//			g.drawOval((int)p.getCircleBallBounds().x + viewX, (int)p.getCircleBallBounds().y + viewY, (int)p.getCircleBallBounds().width, (int)p.getCircleBallBounds().height);
			
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
		//Image ballSprite = Toolkit.getDefaultToolkit().getImage("images/ball_small.png");//"field_satur=45.png");
		g.drawImage(ballSprite, (int)ball.getX() + viewX, (int)ball.getY() + viewY, this);//
		
		
		//Image scoreBalk = Toolkit.getDefaultToolkit().getImage("images/logo-score.png");//"field_satur=45.png");
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
		//	Image goal = Toolkit.getDefaultToolkit().getImage("images/goal.png");//"field_satur=45.png");
			g.drawImage(goalImg, VeldPanel.getFrameWidth()/2-500, VeldPanel.getFrameHeight()/2-150, this);//
		}
		
		if(start){
		//	Image goal = Toolkit.getDefaultToolkit().getImage("images/game_start.png");//"field_satur=45.png");
			g.drawImage(gameStartImg, VeldPanel.getFrameWidth()/2-273, VeldPanel.getFrameHeight()/2-120, this);//
			g.setFont (new Font ("Arial", Font.BOLD , 20));
			
			g.setColor(Color.decode("#464e5a"));
			g.drawString( ball.getTeam1().getNaam(), VeldPanel.getFrameWidth()/2-200, VeldPanel.getFrameHeight()/2-30);//75*75
			g.drawString( ball.getTeam2().getNaam(), VeldPanel.getFrameWidth()/2+94, VeldPanel.getFrameHeight()/2-30);//75*75
			
//			Image t1 = Toolkit.getDefaultToolkit().getImage("images/logos/"+ ball.getTeam1().getNaam()+".png");//"field_satur=45.png");
//			Image t2 = Toolkit.getDefaultToolkit().getImage("images/logos/"+ ball.getTeam2().getNaam()+".png");//"field_satur=45.png");
			g.drawImage(t1, VeldPanel.getFrameWidth()/2-190, VeldPanel.getFrameHeight()/2-10, this);//h=120
			g.drawImage(t2, VeldPanel.getFrameWidth()/2+80, VeldPanel.getFrameHeight()/2-10, this);//
			
		}
		
		if(end){
			//"field_satur=45.png");
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
