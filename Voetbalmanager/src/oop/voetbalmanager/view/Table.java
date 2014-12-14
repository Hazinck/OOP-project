package oop.voetbalmanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel{
	
	//	String col[] = {"Team: ","/*Teamnaam*/"};

	
	public void start(ViewFrame vframe){
	//	setLayout(new BorderLayout());
		setLayout(null);
		setBackground(Color.WHITE);
		
	//	Box main = Box.createVerticalBox();
		
		
		ImagePanel img = new ImagePanel(vframe);
		img.addNameLable();
		img.addLogoutButton();
		add(img);//main.add(img);
		img.setBounds(0, 0 ,(int)(ViewFrame.getFrameWidth()*0.25), (int)(ViewFrame.getFrameHeight()*0.50));
		
		JTable tableLeft = newTable(vframe);
		JScrollPane	pane = new JScrollPane(tableLeft);
		pane.getViewport().setBackground(Color.WHITE);

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
		JTable table;
		model = new DefaultTableModel()//;col,6
		 {
			 public Class getColumnClass(int columnIndex) {
		          return String.class;
		        }
		 };
		 model.setDataVector(
				 new Object[][] { 
						 { "Balans: ", "" },
						 { "Speeldag: " }, 
						 { "Punten: " }, 
						 { "Ranking: " }, 
						 { "Volgende \ntegenstander: " } 
						 }, new Object[] { "Team", vframe.getTeamNaam() });
		table=new JTable(model){
			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
			
		};   
		
	//	table.setValueAt("Balans: ",0,0);
	//	table.setValueAt("Speeldag: ",1,0);
	//	table.setValueAt("Punten: ",2,0);
	//	table.setValueAt("Ranking: ",3,0);
	//	table.setValueAt("Volgende \ntegenstander: ",4,0);
		
		table.setRowHeight(table.getRowHeight() * 3);
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.20  * 0.60));//95
		table.getColumnModel().getColumn(1).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.20 * 0.40));//100
		
		return table;
	}
	
}

class ImagePanel extends JPanel{
	private BufferedImage image;
	
	JLabel naamLable = new JLabel();
	private String username;
    public ImagePanel(ViewFrame vframe) {
    	this.username = vframe.getUsername();
    	String imgName = vframe.getImgName();
    	setLayout(null);
       try {                
    	   if(imgName == null || imgName.equals("")){
    		   image = ImageIO.read(new File(vframe.getImgPath() + "user_default.png"));
    	   }
    	   else{
    		   image = ImageIO.read(new File(vframe.getImgPath() + imgName));
    	   }
       } catch (IOException ex) {
            // handle exception...
       }
       
       
    }
    
    public void addNameLable(){
    	naamLable.setFont(new Font("Arial", Font.BOLD, 20)); 
		naamLable.setText("<html><body style='text-align: center; width: "+(int)(ViewFrame.getFrameWidth()*0.15)+"px'>"+username);//150px
		naamLable.setForeground(Color.decode("333333"));
		setBackground(Color.WHITE);
		add(naamLable);//, BorderLayout.PAGE_START);
		naamLable.setBounds(0, 0 ,(int)(ViewFrame.getFrameWidth()*0.20), (int)(ViewFrame.getFrameHeight()*0.15));//150
    }
    
    public void addLogoutButton(){
		JButton logout = new JButton("Logout and Save");
	//	add(Box.createRigidArea(new Dimension(0,(int)(ViewFrame.getFrameHeight()*0.66))));//image.getHeight()*2 + 30
		add(logout);
		logout.setBounds(0, (int)(ViewFrame.getFrameHeight()*0.40) ,(int)(ViewFrame.getFrameWidth()*0.20), 30);//150
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        int w = image.getWidth(null);
//        int h = image.getHeight(null);

        g.drawImage(image, ((int)(ViewFrame.getFrameWidth()*0.20)-150)/2, naamLable.getHeight(), 150, 150, this);//null); 
    }
	@Override
	public Dimension getPreferredSize() {
		int w = (int)(ViewFrame.getFrameWidth()*0.25);//150;
		int h = (int)(ViewFrame.getFrameHeight()*0.25);//250;// * percent / 100;
		return new Dimension(w,h);
	}

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return image;
	}
}
