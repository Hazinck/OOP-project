package oop.voetbalmanager.view;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Competition extends JPanel{
	
	public Competition(){
        JLabel text = new JLabel("Competition");
        add(text);
        setBackground(Color.cyan);
	}
	

}
