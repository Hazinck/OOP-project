package oop.voetbalmanager.view;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveOpstellingDialog {
	 
	public static String popup() {
		String naam = "";
		JTextField naamField = new JTextField("");
	    JPanel panel = new JPanel(new GridLayout(0, 1));
	    panel.add(new JLabel("Noem je opstelling:"));
	    panel.add(naamField);
	    int result = JOptionPane.showConfirmDialog(null, panel, "Nieuwe opstelling",
	    		JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
	    if (result == JOptionPane.OK_OPTION) {
	    	//System.out.println("Niewe opstelling: " + naam);
	    	naam = naamField.getText();
	    	if(naam.equals("")){
	    		JOptionPane.showMessageDialog(null,
	    			    "Naamveld is leeg!",
	    			    "Opstelling is niet opgeslagen.",
	    			    JOptionPane.ERROR_MESSAGE);
	    	}
	    } else {
	        System.out.println("Cancelled");
	    }
	    
	    
	    return naam;
	}
}
