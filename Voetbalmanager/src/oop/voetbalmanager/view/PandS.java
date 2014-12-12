package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
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
	
	public PandS(ViewFrame vframe){
   //     JLabel text = new JLabel("Profile and settings");
   //     Container panel2 = layoutComponents("Center", Component.CENTER_ALIGNMENT);
       
        //Change Username and Password
        add(userPass());
        
        //Change avatar
        add(avatar(vframe));
        
        //Save file
        add(save());
        
        setBackground(Color.decode("#FFFFFF"));
	
	}
	
	 private JComponent fields() {
		 	JPanel panel = new JPanel();
	        JPanel form = new JPanel(new SpringLayout());
	        panel.setBackground(null);
	        form.setBackground(null);
	        
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
	        
	        JButton button = new JButton("Save chages");
	        
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
	 
	 public JPanel userPass(){
		 JPanel userPass = new JPanel();
	        userPass.setBorder(BorderFactory.createTitledBorder(null, "Change Username and Password", 
	        		TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial",Font.PLAIN,20), 
	        		Color.decode("333333")));//("Change Username and Password") title);
		    BoxLayout layoutUP = new BoxLayout(userPass, BoxLayout.Y_AXIS);
		    userPass.setLayout(layoutUP);
		    userPass.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.50), (int)(ViewFrame.getFrameHeight()*0.283)));//500,170));
		    userPass.setBackground(null);
		    
		    JComponent fields = fields();
		    fields.setBackground(null);
		    userPass.add(fields);
		    
		    return userPass;
	 } 
	 
	 public JPanel avatar(ViewFrame vframe){
		 JPanel avatar = new JPanel();
	        avatar.setBorder(BorderFactory.createTitledBorder(null, "Change avatar", 
	        		TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial",Font.PLAIN,20), 
	        		Color.decode("333333")));//("Change avatar") title);
	        BoxLayout layoutAv = new BoxLayout(avatar, BoxLayout.X_AXIS);
		    avatar.setLayout(layoutAv);
		    avatar.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.50), (int)(ViewFrame.getFrameHeight()*0.33)));//500,200));
		    avatar.setBackground(null);
		    
		    ImagePanel imgP = new ImagePanel(vframe);
		    imgP.setBackground(null);
		    avatar.add(Box.createRigidArea(new Dimension(60,0)));
		    avatar.add(imgP, BorderLayout.WEST);
		    
		    JButton up = new JButton("Upload");
		    JButton del = new JButton("Delete");
		    
		    Box buttonBox = Box.createVerticalBox();
		    buttonBox.add(up);
		    buttonBox.add(Box.createRigidArea(new Dimension(0,40)));
		    buttonBox.add(del);
		    avatar.add(buttonBox, BorderLayout.EAST);
		    
		    avatar.add(Box.createRigidArea(new Dimension(60,0)));
		    
		    return avatar;
	 }
	 
	 public JPanel save(){
		 JPanel save = new JPanel();
	        save.setBorder(BorderFactory.createTitledBorder(null, "Save file", 
	        		TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial",Font.PLAIN,20), 
	        		Color.decode("333333")));//("Change avatar") title);
	        BoxLayout layoutAv = new BoxLayout(save, BoxLayout.X_AXIS);
		    save.setLayout(layoutAv);
		    save.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.50), (int)(ViewFrame.getFrameHeight()*0.167)));//500,100));
		    save.setBackground(null);
		    
		   
		    JButton imp = new JButton("Import");
		    JButton exp = new JButton("Export");
		    JButton del = new JButton("Delete");
		    
		    Box buttonBox = Box.createHorizontalBox();
		    save.add(Box.createRigidArea(new Dimension(70,0)));
		    buttonBox.add(imp);
		    buttonBox.add(Box.createRigidArea(new Dimension(70,0)));
		    buttonBox.add(exp);
		//    save.add(buttonBox, BorderLayout.WEST);
		    buttonBox.add(Box.createRigidArea(new Dimension(70,0)));
		    buttonBox.add(del);
		    
		    save.add(buttonBox, BorderLayout.CENTER);
		    
		    return save;
	 }
}
