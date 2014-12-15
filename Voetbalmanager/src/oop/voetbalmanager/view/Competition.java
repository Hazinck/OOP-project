package oop.voetbalmanager.view;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Competition extends JPanel{

	private ViewFrame vframe;
	
	public Competition(ViewFrame vframe){
		this.vframe = vframe;
        setBackground(Color.WHITE);
        
        Box main = Box.createHorizontalBox();
        
        main.add(Box.createRigidArea(new Dimension(40,0)));
        main.add(createTable("Ranking", "Uitgebreide ranking met doelpunten saldo etc"));
        main.add(Box.createRigidArea(new Dimension(40,0)));
        main.add(createTable("Transferlijst","(spelers die te koop worden gezet <br>of alle spelers van andere <br>teams worden hier geshowt en je kan bieden)"));
        main.add(Box.createRigidArea(new Dimension(40,0)));
        
        add(main);
	}
	
	public JScrollPane createTable(String title, String description){
		
		
		DefaultTableModel model = new DefaultTableModel()//;col,6
		 {
			 public Class getColumnClass(int columnIndex) {
		          return String.class;
		        }
		 };
		 model.setDataVector(
				 new Object[20][1] , new Object[] { "<html><center>"+title+"<br>"+description });
		JTable table=new JTable(model)
		{@Override
		public boolean isCellEditable(int row, int col) {
			return false;
		}};   
		
		table.setValueAt("Balans: ",0,0);
	//	table.setValueAt("Speeldag: ",1,0);
	//	table.setValueAt("Punten: ",2,0);
	//	table.setValueAt("Ranking: ",3,0);
	//	table.setValueAt("Volgende \ntegenstander: ",4,0);
		
		table.setRowHeight(table.getRowHeight() * 3);
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.348));//345);
	
		JScrollPane	pane = new JScrollPane(){
			@Override
			public Dimension getPreferredSize() {
			int w = (int)(ViewFrame.getFrameWidth()*0.35);//350;
			int h = (int)(ViewFrame.getFrameHeight()*0.90);//500;// * percent / 100;
			return new Dimension(w,h);
			}
		};
		pane.getViewport().setBackground(Color.WHITE);
		pane.getViewport().add(table);
		

		return pane;
	}
	@Override
	public Dimension getPreferredSize() {
	Dimension d = getParent().getSize();
	int w = d.width * 20 / 100;
	int h = d.height;// * percent / 100;
	return new Dimension(w,h);
	}
}
