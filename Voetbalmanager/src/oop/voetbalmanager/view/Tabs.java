package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import oop.voetbalmanager.model.User;

//tweede panel waar alle andere panels(tabs) in zitten
public class Tabs extends JPanel{
		private JButton button = new JButton("Terug");;
		private Table table;
		private ArrayList<JCheckBox> sponsors = new ArrayList<JCheckBox>();
		private ArrayList<String> sponsorsChecked = new ArrayList<String>();
		private JPanel sponsorPane;
		
		private ViewFrame viewFrame;
		
		//Tabs(ViewFrame, Home, Team, Competition, Profile and Settings)
		public Tabs(ViewFrame viewFrame, Home home, TeamPanel teamPanel, Competition comp, PandS ps){
			this.viewFrame = viewFrame;
		    setBackground(Color.green);
		    //setLayout(new GridLayout());
		    setLayout(new BorderLayout());
		    setOpaque(false);
		    //tabs panel toevoegen aan ViewFrame
		    viewFrame.getControlPanel().add(this);  
		    
		    //table
		  /*Table*/ table = new Table();
		    table.start(viewFrame);//.getTeamNaam(), viewFrame.getUsername()
		    //table toevoegen
		    add(table, BorderLayout.LINE_START);
		    
		    //jtabbedpane aanmaken
		    JTabbedPane tabbedPane = new JTabbedPane();
		    tabbedPane.setOpaque(false);
		    
		    sponsorPane = sponsors();
		    //5 andere panels hieraan toevoegen als tabs: ("Title", panel)
		    tabbedPane.add("Home",home);
		    tabbedPane.add("Team",teamPanel);
		    tabbedPane.add("Competition",comp);
		    tabbedPane.add("Sponsors", sponsorPane);
		    tabbedPane.add("Profile and settings",ps);
		  //jtabbedpane toevoegen aan deze panel(Tabs)
		    add(tabbedPane,BorderLayout.CENTER);

		}  
		
		
		
		public void showThis(JPanel loginPanel){
			loginPanel.setVisible(false);
			this.setVisible(true);
		}

		/**
		 * @return the table
		 */
		public Table getTable() {
			return table;
		}
	
		
		public JPanel sponsors(){			
			JPanel sponsorPane = new JPanel();
			sponsorPane.setLayout(new GridLayout(7, 2, 20, 20));
			sponsorPane.setOpaque(false);
			
			File folder = new File( System.getProperty("user.dir") +"/images/sponsors");
		    for (final File fileEntry : folder.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	sponsors();
		        } else{
		            String sponsorImgFile = fileEntry.getName();
		            String sponsorNaam = sponsorImgFile.replaceAll(".jpg", "");
		            
		            if(!sponsorNaam.equals("eredivisie")){
			            JCheckBox spCB = new JCheckBox(sponsorNaam){
							@Override
						    protected void paintComponent(Graphics g) {
								g.setColor( new Color(0, 0, 0, 150) );
						        g.fillRect(0, 0, getWidth(), getHeight());
						    	super.paintComponent(g);
						         
							}};
			            spCB.addItemListener(new CheckBoxListener(spCB)); 
						spCB.setOpaque(false);
			            spCB.setForeground(Color.WHITE);
						
						JLabel iconSponsor =  new JLabel();
						iconSponsor.setIcon(new ImageIcon("images/sponsors/"+sponsorImgFile));
						sponsorPane.add(spCB);
						sponsorPane.add(iconSponsor);
			            
			            sponsors.add(spCB);
		            }
		        }
		    }
			
		    for(int i =0; i<sponsors.size(); i++){
		    	 if(User.getTeam().getScore()<(i+1)*10){
		    		 sponsors.get(i).setEnabled(false);
		    		 sponsors.get(i).setText("<html><body>"+sponsors.get(i).getText() + "<br><i>Team score is te laag, je hebt ten minste "+(int)(i+1)*10+" punten nodig.</i>");;
		    	 }
		    }
//			JCheckBox tuDelft = new JCheckBox("Sponsor1");
//			tuDelft.addItemListener(new CheckBoxListener(tuDelft)); 
//			
//			JLabel iconDelft =  new JLabel();
//			iconDelft.setIcon(new ImageIcon("images/sponsors/TUDelft.jpg"));
//			sponsorPane.add(tuDelft);
//			sponsorPane.add(iconDelft);
//			
//			JCheckBox mac = new JCheckBox("Sponsor2");
//			mac.addItemListener(new CheckBoxListener(mac)); 
//			JLabel iconMac =  new JLabel();
//			iconMac.setIcon(new ImageIcon("images/sponsors/McDonald's.jpg"));
//			sponsorPane.add(mac);
//			sponsorPane.add(iconMac);
//			
//			JCheckBox ing = new JCheckBox("Sponsor3");
//			ing.addItemListener(new CheckBoxListener(ing)); 
//			JLabel iconIng =  new JLabel();
//			iconIng.setIcon(new ImageIcon("images/sponsors/ING.jpg"));
//			sponsorPane.add(ing);
//			sponsorPane.add(iconIng);
			return sponsorPane;
		}
		
		 private class CheckBoxListener implements ItemListener{
			 	JCheckBox one;
			 	
			 	public CheckBoxListener(JCheckBox one){
			 		this.one = one;
			 	}
		        public void itemStateChanged(ItemEvent e) {
		            if(e.getSource()==one){
		            	String text = one.getText().replaceAll("<html><body>", "");
		            	if(text.contains("<br>")){
		            		text = text.split("<br>")[0];
		            	}
		                if(one.isSelected()) {
		                    System.out.println(text + " has been selected");//one.getText()
		                    sponsorsChecked.add(text);//one.getText()
		                } else {
		                	System.out.println(text + " has been DEselected");
		                	sponsorsChecked.remove(text);
		                }
		                System.out.println(sponsorsChecked);
		            }
		        }
		    }

		/**
		 * @return the sponsorsChecked
		 */
		public ArrayList<String> getSponsorsChecked() {
			return sponsorsChecked;
		}



		/**
		 * @return the sponsorPane
		 */
		public JPanel getSponsorPane() {
			return sponsorPane;
		}



		/**
		 * @return the sponsors
		 */
		public ArrayList<JCheckBox> getSponsors() {
			return sponsors;
		}



		/**
		 * @param sponsors the sponsors to set
		 */
		public void setSponsors(ArrayList<JCheckBox> sponsors) {
			this.sponsors = sponsors;
		}
}
