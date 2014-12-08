package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;


public class TeamPanel extends JPanel{
	
	public TeamPanel(){		
        add(spelerlijst());
        add(tactiek());
        setBackground(Color.decode("#FFFFFF"));
	}
	
	public JPanel spelerlijst() {
		JPanel spelerlijst = new JPanel();
		spelerlijst.setBorder(BorderFactory.createTitledBorder(null, "Spelerlijst", 
				TitledBorder.CENTER, TitledBorder.TOP));
		spelerlijst.setPreferredSize(new Dimension(300, 500));
		spelerlijst.setBackground(null);
		

		spelerlijst.add(new JLabel("Aanvallers"));
		spelerlijst.add(maakLijst(new Dimension(250, 95)));
		
		spelerlijst.add(new JLabel("Middenvelders"));
		spelerlijst.add(maakLijst(new Dimension(250, 95)));
		
		spelerlijst.add(new JLabel("Verdedigers"));
		spelerlijst.add(maakLijst(new Dimension(250, 95)));
		
		spelerlijst.add(new JLabel("Keepers"));
		spelerlijst.add(maakLijst(new Dimension(250, 65)));
		
		return spelerlijst;
	}
	
	public JPanel opstelling() {
		JPanel opstelling = new JPanel();
		opstelling.setBorder(BorderFactory.createTitledBorder(null, "Opstelling", 
				TitledBorder.CENTER, TitledBorder.TOP));
		opstelling.setPreferredSize(new Dimension(375, 370));
		opstelling.setBackground(null);
		
		JComboBox keuze = new JComboBox();
		keuze.setPreferredSize(new Dimension(250, 25));
		opstelling.add(keuze);
		
		imgpanel image = new imgpanel("images/opstelling.png");
		image.setPreferredSize(new Dimension(300,300));
		opstelling.add(image);
		
		return opstelling;
	}
	
	public JPanel tactiek() {
		JPanel tactiek = new JPanel();
		tactiek.setBorder(BorderFactory.createTitledBorder(null, "Tactiek", 
				TitledBorder.CENTER, TitledBorder.TOP));
		tactiek.setPreferredSize(new Dimension(400, 500));
		tactiek.setBackground(null);
		
		tactiek.add(opstelling());
		
		JSlider slider = new JSlider();
		slider.setBackground(null);
		slider.setPreferredSize(new Dimension(300,80));
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(new Integer(0), new JLabel("Verdedigend"));
		labels.put(new Integer(100), new JLabel("Aanvallend"));
		slider.setLabelTable(labels);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(25);
		slider.setPaintTicks(true);
		tactiek.add(slider);
		
		return tactiek;
	}
	
	/**
	 * geeft een nieuwe JScrollPane met een JList
	 * @param d		de grootte van de lijst
	 * @return		het nieuwe JScrollPane-object
	 */
	public JScrollPane maakLijst(Dimension d) {
		JScrollPane pane = new JScrollPane(new JList(),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(d);
		
		return pane;
	}
	
}
