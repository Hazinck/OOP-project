package oop.voetbalmanager.view;
import javax.swing.*;

import java.awt.*;

/**
 * 
 * HomePanel
 *
 */
public class Home extends JPanel{

	private homemidden hm=new homemidden();
	private homerechts hr=new homerechts();
	
	public Home(){
	//	playButton = hm.getPlayButton();
		
		add(hm);
		
		add(hr);
		
	}

	/**
	 * @return the hm
	 */
	public homemidden getHm() {
		return hm;
	}

	/**
	 * @return the hr
	 */
	public homerechts getHr() {
		return hr;
	}

	

}
