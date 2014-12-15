package oop.voetbalmanager.view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel {
	
	private Image image;
    public BackgroundPanel(Image image) {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
          g.drawImage(image, 0, 0, null);
  }

}
