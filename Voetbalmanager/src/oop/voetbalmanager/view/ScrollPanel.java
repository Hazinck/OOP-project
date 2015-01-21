package oop.voetbalmanager.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class ScrollPanel extends JScrollPane{
	
	private JTable table;
	private Object[][] koopButtons;
	private DefaultTableModel model;
	
	public ScrollPanel(){
		
	}
			
			
	public ScrollPanel(int length, Object[][] data, String[] columnNames){//String title, String description, 
		
		
		model = new DefaultTableModel(data, columnNames);//;col,6
		
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
		
		table.setRowSelectionAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
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
			System.out.println("add delete");
			Action delete = new AbstractAction()
			{
			    public void actionPerformed(ActionEvent e)
			    {
			        JTable table = (JTable)e.getSource();
			        int modelRow = Integer.valueOf( e.getActionCommand() );
			        ((DefaultTableModel)table.getModel()).removeRow(modelRow);
			    }
			};
			 
			ButtonColumn buttonColumn = new ButtonColumn(table, delete, 2);
//			 table.getColumnModel().getColumn(2).setCellRenderer( new ButtonRenderer());
//			 table.getColumnModel().getColumn(2).setCellEditor(
//			        new ButtonEditor(new JCheckBox()));
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
	 *  The ButtonColumn class provides a renderer and an editor that looks like a
	 *  JButton. The renderer and editor will then be used for a specified column
	 *  in the table. The TableModel will contain the String to be displayed on
	 *  the button.
	 *
	 *  The button can be invoked by a mouse click or by pressing the space bar
	 *  when the cell has focus. Optionally a mnemonic can be set to invoke the
	 *  button. When the button is invoked the provided Action is invoked. The
	 *  source of the Action will be the table. The action command will contain
	 *  the model row number of the button that was clicked.
	 *
	 */
	class ButtonColumn extends AbstractCellEditor
		implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener{
		private JTable table;
		private Action action;
		private int mnemonic;
		private Border originalBorder;
		private Border focusBorder;

		private JButton renderButton;
		private JButton editButton;
		private Object editorValue;
		private boolean isButtonColumnEditor;

		/**
		 *  Create the ButtonColumn to be used as a renderer and editor. The
		 *  renderer and editor will automatically be installed on the TableColumn
		 *  of the specified column.
		 *
		 *  @param table the table containing the button renderer/editor
		 *  @param action the Action to be invoked when the button is invoked
		 *  @param column the column to which the button renderer/editor is added
		 */
		public ButtonColumn(JTable table, Action action, int column)
		{
			this.table = table;
			this.action = action;

			renderButton = new JButton();
			editButton = new JButton();
			editButton.setFocusPainted( false );
			editButton.addActionListener( this );
			originalBorder = editButton.getBorder();
			setFocusBorder( new LineBorder(Color.BLUE) );

			TableColumnModel columnModel = table.getColumnModel();
			columnModel.getColumn(column).setCellRenderer( this );
			columnModel.getColumn(column).setCellEditor( this );
			table.addMouseListener( this );
		}


		/**
		 *  Get foreground color of the button when the cell has focus
		 *
		 *  @return the foreground color
		 */
		public Border getFocusBorder()
		{
			return focusBorder;
		}

		/**
		 *  The foreground color of the button when the cell has focus
		 *
		 *  @param focusBorder the foreground color
		 */
		public void setFocusBorder(Border focusBorder)
		{
			this.focusBorder = focusBorder;
			editButton.setBorder( focusBorder );
		}

		public int getMnemonic()
		{
			return mnemonic;
		}

		/**
		 *  The mnemonic to activate the button when the cell has focus
		 *
		 *  @param mnemonic the mnemonic
		 */
		public void setMnemonic(int mnemonic)
		{
			this.mnemonic = mnemonic;
			renderButton.setMnemonic(mnemonic);
			editButton.setMnemonic(mnemonic);
		}

		@Override
		public Component getTableCellEditorComponent(
			JTable table, Object value, boolean isSelected, int row, int column)
		{
			if (value == null)
			{
				editButton.setText( "" );
				editButton.setIcon( null );
			}
			else if (value instanceof Icon)
			{
				editButton.setText( "" );
				editButton.setIcon( (Icon)value );
			}
			else
			{
				editButton = (JButton) value;
//				JButton temp = (JButton) value;
//				editButton.setText( temp.getText() );
//				editButton.setIcon( null );
			}

			this.editorValue = value;
			return editButton;
		}

		@Override
		public Object getCellEditorValue()
		{
			return editorValue;
		}

	//
	//  Implement TableCellRenderer interface
	//
		public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
		{
			if (isSelected)
			{
				renderButton.setForeground(table.getSelectionForeground());
		 		renderButton.setBackground(table.getSelectionBackground());
			}
			else
			{
				renderButton.setForeground(table.getForeground());
				renderButton.setBackground(UIManager.getColor("Button.background"));
			}

			if (hasFocus)
			{
				renderButton.setBorder( focusBorder );
			}
			else
			{
				renderButton.setBorder( originalBorder );
			}

//			renderButton.setText( (value == null) ? "" : value.toString() );
			if (value == null)
			{
				renderButton.setText( "" );
				renderButton.setIcon( null );
			}
			else if (value instanceof Icon)
			{
				renderButton.setText( "" );
				renderButton.setIcon( (Icon)value );
			}
			else
			{
				renderButton = (JButton) value;
//				JButton temp = (JButton) value;
//				renderButton.setText( temp.getText() );
////				renderButton.setText( value.toString() );
//				renderButton.setIcon( null );
			}

			return renderButton;
		}

	//
	//  Implement ActionListener interface
	//
		/*
		 *	The button has been pressed. Stop editing and invoke the custom Action
		 */
		public void actionPerformed(ActionEvent e)
		{
			int row = table.convertRowIndexToModel( table.getEditingRow() );
			fireEditingStopped();

			//  Invoke the Action

			ActionEvent event = new ActionEvent(
				table,
				ActionEvent.ACTION_PERFORMED,
				"" + row);
			action.actionPerformed(event);
		}

	//
	//  Implement MouseListener interface
	//
		/*
		 *  When the mouse is pressed the editor is invoked. If you then then drag
		 *  the mouse to another cell before releasing it, the editor is still
		 *  active. Make sure editing is stopped when the mouse is released.
		 */
	    public void mousePressed(MouseEvent e)
	    {
	    	if (table.isEditing()
			&&  table.getCellEditor() == this)
				isButtonColumnEditor = true;
	    }

	    public void mouseReleased(MouseEvent e)
	    {
	    	if (isButtonColumnEditor
	    	&&  table.isEditing())
	    		table.getCellEditor().stopCellEditing();

			isButtonColumnEditor = false;
	    }

	    public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e) {}
	
	}
//	
//	/**
//	 * @version 1.0 11/09/98
//	 */
//
//	class ButtonRenderer extends JButton implements TableCellRenderer {
//
//	  public ButtonRenderer() {
//	    setOpaque(true);
//	  }
//
//	  public Component getTableCellRendererComponent(JTable table, Object value,
//	      boolean isSelected, boolean hasFocus, int row, int column) {
//	    if (isSelected) {
//	      setForeground(table.getSelectionForeground());
//	      setBackground(table.getSelectionBackground());
//	    } else {
//	      setForeground(table.getForeground());
//	      setBackground(UIManager.getColor("Button.background"));
//	    }
//	    //setText((value == null) ? "" : value.toString());
//	    JButton button = (JButton) value;
//		setText(button.getText());
//	    
//	    return this;
//	  }
//	}
//
//	/**
//	 * @version 1.0 11/09/98
//	 */
//
//	class ButtonEditor extends DefaultCellEditor {
//	//  protected JButton button;
//	  protected JButton button2;
//
////	  private String label;
//
//	  private boolean isPushed;
//
//	  public ButtonEditor(JCheckBox checkBox) {
//	    super(checkBox);
//	    button2 = new JButton();
//	    button2.setOpaque(true);
////	    button = new JButton();
////	    button.setOpaque(true);
////	    button.addActionListener(new ActionListener() {
////	      public void actionPerformed(ActionEvent e) {
////	        fireEditingStopped();
////	      }
////	    });
//	  }
//
//	  public Component getTableCellEditorComponent(JTable table, Object value,
//	      boolean isSelected, int row, int column) {
//	    if (isSelected) {
//	      button2.setForeground(table.getSelectionForeground());
//	      button2.setBackground(table.getSelectionBackground());
//	    } else {
//	      button2.setForeground(table.getForeground());
//	      button2.setBackground(table.getBackground());
//	    }
////	    label = (value == null) ? "" : value.toString();
////	    button.setText(label);
//	    button2 = (JButton) value;
////	    button2.setText(button2.getText());
//	    isPushed = true;
//	    return button2;
//	  }
//
//	  public Object getCellEditorValue() {
//		  
//	    if (isPushed) {
//	      // 
//	      // koop Speler Functie hierin zetten	    	
//	    	//Controller.spelerKopen(label, User.getTeam());
//	    	System.out.println("koop " + button2.getText());
//	    }
//	    System.out.println("remove button " + button2.getText());
//	    
//	    isPushed = false;
//	    return new String(button2.getText());
//	  }
//
//	  public boolean stopCellEditing() {
//	    isPushed = false;
//	    return super.stopCellEditing();
//	  }
//
//	  protected void fireEditingStopped() {
//	    super.fireEditingStopped();
//	  }
//	}
//
	/**
	 * @return the model
	 */
	public DefaultTableModel getModel() {
		return model;
	}
}
