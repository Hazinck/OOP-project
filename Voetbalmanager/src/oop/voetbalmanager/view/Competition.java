package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.Team;


public class Competition extends JPanel{

	private ViewFrame vframe;
	private Box main = Box.createHorizontalBox();
	private ScrollPanel pane;// = new ScrollPanel("Ranking", "Uitgebreide ranking met doelpunten saldo etc", 18);
	//private ScrollPanel transferPane = new ScrollPanel("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)");
	
	public Competition(ViewFrame vframe){
		this.vframe = vframe;
        setBackground(Color.WHITE);
        
        
        
        
        main.add(Box.createRigidArea(new Dimension(40,0)));
//        main.add(rankPane);//ScrollPanel.createTable("Ranking", "Uitgebreide ranking met doelpunten saldo etc"));
//        main.add(Box.createRigidArea(new Dimension(40,0)));
     //   main.add(transferPane);//ScrollPanel.createTable("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)"));
//        main.add(Box.createRigidArea(new Dimension(40,0)));
        
        add(main);
      //  addRank();
	}
	
	public void addPane(Object[][] data, String[] columnNames){
//		ArrayList<ImageIcon> imgList = new ArrayList<ImageIcon>();
//		ArrayList<String> teamDescrList = new ArrayList<String>();
		
		//ImageIcon ajaxIcon = new ImageIcon("images/logos/Ajax.png");
		
		
//        Object[][] data =	{ {ajaxIcon, "Ajax"},
//					        };
        
        pane = new ScrollPanel(18, data, columnNames);
        main.add(pane);
        main.add(Box.createRigidArea(new Dimension(40,0)));
	}

	/**
	 * @return the pane
	 */
	public ScrollPanel getPane() {
		return pane;
	}
	
	
}
