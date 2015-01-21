package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Competition extends JPanel{

	private ViewFrame vframe;
	private Box main = Box.createHorizontalBox();
	//private ArrayList<JScrollPane> pane = new ArrayList<JScrollPane>();// = new ScrollPanel("Ranking", "Uitgebreide ranking met doelpunten saldo etc", 18);
	private ScrollPanel rankPane;// = new ScrollPanel("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)");
	private ScrollPanel transferPane;
	
	public Competition(ViewFrame vframe){
		this.vframe = vframe;
        setBackground(Color.WHITE);
        setOpaque(false);
        
        
        
        main.add(Box.createRigidArea(new Dimension(40,0)));
//        main.add(rankPane);//ScrollPanel.createTable("Ranking", "Uitgebreide ranking met doelpunten saldo etc"));
//        main.add(Box.createRigidArea(new Dimension(40,0)));
     //   main.add(transferPane);//ScrollPanel.createTable("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)"));
//        main.add(Box.createRigidArea(new Dimension(40,0)));
        
        add(main);
      //  addRank();
	}
	
	public void addPane(Object[][] data, String[] columnNames, int i){
//		ArrayList<ImageIcon> imgList = new ArrayList<ImageIcon>();
//		ArrayList<String> teamDescrList = new ArrayList<String>();
		
		//ImageIcon ajaxIcon = new ImageIcon("images/logos/Ajax.png");
		
		
//        Object[][] data =	{ {ajaxIcon, "Ajax"},
//					        };
		if(i==0){
			rankPane = new ScrollPanel(18, data, columnNames);
	        main.add(rankPane);
		}else if(i==1){
			transferPane = new ScrollPanel(18, data, columnNames);
	        main.add(transferPane);
		}
		
        main.add(Box.createRigidArea(new Dimension(40,0)));
	}

	/**
	 * @return the rankPane
	 */
	public ScrollPanel getRankPane() {
		return rankPane;
	}

	/**
	 * @return the transferPane
	 */
	public ScrollPanel getTransferPane() {
		return transferPane;
	}

	
	
	
}
