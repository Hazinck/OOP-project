package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;


public class TeamPanel extends JPanel{
	
	public TeamPanel(){		
        add(spelerlijst());
        add(opstelling());
        setBackground(Color.decode("#FFFFFF"));
	}
	
	public JPanel spelerlijst() {
		JPanel spelerlijst = new JPanel();
		spelerlijst.setBorder(BorderFactory.createTitledBorder(null, "Spelerlijst", 
				TitledBorder.CENTER, TitledBorder.TOP));
		spelerlijst.setPreferredSize(new Dimension(300, 500));
		spelerlijst.setBackground(null);
		
		JLabel aanvalLabel = new JLabel("Aanvallers");
		JList aanvalList = new JList();
		JScrollPane aanvalPane = new JScrollPane(aanvalList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		aanvalPane.setPreferredSize(new Dimension( 250, 95));
		spelerlijst.add(aanvalLabel);
		spelerlijst.add(aanvalPane);
		
		JLabel middenLabel = new JLabel("Middenvelders");
		JList middenList = new JList();
		JScrollPane middenPane = new JScrollPane(middenList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		middenPane.setPreferredSize(new Dimension( 250, 95));
		spelerlijst.add(middenLabel);
		spelerlijst.add(middenPane);
		
		JLabel verdedigLabel = new JLabel("Verdedigers");
		JList verdedigList = new JList();
		JScrollPane verdedigPane = new JScrollPane(verdedigList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		verdedigPane.setPreferredSize(new Dimension( 250, 95));
		spelerlijst.add(verdedigLabel);
		spelerlijst.add(verdedigPane);
		
		JLabel keeperLabel = new JLabel("Keepers");
		JList keeperList = new JList();
		JScrollPane keeperPane = new JScrollPane(keeperList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		keeperPane.setPreferredSize(new Dimension( 250, 65));
		spelerlijst.add(keeperLabel);
		spelerlijst.add(keeperPane);
		
		return spelerlijst;
	}
	
	public JPanel opstelling() {
		JPanel opstelling = new JPanel();
		opstelling.setBorder(BorderFactory.createTitledBorder(null, "Opstelling", 
				TitledBorder.CENTER, TitledBorder.TOP));
		opstelling.setPreferredSize(new Dimension(400, 500));
		opstelling.setBackground(null);
		
		
		
		return opstelling;
	}
	
}
