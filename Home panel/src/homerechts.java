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
	private JButton logout;
	private JTextPane titel;
	private JTextPane rankings;
	public homerechts(){
		GridBagLayout gl=new GridBagLayout();
		GridBagConstraints c= new GridBagConstraints();
		setLayout(gl);
		logout=new JButton("Logout and save");
		
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
		
		c.gridx = 0;
		c.gridy = 0;
		add(logout, c);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		add(titel,c);
		c.ipady = 300;      
		c.gridx = 0;
		c.gridy = 2;
		add(rankings, c);
	}
}
