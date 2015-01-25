package oop.voetbalmanager.spel2D;


import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Collision{
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	private static String toVerslag = "";
	
	public static void collision(ArrayList<Player> players){
		for (int i = 0; i < players.size(); i++){  
			for (int j = i + 1; j < players.size(); j++){ 
				Player p1 = players.get(i);
			    Player p2 = players.get(j);
			    Ellipse2D p1Bounds = p1.getCircleBounds();
			    Ellipse2D p2Bounds = p2.getCircleBounds();
			    
		        double distance = Math.sqrt(Math.pow((p1Bounds.getCenterX()) - (p2Bounds.getCenterX()),2) +
		        					Math.pow((p1Bounds.getCenterY()) - (p2Bounds.getCenterY()), 2));
		
		        if((int)distance<(p1Bounds.getWidth())){
		        	double normX = ((p1Bounds.getCenterX())) - (p2Bounds.getCenterX()) / distance;
		        	double normY = ((p1Bounds.getCenterY())) - (p2Bounds.getCenterY()) / distance;
		        	
		        	double correctieXp1 = (int)Math.cos(normX*(p2Bounds.getWidth()/2 + p1Bounds.getWidth()/2))*(p2Bounds.getWidth()/2);
		        	double correctieYp1 = (int)Math.sin(normY*(p2Bounds.getHeight()/2 + p1Bounds.getHeight()/2))*(p2Bounds.getHeight()/2);

		        	double correctieXp2 = (int)Math.cos(normX*(p1Bounds.getWidth()/2 + p2Bounds.getWidth()/2))*(p1Bounds.getWidth()/2);
		        	double correctieYp2 = (int)Math.sin(normY*(p1Bounds.getHeight()/2 + p2Bounds.getHeight()/2))*(p1Bounds.getHeight()/2);
		            //p1 move

		            double newXp1 = (p1Bounds.getCenterX())+correctieXp1;
		            double newYp1 = (p1Bounds.getCenterY())+correctieYp1;
		            
		            double newXp2 = (p2Bounds.getCenterX())+correctieXp2;
		            double newYp2 = (p2Bounds.getCenterY())+correctieYp2;
		            
		            if(p2Bounds.getCenterY()< p1Bounds.getCenterY()){
		            	p2.setY((int)newYp2);
		            }else{
			            p1.setY((int)newYp1);
		            }
		            if(p2Bounds.getCenterX()< p1Bounds.getCenterX()){
		           
		            	p2.setX((int)newXp2);
		            }else{
		            	p1.setX((int)newXp1);
		            }		       
		            
		            //kick the ball
					if(p1.isBallOwner()){
						System.out.println("Collision: "+p1.getSpeler().getNaam() + " kick it    t=" + p1.getBall().getT() + " ball: " + p2.getBall().getX() + "," + p2.getBall().getY());
						toVerslag = "Nu probeert "+p1.getSpeler().getNaam()+" langs "+p2.getSpeler().getNaam()+" te gaan met een vloeiende beweging, maar hij speelt de bal te ver voor zich uit.";
						Controller2D.kickBal(p1);
					}else if(p2.isBallOwner()){
						System.out.println("Collision: "+p2.getSpeler().getNaam() + " kick it    t=" + p2.getBall().getT() + " ball: " + p2.getBall().getX() + "," + p2.getBall().getY());
						toVerslag = "Nu probeert "+p2.getSpeler().getNaam()+" langs "+p1.getSpeler().getNaam()+" te gaan met een vloeiende beweging, maar hij speelt de bal te ver voor zich uit.";
						Controller2D.kickBal(p2);
					}
					
		        }
		    }
		}
    }

	/**
	 * @return the toVerslag
	 */
	public static String getToVerslag() {
		return toVerslag;
	}
	
}
