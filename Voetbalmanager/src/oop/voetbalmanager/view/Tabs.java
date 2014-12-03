package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

//tweede panel waar alle andere panels(tabs) in zitten
public class Tabs extends JPanel{
		private JButton button = new JButton("Terug");;
		
		private ViewFrame viewFrame;
		
		//Tabs(ViewFrame, Home, Team, Competition, Profile and Settings)
		public Tabs(ViewFrame viewFrame, Home home, TeamPanel teamPanel, Competition comp, PandS ps){
			this.viewFrame = viewFrame;
		    setBackground(Color.green);
		    setLayout(new GridLayout());

		    //tabs panel toevoegen aan ViewFrame
		    viewFrame.controlPanel.add(this);  
		    
		    //jtabbedpane aanmaken
		    JTabbedPane tabbedPane = new JTabbedPane();
		    
		    //4 andere panels hieraan toevoegen als tabs: ("Title", panel)
		    tabbedPane.add("Home",home);
		    tabbedPane.add("Team",teamPanel);
		    tabbedPane.add("Competition",comp);
		    tabbedPane.add("Profile and settings",ps);
		  
		  //jtabbedpane toevoegen aan deze panel(Tabs)
		    add(tabbedPane);

		}  
		
		/**
		 * @return the button
		 */
		
		public void showThis(Login loginPanel){
			loginPanel.setVisible(false);
			this.setVisible(true);
		}
}
