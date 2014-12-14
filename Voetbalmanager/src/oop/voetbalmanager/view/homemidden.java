package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.User;
public class homemidden extends JPanel {
	private JTextPane scores;
	private JTextPane goals;
	private imgpanel image;
	private JButton playButton;
	private String gameResult;
	
	public homemidden(){
		playButton = new JButton("Play!");
		add(playButton);
		playButton.setBounds((int)(ViewFrame.getFrameWidth()*0.45), (int)(ViewFrame.getFrameHeight()*0.80),
	    		100, 30);
		
		GridBagLayout gl=new GridBagLayout();
		GridBagConstraints c= new GridBagConstraints();
		setLayout(gl);
		
		scores=new JTextPane();
		StyledDocument score=scores.getStyledDocument();
		Style defaul = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
	    Style normal = score.addStyle( "regular", defaul );
		Style large =score.addStyle("large",defaul);
		StyleConstants.setFontSize( large, 35 );
		StyleConstants.setBold(large, true);
		String vs=User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam();
		try{
			score.insertString(0, vs, large);
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		scores.setEditable(false);
		goals=new JTextPane();
		goals.setBorder(BorderFactory.createLineBorder(Color.black));
		StyledDocument goal=goals.getStyledDocument();
	//	String answer="Huntelaar 29e minuut goal\n Huntelaar gele kaart";
		Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
	    Style regular = goal.addStyle( "regular", def );
		try{
			goal.insertString(0,gameResult, regular);
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		goals.setEditable(false);
		image=new imgpanel("images/football.jpg");
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(scores,c);
		c.ipady = (int)(ViewFrame.getFrameHeight()*0.167);//100;      
		c.ipadx = (int)(ViewFrame.getFrameWidth()*0.5);//468;
		c.gridx = 0;
		c.gridy = 1;
		add(goals,c);
		c.ipady= (int)(ViewFrame.getFrameHeight()*0.6);//300;*0.5
		c.gridx = 0;
		c.gridy = 3;
		add(image,c);
		
		
	}



	/**
	 * @return the playButton
	 */
	public JButton getPlayButton() {
		return playButton;
	}



	/**
	 * @return the goals
	 */
	public JTextPane getGoals() {
		return goals;
	}



	/**
	 * @return the scores
	 */
	public JTextPane getScores() {
		return scores;
	}




	
}
