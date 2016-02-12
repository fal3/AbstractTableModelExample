import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

public class RowHeaderTable extends JFrame {

	public static void main(String[] args) {
		RowHeaderTable rht = new RowHeaderTable();
		rht.setVisible(true);
	}
	
	public RowHeaderTable() {
		super("Header");
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		TableModel tm = new AbstractTableModel() {
			String data[] = {"","a","c","b","e","d"};
			String headers[] = {"Row #","Column 1","Column 2" , "Column 3","Column 4" , "Column 5"};
			public int getColumnCount() {return data.length; }
			public int getRowCount() {return 100; }
			public String getColumnName(int col) {return headers[col]; }
			
			public Object getValueAt(int row, int col) {
				return data[col] + row;
			}
		};
		
		DefaultTableColumnModel cm = new DefaultTableColumnModel() {
			boolean first = true;
			public void addColumn(TableColumn tc) {
				if(first) {
					first = false;
					return;
				}
				tc.setMinWidth(150);
				super.addColumn(tc);
			}
		};
		
		//Create a column model that will serve as our row header table
		TableColumnModel rowHeaderModel = new DefaultTableColumnModel() {
			boolean first = true;
			public void addColumn(TableColumn tc) {
				if (first) {
					tc.setMaxWidth(tc.getPreferredWidth());
					super.addColumn(tc);
					first = false;
				}
				//drop the rest of the columns, this is the header only
			}
		};
		
		JTable jt = new JTable(tm,cm);
		
		//set up header column and hook it to everything
		JTable headerColumn = new JTable (tm,rowHeaderModel);
		jt.createDefaultColumnsFromModel();
		headerColumn.createDefaultColumnsFromModel();
		
		jt.setSelectionModel(headerColumn.getSelectionModel());
		
		//make header look pretty
		headerColumn.setBackground(Color.lightGray);
		headerColumn.setColumnSelectionAllowed(false);
		headerColumn.setCellSelectionEnabled(false);
		
		//put in viewport that we can control
		JViewport jv = new JViewport();
		jv.setView(headerColumn);
		jv.setPreferredSize(headerColumn.getMaximumSize());
		
		//without shutting off autoResizemode our tables wont scroll correctly
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//we have too manually attach the row header but the scroll pane will keep them in sync
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setRowHeader(jv);
		jsp.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, headerColumn.getTableHeader());
		getContentPane().add(jsp, BorderLayout.CENTER);
	}

}
