package oop.voetbalmanager.spel2D;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import oop.voetbalmanager.model.RNG;


public class Controller2D {
	
	private ArrayList<Player> team1 = new ArrayList<Player>();
	private Ball ball;
	
	private ArrayList<Player> sortTeam1 = new ArrayList<Player>();
	private boolean kickToPlayer = false;
	
	private long currentTimeFalse = 0;
	private static boolean kicked;
	
	public Controller2D(ArrayList<Player> team1, Ball ball){
		this.team1 = team1;
		this.ball = ball;
		kicked = false;
	}
	
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
		
	}
	
	public void controlBallPerPlayer(final Player p){
  		if(!kicked && p.getCircleBallBounds().contains((int)ball.getXforP(), (int)ball.getYforP()) && p.getSpeler().getType().equals("doelman")&&
  				(p.getBall().getLastBallOwner()==null || !p.getBall().getLastBallOwner().getSpeler().getNaam().equals(p.getSpeler().getNaam()))){
			Controller2D.kickBal(p);
		}
  		else if(!kicked && p.getCircleBallBounds().contains((int)ball.getXforP(), (int)ball.getYforP()) ){//zonder Ball
  			ball.setOwner(p);
	  		p.setBallOwner(true);

  		}else{
  			p.setBallOwner(false);
  		}
		
		if(p.getGp().isManualPlay() && p.getBall().getLastBallOwner()==null && p.getTeam12()==1){
			topPlayers();
			p.getBall().setLastBallOwner(sortTeam1.get(0));
		}
		
	}
	
	public static void kickBal(Player p){
		kicked = true;
		p.getBall().setT(0);
		if(((p.getTeam12() == 1 && p.getBall().getGoalRToKick().contains(p.getX(), p.getY())) || 
				(p.getTeam12() == 2 && p.getBall().getGoalLToKick().contains(p.getX(), p.getY())))){
			System.out.println("Controller: check toGoal()");
			
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
			if(p.getTeam12()==1){
				p.getBall().setLastBallOwner(p);
			}
			p.getBall().setTargetX(alle.get(pIdx).getX());
			p.getBall().setTargetY(alle.get(pIdx).getY());
			if(p.getGp().isManualPlay() && p.getTeam12()==1){
				double toX = alle.get(pIdx).getX(), toY = alle.get(pIdx).getY();
				Dimension d = targetByRichting(p,toX, toY);
				p.getBall().setTargetX(d.width);
				p.getBall().setTargetY(d.height);
			}
			
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
		
		if(p.getTeam12()==1){
			p.getBall().setLastBallOwner(p);
		}
		
		p.getBall().setTargetX(targetGoalX);
		p.getBall().setTargetY(targetGoalY);
		p.getBall().setKick(true);
		p.getBall().setKickedToGoal(p);
	}
	
	public static Dimension targetByRichting(Player p, double toX, double toY){
		p.findRichting();
		String r = p.getRichting();
		switch(r){
    	case "N":
    		toX=p.getX();
    		toY=p.getY()-250;
    		break;
    	case "NE":
    		toX=p.getX()+250;
    		toY=p.getY()-250;
    		break;
    	case "NW":
    		toX=p.getX()-250;
    		toY=p.getY()-250;
    		break;
    	case "E":
    		toX=p.getX()+250;
    		toY=p.getY();
        	break;
    	case "W":
    		toX=p.getX()-250;
    		toY=p.getY();
    		break;
    	case "SE":
    		toX=p.getX()+250;
    		toY=p.getY()+250;
    		break;
    	case "SW":
    		toX=p.getX()-250;
    		toY=p.getY()+250;
    		break;
    	case "S":
    		toX=p.getX();
    		toY=p.getY()+250;
    		break;
    	}
		return new Dimension((int)toX, (int)toY);
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

	/**
	 * @return the kicked
	 */
	public static boolean isKicked() {
		return kicked;
	}

	/**
	 * @param kicked the kicked to set
	 */
	public static void setKicked(boolean kicked) {
		Controller2D.kicked = kicked;
	}

	
	
}
