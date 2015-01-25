package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;


public class homerechts extends JPanel {
//	private JTextPane titel;
	private JLabel rankings;
	public homerechts(){
//		GridBagLayout gl=new GridBagLayout();
//		GridBagConstraints c= new GridBagConstraints();
		setLayout(null);
		setOpaque(false);
//		titel=new JTextPane(){
//			@Override
//		    protected void paintComponent(Graphics g) {
//		    	super.paintComponent(g);
//				g.setColor( new Color(0, 0, 0, 150) );
//		        g.fillRect(0, 0, getWidth(), getHeight());
//			}
//		};
//		titel.setBorder(BorderFactory.createLineBorder(Color.black));
//		titel.setOpaque(false);
//		StyledDocument tit=titel.getStyledDocument();
//		Style defaul=StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
//		Style large=titel.addStyle("large", defaul);
//	    StyleConstants.setFontSize(large, 25);
//	    StyleConstants.setBold(large, true);
//	    StyleConstants.setForeground(large, Color.WHITE);
//	    String rank="Ranking\n";
//	    try{
//	    	tit.insertString(0, rank, large);
//	    }catch(Exception e){
//	    	System.out.println("Something went wrong");
//	    }
		JLabel rank = new JLabel("Ranking");
		rank.setFont(new Font("Arial Bold",Font.PLAIN,30));
		rank.setForeground(Color.WHITE);
//		titel.add(rank, BorderLayout.NORTH);
//	    titel.setEditable(false);
		rankings = new JLabel();
		rankings.setFont(new Font("Arial",Font.PLAIN,20));
		rankings.setForeground(Color.WHITE);
//	    rankings=new JTextPane();
//	    rankings.setOpaque(false);
////	    rankings.setBorder(BorderFactory.createLineBorder(Color.WHITE));
//		StyledDocument doc=rankings.getStyledDocument();
//		Style def = StyleContext.getDefaultStyleContext().getStyle( StyleContext.DEFAULT_STYLE );
//	    Style regular = doc.addStyle( "regular", def );
//	    rankings.setFont(new Font("Arial Bold",Font.PLAIN,15));
//	    rankings.setForeground(Color.WHITE);
//		StyledDocument ranking=rankings.getStyledDocument();
//		String puntentotaal="1. Ajax 69\n2. PSV 21";
//		try{
//			ranking.insertString(doc.getLength(), puntentotaal, regular );
//		}catch(Exception e){
//			System.out.println("Something went wrong");
//		}
//		rankings.setEditable(false);
//		c.fill=GridBagConstraints.HORIZONTAL;
//		c.weightx = 50;
//		c.gridx = 0;
//		c.gridy = 0;
		add(rank);//,c);
		rank.setBounds(10, 10,
				(int)(ViewFrame.getFrameWidth()*0.16), (int)(ViewFrame.getFrameHeight()*0.10));
//		c.ipady = (int)(ViewFrame.getFrameHeight()*0.667);//400;      
//		c.gridx = 0;
//		c.gridy = 1;
		add(rankings);//, c);
		rankings.setBounds(0, (int)(ViewFrame.getFrameHeight()*0.10) ,//+10
				(int)(ViewFrame.getFrameWidth()*0.16), (int)(ViewFrame.getFrameHeight()*0.80));
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		g.setColor( new Color(0, 0, 0, 150) );
        g.fillRect(0, 0, getWidth(), getHeight());
         
	}
	/**
	 * @return the rankings
	 */
	public JLabel getRankings() {
		return rankings;
	}
	/**
	 * @param rankings the rankings to set
	 */
	public void setRankings(JLabel rankings) {
		this.rankings = rankings;
	}
}
