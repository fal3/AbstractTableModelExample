import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

/**
 * ErrorTable class is a model class used in RowHeaderTable. 
 * It's of type AbstractTableModel. It parses values of incoming error 
 * dialogs and inserts them into this model.
 * 
 * @author afallah
 * @created 2-19-16
 */
public class ErrorTable extends AbstractTableModel {
	
	private ArrayList<String> dataRow;
	private LinkedList data = new LinkedList();
	private String headers[] = {"Date Stamp","Time Stamp","Error Description"};
	private static final int MAX_ROW_SIZE = 10;
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		if (this.data.size() >= MAX_ROW_SIZE) {
			this.data.removeLast();
		}
		return this.data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
//		if (col == 0) {
//			return row+1;
//		}
		ArrayList rowData = new ArrayList<String>();
		rowData = (ArrayList<String>)data.get(row);
		return rowData.get(col);
	}
	
	@Override
	public String getColumnName(int col) {
		return headers[col];
	}
	
	/**
	 * Called by inner method makeRowList(). Limits the number of 
	 * rows to 100.
	 */
	private void addRow()
    {
		this.data.add(0,dataRow);
		if(this.data.size() >= MAX_ROW_SIZE) {
			System.out.println("row count" + getRowCount());
			fireTableRowsDeleted(0,	getRowCount());
		} else {
			fireTableRowsInserted(this.data.size() - 1, this.data.size() - 1);
		}
    }
	
	/**
	 * Invoked whenever an error happens from SC or RC 
	 * through DisplayHandlers
	 * 
	 * @param errorDescription
	 * @param dateStamp 
	 * @param timeStamp The time stamp is passed in format HH_mm_ss_SSS.
	 */
	public void makeRowList( String dateStamp, String timeStamp,String errorDescription) {
		this.dataRow = new ArrayList<String>();
		this.dataRow.add(parseDate(dateStamp));
		if (getRowCount() >= MAX_ROW_SIZE-1) {
			this.dataRow.add("ALEX");
		} else {
			this.dataRow.add(parseTime(timeStamp));	
		}
		this.dataRow.add(errorDescription);
		
		addRow();
	}
	
	/**
	 * Parses date into MM/dd/yyyy format
	 * 
	 * @param strDate
	 * @return
	 */
	private String parseDate(String strDate) {
	    SimpleDateFormat sdf = new SimpleDateFormat("MMddyy");
	    Date str = null;
		try {
			str = (Date) sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(str);
	}
	
	/**
	 * Parses time into hh:mm:ss format
	 * 
	 * @param strTime
	 * @return
	 */
	private String parseTime(String strTime) { 
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss:SSS");
	    Date tStr = null;
		try {
			tStr = (Date) stf.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		DateFormat tf = new SimpleDateFormat("hh:mm:ss");
		return tf.format(tStr);
	}
	
}
