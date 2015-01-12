package oop.voetbalmanager;
import java.awt.EventQueue;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.ViewFrame;


public class Application {
	
	
	public static void main(String[] args) {
//		XMLreader reader = new XMLreader();
//		final Divisie divisie = reader.readDivisie(Driver.path);
//		final Team team = divisie.getTeamList().get(8);
//		final Wedstrijdteam wteam = reader.readWedstrijdteam(team, Driver.path);
		
	    EventQueue.invokeLater(new Runnable() {
	      
	    	@Override
	      public void run() {
	    	//  User.setNaam("Andy Zaidman");
//	    	  User.setTeam(team);
//	    	  User.setWteam(wteam);
//	    	  
//	    	  Bot.setDivisie(divisie);
//	    	  Bot.setUserTeam(team);
//	    	  Bot.volgendeTeam();
	    	  
	    	  ViewFrame viewFrame = new ViewFrame();
	  	   	  Login l = new Login(viewFrame);
//	  	   	  Home h = new Home();
//	  	   	  TeamPanel t = new TeamPanel();
//	  	   	  Competition comp = new Competition(viewFrame);
//	  	   	  PandS ps = new PandS(viewFrame);
	  	   	  

//	  	   	  Bot.teamToWTeam(t.getOpst().getOpstellingen());
//	  	   	  p1.showThis(p2); 
              Controller controller = new Controller(viewFrame, l);//, h, t, comp, ps);
              controller.control();
              
              
//              viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	  	      viewFrame.setVisible(true);
	      }
	    });
	  }	
	
}
