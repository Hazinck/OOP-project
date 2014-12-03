package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;


//window(frame) van programma
public class ViewFrame extends JFrame{
	public JPanel controlPanel;
	
	//neem scherm breedte en hoogte
	private int frameWidth = 1000;//(int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private int frameHeight = 600;//(int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	
	public ViewFrame(){
	    setSize(frameWidth,frameHeight);    
	    setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Voetbalmanager Eredivisie 2015");  

	    controlPanel = new JPanel();
	    controlPanel.setLayout(new BorderLayout());
	    controlPanel.setSize(this.getSize());
	    controlPanel.setBackground(Color.green);
	      
	    
	    UIManager.put("TabbedPane.borderHightlightColor", java.awt.Color.RED); 
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.RED); 
	    UIManager.put("TabbedPane.light", java.awt.Color.RED);
	    UIManager.put("TabbedPane.selectHighlight", java.awt.Color.RED);
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.RED);
	    UIManager.put("TabbedPane.focus", java.awt.Color.RED);
	    add(controlPanel);
	}

	/**
	 * @return the frameWidth
	 */
	public int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public int getFrameHeight() {
		return frameHeight;
	}
	 
	
}
