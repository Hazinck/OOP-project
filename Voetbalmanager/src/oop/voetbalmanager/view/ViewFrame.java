package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


//window(frame) van programma
public class ViewFrame extends JFrame{
	public JPanel controlPanel;
	
	//tijdelijk
	private String username = "Voornaam Achternaam";
	private String teamNaam = "Go Ahead Eagles";
	private String imgPath = System.getProperty("user.dir") + "/images/";
	private String imgName = "user_default.png";
	
	
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
	      
	    
	    UIManager.put("TabbedPane.borderHightlightColor", java.awt.Color.LIGHT_GRAY); 
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.LIGHT_GRAY); 
	    UIManager.put("TabbedPane.light", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.selectHighlight", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.focus", java.awt.Color.LIGHT_GRAY);
	    
	    UIManager.put("TabbedPane.contentAreaColor ",ColorUIResource.WHITE);
	    UIManager.put("TabbedPane.selected",ColorUIResource.decode("333333"));
	    UIManager.put("TabbedPane.selectedForeground",ColorUIResource.decode("#FFFFFF"));
	    UIManager.put("TabbedPane.background",ColorUIResource.WHITE);
	    UIManager.put("TabbedPane.shadow",ColorUIResource.WHITE);
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

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the teamNaam
	 */
	public String getTeamNaam() {
		return teamNaam;
	}

	/**
	 * @return the imgPath
	 */
	public String getImgPath() {
		return imgPath;
	}

	/**
	 * @return the imgName
	 */
	public String getImgName() {
		return imgName;
	}
	 
	
}
