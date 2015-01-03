package oop.voetbalmanager.spel2D;


import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import oop.voetbalmanager.model.RNG;

public class Collision{
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	
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
	//		            System.out.println("New collision old coordinates: "+p2.getX() + "," + p2.getY());
			            p2.setX((int)newXp2);
			            p2.setY((int)newYp2);
	//		            System.out.println("New collision new coordinates: "+newX + "," + newY);
		            }else{
		            	p1.setX((int)newXp1);
			            p1.setY((int)newYp1);
		            }
		            //kick the ball
		            
					if(p1.isBallOwner()){
						System.out.println(p1.getSpeler().getNaam() + " kick it");
						kickBal(p1);
					}else if(p2.isBallOwner()){
						System.out.println(p2.getSpeler().getNaam() + " kick it");
						kickBal(p2);
					}
					
		        }
		    }
		}
    }
	
	public static void kickBal(Player p){
		if((p.getTeam12() == 1 && p.getBall().getGoalRToKick().contains(p.getX(), p.getY())) || 
				(p.getTeam12() == 2 && p.getBall().getGoalLToKick().contains(p.getX(), p.getY()))){
			goal(p);
		}else{
			passBal(p);
		}
	}
	
	public static void passBal(Player p){
		ArrayList<Player> alle = new ArrayList<Player>();
		
		alle.addAll(p.getGp().getPlayerListTeam1());
		alle.addAll(p.getGp().getPlayerListTeam2());
		
		int pIdx = RNG.getalTot(22);
		p.setBallOwner(false);
		p.getBall().setT(0);
		p.getBall().setTargetX(alle.get(pIdx).getX());
		p.getBall().setTargetY(alle.get(pIdx).getY());
	//	sortTeam1.get(pIdx).setBallOwner(true);
	//	p.getBall().setOwner(alle.get(pIdx));
		p.getBall().setKick(true);
	}
	
	
	public static void goal(Player p){
		int targetGoalX;
		int targetGoalY;
		if(p.getTeam12()==1){
			targetGoalX = 2256;
		}else{
			targetGoalX = 300;
		}
		targetGoalY = 700 + RNG.getalTot(81);
		
		p.setBallOwner(false);
		p.getBall().setT(0);
		p.getBall().setTargetX(targetGoalX);
		p.getBall().setTargetY(targetGoalY);
	//	sortTeam1.get(pIdx).setBallOwner(true);
	//	p.getBall().setOwner(alle.get(pIdx));
		p.getBall().setKick(true);
	}
/*	public static void collide(ArrayList<Player> players){

		for (int i = 0; i < players.size(); i++){  
//			if( !players.get(i).isBallOwner()){
			    for (int j = i + 1; j < players.size(); j++){ 
//			    	if( !players.get(j).isBallOwner()){
				    	Player p1 = players.get(i);
					    Player p2 = players.get(j);
//					    System.out.println("collision : "+ p1.getTargetY());
						double x1 = p1.getX();
						double y1 = p1.getY();
						
						double x2 = p2.getX();
						double y2 = p2.getY();
	
						//target
						double tx1 = p1.getTargetX();
						double ty1 = p1.getTargetY();
						
						double tx2 = p2.getTargetX();
						double ty2 = p2.getTargetY();
						
						Rectangle boundsP1 = p1.getBounds();
						Rectangle boundsP2 = p2.getBounds();
						
					//	System.out.println("collision: "+ tx1 + "," + ty1 + "     " + tx2 + "," + ty2);
						
						if(boundsP1.intersects(boundsP2)){
							if(!p1.isCollision() && !p2.isCollision()){
								if(p1.isBallOwner() || p2.isBallOwner()){
									
									Player p;
									if(p1.isBallOwner()){
										p=p1;
									}else{
										p=p2;
									}
									int targetPlayer = RNG.getalTot(11);
									
									if(p.getTeam12()==1){
										p.getBall().setTargetX(p.getGp().getPlayerListTeam1().get(targetPlayer).getX());
										p.getBall().setTargetY(p.getGp().getPlayerListTeam1().get(targetPlayer).getY());
									}else{
										p.getBall().setTargetX(p.getGp().getPlayerListTeam2().get(targetPlayer).getX());
										p.getBall().setTargetY(p.getGp().getPlayerListTeam2().get(targetPlayer).getY());
									}
									p.getBall().setKick(true);
									p.getBall().kick();
									p.getGp().getPlayerListTeam2().get(targetPlayer).setBallOwner(true);
									p.getBall().setOwner(p.getGp().getPlayerListTeam2().get(targetPlayer));
									System.out.println("Collision class: "+p1.getSpeler().getNaam() + " kick da ball " + p.getBall().getStartX() + p.getBall().getTargetX());
									
								}
								if( !players.get(i).isBallOwner() && !players.get(j).isBallOwner()){
//								if(p1.getBounds().getCenterY() >= p2.getBounds().getCenterY()){
//									y1 += p1.getSpeedY();
//									y2 -= p2.getSpeedY();
//								} else{
//									y1 -= p1.getSpeedY();
//									y2 += p2.getSpeedY();
//								}
//								if(p1.getBounds().getCenterX() >= p2.getBounds().getCenterX()){
//									x1 += p1.getSpeedX();
//									x2 -= p2.getSpeedX();
//								} else{
//									x1 -= p1.getSpeedX();
//									x2 += p2.getSpeedX();
//								}
								
								p1.setCollision(true);
								p2.setCollision(true);
								if(x1==x2 || y1==y2){
									if(x1==x2){
										
										ty1 = -tx1;
										ty2 = tx2;
									}else if(y1==y2){
										tx1 = -ty1;
										tx2 = ty2;
									}
								}
								else{
									
									ty1 = 0;
									ty2 = 0;
									if(x1>x2+5){
										x1 += p1.getSpeedX()*2;
										x2 -= p2.getSpeedX()*2;
									}else if(x1+5<x2){
										x1 -= p1.getSpeedX()*2;
										x2 += p2.getSpeedX()*2;
									}
									if(y1>y2 + 10){
										y1 += p1.getSpeedY()*2;
										y2 -= p2.getSpeedY()*2;
									}else if(y1+10<y2){
										y1 -= p1.getSpeedY()*2;
										y2 += p2.getSpeedY()*2;
									}
								}
						
							}
						}else{
							p1.setCollision(false);
							p2.setCollision(false);
						}
							
							
						p1.setX((int)x1);
						p2.setX((int)x2);
						p1.setY((int)y1);
						p2.setY((int)y2);
						p1.setTargetX((int)tx1);
						p2.setTargetX((int)tx2);
						p1.setTargetY((int)ty1);
						p2.setTargetY((int)ty2);
					
			    	}
			    }
			}
//		}
	}
*/
}
