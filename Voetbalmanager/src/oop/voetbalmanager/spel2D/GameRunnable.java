package oop.voetbalmanager.spel2D;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.view.ViewFrame;


public class GameRunnable implements Runnable {
	
	private GamePanel gp;
	private VeldPanel frame;
	private Controller control;
	private ArrayList<Player> playerListAll = new ArrayList<Player>();
	private ArrayList<Timer> timers = new ArrayList<Timer>(); 
	private boolean stop = false;
	private Position position1;
	private Position position2;
	private boolean goal = false;
	
	public GameRunnable(GamePanel gp, VeldPanel frame){// VeldFrame frame){
		this.gp = gp;
		this.frame = frame;
		
		playerListAll.addAll(gp.getPlayerListTeam1());
		playerListAll.addAll(gp.getPlayerListTeam2());
		
		control = new Controller(playerListAll, gp.getBall());
		control.topPlayers();
		gp.setController(control);
		
		position1 = new Position(gp.getPlayerListTeam1(), User.getWteam().getOpstelling(), 1);
		position1.setPosition();
		
		position2 = new Position(gp.getPlayerListTeam2(), Bot.getWteam().getOpstelling(), 2);
		position2.setPosition();
	}
	
	
	public void run() {
		goalAgain();
		//  control.controlBall(frame);
		  animatePlayer();
		//  moveCam();
		  moveAll();
		  
	  }
	
	public void goalAgain(){
		if(gp.getBall().isBallInGoal()){
			goal = true;
			System.out.println("GOAL!!!");
			for(Timer t: timers){
				t.stop();
				stop = true;
				position1.setPosition();
				position2.setPosition();
				gp.getBall().reset();
			}
		}
	}
	
	public void animatePlayer(){
		 ActionListener updateAnim = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	for(Player p: playerListAll){
	     			p.getSpriteObj().update();
	     			
	     		}
	            frame.repaint();  // Refresh the JFrame, callback paintComponent()
	         }
	      };
	      Timer t =  new Timer(50, updateAnim);
	      timers.add(t);
	      t.start();
	}
	
	public void moveAll(){
		final Rectangle bounds = frame.getBounds();
		  ActionListener updateRun = new ActionListener() {
		         @Override
		         public void actionPerformed(ActionEvent evt) {
		        	 for(Player p: playerListAll){
		        		 
			     			p.move(bounds,  (int)gp.getBall().getXforP(),  (int)gp.getBall().getYforP());//targetX,Y
//			     		System.out.println("gameRunnable: " + p.getTargetX() + " = " + (int)gp.getBall().getXforP());	
			     	 }
		        	// Collision.collide(playerListAll);
		        	 Collision.collision(playerListAll);
		        	 gp.getBall().move();
		        	 autoCam(gp.getBall());

		        	 frame.repaint(); 
		         }
		      };
		      // Fullocate a Timer to run updateTask's actionPerformed() after every delay msec
		 Timer t =  new Timer(30, updateRun);
		 timers.add(t);
		 t.start();
	}
	
	public void autoCam(Ball b){
		
//		System.out.println(VeldFrame.getFrameWidth()/2  +" "+ b.getX() + gp.getViewX());
		
			if(ViewFrame.getFrameWidth()/2  > b.getX() + gp.getViewX() + 50){
				gp.setViewX(gp.getViewX()+3);
			}else if(ViewFrame.getFrameWidth()/2 < b.getX() + gp.getViewX() - 50){
				gp.setViewX(gp.getViewX()-3);
			}
			
			if(ViewFrame.getFrameHeight()/2  > b.getY() + gp.getViewY() + 50){
				gp.setViewY(gp.getViewY()+3);
			}else if(ViewFrame.getFrameHeight()/2 < b.getY() + gp.getViewY() - 50){
				gp.setViewY(gp.getViewY()-3);
			}
		
	}
	
	public void moveCam(){
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
            	if(ke.getID() == KeyEvent.KEY_PRESSED){
                    switch (ke.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        gp.setViewX(gp.getViewX()+5);
                        break;
                    case KeyEvent.VK_RIGHT:
                    	gp.setViewX(gp.getViewX()-5);
                        break;
                    case KeyEvent.VK_UP:
                    	gp.setViewY(gp.getViewY()+5);
                        break;
                    case KeyEvent.VK_DOWN:
                    	gp.setViewY(gp.getViewY()-5);
                        break;
                    }
                    if(stop){
                    	gp.repaint();
                    }
                }
                    return false;
            }
            
        });
	}
	
	public void stop(){
		for(Timer t: timers){
			t.stop();
			stop = true;
		}
	}
	
	public void start(){
		for(Timer t: timers){
			t.start();
			stop = false;
		}
	}

	

	/**
	 * @return the timers
	 */
	public ArrayList<Timer> getTimers() {
		return timers;
	}


	/**
	 * @return the playerListAll
	 */
	public ArrayList<Player> getPlayerListAll() {
		return playerListAll;
	}
	
}

