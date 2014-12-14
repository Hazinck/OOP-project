package oop.voetbalmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Spel;
import oop.voetbalmanager.model.User;
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
                play();
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
	
	public void play(){
		JButton playButton = home.getHm().getPlayButton();
		playButton.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent actionEvent) { 
	        	int geluksfactor = RNG.getalTot(600);
	       		Spel s = new Spel(User.getTeam(), Bot.getBotTeam(), geluksfactor);
	       		System.out.println(s.winner().getNaam() + " geluksfactor: "+geluksfactor);
	       		home.getHm().getGoals().setText((s.winner().getNaam()+" heeft gewonnen!"));
	       		updateStats();
	       		}
		});
		                
	 //    p2.getButton().addActionListener(actionListener2);   
		
	}
	
	public void updateStats(){
		Bot.volgendeTeam();
		int speeldag = tabs.getTable().getSpeeldag() + 1;
   		home.getHm().getScores().setText(User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam());
   		tabs.getTable().getTable().setValueAt(User.getTeam().getBudget(),0,1);
   		tabs.getTable().getTable().setValueAt(speeldag,1,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getScore(),2,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getRank(),3,1);
   		tabs.getTable().getTable().setValueAt(Bot.getBotTeam().getNaam(),4,1);
	}
}
