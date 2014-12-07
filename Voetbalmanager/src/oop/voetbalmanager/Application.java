package oop.voetbalmanager;
import java.awt.EventQueue;

import javax.swing.JFrame;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.view.Competition;
import oop.voetbalmanager.view.Home;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.PandS;
import oop.voetbalmanager.view.TeamPanel;
import oop.voetbalmanager.view.ViewFrame;


public class Application {
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	      
	    	@Override
	      public void run() {
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
