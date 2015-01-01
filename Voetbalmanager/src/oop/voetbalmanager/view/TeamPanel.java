package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

import oop.voetbalmanager.controller.Controller;
import oop.voetbalmanager.model.Driver;
import oop.voetbalmanager.model.Opstelling;
import oop.voetbalmanager.model.Team;
import oop.voetbalmanager.model.User;
import oop.voetbalmanager.model.XMLreader;


public class TeamPanel extends JPanel{
	
	private DefaultListModel<String> aanvallers = new DefaultListModel<String>();
	private DefaultListModel<String> middenvelders = new DefaultListModel<String>();
	private DefaultListModel<String> verdedigers = new DefaultListModel<String>();
	private DefaultListModel<String> keepers = new DefaultListModel<String>();
	private OpstellingPanel opst = new OpstellingPanel(this);
	private JButton opslaanButton = new JButton("Opstelling opslaan");
	private JButton wteamOpslaanButton = new JButton("Wedstrijdteam opslaan");
	private JSlider slider = new JSlider();
	private JComboBox opstellingKeuze;
	
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
		spelerlijst.add(maakLijst(aanvallers, new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Middenvelders"));
		spelerlijst.add(maakLijst(middenvelders, new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Verdedigers"));
		spelerlijst.add(maakLijst(verdedigers, new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 95)));
		
		spelerlijst.add(new JLabel("Keepers"));
		spelerlijst.add(maakLijst(keepers, new Dimension((int)(ViewFrame.getFrameWidth()*0.28), (int)(ViewFrame.getFrameHeight()*0.158))));//250, 65)));
		
		return spelerlijst;
	}
	
	public JPanel opstelling() {
		JPanel opstelling = new JPanel();
		
		opstelling.setBorder(BorderFactory.createTitledBorder(null, "Opstelling", 
				TitledBorder.CENTER, TitledBorder.TOP));
		opstelling.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.375), (int)(ViewFrame.getFrameHeight()*0.75)));//375, 370));
		opstelling.setBackground(null);
		
		opstellingKeuze = new JComboBox();
		for(Opstelling op: opst.getOpstellingen()){
			opstellingKeuze.addItem(op.getNaam());
		}
		opstellingKeuze.setSelectedItem(opst.getOpstelling().getNaam());
		opstellingKeuze.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.05), (int)(ViewFrame.getFrameHeight()*0.04)));//250, 25));
		opstelling.add(opstellingKeuze);
		
		
		opstelling.add(opslaanButton);
		opstelling.add(wteamOpslaanButton);
