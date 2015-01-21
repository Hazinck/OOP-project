package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;


//window(frame) van programma
public class ViewFrame extends JFrame{
	private JPanel controlPanel;

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

	    controlPanel = new JPanel(){
	    	@Override
	        protected void paintComponent(Graphics g) {
	        	super.paintComponent(g);
	        	Image image;
	        	
	    		try {
	    			image = ImageIO.read(new File("images/background.png"));
//	    			image = ImageIO.read(new File(viewFrame.getImgPath() + "football.jpg"));
	    			g.drawImage(image, 0, -70, null);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	             
	    	}
	    };
	    controlPanel.setLayout(new BorderLayout());
	    controlPanel.setSize(this.getSize());
	  //  controlPanel.setBackground(Color.red);
	      
	    
	    UIManager.put("TabbedPane.borderHightlightColor", java.awt.Color.LIGHT_GRAY); //java.awt.Color.LIGHT_GRAY
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.LIGHT_GRAY); 
	    UIManager.put("TabbedPane.light", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.selectHighlight", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.darkShadow", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.focus", java.awt.Color.LIGHT_GRAY);
	    UIManager.put("TabbedPane.contentOpaque", false);
	    
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

	/**
	 * @return the controlPanel
	 */
	public JPanel getControlPanel() {
		return controlPanel;
	}
	 
	
}
