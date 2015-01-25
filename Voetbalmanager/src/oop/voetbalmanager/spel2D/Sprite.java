package oop.voetbalmanager.spel2D;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Sprite{
	

	private BufferedImage sprite;
	private BufferedImage spriteFull;
	private BufferedImage idle_sprite;
	private int subX, subY ;
	private int xSubSprite = 0;
	private int UPDATE_TIME = 200;
	private  String richting;
	private boolean idle = false;
	
    public Sprite(String imgFile, int team12){
    	if(team12==1){
    		richting="E";
    	}else if(team12==2){
    		richting="W";
    	}
    	try {
    		ImageIO.setUseCache(false);
    		spriteFull = ImageIO.read(new File(imgFile+"/run.png"));
    		idle_sprite = ImageIO.read(new File(imgFile+"/idle.png"));
    		update();
		} catch (IOException e) {
			e.printStackTrace();
		} 

    }
    
    public void update(){
    	if(idle){
    		if(richting.contains("E")){
    			sprite = idle_sprite.getSubimage(0, 0, 85, 48);
    		}else{
    			sprite = idle_sprite.getSubimage(0, 48, 85, 48);
    		}
    	}else{
	    	kiesRichting();
			sprite = spriteFull.getSubimage(xSubSprite*85, subY, 85, 48);
			if(xSubSprite>=14){
				xSubSprite = 0;
			}
   
		xSubSprite++;
    	}
    }
    
   
    
    public void kiesRichting(){

    	switch(richting){
    	case "N":
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
    	case "SE":
    		subX=0;
    		subY=240;
    		break;
    	case "SW":
    		subX=0;
    		subY=288;
    		break;
    	case "S":
    		subX=0;
    		subY=336;
    		break;
    	}
    }

    public void clearImg(){
    	sprite.flush();
    	spriteFull.flush();
    	idle_sprite.flush();
    	sprite = null;
    	spriteFull = null;
    	idle_sprite = null;
    	
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
   
}
