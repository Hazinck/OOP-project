package oop.voetbalmanager;
import java.awt.EventQueue;

import javax.swing.JFrame;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
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
		Divisie divisie = reader.readDivisie();
		final Team ajax = divisie.getTeamList().get(1);
		
	    EventQueue.invokeLater(new Runnable() {
	      
	    	@Override
	      public void run() {
	    	  User.setNaam("Kamran Tadzjibov");
	    	  User.setTeam(ajax);
	    	  ViewFrame viewFrame = new ViewFrame();
	  	   	  Login l = new Login(viewFrame);
	  	   	  Home h = new Home();
	  	   	  TeamPanel t = new TeamPanel();
	  	   	  Competition comp = new Competition(viewFrame);
	  	   	  PandS ps = new PandS(viewFrame);
//	  	   	  p1.showThis(p2); 
              Controller controller = new Controller(viewFrame, l, h, t, comp, ps);
              controller.contol();
              
              
//              viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	  	      viewFrame.setVisible(true);
	      }
	    });
	  }	
	
}
