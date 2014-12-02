package oop.voetbalmanager.view;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class TeamPanel  extends  JPanel{
	
	public TeamPanel(){
        JLabel text = new JLabel("TEAM");
        add(text);
        setBackground(Color.GRAY);
	}
	
	
}
