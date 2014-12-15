package oop.voetbalmanager.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.RNG;
import oop.voetbalmanager.model.Spel;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.XMLreader;
import oop.voetbalmanager.view.Competition;
import oop.voetbalmanager.view.Home;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.LoginPanel;
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
	private ArrayList<String> ranglijst = new ArrayList<String>();
	
	
	
	public Controller(ViewFrame viewFrame, Login l, Home home, TeamPanel teamPanel, Competition comp, PandS ps) {
		this.viewFrame = viewFrame;
		this.l = l;
		this.home = home;
		this.teamPanel = teamPanel;
		this.comp = comp;
		this.ps = ps;

	}

	public void contol() {
		ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");ranglijst.add("");
		
		ActionListener actionListener = new ActionListener() {
             public void actionPerformed(ActionEvent actionEvent) { 
            	 System.out.println("Inloggen");
            	 User.setNaam(LoginPanel.setName());
            	 tabs = new Tabs(viewFrame, home, teamPanel, comp, ps);
                tabs.showThis(l);
                teamPanel.addAanvaller("Andy Zaidman");
                teamPanel.addAanvaller("Henk");
                teamPanel.removeAanvaller("Henk");
             //   controlPanel2();
                addLogoutListener();
                play();
                ranking();
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
	       		spel(s);
	       		updateStats(s);
	       		}
		});
		                
	 //    p2.getButton().addActionListener(actionListener2);   
		
	}
	
	public void updateStats(Spel s){
		Bot.volgendeTeam();
		int speeldag = tabs.getTable().getSpeeldag() + 1;
		tabs.getTable().setSpeeldag(speeldag);
   		home.getHm().getScores().setText(User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam());
   		tabs.getTable().getTable().setValueAt(User.getTeam().getBudget(),0,1);
   		tabs.getTable().getTable().setValueAt(speeldag,1,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getScore(),2,1);
   		tabs.getTable().getTable().setValueAt(User.getTeam().getRank(),3,1);
   		tabs.getTable().getTable().setValueAt(Bot.getBotTeam().getNaam(),4,1);
   		ranking();
   	//	teamPanel.get;
	}
	
	public void spel(Spel s){
		Document doc = home.getHm().getGoals().getDocument();
    	for(String v: s.verslag()){
			Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
	    	System.out.println( tijd.format(cal.getTime()) );
    		try {
				doc.insertString(doc.getLength(), tijd.format(cal.getTime()) + " " + v + "\n", null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	//home.getHm().getGoals().setText(tijd.format(cal.getTime()) + " " + v + "\n");
    	
    	}
    	//home.getHm().getGoals().setText((s.winner().getNaam()+" heeft gewonnen!"));
    	try {
    		Calendar cal = Calendar.getInstance();
	    	cal.getTime();
	    	SimpleDateFormat tijd = new SimpleDateFormat("HH:mm");
			doc.insertString(doc.getLength(),tijd.format(cal.getTime()) + " " + s.winner().getNaam()+" heeft gewonnen!"
					+ "\n\n===================================\n\n", null);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void ranking(){
		XMLreader reader = new XMLreader();
		Divisie divisie = reader.readDivisie();
		
		for(int i = 0; i<18; i++){
			int rank = divisie.getTeamList().get(i).getRank();
			
			String team = rank + ". " + divisie.getTeamList().get(i).getNaam() + " " + divisie.getTeamList().get(i).getScore();
			ranglijst.set(rank-1, team);
		}
		
		String rankList="";
		
		for(String s: ranglijst){
			rankList += s + "\n";
		}
		
		home.getHr().getRankings().setText(rankList);
	}
	
}
