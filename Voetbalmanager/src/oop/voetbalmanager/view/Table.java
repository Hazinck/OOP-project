package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import oop.voetbalmanager.model.Bot;
import oop.voetbalmanager.model.Divisie;
import oop.voetbalmanager.model.User;

public class Table extends JPanel{
	
	//	String col[] = {"Team: ","/*Teamnaam*/"};
	private ViewFrame vframe;
	protected static ImagePanel img;
	private int speeldag;
	private JTable table;
	
	public void start(ViewFrame vframe){
		this.vframe = vframe;
	//	setLayout(new BorderLayout());
		setLayout(null);
		setBackground(Color.WHITE);
		setOpaque(false);
	//	Box main = Box.createVerticalBox();
		
		
		img = new ImagePanel(vframe);
		img.addNameLable();
		img.addLogoutButton();
		add(img);//main.add(img);
		img.setBounds(0, 0 ,(int)(ViewFrame.getFrameWidth()*0.25), (int)(ViewFrame.getFrameHeight()*0.50));
		
		JTable tableLeft = newTable(vframe);
		tableLeft.setOpaque(false);
		JScrollPane	pane = new JScrollPane(tableLeft);
		pane.getViewport().setOpaque(false);;
		pane.setOpaque(false);
		add(pane);//main.add(pane);
		
		int w = (int)(ViewFrame.getFrameWidth()*0.20);//d.width * 20 / 100;
	    //Positie op 20% van frame hoogte naar beneden
	    int topPos = img.getHeight()+10;
	    pane.setBounds(0, topPos ,
	    		w, ViewFrame.getFrameHeight()-topPos);
		
	//	add(main);
	//	setLocation(0,0);
	}
	@Override
	public Dimension getPreferredSize() {
		Dimension d = getParent().getSize();
		int w = (int)(ViewFrame.getFrameWidth()*0.20);//d.width * 20 / 100;
		int h = ViewFrame.getFrameHeight();//d.height;// * percent / 100;
		return new Dimension(w,h);
	}
	public JTable newTable(ViewFrame vframe){
		DefaultTableModel model;
		setOpaque(false);
		model = new DefaultTableModel()//;col,6
		 {
			 public Class getColumnClass(int columnIndex) {
		          return String.class;
		        }
		 };
		 model.setDataVector(
				 new Object[][] { 
						 { "Balans (mln.€): ", User.getTeam().getBudget() },
						 { "Speeldag: ", Divisie.getSpeeldag() }, 
						 { "Punten: ",  User.getTeam().getScore()}, 
						 { "Ranking: ", User.getTeam().getRank() }, 
						 { "Volgende \ntegenstander: ", Bot.getBotTeam().getNaam()} 
						 }, new Object[] { "Team", User.getTeam().getNaam() });
		table=new JTable(model){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
			
		};   
		
		
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
	//	table.setValueAt("Balans: ",0,0);
	//	table.setValueAt("Speeldag: ",1,0);
	//	table.setValueAt("Punten: ",2,0);
	//	table.setValueAt("Ranking: ",3,0);
	//	table.setValueAt("Volgende \ntegenstander: ",4,0);
		
		table.setRowHeight(table.getRowHeight() * 3);
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.20  * 0.60));//95
		table.getColumnModel().getColumn(1).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.20 * 0.39));//100
		
		return table;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
		g.setColor( new Color(0, 0, 0, 150) );
        g.fillRect(0, 0, getWidth(), getHeight());
         
	}
	
	public ImagePanel getImagePanel(){
		return img;
	}
	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
	/**
	 * @return the speeldag
	 */
	public int getSpeeldag() {
		return speeldag;
	}
	/**
	 * @param speeldag the speeldag to set
	 */
	public void setSpeeldag(int speeldag) {
		this.speeldag = speeldag;
	}
	
}


