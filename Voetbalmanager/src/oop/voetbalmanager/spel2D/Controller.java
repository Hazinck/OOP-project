package oop.voetbalmanager.spel2D;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.Timer;

import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.User;


public class Controller {
	
	private ArrayList<Player> team1 = new ArrayList<Player>();
	private Ball ball;
	
	private ArrayList<Player> sortTeam1 = new ArrayList<Player>();
	private boolean kickToPlayer = false;
	
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
			ball.setOwner(p);
	  		p.setBallOwner(true);
  		}else{
  			p.setBallOwner(false);
  		}
		
		
		
	}
	
	public static void kickBal(Player p){
		p.getBall().setT(0);
		if(((p.getTeam12() == 1 && p.getBall().getGoalRToKick().contains(p.getX(), p.getY())) || 
				(p.getTeam12() == 2 && p.getBall().getGoalLToKick().contains(p.getX(), p.getY())))){
			if(toGoal(p)){
				boolean wtf =  p.getBall().getScore().width < p.getBall().getFinalResult().width;
				System.out.println(p.getBall().getFinalResult().toString());
				System.out.println("CONTROLLER: "+wtf+" " + p.getBall().getScore().width +" < "+ p.getBall().getFinalResult().width);
				goal(p);
			}else{
				passBal(p);
			}
		}else{
			passBal(p);
		}
	}
	
	
	public static void passBal(Player p){
		p.getBall().setT(0);
		ArrayList<Player> alle = new ArrayList<Player>();
		
		alle.addAll(p.getGp().getPlayerListTeam1());
		alle.addAll(p.getGp().getPlayerListTeam2());
		
		int pIdx = RNG.getalTot(22);
		p.setBallOwner(false);
		
		p.getBall().setTargetX(alle.get(pIdx).getX());
		p.getBall().setTargetY(alle.get(pIdx).getY());
	//	sortTeam1.get(pIdx).setBallOwner(true);
	//	p.getBall().setOwner(alle.get(pIdx));
		p.getBall().setKick(true);
	}
	
	
	public static void goal(Player p){
		p.getBall().setT(0);
		int targetGoalX;
		int targetGoalY;
		if(p.getTeam12()==1){
			targetGoalX = 2256;
		}else{
			targetGoalX = 300;
		}
		targetGoalY = 700 + RNG.getalTot(81);
		
		p.setBallOwner(false);
		p.getBall().setTargetX(targetGoalX);
		p.getBall().setTargetY(targetGoalY);
	//	sortTeam1.get(pIdx).setBallOwner(true);
	//	p.getBall().setOwner(alle.get(pIdx));
		p.getBall().setKick(true);
		p.getBall().setKickedToGoal(p);
	}
	
	public static boolean toGoal(Player p){
		if(p.getTeam12()==1 && 
					p.getBall().getScore().width < p.getBall().getFinalResult().width){
			return true;
			
		}else if(p.getTeam12()==2 && 
					p.getBall().getScore().height < p.getBall().getFinalResult().height){
			return true;
			
		}else{
			return false;
		}
	}
	
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
