package oop.voetbalmanager;
import java.awt.EventQueue;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.view.Login;
import oop.voetbalmanager.view.ViewFrame;


public class Application {
	
	
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	      
	    	@Override
	      public void run() {
	    	  ViewFrame viewFrame = new ViewFrame();
	  	   	  Login l = new Login(viewFrame);

              Controller controller = new Controller(viewFrame, l);//, h, t, comp, ps);
              controller.control();
	    	}
	    });
	  }	
	
}
