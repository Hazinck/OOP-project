package oop.voetbalmanager.spel2D;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Timer;

import oop.voetbalmanager.model.RNG;


public class Controller {
	
	private ArrayList<Player> team1 = new ArrayList<Player>();
	private Ball ball;
	
	private ArrayList<Player> sortTeam1 = new ArrayList<Player>();
	private boolean kickToPlayer = false;
	//score voorlopig hardcoded   //(team1 score, team2 score)
	private Dimension score = new Dimension(3, 2);
	private long currentTimeFalse = 0;
	
	public Controller(ArrayList<Player> team1, Ball ball){
		this.team1 = team1;
		this.ball = ball;
	}
	
	//voorlopig 1 team
	public void topPlayers(){
		for(Player p: team1){
				sortTeam1.add(p);
		}
		Collections.sort(sortTeam1, new Comparator<Player>() {
		    public int compare(Player p1, Player p2) {
		    	Integer p1O = p1.getSpeler().getOffense();
		    	Integer p2O = p2.getSpeler().getOffense();
		        return p1O.compareTo(p2O);
		    }
		});
		Collections.reverse(sortTeam1);
		
	//	System.out.println(sortTeam1.toString());
	}
	
	public void controlBallPerPlayer(final Player p){

		if(p.getCircleBounds().contains((int)ball.getXforP(), (int)ball.getYforP())){
//			long currentTime = System.currentTimeMillis()/1000;
		//	System.out.println(currentTimeFalse);
		//	if(currentTimeFalse+5 <= currentTime){
//	  			System.out.println("Controller: "+p.getSpeler().getNaam() + " heeft bal" + currentTimeFalse + " "+ currentTime);
		//	System.out.println("Controller: "+p.getSpeler().getNaam() +" boundsCenterX "+p.getCircleBounds().getCenterX() + "  bal: " + (int)ball.getX() + "," + (int)ball.getY());	
//			System.out.println("Controller: "+p.getSpeler().getNaam() + " heeft bal " + (int)ball.getX() + "," + (int)ball.getY());
				ball.setOwner(p);
	  			p.setBallOwner(true);
//	  			p.getGp().setBallControllerFalseTimer(p, currentTime);
		//	}
  		}else{
  			p.setBallOwner(false);
  		}
		
		
	}
	/*
	public void controlBal(final VeldPanel frame){//VeldFrame frame){
		if(kickToPlayer){	
			ActionListener updateAnim = new ActionListener() {
		         @Override
		         public void actionPerformed(ActionEvent evt) {
		
	        	int pIdx = RNG.getalTot(7);
	     		
	     		if( !sortTeam1.get(pIdx).isBallOwner() ){
	     			for(Player p : sortTeam1){
		     			p.setBallOwner(false);
		     		}
	     			ball.setTargetX(sortTeam1.get(pIdx).getX());
	     			ball.setTargetY(sortTeam1.get(pIdx).getY());
	     		//	sortTeam1.get(pIdx).setBallOwner(true);
	     			ball.setOwner(sortTeam1.get(pIdx));
	     			ball.setKick(true);
	     		//	System.out.println(sortTeam1.get(pIdx).getSpeler().getNaam() + " has ball");
	     		}
	     		
	            frame.repaint(); // Refresh the JFrame, callback paintComponent()
		         
			
	         }
	      };
	      Timer t =  new Timer(50, updateAnim);
	      frame.getGr().getTimers().add(t);
	      t.start();
		}
//	      // Fullocate a Timer to run updateTask's actionPerformed() after every delay msec
	     
		
	}*/

	/**
	 * @return the currentTimeFalse
	 */
	public long getCurrentTimeFalse() {
		return currentTimeFalse;
	}

	/**
	 * @param currentTimeFalse the currentTimeFalse to set
	 */
	public void setCurrentTimeFalse(long currentTimeFalse) {
		this.currentTimeFalse = currentTimeFalse;
	}

	/**
	 * @return the kickToPlayer
	 */
	public boolean isKickToPlayer() {
		return kickToPlayer;
	}

	/**
	 * @param kickToPlayer the kickToPlayer to set
	 */
	public void setKickToPlayer(boolean kickToPlayer) {
		this.kickToPlayer = kickToPlayer;
	}
	
}
