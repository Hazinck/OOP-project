import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.*;
public class homemidden extends JPanel {
	private JTextPane tabs;
	private JTextPane scores;
	private JTextPane goals;
	private imgpanel image;
	
	public homemidden(){
		GridBagLayout gl=new GridBagLayout();
		GridBagConstraints c= new GridBagConstraints();
		setLayout(gl);
		
		tabs=new JTextPane();
		tabs.setBorder(BorderFactory.createLineBorder(Color.black));
		StyledDocument tab=tabs.getStyledDocument();
		Style de = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
		Style big =tab.addStyle("large",de);
		StyleConstants.setFontSize( big, 25 );
		StyleConstants.setBold(big, true);
		String home="Home	 Team	 Competition   Profile and settings";
		try{
			tab.insertString(0,home, big);
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		scores=new JTextPane();
		StyledDocument score=scores.getStyledDocument();
		Style defaul = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
	    Style normal = score.addStyle( "regular", defaul );
		Style large =score.addStyle("large",defaul);
		StyleConstants.setFontSize( large, 35 );
		StyleConstants.setBold(large, true);
		String vs="PSV 5 VS 5 AJAX";
		try{
			score.insertString(0, vs, large);
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		
		goals=new JTextPane();
		goals.setBorder(BorderFactory.createLineBorder(Color.black));
		StyledDocument goal=goals.getStyledDocument();
		String answer="Huntelaar 29e minuut goal\n Huntelaar gele kaart";
		Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
	    Style regular = goal.addStyle( "regular", def );
		try{
			goal.insertString(0,answer, regular);
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		image=new imgpanel("C:/Users/HM/workspace/OOP project/Images/football.jpg");
		
		
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		add(tabs,c);
		c.gridx = 0;
		c.gridy = 1;
		add(scores,c);
		c.ipady = 100;      
		c.ipadx = 468;
		c.gridx = 0;
		c.gridy = 2;
		add(goals,c);
		c.ipady= 300;
		c.gridx = 0;
		c.gridy = 4;
		add(image,c);
		
	}
	
}
