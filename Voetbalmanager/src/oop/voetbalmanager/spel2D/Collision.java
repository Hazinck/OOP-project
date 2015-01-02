package oop.voetbalmanager.spel2D;


import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision{// implements Runnable{
	
	private static ArrayList<Player> players = new ArrayList<Player>();
	
//	public Collision(ArrayList<Player> players){
//		this.players = players;		   
//	}
	
	public static void collide(ArrayList<Player> players){

		for (int i = 0; i < players.size(); i++){  
			if( !players.get(i).isBallOwner()){
			    for (int j = i + 1; j < players.size(); j++){ 
			    	if( !players.get(j).isBallOwner()){
				    	Player p1 = players.get(i);
					    Player p2 = players.get(j);
					    
						double x1 = p1.getX();
						double y1 = p1.getY();
						
						double x2 = p2.getX();
						double y2 = p2.getY();
	
						//target
						double tx1 = p1.getTargetX();
						double ty1 = p1.getTargetY();
						
						double tx2 = p2.getTargetX();
						double ty2 = p2.getTargetY();
						
						Rectangle boundsP1 = p1.getBounds();//new Rectangle((int)x1-30, (int)y1-30, 30, 48);
						Rectangle boundsP2 = p2.getBounds();//new Rectangle((int)x2-30, (int)y2-30, 30, 48);
						
						p1.setCollision(false);
						p2.setCollision(false);
						if(boundsP1.intersects(boundsP2)){
						//	System.out.println("collideeeeeeeeeeeeee" + boundsP1.toString() + " " + boundsP2.toString());
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
//							else if(x1==x2 && y1==y2){
//								x1 += p1.getSpeedX();
//								x2 -= p2.getSpeedX();
//								y1 += p1.getSpeedY();
//								y2 -= p2.getSpeedY();
//							}
							else{
								ty1 = 0;
								ty2 = 0;
								if(x1>x2){
									x1 += p1.getSpeedX()*2;
									x2 -= p2.getSpeedX()*2;
								}else{
									x1 -= p1.getSpeedX()*2;
									x2 += p2.getSpeedX()*2;
								}
								if(y1>y2){
									y1 += p1.getSpeedY()*2;
									y2 -= p2.getSpeedY()*2;
								}else{
									y1 -= p1.getSpeedY()*2;
									y2 += p2.getSpeedY()*2;
								}
							}
						
							
						}
	//					double dx = x2 - x1;
	//					double dy = y2 - y1;
	//					
	//					if( p2.isBallOwner()){	
	//						if(dx>0 && dx<16 && Math.abs(dy)<40){// 36dx en 40dy
	//							x1 -= p1.getSpeedX();//1
	//						}
	//						if(dy>0 && dy<40 && Math.abs(dx)<16){
	//							y1 -= p1.getSpeedY();//1;
	//						}
	//					}else{
	//						if(dx>0 && dx<16 && Math.abs(dy)<40){// 36dx en 40dy
	//							x2 += p2.getSpeedX();//1
	//							x1 -= p1.getSpeedX();//1
	//						}
	//		//				if(dx<0 && dx>-36 && Math.abs(dy)<40){
	//		//					x2 -= 1;
	//		//					x1 += 1;
	//		//				}
	//						if(dy>0 && dy<20 && Math.abs(dx)<16){
	//							y2 += p2.getSpeedY();//1;
	//							y1 -= p1.getSpeedY();//1;
	//						}
	//					}
	
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
		}
	}

//	@Override
//	public void run() {
//		while(true)
//			collide();
//		
//	}
}
