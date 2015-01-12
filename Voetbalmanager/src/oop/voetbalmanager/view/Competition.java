package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;


public class Competition extends JPanel{

	private ViewFrame vframe;
	
	public Competition(ViewFrame vframe){
		this.vframe = vframe;
        setBackground(Color.WHITE);
        
        Box main = Box.createHorizontalBox();
        
        main.add(Box.createRigidArea(new Dimension(40,0)));
        main.add(ScrollPanel.createTable("Ranking", "Uitgebreide ranking met doelpunten saldo etc"));
        main.add(Box.createRigidArea(new Dimension(40,0)));
        main.add(ScrollPanel.createTable("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)"));
        main.add(Box.createRigidArea(new Dimension(40,0)));
        
        add(main);
	}
	
	
}
