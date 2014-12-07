package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import layout.SpringUtilities;


public class PandS extends JPanel{
	
	private JTextField userField, passField;
	
	public PandS(){
        JLabel text = new JLabel("Profile and settings");
   //     Container panel2 = layoutComponents("Center", Component.CENTER_ALIGNMENT);
       
        //Change Username and Password
        JPanel userPass = new JPanel();
        userPass.setBorder(BorderFactory.createTitledBorder(null, "Change Username and Password", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial",Font.PLAIN,20), Color.decode("333333")));//("Change Username and Password"));
	    BoxLayout layout = new BoxLayout(userPass, BoxLayout.Y_AXIS);
	    userPass.setLayout(layout);
	    userPass.setPreferredSize(new Dimension(500,170));
	    userPass.setBackground(null);
	    
	    userPass.add(fields());
	    
        add(userPass);
        setBackground(Color.decode("#FFFFFF"));
	
	}
	
	 private JComponent fields() {
		 	JPanel panel = new JPanel();
	        JPanel form = new JPanel(new SpringLayout());
	        
	        
	        JLabel userLable = new JLabel("Username: ");
	        userField  = new JTextField();
	        userField.setColumns(20);
	        form.add(userLable);
	        form.add(userField);
	        
	        JLabel passLable = new JLabel("Password: ");
	        passField = new JTextField();
	        passField.setColumns(20);
	        form.add(passLable);
	        form.add(passField);
	        
	        JButton button = new JButton("Wijzigingen opslaan");
	        
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new BorderLayout());
	        buttonPanel.add(button, BorderLayout.PAGE_END);
	        
	        panel.add(form, BorderLayout.PAGE_START);
	        panel.add(buttonPanel, BorderLayout.PAGE_END);
	        
	        SpringUtilities.makeCompactGrid(form,
                    2, 2, //rows, cols
                    20, 30,        //initX, initY
                    20, 10);       //xPad, yPad
	        //Associate label/field pairs, add everything,
	        //and lay it out.
	       
//	            //Add listeners to each field.
//	            JTextField tf = null;
//	            if (fields[i] instanceof JSpinner) {
//	                tf = getTextField((JSpinner)fields[i]);
//	            } else {
//	                tf = (JTextField)fields[i];
//	            }
//	            tf.addActionListener(this);
//	            tf.addFocusListener(this);
	        
	       
	        return panel;
	    }
	 
	 
}
