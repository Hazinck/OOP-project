package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;

import oop.voetbalmanager.model.User;


public class TeamPanel extends JPanel{
	
	JPanel opstelling = new JPanel();
	
	public TeamPanel(){		
        add(spelerlijst());
        add(tactiek());
        setBackground(Color.decode("#FFFFFF"));
	}
	
	public JPanel spelerlijst() {
		JPanel spelerlijst = new JPanel();
		spelerlijst.setBorder(BorderFactory.createTitledBorder(null, "Spelerlijst", 
				TitledBorder.CENTER, TitledBorder.TOP));
		spelerlijst.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.30), (int)(ViewFrame.getFrameHeight()*0.90)));//300, 500));
		spelerlijst.setBackground(null);
		

		spelerlijst.add(new JLabel("Aanvallers"));
		spelerlijst.add(maakLijst(new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Middenvelders"));
		spelerlijst.add(maakLijst(new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Verdedigers"));
		spelerlijst.add(maakLijst(new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Keepers"));
		spelerlijst.add(maakLijst(new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 65)));
		
		return spelerlijst;
	}
	
	public JPanel opstelling() {
		
		opstelling.setBorder(BorderFactory.createTitledBorder(null, "Opstelling", 
				TitledBorder.CENTER, TitledBorder.TOP));
		opstelling.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.375), (int)(ViewFrame.getFrameHeight()*0.75)));//375, 370));
		opstelling.setBackground(null);
		
		JComboBox keuze = new JComboBox();
		keuze.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.25), (int)(ViewFrame.getFrameHeight()*0.04)));//250, 25));
		opstelling.add(keuze);
		
//		imgpanel image = new imgpanel("images/veldTopVert_small.png");
//		image.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.217), (int)(ViewFrame.getFrameHeight()*0.66)));//297,475));
		Opstelling opst = new Opstelling();
		opst.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.217), (int)(ViewFrame.getFrameHeight()*0.66)));//297,475));
		opstelling.add(opst);//image);
		System.out.println((int)(ViewFrame.getFrameWidth()) +" "+ (int)(ViewFrame.getFrameHeight()));//);
		return opstelling;
	}
	
	public JPanel tactiek() {
		JPanel tactiek = new JPanel();
		tactiek.setBorder(BorderFactory.createTitledBorder(null, "Tactiek", 
				TitledBorder.CENTER, TitledBorder.TOP));
		tactiek.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.45), (int)(ViewFrame.getFrameHeight()*0.90)));//400, 500));
		tactiek.setBackground(null);
		
		tactiek.add(opstelling());
		
		JSlider slider = new JSlider();
		slider.setBackground(null);
		slider.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.30), (int)(ViewFrame.getFrameHeight()*0.133)));//300,80));
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(new Integer(0), new JLabel("Verdedigend"));
		labels.put(new Integer(100), new JLabel("Aanvallend"));
		slider.setLabelTable(labels);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(25);
		slider.setPaintTicks(true);
		tactiek.add(slider);
		
		return tactiek;
	}
	
	/**
	 * geeft een nieuwe JScrollPane met een JList
	 * @param d		de grootte van de lijst
	 * @return		het nieuwe JScrollPane-object
	 */
	public JScrollPane maakLijst(Dimension d) {
		JScrollPane pane = new JScrollPane(new JList(),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(d);
		
		return pane;
	}
	

}

class Opstelling extends JPanel implements MouseListener, MouseMotionListener{

		  private Image backgroundImage;
		  private Dimension[] playerPos = new Dimension[11];
		  private Rectangle[] playerBounds = new Rectangle[11];
		  private JComboBox[] playersDDList = new JComboBox[11];//players Drop-down list
		  private int opstCode = 433;
		  private Image shirt;
		  private boolean dragging;
		  private int playerNum;
		  
