package oop.voetbalmanager.spel2D;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;


public class VerslagPanel extends JPanel{
	private JTextArea verslag;
	public VerslagPanel(VeldPanel veldPanel){
		setOpaque(false);
		setBackground( new Color(0, 0, 0, 50) );
		
		verslag = new JTextArea(11, 35);
		verslag.setText("Live Verslag\n");
		verslag.setLineWrap(true);
		verslag.setEditable(false);
		verslag.setForeground(Color.white);
		verslag.setBackground(null);
		verslag.setOpaque(false);
		
		DefaultCaret caret = (DefaultCaret) verslag.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		JScrollPane	pane = new JScrollPane(verslag);
		pane.setBackground(null);
		pane.getViewport().setOpaque(false);
		pane.setBorder(null);
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	}
	
	@Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	/**
	 * @return the verslag
	 */
	public JTextArea getVerslag() {
		return verslag;
	}
	
}
