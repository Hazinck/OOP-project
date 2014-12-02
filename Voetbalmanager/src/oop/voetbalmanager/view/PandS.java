package oop.voetbalmanager.view;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class PandS extends JPanel{
	
	public PandS(){
        JLabel text = new JLabel("Profile and settings");
        add(text);
        setBackground(Color.magenta);
	}
	

}
