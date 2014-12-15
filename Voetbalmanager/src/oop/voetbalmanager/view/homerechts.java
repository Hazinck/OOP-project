package oop.voetbalmanager.view;
import java.awt.Color;
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


public class homerechts extends JPanel {
	private JTextPane titel;
	private JTextPane rankings;
	public homerechts(){
//		GridBagLayout gl=new GridBagLayout();
//		GridBagConstraints c= new GridBagConstraints();
		setLayout(null);
		
		titel=new JTextPane();
		titel.setBorder(BorderFactory.createLineBorder(Color.black));
		StyledDocument tit=titel.getStyledDocument();
		Style defaul=StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
		Style large=titel.addStyle("large", defaul);
	    StyleConstants.setFontSize(large, 25);
	    StyleConstants.setBold(large, true);
	    String rank="Ranking\n";
	    try{
	    	tit.insertString(0, rank, large);
	    }catch(Exception e){
	    	System.out.println("Something went wrong");
	    }
	    titel.setEditable(false);
		
	    rankings=new JTextPane();
	    rankings.setBorder(BorderFactory.createLineBorder(Color.black));
		StyledDocument doc=rankings.getStyledDocument();
		Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
	    Style regular = doc.addStyle( "regular", def );
		StyledDocument ranking=rankings.getStyledDocument();
		String puntentotaal="1. Ajax 69\n2. PSV 21";
		try{
			ranking.insertString(doc.getLength(), puntentotaal, regular );
		}catch(Exception e){
			System.out.println("Something went wrong");
		}
		rankings.setEditable(false);
//		c.fill=GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.gridx = 0;
//		c.gridy = 0;
		add(titel);//,c);
		titel.setBounds(0, 10,
				(int)(ViewFrame.getFrameWidth()*0.16), (int)(ViewFrame.getFrameHeight()*0.10));
//		c.ipady = (int)(ViewFrame.getFrameHeight()*0.667);//400;      
//		c.gridx = 0;
//		c.gridy = 1;
		add(rankings);//, c);
		rankings.setBounds(0, (int)(ViewFrame.getFrameHeight()*0.10+10) ,
				(int)(ViewFrame.getFrameWidth()*0.16), (int)(ViewFrame.getFrameHeight()*0.80));
	}
	/**
	 * @return the rankings
	 */
	public JTextPane getRankings() {
		return rankings;
	}
	/**
	 * @param rankings the rankings to set
	 */
	public void setRankings(JTextPane rankings) {
		this.rankings = rankings;
	}
}
