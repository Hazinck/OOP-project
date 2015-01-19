package oop.voetbalmanager.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class ScrollPanel extends JScrollPane{
	
	private JTable table;
	private Object[][] koopButtons;
	public ScrollPanel(int length, Object[][] data, String[] columnNames){//String title, String description, 
		
		
		DefaultTableModel model = new DefaultTableModel(data, columnNames);//;col,6
//		 {
//			 public Class getColumnClass(int columnIndex) {
//		          return String.class;
//		        }
//		 };
//		 model.setDataVector(
//				 new Object[length][1] , new Object[] { "<html><center>"+title+"<br>"+description });
		table=new JTable(model){
			@Override
			public boolean isCellEditable(int row, int col) {
				if(col==2){
					return true;
				}
				return false;
			}
			public Class getColumnClass(int column){
                return getValueAt(0, column).getClass();
            }
		};   
		
		
		
	//	table.setValueAt("Balans: ",0,0);
	//	table.setValueAt("Speeldag: ",1,0);
	//	table.setValueAt("Punten: ",2,0);
	//	table.setValueAt("Ranking: ",3,0);
	//	table.setValueAt("Volgende \ntegenstander: ",4,0);
		
		table.setRowHeight(table.getRowHeight() * 8);//110);//
		table.setDefaultRenderer(String.class, new MultiLineCellRenderer());
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		if(table.getColumnCount()==3){
			table.getColumnModel().getColumn(0).setPreferredWidth(110);//345);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.340) - 240);
		}else if(table.getColumnCount()==1){
			table.getColumnModel().getColumn(0).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.348));//345);
		}else{
			table.getColumnModel().getColumn(0).setPreferredWidth(110);//(int)(ViewFrame.getFrameWidth()*0.348));//345);
			table.getColumnModel().getColumn(1).setPreferredWidth((int)(ViewFrame.getFrameWidth()*0.340) - 120);
		}
//		JScrollPane	pane = new JScrollPane(){
//			@Override
//			public Dimension getPreferredSize() {
//			int w = (int)(ViewFrame.getFrameWidth()*0.35);//350;
//			int h = (int)(ViewFrame.getFrameHeight()*0.90);//500;// * percent / 100;
//			return new Dimension(w,h);
//			}
//		};
		if(data[0].length==3){
			 table.getColumnModel().getColumn(2).setCellRenderer( new ButtonRenderer());
			 table.getColumnModel().getColumn(2).setCellEditor(
			        new ButtonEditor(new JCheckBox()));
		}
		getViewport().setBackground(Color.WHITE);//pane.
		getViewport().add(table);//pane.
		
		
	//	return pane;
	}
	@Override
	public Dimension getPreferredSize() {
		int w = (int)(ViewFrame.getFrameWidth()*0.35);//350;
		int h = (int)(ViewFrame.getFrameHeight()*0.90);//500;// * percent / 100;
		return new Dimension(w,h);
	}
/*	@Override
	public Dimension getPreferredSize() {
	Dimension d = getParent().getSize();
	int w = d.width * 20 / 100;
	int h = d.height;// * percent / 100;
	return new Dimension(w,h);
	}
*/
	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}
	/**
	 * @return the koopButtons
	 */
	public Object[][] getKoopButtons() {
		return koopButtons;
	}
	/**
	 * @param koopButtons the koopButtons to set
	 */
	public void setKoopButtons(Object[][] koopButtons) {
		this.koopButtons = koopButtons;
	}
	
	
	
	
	
	/**
	 * @version 1.0 11/09/98
	 */

	class ButtonRenderer extends JButton implements TableCellRenderer {

	  public ButtonRenderer() {
	    setOpaque(true);
	  }

	  public Component getTableCellRendererComponent(JTable table, Object value,
	      boolean isSelected, boolean hasFocus, int row, int column) {
	    if (isSelected) {
	      setForeground(table.getSelectionForeground());
	      setBackground(table.getSelectionBackground());
	    } else {
	      setForeground(table.getForeground());
	      setBackground(UIManager.getColor("Button.background"));
	    }
	    setText((value == null) ? "" : value.toString());
	    return this;
	  }
	}

	/**
	 * @version 1.0 11/09/98
	 */

	class ButtonEditor extends DefaultCellEditor {
	  protected JButton button;

	  private String label;

	  private boolean isPushed;

	  public ButtonEditor(JCheckBox checkBox) {
	    super(checkBox);
	    button = new JButton();
	    button.setOpaque(true);
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        fireEditingStopped();
	      }
	    });
	  }

	  public Component getTableCellEditorComponent(JTable table, Object value,
	      boolean isSelected, int row, int column) {
	    if (isSelected) {
	      button.setForeground(table.getSelectionForeground());
	      button.setBackground(table.getSelectionBackground());
	    } else {
	      button.setForeground(table.getForeground());
	      button.setBackground(table.getBackground());
	    }
	    label = (value == null) ? "" : value.toString();
	    button.setText(label);
	    isPushed = true;
	    return button;
	  }

	  public Object getCellEditorValue() {
	    if (isPushed) {
	      // 
	      // koop Speler Functie hierin zetten
	      JOptionPane.showMessageDialog(button, label + " kopen");
	      // System.out.println(label + ": Ouch!");
	    }
	    isPushed = false;
	    return new String(label);
	  }

	  public boolean stopCellEditing() {
	    isPushed = false;
	    return super.stopCellEditing();
	  }

	  protected void fireEditingStopped() {
	    super.fireEditingStopped();
	  }
	}
}
