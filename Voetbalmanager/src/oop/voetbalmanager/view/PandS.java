package oop.voetbalmanager.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.io.FileUtils;

import oop.voetbalmanager.model.User;
import layout.SpringUtilities;


public class PandS extends JPanel{
	
	private JTextField userField, passField;
	private ImagePanel imgP;
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
	        
	        JButton button = new JButton("Save changes");
	        
	        button.addActionListener(new ActionListener(){
	        	public void actionPerformed(ActionEvent e){
	        		if(!(userField.getText().length()==0)){
	        			User.setNaam(userField.getText());
	        			ImagePanel.setNameLable(userField.getText());
	        		}
	        		else if(!(passField.getText().length()==0)){
	        			LoginPanel.setWachtwoord(passField.getText());
	        		}
	        	}
	        });
	        
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
		    
		    imgP = new ImagePanel(vframe);
		    avatar.add(Box.createRigidArea(new Dimension(60,0)));
		    avatar.add(imgP, BorderLayout.WEST);
		    imgP.setBackground(null);
		    
		    JButton up = new JButton("Upload");
		    JButton del = new JButton("Delete");
		    
		    up.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		JFileChooser upload=new JFileChooser();
		    		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    		        "JPG Images", "jpg");
		    		upload.setFileFilter(filter);
		    		int returnVal = upload.showOpenDialog(getParent());
		    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	       Image backgroundImage=null;
		    	       try{
		    	       	backgroundImage = ImageIO.read(new File(upload.getSelectedFile().getAbsolutePath()));
		    	       }catch(IOException io){
		    	       	System.out.println("Failed loading image");
		    	       }
		    	       imgP.setImage(backgroundImage);
		    	      Table.img.setImage(backgroundImage);
		    	      repaint();
		    	    }
		    	}
		    });
		    
		    del.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		Image backgroundImage=null;
		    	       try{
		    	       	backgroundImage = ImageIO.read(new File("images/user_default.png"));
		    	       }catch(IOException io){
		    	       	System.out.println("Failed loading image");
		    	       }
		    		imgP.setImage(backgroundImage);
		    		Table.img.setImage(backgroundImage);
		    		repaint();
		    	}
		    });
		    
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
		    
		    imp.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		JFileChooser importeer=new JFileChooser();
		    		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		    		        "XML saves", "xml");
		    		importeer.setFileFilter(filter);
		    		int returnVal = importeer.showOpenDialog(getParent());
		    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	    	File src=new File(importeer.getSelectedFile().getAbsolutePath());
		    	    	File dest=new File(System.getProperty("user.dir") + "/saved/"+importeer.getSelectedFile().getName());
		    	    	System.out.println(importeer.getSelectedFile().getAbsolutePath());
		    	    	try {
		    	    	    FileUtils.copyFile(src, dest);
		    	    	} catch (IOException io) {
		    	    	    System.out.println("Failed copying the save file");
		    	    	}
		    	    }
		    	}
		    });
		    
		    exp.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		final LoadGamePanel export=new LoadGamePanel();
		    		for(int i = 0; i < export.getLoadButtons().size(); i++){
		    			final int idx = i;
		    			ActionListener actionListener = new ActionListener(){
		    				public void actionPerformed(ActionEvent e){
		    					JFileChooser ex=new JFileChooser();
		    					ex.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		    					ex.setAcceptAllFileFilterUsed(false);
		    					if (ex.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
		    						File src=new File(System.getProperty("user.dir") + "/saved/"+export.getSaveFiles().get(idx)+".xml");
		    						File dest=new File(ex.getSelectedFile().getAbsolutePath()+"/"+export.getSaveFiles().get(idx)+".xml");
		    						try {
		    		    	    	    FileUtils.copyFile(src, dest);
		    		    	    	} catch (IOException io) {
		    		    	    	    System.out.println("Failed copying the save file");
		    		    	    	}
		    					}
		    				}
		    			};
		    		export.getLoadButtons().get(idx).addActionListener(actionListener);
		    		}SaveDialog.loadGamePopup(export);
		    	}
		    });
		    
		    del.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		final LoadGamePanel delete=new LoadGamePanel();
		    		for(int i = 0; i < delete.getLoadButtons().size(); i++){
		    			final int idx = i;
		    			final Path save= Paths.get(System.getProperty("user.dir") + "/saved/"+delete.getSaveFiles().get(idx)+".xml");
		    			ActionListener actionListener = new ActionListener(){
		    				public void actionPerformed(ActionEvent e){
		    					try{
		    						Files.delete(save);
		    						}catch(Exception d){
		    							System.out.println("Failed deleting save file");
		    						}
		    					}
		    			};
		    			delete.getLoadButtons().get(idx).addActionListener(actionListener);
		    		}
		    		SaveDialog.loadGamePopup(delete);
		    	}
		    });
		    
		    Box buttonBox = Box.createHorizontalBox();
		    save.add(Box.createRigidArea(new Dimension((int)(ViewFrame.getFrameWidth()*0.085),0)));//70
            
		    buttonBox.add(imp);
		    buttonBox.add(Box.createRigidArea(new Dimension((int)(ViewFrame.getFrameWidth()*0.085),0)));
		    buttonBox.add(exp);
		//    save.add(buttonBox, BorderLayout.WEST);
		    buttonBox.add(Box.createRigidArea(new Dimension((int)(ViewFrame.getFrameWidth()*0.085),0)));
		    buttonBox.add(del);
		    
		    save.add(buttonBox, BorderLayout.CENTER);
		    
		    return save;
	 }
}
