package oop.voetbalmanager.spel2D;
import java.util.ArrayList;

import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Positie;



public class Position {
	
	private int x;
	private int y;
	private String type;
	private int playerID;
	private Player player;
	private ArrayList<Player> playerList;
	private Opstelling opstelling;
	private int team12;
	
	
	public Position(ArrayList<Player> playerList, Opstelling opstelling, int team12){//Player p, String type, int i){
		this.playerList = playerList;
		this.opstelling = opstelling;
		this.team12 = team12;
	}
	
	
	public void setPosition(){
		
		for(int i = 0; i < opstelling.getPosities().size(); i++){
			Positie p1 = opstelling.getPosities().get(i);
			Positie p2 = correctie(p1, team12);
			
			
			int startX = (int)(p2.getX() * 0.60);
			if(team12==2){
				startX += 2560*0.40;
			}
			playerList.get(i).setX(startX);
			playerList.get(i).setY(p2.getY());

			playerList.get(i).setGrenzen(p2.getX(), p2.getY());
			if(playerList.get(i).getSpeler().getType().equals("doelman")){

			}

		}
		
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @param type
	 * @param team12 1 = team links, 2 = team rechts
	 * @return
	 */
	public Positie correctie(Positie p, int team12){
		//stap 1
		int stap1X = p.getY();
		int stap1Y = p.getX();
		
		//stap 2
		int stap2X = 475 - stap1X;
		
		//stap 3
		double stap3X = (stap2X * 2560) / 475;
		double stap3Y = (stap1Y * 1115) / 297;
		
		//stap 4
		double stap4procent = (stap3Y * 30.9) / 1089;
		
		//stap 5
		double stap5procent = 68.18 + stap4procent;
		
		//stap 6 pre final
		double stap6Y = stap3Y * stap5procent / 100;
		
		//stap 7.1
		double stap71pixX = 1115 - stap6Y;
		//stap 7.2
		double stap72pixX = (stap71pixX * 330) / 1115;
		//stap 7.3
		double stap73pixX = stap72pixX * 0.75;// + 100;//extra correctie door opstelling
		//stap 7.4
		double finalX;
		if(stap3X < 1275){
			finalX = stap3X + stap73pixX;
		}else{
			finalX = stap3X - stap73pixX;
		}
		
		//stap 8
		double finalY = stap6Y + 335;
		
		if(team12==2){
			finalX = 2560 - finalX;
		}
		
		return new Positie((int)finalX, (int)finalY, p.getType());
	}
	
	
	public int playerXPlaats(String type, int i){
		  int x = -30;
		  int dXi = i;
		  int vXi = dXi - 1;
		  int mXi = vXi - 4;
		  int aXi = mXi - 3;
		  
		  if(type.equals("aanvaller")){
			  if(aXi==1){
				  x = 390;
			  }else if((aXi==0 || aXi==2)){
				  x = 370;
			  }else if(aXi==11){
				  x = 450;
			  }else if((aXi==13 || aXi==12)){
				  x = 470;
			  }
		  }
		  
		  if(type.equals("middenvelder")){
			  if(mXi==0){
				  x = 300;
			  }else if((mXi==1 || mXi==2)){
				  x = 280;
			  }else if(mXi==13){
				  x = 540;
			  }else if((mXi==11 || mXi==12)){
				  x = 560;
			  }
		  }
		  if(type.equals("verdediger")){
			  if(vXi<=3){
				  x = 215;
			  }else if(x >= 11 || x<= 14){
				  x = 625;
			  }
		  }
		  if(type.equals("doelman")){
			  if(dXi==0){
				  x = 65;
			  }else if(dXi==11){
				  x = 775;
			  }
		  }
		  
		  return x;
	  }
	  
	  public int playerYPlaats(String type, int i){
		  int y = -30;
		  int dYi = i;
		  int vYi = dYi - 1;
		  int mYi = vYi - 4;
		  int aYi = mYi - 3;

		  if(type.equals("aanvaller") && (aYi==0 || aYi==13)){
			  y = 145;
		  }else if(type.equals("aanvaller") && (aYi==1 || aYi==11)){
			  y = 265;
		  }else if(type.equals("aanvaller") && (aYi==2 || aYi==12)){
			  y = 380;
		  }

		  if(type.equals("middenvelder") && (mYi==0 || mYi==13)){
			  y = 265;
		  }else if(type.equals("middenvelder") && (mYi==1 || mYi==11)){
			  y = 80;
		  }else if(type.equals("middenvelder") && (mYi==2 || mYi==12)){
			  y = 444;
		  }
		  
		  if(type.equals("verdediger") && (vYi==0 || vYi==13)){
			  y = 340;
		  }else if(type.equals("verdediger") && (vYi==1 || vYi==11)){
			  y = 444;
		  }else if(type.equals("verdediger") && (vYi==2 || vYi==12)){
			  y = 80;
		  }else if(type.equals("verdediger") && (vYi==3 || vYi==14)){
			  y = 190;
		  }
		  
		  if(type.equals("doelman") && (dYi==0 || dYi==11)){
			  y = 265;
		  }
		  
		  return y;
	  }
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the playerID
	 */
	public int getPlayerID() {
		return playerID;
	}


	
}
