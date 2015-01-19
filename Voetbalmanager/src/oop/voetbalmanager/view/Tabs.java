package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

//tweede panel waar alle andere panels(tabs) in zitten
public class Tabs extends JPanel{
		private JButton button = new JButton("Terug");;
		private Table table;
		
		private ViewFrame viewFrame;
		
		//Tabs(ViewFrame, Home, Team, Competition, Profile and Settings)
		public Tabs(ViewFrame viewFrame, Home home, TeamPanel teamPanel, Competition comp, PandS ps){
			this.viewFrame = viewFrame;
		    setBackground(Color.green);
		    //setLayout(new GridLayout());
		    setLayout(new BorderLayout());
		    
		    //tabs panel toevoegen aan ViewFrame
		    viewFrame.getControlPanel().add(this);  
		    
		    //table
		  /*Table*/ table = new Table();
		    table.start(viewFrame);//.getTeamNaam(), viewFrame.getUsername()
		    //table toevoegen
		    add(table, BorderLayout.LINE_START);
		    
		    //jtabbedpane aanmaken
		    JTabbedPane tabbedPane = new JTabbedPane();
		    
		    //4 andere panels hieraan toevoegen als tabs: ("Title", panel)
		    tabbedPane.add("Home",home);
		    tabbedPane.add("Team",teamPanel);
		    tabbedPane.add("Competition",comp);
		    tabbedPane.add("Profile and settings",ps);
		    tabbedPane.add("Sponsors", sponsors());
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
			sponsorPane.setLayout(new GridLayout(3, 2, 20, 20));
			
			JCheckBox tuDelft = new JCheckBox("Sponsor1");
			tuDelft.addItemListener(new CheckBoxListener(tuDelft)); 
			
			JLabel iconDelft =  new JLabel();
			iconDelft.setIcon(new ImageIcon("images/sponsors/TUDelft.jpg"));
			sponsorPane.add(tuDelft);
			sponsorPane.add(iconDelft);
			
			JCheckBox mac = new JCheckBox("Sponsor2");
			mac.addItemListener(new CheckBoxListener(mac)); 
			JLabel iconMac =  new JLabel();
			iconMac.setIcon(new ImageIcon("images/sponsors/McDonald's.jpg"));
			sponsorPane.add(mac);
			sponsorPane.add(iconMac);
			
			JCheckBox ing = new JCheckBox("Sponsor3");
			ing.addItemListener(new CheckBoxListener(ing)); 
			JLabel iconIng =  new JLabel();
			iconIng.setIcon(new ImageIcon("images/sponsors/ING.jpg"));
			sponsorPane.add(ing);
			sponsorPane.add(iconIng);
			return sponsorPane;
		}
		
		 private class CheckBoxListener implements ItemListener{
			 	JCheckBox one;
			 	
			 	public CheckBoxListener(JCheckBox one){
			 		this.one = one;
			 	}
		        public void itemStateChanged(ItemEvent e) {
		            if(e.getSource()==one){
		                if(one.isSelected()) {
		                    System.out.println(one.getText() + " has been selected");
		                } else {System.out.println("nothing");}
		            }
		        }
		    }
}
