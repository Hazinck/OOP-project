package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import oop.voetbalmanager.model.User;


//window(frame) van programma
public class ViewFrame extends JFrame{
	public JPanel controlPanel;
	
	//tijdelijk
//	private String username = User.getNaam();//"Kamran Tadzjibov";
//	private String teamNaam = User.getTeam().getNaam();//"Go Ahead Eagles";
	private String imgPath = System.getProperty("user.dir") + "/images/";
	private String imgName = "user_default.png";
	
	
	//neem scherm breedte en hoogte
	private static int frameWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();//1000;//
	private static int frameHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()-50;//600;//
	
	public ViewFrame(){
		setUndecorated(true);
	    setSize(frameWidth,frameHeight);    
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2-20);
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
	public static int getFrameWidth() {
		return frameWidth;
	}

	/**
	 * @return the frameHeight
	 */
	public static int getFrameHeight() {
		return frameHeight;
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
