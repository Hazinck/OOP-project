package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import oop.voetbalmanager.model.RNG;

//eerste panel
public class Login extends JPanel{
	
//	private LoginPanel loginpanel;
//	private JButton button = new JButton("Inloggen");
	private JButton newGame = new JButton("New game");
	private JButton loadGame = new JButton("Load game");
	private JButton exit = new JButton("Quit");
	private ViewFrame viewFrame;
	private int img = RNG.getalTot(9) + 1;
	/**
	 * @param view
	 */
	
	
	public Login(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;		
	    setBackground(Color.black);
		
		//Absolute positionering binnen deze panel
	    setLayout(null);
	    
	    //deze panel toevoegen aan View frame
	    viewFrame.getControlPanel().add(this);  
	    Insets insets = this.getInsets();
	    
	    //tekst aanmaken
//	    JLabel label  = new JLabel();        
//	    label.setText("<Voetbalmanager>");
//	    label.setFont(new Font("Consolas", Font.BOLD, 30));
//	    label.setOpaque(true);
//	    label.setBackground(Color.yellow);
//	    label.setForeground(Color.black);

	    //loginpanel aanmaken
	//    loginpanel = new LoginPanel();

//	    loginpanel.setForeground(Color.black);
	    
	    //tekst, button en loginpanel positioneren
	    
//	    add(label);
//	    Dimension sizeText = label.getPreferredSize();
//	    //Positie op 50% van frame breedte naar rechts
//	    int labelLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeText.width/2;
//	    //Positie op 20% van frame hoogte naar beneden
//	    int labelTop = viewFrame.getHeight()*20/100 + insets.top;
//	    label.setBounds(labelLeft, labelTop ,
//	    		sizeText.width, sizeText.height);

//	    add(button);
//	    Dimension sizeButton = button.getPreferredSize();
//	  //Positie op 50% van frame breedte naar rechts
//	    int buttonLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeButton.width/2;
//	    //Positie op 20% van frame hoogte naar beneden
//	    int buttonTop = viewFrame.getHeight()*50/100 + insets.top;
//	    button.setBounds(buttonLeft, buttonTop,
//	    		sizeButton.width, sizeButton.height);
	    add(newGame);
	    Dimension sizeNG = newGame.getPreferredSize();
	    int ngLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeNG.width/2;
	    int ngTop = viewFrame.getHeight()*40/100 + insets.top;
	    newGame.setBounds(ngLeft, ngTop,
	    		sizeNG.width, sizeNG.height);
	    
	    add(loadGame);
	    Dimension sizeLG = loadGame.getPreferredSize();
	    int lgLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeLG.width/2;
	    int lgTop = viewFrame.getHeight()*50/100 + insets.top;
	    loadGame.setBounds(lgLeft, lgTop,
	    		sizeLG.width, sizeLG.height);
	    
	    add(exit);
	    Dimension sizeEX = exit.getPreferredSize();
	    int exLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeEX.width/2;
	    int exTop = viewFrame.getHeight()*60/100 + insets.top;
	    exit.setBounds(exLeft, exTop,
	    		sizeEX.width, sizeEX.height);
	    
//	    add(loginpanel);
//	    Dimension sizePanel = loginpanel.getPreferredSize();
//	    int loginpanelLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizePanel.width/2;
//	    int loginpanelTop = viewFrame.getHeight()*40/100 + insets.top;
//	    loginpanel.setBounds(loginpanelLeft, loginpanelTop, sizePanel.width, sizePanel.height);

	}
	
	public void showThis(Tabs tabsPanel){
		tabsPanel.setVisible(false);
		this.setVisible(true);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Image image;
    	
		try {
			image = ImageIO.read(new File(viewFrame.getImgPath() + "start/"+img+".jpg"));
//			image = ImageIO.read(new File(viewFrame.getImgPath() + "football.jpg"));
			g.drawImage(image, 0, 0,(int)(ViewFrame.getFrameWidth()),(int)(ViewFrame.getFrameHeight()), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
  }

	/**
	 * @return the newGame
	 */
	public JButton getNewGame() {
		return newGame;
	}

	/**
	 * @return the loadGame
	 */
	public JButton getLoadGame() {
		return loadGame;
	}

	/**
	 * @return the exit
	 */
	public JButton getExit() {
		return exit;
	}

	/**
	 * @return the img
	 */
	public int getImg() {
		return img;
	}

	
}
