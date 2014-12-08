package oop.voetbalmanager.view;
import javax.swing.*;

import java.awt.*;

/**
 * 
 * HomePanel
 *
 */
public class Home extends JPanel{
	public Home(){
		
		homemidden hm=new homemidden();
		homerechts hr=new homerechts();
		
		add(hm);
		
		add(hr);
		
	}
	

}
