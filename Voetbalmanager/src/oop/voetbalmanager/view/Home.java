package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Home extends  JPanel{
	
	public Home(){
        JLabel text = new JLabel("HOME");
        add(text);
        setBackground(Color.yellow);
	}
	
}
