package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import oop.voetbalmanager.model.RNG;

public class NewGamePanel extends JPanel{
	
	private ArrayList<String> teamNames = new ArrayList<String>();
	private ArrayList<JButton> teamButtons = new ArrayList<JButton>();
	
	private JLabel userLabel = new JLabel("Naam");
	private JTextField userText = new JTextField(10);
	private ViewFrame viewFrame;
	private Login startPanel;
	private int img = 1;
	
	public NewGamePanel(ViewFrame viewFrame){
		this.viewFrame = viewFrame;
		setBackground(Color.white);
		setLayout(null);
		
		JPanel user = new JPanel();
		user.setLayout(new FlowLayout());
		user.add(userLabel);
		user.add(userText);
		add(user);
		Dimension sizeUS = user.getPreferredSize();
	    int usLeft = ViewFrame.getFrameWidth()*50/100 - sizeUS.width/2;
	    int usTop = ViewFrame.getFrameHeight()*10/100;
	    user.setBounds(usLeft, usTop,
	    		sizeUS.width, sizeUS.height);
//		
	    JPanel teamsChoise = teamsChoise();
	    teamsChoise.setBackground(null);
	    teamsChoise.setOpaque(false);
		Dimension sizeTC = teamsChoise.getPreferredSize();
	    int tcLeft = ViewFrame.getFrameWidth()*50/100 - sizeTC.width/2;
	    int tcTop = usTop + sizeUS.height + 20;
	    teamsChoise.setBounds(tcLeft, tcTop,
	    		sizeTC.width, sizeTC.height);
	    
		add(teamsChoise);
		
		viewFrame.getControlPanel().add(this);
		
    	
	}
	
	public JPanel teamsChoise(){
		JPanel teams = new JPanel();
		
		teams.setLayout(new GridLayout(4, 2, 20, 20));
		
		File folder = new File( System.getProperty("user.dir") +"/images/logos");
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	teamsChoise();
	        } else {
	        	String teamImg = fileEntry.getName();
	            String teamImgNaam = teamImg.replaceAll(".png", "");
	            teamNames.add(teamImgNaam);
	            
	        	BufferedImage buttonIcon;
	    		try {
	    			buttonIcon = ImageIO.read(fileEntry);
	    			JButton teamChButton = new JButton(new ImageIcon(buttonIcon));
	    			teamButtons.add(teamChButton);
	    			teams.add(teamChButton);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	        }
	    }
	    
		return teams;
	}
	
	public void showThis(Login startPanel){
		this.startPanel = startPanel;
		startPanel.setVisible(false);
		this.setVisible(true);
		
		boolean find = true;
    	while(find){
    		img = RNG.getalTot(9) + 1;
    		if(img!=startPanel.getImg()){
    			find = false;
    		}
    	}
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
		g.setColor( new Color(255, 255, 255, 100) );
        g.fillRect(0, 0, getWidth(), getHeight());
         
  }

	
	/**
	 * @return the teamNames
	 */
	public ArrayList<String> getTeamNames() {
		return teamNames;
	}

	/**
	 * @return the teamButtons
	 */
	public ArrayList<JButton> getTeamButtons() {
		return teamButtons;
	}

	/**
	 * @return the userText
	 */
	public JTextField getUserText() {
		return this.userText;
	}
	
	
}

