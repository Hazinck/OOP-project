package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
		    //setLayout(new GridLayout());
		    setLayout(new BorderLayout());
		    
		    //tabs panel toevoegen aan ViewFrame
		    viewFrame.controlPanel.add(this);  
		    
		    //table
		    Table table = new Table();
		    table.start("Go Ahead Eagles", "Voornaam Achternaam");
		    //table toevoegen
		    add(table, BorderLayout.LINE_START);
		    
		    //jtabbedpane aanmaken
		    JTabbedPane tabbedPane = new JTabbedPane();
		    
		    //4 andere panels hieraan toevoegen als tabs: ("Title", panel)
		    tabbedPane.add("Home",home);
		    tabbedPane.add("Team",teamPanel);
		    tabbedPane.add("Competition",comp);
		    tabbedPane.add("Profile and settings",ps);
		  
		  //jtabbedpane toevoegen aan deze panel(Tabs)
		    add(tabbedPane,BorderLayout.CENTER);

		}  
		
		/**
		 * @return the button
		 */
		
		public void showThis(Login loginPanel){
			loginPanel.setVisible(false);
			this.setVisible(true);
		}
	
}
