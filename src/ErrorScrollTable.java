import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Called by the DisplayHandlerError. Has a table model of
 * type ErrorTable. This class is set to visible every time
 * an error occurs from SC or RC.
 * 
 * @author afallah
 * @created 2-19-16
 */
public class ErrorScrollTable extends JFrame {

	public static void main(String[] args) {
		ErrorScrollTable rht = new ErrorScrollTable();
		rht.setVisible(true);
	}
	private ErrorTable et = new ErrorTable();
	public ErrorScrollTable() {
		super("Error Dialog");
		Dimension prefSize = new Dimension(1100, 400);
		setSize(prefSize);
		setPreferredSize(prefSize);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		JButton closeButt = new JButton("Close");
		closeButt.setSize(new Dimension(80, 40));
		closeButt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        setVisible(false);
		    }
		});
		
		DefaultTableColumnModel cm = new DefaultTableColumnModel() {
			boolean first = true; 
			int columnCount = 0;
			public void addColumn(TableColumn tc) {
				if(columnCount < 2) {
					columnCount++;
					return;
				}
				tc.setMinWidth(150);
				super.addColumn(tc);
			}
		};
		
		//Create a column model that will serve as our row header table
		TableColumnModel rowHeaderModel = new DefaultTableColumnModel() {
			int columnCount = 0;
			public void addColumn(TableColumn tc) {
				if (columnCount < 2) {
					tc.setMaxWidth(75);
					super.addColumn(tc);
					columnCount++;
				}
				//drop the rest of the columns, this is the header only
			}
		};
		
		
		
		JTable jt = new JTable(et,cm){
            public boolean getScrollableTracksViewportWidth()
            {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        
		
		//Set up header column and hook it to everything
		JTable headerColumn = new JTable (et,rowHeaderModel);
		jt.createDefaultColumnsFromModel();
		headerColumn.createDefaultColumnsFromModel();
		
		jt.setSelectionModel(headerColumn.getSelectionModel());
		
		 jt.setCellSelectionEnabled(true);
		 ListSelectionModel cellSelectionModel = jt.getSelectionModel();
		 cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Make header look pretty
		headerColumn.setColumnSelectionAllowed(false);
		headerColumn.setCellSelectionEnabled(false);

		
		//Put in viewport that we can control
		JViewport jv = new JViewport();
		jv.setView(headerColumn);
		jv.setPreferredSize(headerColumn.getMaximumSize());
		
		//Without shutting off autoResizemode our tables wont scroll correctly
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jt.getTableHeader().setReorderingAllowed(false);
		setResizable(false);
			
		//We have too manually attach the row header but the scroll pane will keep them in sync
		JScrollPane jsp = new JScrollPane(jt);
		jsp.setRowHeader(jv);
		jsp.setCorner(ScrollPaneConstants.UPPER_LEFT_CORNER, headerColumn.getTableHeader());
		
		rootPane.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 270;      //make this component tall
		c.ipadx = 1070;
		c.weightx = 0.0;
		c.gridwidth = 4;
		c.gridx = 0;
		c.gridy = 1;
		rootPane.add(jsp, c);
			
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(10,0,0,0);
		d.gridx = 3;
		d.gridy = 2;
		rootPane.add(closeButt, d);
		
		JButton addRowButt = new JButton("add row");
		addRowButt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		    	et.makeRowList("021916","15:10:51:163", "Scan Manager: there is a mismatch in the number of data elements "
		    			+ "for Powerlink Calibration of phase/frequency initial "
		    			+ "gains or gain limits." );
		    }
		});
		GridBagConstraints e = new GridBagConstraints();
		d.gridx = 3;
		d.gridy = 3;
		rootPane.add(addRowButt, e);
	}

}
