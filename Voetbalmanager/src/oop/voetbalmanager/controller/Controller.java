package oop.voetbalmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import oop.voetbalmanager.view.Competition;
import oop.voetbalmanager.view.Home;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.PandS;
import oop.voetbalmanager.view.Tabs;
import oop.voetbalmanager.view.TeamPanel;
import oop.voetbalmanager.view.ViewFrame;


public class Controller {
	private ViewFrame viewFrame;
	private Login l;
	public Tabs tabs;
	private Home home;
	private TeamPanel teamPanel;
	private Competition comp;
	private PandS ps;
	
	public Controller(ViewFrame viewFrame, Login l, Home home, TeamPanel teamPanel, Competition comp, PandS ps) {
		this.viewFrame = viewFrame;
		this.l = l;
		this.home = home;
		this.teamPanel = teamPanel;
		this.comp = comp;
		this.ps = ps;

	}

	public void contol() {

		
		ActionListener actionListener = new ActionListener() {
             public void actionPerformed(ActionEvent actionEvent) { 
            	 System.out.println("Inloggen");
            	tabs = new Tabs(viewFrame, home, teamPanel, comp, ps);
                tabs.showThis(l);
             //   controlPanel2();
                addLogoutListener();
             }
       };                
       l.getButton().addActionListener(actionListener);   
       
       
		
	}
	
	public void addLogoutListener(){
		JButton logout = tabs.getTable().getImagePanel().getLogoutButton();
       logout.addActionListener(new ActionListener() {
    	    public void actionPerformed(ActionEvent e)
    	    {
    	       viewFrame.dispose();
    	    }
    	});
	}
	
	public void controlPanel2(){
		 ActionListener actionListener2 = new ActionListener() {
	           public void actionPerformed(ActionEvent actionEvent) { 
	          	 System.out.println("Terug");
	              l.showThis(tabs);
	              
	           }
	     };                
	 //    p2.getButton().addActionListener(actionListener2);   
		
	}

}
