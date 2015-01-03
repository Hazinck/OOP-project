package oop.voetbalmanager.spel2D;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Sprite{
	

	private BufferedImage sprite;
	private BufferedImage spriteFull;
	private BufferedImage idle_sprite;
//	private int x, y ;
	private int subX, subY ;
	private int xSubSprite = 0;
	private int UPDATE_TIME = 200;
	private  String richting;
	private boolean idle = false;
	
    public Sprite(String imgFile, int team12){//String richting){//, int x, int y){//W, NW, N, NE, E, SE, S, SW,
    //	this.richting =  richting;
    	if(team12==1){
    		richting="E";
    	}else if(team12==2){
    		richting="W";
    	}
//    	this.x = x;
//    	this.y = y;
    	try {
    		spriteFull = ImageIO.read(new File(imgFile));
    		idle_sprite = ImageIO.read(new File("player/idle.png"));//idle_E
    		update();
		} catch (IOException e) {
			e.printStackTrace();
		} 

    }
    
    public void update(){
    	if(idle){
    		if(richting.contains("E")){
    			sprite = idle_sprite.getSubimage(0, 0, 60, 48);
    		}else{
    			sprite = idle_sprite.getSubimage(0, 48, 60, 48);
    		}
    	//	sprite = idle_sprite;
    	}else{
	    	kiesRichting();
	    //	System.out.println(xSubSprite+"update()");
		//	sprite = spriteFull.getSubimage(xSubSprite*48, subY, 48, 65);//x=48;y=
			sprite = spriteFull.getSubimage(xSubSprite*85, subY, 85, 48);//x=48;y=
			if(xSubSprite>=14){//%4==0){
				xSubSprite = 0;
			}
   
		xSubSprite++;
    	}
    }
    
   
    
    public void kiesRichting(){

    	switch(richting){
    	case "N"://niet
    		subX=0;
    		subY=0;
    		break;
    	case "NE":
    		subX=0;
    		subY=48;
    		break;
    	case "NW":
    		subX=0;
    		subY=96;
    		break;
    	case "E":
        	subX=0;
        	subY=144;
        	break;
    	case "W":
    		subX=0;
    		subY=192;
    		break;
    	case "SE"://niet
    		subX=0;
    		subY=240;
    		break;
    	case "SW"://niet
    		subX=0;
    		subY=288;
    		break;
    	case "S"://niet
    		subX=0;
    		subY=336;
    		break;
    	}
    //	System.out.println(subY + 48);
    }


	/**
	 * @return the sprite
	 */
	public BufferedImage getSprite() {
		return sprite;
	}

	/**
	 * @param richting the richting to set
	 */
	public void setRichting(String richting) {
		this.richting = richting;
	}

	/**
	 * @param xSubSprite the xSubSprite to set
	 */
	public void setxSubSprite(int xSubSprite) {
		this.xSubSprite = xSubSprite;
	}

	/**
	 * @param idle the idle to set
	 */
	public void setIdle(boolean idle) {
		this.idle = idle;
	}

	/**
	 * @return the idle
	 */
	public boolean isIdle() {
		return idle;
	}


	
    
//        @Override
//        public void paintComponent(Graphics g) {
//           g.drawImage(sprite, x, y, this);
//        }
     
   
}
