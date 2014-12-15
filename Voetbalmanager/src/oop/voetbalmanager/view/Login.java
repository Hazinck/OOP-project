package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

//eerste panel
public class Login extends JPanel{
	
	private LoginPanel loginpanel;
	private JButton button = new JButton("Inloggen");	
	private ViewFrame viewFrame;
	/**
	 * @param view
	 */
	
	
	public Login(ViewFrame viewFrame) {
		this.viewFrame = viewFrame;
		
	    setBackground(Color.black);
		
		//Absolute positionering binnen deze panel
	    setLayout(null);
	    
	    //deze panel toevoegen aan View frame
	    viewFrame.controlPanel.add(this);  
	    Insets insets = this.getInsets();
	    
	    //tekst aanmaken
	    JLabel label  = new JLabel();        
	    label.setText("<Voetbalmanager>");
	    label.setFont(new Font("Consolas", Font.BOLD, 30));
	    label.setOpaque(true);
	    label.setBackground(Color.yellow);
	    label.setForeground(Color.black);

	    //loginpanel aanmaken
	    loginpanel = new LoginPanel();
	    loginpanel.setOpaque(false);
	    loginpanel.setBackground(Color.black);
	    loginpanel.setForeground(Color.black);
	    
	    //tekst, button en loginpanel positioneren
	    
	    add(label);
	    Dimension sizeText = label.getPreferredSize();
	    //Positie op 50% van frame breedte naar rechts
	    int labelLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeText.width/2;
	    //Positie op 20% van frame hoogte naar beneden
	    int labelTop = viewFrame.getHeight()*20/100 + insets.top;
	    label.setBounds(labelLeft, labelTop ,
	    		sizeText.width, sizeText.height);

	    add(button);
	    Dimension sizeButton = button.getPreferredSize();
	  //Positie op 50% van frame breedte naar rechts
	    int buttonLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizeButton.width/2;
	    //Positie op 20% van frame hoogte naar beneden
	    int buttonTop = viewFrame.getHeight()*50/100 + insets.top;
	    button.setBounds(buttonLeft, buttonTop,
	    		sizeButton.width, sizeButton.height);
	    
	    
	    add(loginpanel);
	    Dimension sizePanel = loginpanel.getPreferredSize();
	    int loginpanelLeft = viewFrame.getFrameWidth()*50/100 + insets.left - sizePanel.width/2;
	    int loginpanelTop = viewFrame.getHeight()*40/100 + insets.top;
	    loginpanel.setBounds(loginpanelLeft, loginpanelTop, sizePanel.width, sizePanel.height);

	}
	/**
	 * @return the button
	 */
	public JButton getButton() {
		return button;
	}
	
	public void showThis(Tabs tabsPanel){
		tabsPanel.setVisible(false);
		this.setVisible(true);
	}
	
}
