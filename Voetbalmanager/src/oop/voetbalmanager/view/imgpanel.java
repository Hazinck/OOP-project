package oop.voetbalmanager.view;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class imgpanel extends JPanel {

  private Image backgroundImage;

  public imgpanel(String fileName)  {
    try{
    	backgroundImage = ImageIO.read(new File(fileName));
    }catch(IOException e){
    	System.out.println("Failed loading image");
    }
    }
  

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(backgroundImage, 0, 0, this);
  }
}