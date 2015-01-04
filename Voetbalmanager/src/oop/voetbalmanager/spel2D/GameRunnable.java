package oop.voetbalmanager.spel2D;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Timer;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.view.ViewFrame;


public class GameRunnable implements Runnable {
	
	private GamePanel gp;
	private VeldPanel veldPanel;
	private Controller control;
	private ArrayList<Player> playerListAll = new ArrayList<Player>();
	private ArrayList<Timer> timers = new ArrayList<Timer>(); 
	private boolean stop = false;
	private Position position1;
	private Position position2;
	private boolean goal = false;
	private ArrayList<String> verslag = new ArrayList<String>();
	private long tStart;
	public GameRunnable(GamePanel gp, VeldPanel veldPanel){// VeldFrame veldPanel){
		this.gp = gp;
		this.veldPanel = veldPanel;
		
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
		tStart = System.currentTimeMillis();
		//  control.controlBall(veldPanel);
		  animatePlayer();
		//  moveCam();
		  moveAll();
		  goalAgain();
		  
	  }
	
	
	public void goalAgain(){
		ActionListener reset = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 long tEnd = System.currentTimeMillis();
	        	 long tDelta = tEnd - tStart;
	        	 double elapsedMinutes = tDelta / 1000.0 / 60;
	        	 
	        	 if(elapsedMinutes > 3 && gp.getBall().getFinalResult().equals(gp.getBall().getScore())){
	        		 endspiel();
	        	 }
	        	 else{
	        		 if(gp.getBall().isBallInGoal()){
	        	 		System.out.println("GameRunnable: GOAL!!!");
	        	 		verslag(gp.getBall().getToVerslag());
	        			resetAll();
	        		 }
	        	 }
	         }
	      };
	      Timer t =  new Timer(50, reset);
	      timers.add(t);
	      t.start();
		
	}
	
	public void endspiel(){
		
		 String winner = "Afgelopen! ";
  		 if(gp.getBall().winner()==0){
  			 winner += gp.getBall().getTeam1().getNaam()+" en "+
  					 	gp.getBall().getTeam2().getNaam()+
  					 	" delen de punten: "+gp.getBall().getScore().width+"-"+gp.getBall().getScore().height+".";
  		 }else{
  			 if(gp.getBall().winner()==1){
  				winner +=  gp.getBall().getTeam1().getNaam()+" wint met "+gp.getBall().getScore().width+"-"+gp.getBall().getScore().height+
  							" van "+gp.getBall().getTeam2().getNaam()+".";
  			 }else if(gp.getBall().winner()==2){
  				 winner +=  gp.getBall().getTeam2().getNaam()+" wint met "+gp.getBall().getScore().width+"-"+gp.getBall().getScore().height+
  							" van "+gp.getBall().getTeam1().getNaam()+".";
  			 }
  			 
  		 }
  		 verslag(winner);
  		 veldPanel.getPauseResume().setEnabled(false);
  		 resetAll();
		       
	}
	
	public void resetAll(){
		
 			goal = true;
 			for(Timer t: timers){
 				t.stop();
 				stop = true;
 				position1.setPosition();
 				position2.setPosition();
 				gp.getBall().reset();
 				resetCam();
 				veldPanel.repaint();
 				gp.getBall().setBallInGoal(false);
 			}
 		
	}
	
	public void animatePlayer(){
		 ActionListener updateAnim = new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	for(Player p: playerListAll){
	     			p.getSpriteObj().update();
	     			
	     		}
	            veldPanel.repaint();  // Refresh the JFrame, callback paintComponent()
	         }
	      };
	      Timer t =  new Timer(50, updateAnim);
	      timers.add(t);
	      t.start();
	}
	
	public void moveAll(){
		final Rectangle bounds = veldPanel.getBounds();
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
		        	 
		        	 verslag(Collision.getToVerslag());
		        	 
		        	 veldPanel.repaint(); 
		        	 
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
				if(gp.getViewX()<-5){
					gp.setViewX(gp.getViewX()+3);
				}
			}else if(ViewFrame.getFrameWidth()/2 < b.getX() + gp.getViewX() - 50){
				if(gp.getViewX()>-1190){
					gp.setViewX(gp.getViewX()-3);
				}
			}
			
			if(ViewFrame.getFrameHeight()/2  > b.getY() + gp.getViewY() + 50){
				if(gp.getViewY()<-5){
					gp.setViewY(gp.getViewY()+3);
				}
			}else if(ViewFrame.getFrameHeight()/2 < b.getY() + gp.getViewY() - 50){
				if(gp.getViewY()>-760){
					gp.setViewY(gp.getViewY()-3);
				}
			}
		
	}
	
	public void moveCam(){
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
            	if(ke.getID() == KeyEvent.KEY_PRESSED){
            //		System.out.println("GameRunnable: "+gp.getViewY());
	                    switch (ke.getKeyCode()) {
	                    case KeyEvent.VK_LEFT:
	                    	if(gp.getViewX()<-5){
	                    		gp.setViewX(gp.getViewX()+5);
	                    	}
	                        break;
	                    case KeyEvent.VK_RIGHT:
	                    	if(gp.getViewX()>-1190){
	                    		gp.setViewX(gp.getViewX()-5);
	                    	}
	                        break;
	                    case KeyEvent.VK_UP:
	                    	if(gp.getViewY()<-5){
	                    		gp.setViewY(gp.getViewY()+5);
	                    	}
	                        break;
	                    case KeyEvent.VK_DOWN:
	                    	if(gp.getViewY()>-760){
	                    		gp.setViewY(gp.getViewY()-5);
	                    	}
	                        break;
	                    }
	                    if(stop){
	                    	veldPanel.repaint();
	                    }
	                }
                   return false;
            
            }
            
        });
	}
	
	public void verslag(String line){
	//	System.out.println(verslag.size()==0);
		if(verslag.size()<2 || !line.equals(verslag.get(verslag.size()-1))){
			verslag.add(line);
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
			veldPanel.getVerslagPanel().getVerslag().append( tijd.format(cal.getTime()) + " " + line + "\n");
		}
	}
	
	public void resetCam(){
		gp.setViewX(-1275 + ViewFrame.getFrameWidth()/2);
		gp.setViewY(-805 + ViewFrame.getFrameHeight()/2);
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

