package oop.voetbalmanager.view;
import java.awt.Color;

import javax.swing.JPanel;

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
		setLayout(null);
		setOpaque(false);
		add(hm);
		hm.setBounds(0, 0, (int)(ViewFrame.getFrameWidth()*0.63), (int)(ViewFrame.getFrameHeight()));
		
		add(hr);
		hr.setBounds((int)(ViewFrame.getFrameWidth()*0.63), 0, (int)(ViewFrame.getFrameWidth()*0.46), (int)(ViewFrame.getFrameHeight()));
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