		  public Opstelling()  {
			  String team = User.getTeam().getNaam();
			  
			  ArrayList<String> spelers = new ArrayList<String>();
			  for(int i = 0; i<11; i++){
				  spelers.add(User.getTeam().getSpelerList().get(i).getNaam());
			  }
			  
			  setLayout(null);
		    try{
		    	backgroundImage = ImageIO.read(new File("images/veldTopVert_small.png"));
		    	shirt = ImageIO.read(new File("images/shirts/"+team+".png"));
		    }catch(IOException e){
		    	System.out.println("Failed loading image");
		    }
		    
		    switch (opstCode){
		    case 433:
		    	playerPos[0] = new Dimension(145,395);//doelman
		    	playerPos[1] = new Dimension(75,355);//verdediger
		    	playerPos[2] = new Dimension(220,355);//verdediger
		    	playerPos[3] = new Dimension(35,330);//verdediger
		    	playerPos[4] = new Dimension(250,330);//verdediger
		    	playerPos[5] = new Dimension(150,240);//middenvelder
		    	playerPos[6] = new Dimension(50,210);//mid
		    	playerPos[7] = new Dimension(240,210);//mid
		    	playerPos[8] = new Dimension(150, 80);//aanvaller
		    	playerPos[9] = new Dimension(35,130);//aan
		    	playerPos[10] = new Dimension(270,130);//aan
		    	for(int i = 0; i<11; i++){
		    		playerBounds[i] = new Rectangle(playerPos[i].width -18, playerPos[i].height -20, 36, 40);
		    		
		    		playersDDList[i] = new JComboBox(spelers.toArray());
		    		playersDDList[i].setBounds((int)playerPos[i].getWidth()-50, (int)playerPos[i].getHeight()+20,
		    	    		100, 20);
		    		add(playersDDList[i]);
		    	}
		    }
		    
		    addMouseListener(this);
		    addMouseMotionListener(this);
		 }
		  

		  public void paintComponent(Graphics g) {
		    super.paintComponent(g);
		    g.drawImage(backgroundImage, 0, 0, 
					(int)(ViewFrame.getFrameWidth()*0.217), (int)(ViewFrame.getFrameHeight()*0.66), this);

		    for(int i = 0; i<11; i++){
		    	g.drawImage(shirt, (int)playerPos[i].getWidth()-18, (int)playerPos[i].getHeight()-20, this);
		    	
//		    	g.setColor(Color.white);
//		    	g.drawString(""+i, (int)playerPos[i].getWidth(), (int)playerPos[i].getHeight()+30);
		    }
		  }
		  
		  
		  @Override
		  public void mousePressed(MouseEvent event) {
			  Point point = event.getPoint();
			  System.out.println("mousePressed at point: " + point);
			  for(int i = 0; i<playerBounds.length; i++){
				  if (playerBounds[i].contains(point)){
					  System.out.println("mousePressed at player: " + i + "on point: " + point);
					  dragging = true;
					  playerNum = i;
				  }
			  }
		  }

		  @Override
		  public void mouseReleased(MouseEvent event) {
			  Point point = event.getPoint();
			  System.out.println("Player replaced to: " + point);
			  dragging = false;
			 
		  }

		  @Override
		  public void mouseDragged(MouseEvent event) {
			  if (dragging) {
				  Point point = event.getPoint();
	//			  // System.err.println("mouse drag to " + p);
	//			  curX = p.x;
	//			  curY = p.y;
//				  for(int i = 0; i<playerBounds.length; i++){
					  if (playerBounds[playerNum].contains(point)){
						  int x = xBounds(point);
						  int y = yBounds(point);
						  playerPos[playerNum] = new Dimension(x, y);
						  playerBounds[playerNum] = new Rectangle(playerPos[playerNum].width-18 ,playerPos[playerNum].height-20 ,36, 40);
						  playersDDList[playerNum].setBounds((int)playerPos[playerNum].getWidth()-50, (int)playerPos[playerNum].getHeight()+20,
				    	    		100, 20);
					  }
//				  }
				  repaint();
			  }
				
//			  if (dragging) {
//				  repaint();
//			  }
		  }
		  
		  public int xBounds(Point point){
			  int x =point.x;
			  if(x < 18){
				  x = 18;
			  }else if(x > (int)(ViewFrame.getFrameWidth()*0.217)-18){
				  x = (int)(ViewFrame.getFrameWidth()*0.217)-18;
			  }
			  return x;
		  }
		  
		  public int yBounds(Point point){
			  int y =point.y;
			  if(y < 20){
				  y = 20;
			  }else if(y > (int)(ViewFrame.getFrameHeight()*0.66)-20){
				  y = (int)(ViewFrame.getFrameHeight()*0.66)-20;
			  }
			  return y;
		  }
		  
		  @Override
		  public void mouseMoved(MouseEvent event) {
			 
			}


			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}


			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			} 
}