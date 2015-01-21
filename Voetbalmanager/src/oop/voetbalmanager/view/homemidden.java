package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.User;
public class homemidden extends JPanel {
	private JLabel scores;
	private JTextPane goals;
//	private imgpanel image;
	private JButton playButton;
	private String gameResult;
	
	public homemidden(){
//		GridBagLayout gl=new GridBagLayout();
//		GridBagConstraints c= new GridBagConstraints();
		setLayout(null);
		setBackground(Color.magenta);
		setOpaque(false);
		scores=new JLabel(User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam()){
			@Override
		    protected void paintComponent(Graphics g) {
				g.setColor( new Color(0, 0, 0, 150) );
		        g.fillRect(0, 0, getWidth(), getHeight());
		         
		    	super.paintComponent(g);
			}
		};
		scores.setFont(new Font("Arial Bold",Font.PLAIN,45));
		scores.setForeground(Color.WHITE);
		scores.setAlignmentY(CENTER_ALIGNMENT);;
//		StyledDocument score=scores.getStyledDocument();
//		Style defaul = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
//	    Style normal = score.addStyle( "regular", defaul );
//		Style large =score.addStyle("large",defaul);
//		StyleConstants.setFontSize( large, 35 );
//		StyleConstants.setBold(large, true);
//		String vs=User.getTeam().getNaam() + " VS " + Bot.getBotTeam().getNaam();
//		try{
//			score.insertString(0, vs, large);
//		}catch(Exception e){
//			System.out.println("Something went wrong");
//		}
//		scores.setEditable(false);
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
//		image=new imgpanel("images/football.jpg");
		
//		c.fill=GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 0;
		add(scores);//,c);
		scores.setBounds((int)(ViewFrame.getFrameWidth()*0.01), 10 ,
				(int)(ViewFrame.getFrameWidth()*0.62), (int)(ViewFrame.getFrameHeight()*0.20));
//		c.ipady = (int)(ViewFrame.getFrameHeight()*0.167);//100;      
//		c.ipadx = (int)(ViewFrame.getFrameWidth()*0.5);//468;
//		c.gridx = 0;
//		c.gridy = 1;
		JScrollPane jspGoals = new JScrollPane(goals);
		add(jspGoals);//,c);
		jspGoals.setBounds((int)(ViewFrame.getFrameWidth()*0.01), (int)(ViewFrame.getFrameHeight()*0.20) + 15 ,
				(int)(ViewFrame.getFrameWidth()*0.62), (int)(ViewFrame.getFrameHeight()*0.30));
//		c.ipady= (int)(ViewFrame.getFrameHeight()*0.6);//300;*0.5
//		c.gridx = 0;
//		c.gridy = 3;
//		add(image);//,c);
//		image.setBounds((int)(ViewFrame.getFrameWidth()*0.01), (int)(ViewFrame.getFrameHeight()*0.50) + 15 ,
//				(int)(ViewFrame.getFrameWidth()*0.40), (int)(ViewFrame.getFrameHeight()*0.40));
		
		playButton = new JButton("Play!");
		Font playFont = new Font("Serif", Font.BOLD, 25);
		playButton.setFont(playFont);
		add(playButton);
		playButton.setBounds((int)(ViewFrame.getFrameWidth()*0.50), (int)(ViewFrame.getFrameHeight()*0.70),
	    		150, 50);
		
		
	}

	@Override
    protected void paintComponent(Graphics g) {
		g.setColor( new Color(0, 0, 0, 150) );
        g.fillRect((int)(ViewFrame.getFrameWidth()*0.50)-50, (int)(ViewFrame.getFrameHeight()*0.70)-200, 
        		250, 400);
         
    	super.paintComponent(g);
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
	public JLabel getScores() {
		return scores;
	}




	
}