//		imgpanel image = new imgpanel("images/veldTopVert_small.png");
//		image.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.217), (int)(ViewFrame.getFrameHeight()*0.66)));//297,475));
		
		//opstelling veld
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
		
		
		slider.setBackground(null);
		slider.setPreferredSize(new Dimension((int)(ViewFrame.getFrameWidth()*0.30), (int)(ViewFrame.getFrameHeight()*0.133)));//300,80));
		Hashtable<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
		labels.put(new Integer(0), new JLabel("Verdedigend"));
		labels.put(new Integer(100), new JLabel("Aanvallend"));
		slider.setLabelTable(labels);
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(25);
		slider.setPaintTicks(true);
		slider.setValue(User.getWteam().getTactiek());
		tactiek.add(slider);
		
		return tactiek;
	}
	
	/**
	 * geeft een nieuwe JScrollPane met een JList
	 * @param d		de grootte van de lijst
	 * @return		het nieuwe JScrollPane-object
	 */
	public JScrollPane maakLijst(ListModel<String> model, Dimension d) {
		JScrollPane pane = new JScrollPane(new JList<String>(model),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setPreferredSize(d);
		
		return pane;
	}
	
	public void addAanvaller(String aanvaller){
		this.aanvallers.addElement(aanvaller);
	}
	
	public String[] getAanvaller() {
		return (String[])this.aanvallers.toArray();
	}
	
	public void removeAanvaller(String aanvaller){
		this.aanvallers.removeElement(aanvaller);
	}
	
	public void addMiddenvelder(String middenvelder){
		this.middenvelders.addElement(middenvelder);
	}
	
	public String[] getMiddenvelders() {
		return (String[])this.middenvelders.toArray();
	}
	
	public void removeMiddenvelder(String middenvelder){
		this.middenvelders.removeElement(middenvelder);
	}
	
	public void addVerdediger(String verdediger){
		this.verdedigers.addElement(verdediger);
	}
	
	public String[] getVerdedigers() {
		return (String[]) this.verdedigers.toArray();
	}
	
	public void removeVerdediger(String verdediger){
		this.verdedigers.removeElement(verdediger);
	}
	
	public void addKeeper(String keeper){
		this.keepers.addElement(keeper);
	}
	
	public String[] getKeepers() {
		return (String[]) this.keepers.toArray();
	}
	
	public void removeKeepers(String keeper){
		this.keepers.removeElement(keeper);
	}

	/**
	 * @return the opst
	 */
	public OpstellingPanel getOpst() {
		return opst;
	}

	/**
	 * @return the opslaan
	 */
	public JButton getOpslaanButton() {
		return opslaanButton;
	}
	

//}

	public class OpstellingPanel extends JPanel implements MouseListener, MouseMotionListener{
	
			  private Image backgroundImage;
			  private Dimension[] playerPos = new Dimension[11];
			  private Rectangle[] playerBounds = new Rectangle[11];
			  private JComboBox[] playersDDList = new JComboBox[11];//players Drop-down list
			  private Image shirt;
			  private boolean dragging;
			  private int playerNum;
			  private ArrayList<Opstelling> opstellingen;
			  private  XMLreader reader = new XMLreader();
			  private Opstelling opstelling;
			  
			  public OpstellingPanel(TeamPanel teamPanel)  {
				  //wedstrijdteam laden
				  opstellingen = reader.readOpstellingList(Driver.path);
				  String team = User.getTeam().getNaam();
				  
//				  ArrayList<String> spelers = new ArrayList<String>();
//				  for(int i = 0; i<11; i++){
//					  spelers.add(User.getTeam().getSpelerList().get(i).getNaam());
//				  }
				  
				  setLayout(null);
			    try{
			    	backgroundImage = ImageIO.read(new File("images/veldTopVert_small.png"));
			    	shirt = ImageIO.read(new File("images/shirts/"+team+".png"));
			    }catch(IOException e){
			    	System.out.println("Failed loading image");
			    }
			    
			    System.out.println(playersDDList.length);
			    opstToPlayerPos(opstellingKeuze);
			    addToPlayerPos();
//			    switch (opstCode){
//			    case 433:
//			    	playerPos[0] = new Dimension(145,395);//doelman
//			    	playerPos[1] = new Dimension(75,355);//verdediger
//			    	playerPos[2] = new Dimension(220,355);//verdediger
//			    	playerPos[3] = new Dimension(35,330);//verdediger
//			    	playerPos[4] = new Dimension(250,330);//verdediger
//			    	playerPos[5] = new Dimension(150,240);//middenvelder
//			    	playerPos[6] = new Dimension(50,210);//mid
//			    	playerPos[7] = new Dimension(240,210);//mid
//			    	playerPos[8] = new Dimension(150, 80);//aanvaller
//			    	playerPos[9] = new Dimension(35,130);//aan
//			    	playerPos[10] = new Dimension(270,130);//aan
//			    	
//			    }
			    
			    
			    addMouseListener(this);
			    addMouseMotionListener(this);
			 }
			
			  
			  public void addToPlayerPos(){
				  for(int i = 0; i<11; i++){
			    		playerBounds[i] = new Rectangle(playerPos[i].width -18, playerPos[i].height -20, 36, 40);
			    		
//			    		roteer(spelers);
			    		
			    		playersDDList[i] = new JComboBox();//spelers.toArray()
			    		playersDDList[i].setName(""+i);
			    		playersDDList[i].setBounds((int)playerPos[i].getWidth()-50, (int)playerPos[i].getHeight()+20,
			    	    		100, 20);

			    		
			    		add(playersDDList[i]);
			    	}
			  }
			  public void opstToPlayerPos(JComboBox keuze){
				  	int k = 0;
					
				  	if(keuze!=null){
				  		k = keuze.getSelectedIndex();
				  		opstelling = opstellingen.get(k);
				  	}else{
				  		opstelling = User.getWteam().getOpstelling();
				  	}

				//  	System.out.println(User.getTeam().getNaam());
				//	Dimension[] d = teamPanel.getOpst().getPlayerPos();
					for(int i = 0; i<playerPos.length; i++ ){
						int x = opstelling.getPosities().get(i).getX();
						int y = opstelling.getPosities().get(i).getY();
								
					//	System.out.println(opstelling.getPosities());
						playerPos[i] = new Dimension(x, y);
						if(keuze!=null){
							playerBounds[i].setBounds(x - 18, y - 20, 36, 40);
							playersDDList[i].setBounds(x-50, y+20, 100, 20);
						}
					}
				}
//			  public void roteer(ArrayList<String> list){
//					int n=list.size();
//					String temp = list.get(0);
//					for(int i=0; i<n-2; i++){
//						list.set(i, list.get(i+1));
//					}
//					list.set(n-2, temp);
//					//System.out.println(list);
//			  }
			  
			  public void paintComponent(Graphics g) {
			    super.paintComponent(g);
			    g.drawImage(backgroundImage, 0, 0, 
						(int)(ViewFrame.getFrameWidth()*0.217), (int)(ViewFrame.getFrameHeight()*0.66), this);
			    
			    
			    for(int i = 0; i < playersDDList.length; i++){
    	    		String name = (String)playersDDList[i].getSelectedItem();
    	    		String type = Controller.getSpelerByName(name).getType();
    	    		g.drawImage(shirt, (int)playerPos[i].getWidth()-18, (int)playerPos[i].getHeight()-20, this);
    	    		g.setColor(Color.white);
		    		g.setFont(new Font("default", Font.BOLD, 12));
		    		g.drawString(type, (int)playerPos[i].getWidth()-20, (int)playerPos[i].getHeight()-20);
			    }
//			    for(int i = 0; i<11; i++){
//			    	g.drawImage(shirt, (int)playerPos[i].getWidth()-18, (int)playerPos[i].getHeight()-20, this);
//			    	
//			    	if(i==0){
//			    		g.setColor(Color.blue);
//			    		g.setFont(new Font("default", Font.BOLD, 12));
//			    		g.drawString("Keeper", (int)playerPos[i].getWidth()-20, (int)playerPos[i].getHeight()-20);
//			    	}else if(i<5){
//			    		g.setColor(Color.blue);
//			    		g.setFont(new Font("default", Font.BOLD, 12));
//			    		g.drawString("Middenvelder", (int)playerPos[i].getWidth()-32, (int)playerPos[i].getHeight()-20);
//			    	}
//			    	else if(i<8){
//			    		g.setColor(Color.blue);
//			    		g.setFont(new Font("default", Font.BOLD, 12));
//			    		g.drawString("Vergediger", (int)playerPos[i].getWidth()-27, (int)playerPos[i].getHeight()-20);
//			    	}else if(i<11){
//			    		g.setColor(Color.blue);
//			    		g.setFont(new Font("default", Font.BOLD, 12));
//			    		g.drawString("Aanvaller", (int)playerPos[i].getWidth()-23, (int)playerPos[i].getHeight()-20);
//			    	}
//			    	
//			    }
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
						//  if (playerBounds[playerNum].contains(point)){
							  int x = xBounds(point);
							  int y = yBounds(point);
							  playerPos[playerNum] = new Dimension(x, y);
							  playerBounds[playerNum] = new Rectangle(playerPos[playerNum].width-18 ,playerPos[playerNum].height-20 ,36, 40);
							  playersDDList[playerNum].setBounds((int)playerPos[playerNum].getWidth()-50, (int)playerPos[playerNum].getHeight()+20,
					    	    		100, 20);
						//  }
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
				  }else if(x > this.getWidth() - 18){// (int)(ViewFrame.getFrameWidth()*0.217)-18){
					  x = this.getWidth() - 18;//(int)(ViewFrame.getFrameWidth()*0.217)-18;
				  }
				  return x;
			  }
			  
			  public int yBounds(Point point){
				  int y =point.y;
				  if(y < 20){
					  y = 20;
				  }else if(y > this.getHeight() - 20){
					  y = this.getHeight() - 20;//(int)(ViewFrame.getFrameHeight()*0.66)-20;
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
	
				/**
				 * @return the playersDDList
				 */
				public JComboBox[] getPlayersDDList() {
					return playersDDList;
				}

				/**
				 * @return the playerPos
				 */
				public Dimension[] getPlayerPos() {
					return playerPos;
				}


				/**
				 * @return the opstellingen
				 */
				public ArrayList<Opstelling> getOpstellingen() {
					return opstellingen;
				}


				/**
				 * @return the opstelling
				 */
				public Opstelling getOpstelling() {
					return opstelling;
				} 
	}


	/**
	 * @return the wteamOpslaanButton
	 */
	public JButton getWteamOpslaanButton() {
		return wteamOpslaanButton;
	}

	/**
	 * @return the slider
	 */
	public JSlider getSlider() {
		return slider;
	}

	/**
	 * @return the opstellingKeuze
	 */
	public JComboBox getOpstellingKeuze() {
		return opstellingKeuze;
	}
}