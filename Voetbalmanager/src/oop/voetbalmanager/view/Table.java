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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Table extends JPanel{
	DefaultTableModel model;
	JTable table;
	//	String col[] = {"Team: ","/*Teamnaam*/"};

	
	public void start(ViewFrame vframe){
		setLayout(new BorderLayout());
		ImagePanel img = new ImagePanel(vframe);
		img.addNameLable();
		add(img, BorderLayout.PAGE_START);
		
	       
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
		table=new JTable(model)
		{@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}};   
		
	//	table.setValueAt("Balans: ",0,0);
	//	table.setValueAt("Speeldag: ",1,0);
	//	table.setValueAt("Punten: ",2,0);
	//	table.setValueAt("Ranking: ",3,0);
	//	table.setValueAt("Volgende \ntegenstander: ",4,0);
		
		table.setRowHeight(table.getRowHeight() * 3);
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);//teamnaam.length()*8
		
		JScrollPane	pane = new JScrollPane(table);
		pane.getViewport().setBackground(Color.WHITE);
		
		add(pane, BorderLayout.CENTER);
		setLocation(0,0);
	}
	@Override
	public Dimension getPreferredSize() {
	Dimension d = getParent().getSize();
	int w = d.width * 20 / 100;
	int h = d.height;// * percent / 100;
	return new Dimension(w,h);
	}
	
}

class ImagePanel extends JPanel{
	private BufferedImage image;
	
	JLabel naamLable = new JLabel();
	private String username;
    public ImagePanel(ViewFrame vframe) {
    	this.username = vframe.getUsername();
    	String imgName = vframe.getImgName();
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
		naamLable.setText("<html><body style='width: 150px'>"+username);
		naamLable.setForeground(Color.decode("333333"));
		setBackground(Color.WHITE);
		add(naamLable, BorderLayout.PAGE_START);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = image.getWidth(null);
        int h = image.getHeight(null);

        g.drawImage(image, 20, naamLable.getHeight()+5, 150, 150, this);//null); 
    }
	@Override
	public Dimension getPreferredSize() {
		int w = 150;
		int h = 250;// * percent / 100;
		return new Dimension(w,h);
	}
}
