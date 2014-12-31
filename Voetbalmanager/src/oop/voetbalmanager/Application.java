package oop.voetbalmanager;
import java.awt.EventQueue;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.Wedstrijdteam;
import oop.voetbalmanager.model.XMLreader;
import oop.voetbalmanager.view.Competition;
import oop.voetbalmanager.view.Home;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.PandS;
import oop.voetbalmanager.view.TeamPanel;
import oop.voetbalmanager.view.ViewFrame;


public class Application {
	
	
	public static void main(String[] args) {
		XMLreader reader = new XMLreader();
		final Divisie divisie = reader.readDivisie();
		final Team team = divisie.getTeamList().get(8);
		final Wedstrijdteam wteam = reader.readWedstrijdteam(team);
		
	    EventQueue.invokeLater(new Runnable() {
	      
	    	@Override
	      public void run() {
	    	  User.setNaam("Andy Zaidman");
	    	  User.setTeam(team);
	    	  User.setWteam(wteam);
	    	  
	    	  Bot.setDivisie(divisie);
	    	  Bot.setUserTeam(team);
	    	  Bot.volgendeTeam();
	    	  
	    	  ViewFrame viewFrame = new ViewFrame();
	  	   	  Login l = new Login(viewFrame);
	  	   	  Home h = new Home();
	  	   	  TeamPanel t = new TeamPanel();
	  	   	  Competition comp = new Competition(viewFrame);
	  	   	  PandS ps = new PandS(viewFrame);
	  	   	  

	  	   	  Bot.teamToWTeam(t.getOpst().getOpstellingen());
//	  	   	  p1.showThis(p2); 
              Controller controller = new Controller(viewFrame, l, h, t, comp, ps);
              controller.contol();
              
              
//              viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	  	      viewFrame.setVisible(true);
	      }
	    });
	  }	
	
}
