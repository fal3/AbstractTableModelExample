import java.awt.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ErrorTable extends AbstractTableModel {
	int errorCode;
    String errorDescription;
    String dateStamp;
    String timeStamp;
    
	ArrayList dataRow;
	ArrayList data = new ArrayList();
	String headers[] = {"Row #","Error Code","Error Description" , "Date Stamp","Time Stamp"};
	 
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		if (this.data.size() >= 10) {
			return 10;
		}
		return this.data.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return row+1;
		}
		ArrayList rowData = new ArrayList();
		rowData = (ArrayList)data.get(row);
		return rowData.get(col);
	}
	
	public String getColumnName(int col) {
		return headers[col];
	}
	
	
	public void addRow()
    {
		this.data.add(0,dataRow);
		if(this.data.size() >= 10) {
			System.out.println("row count" + getRowCount());
			System.out.println("Size of 100");
			fireTableRowsDeleted(0,	getRowCount());
		} else {
			fireTableRowsInserted(this.data.size() - 1, this.data.size() - 1);
		}
		
        
    }
	
	public void makeRowList(String errorCode,String errorDescription, String dateStamp, String timeStamp) {
		this.dataRow = new ArrayList();
		this.dataRow.add("");
		this.dataRow.add(errorCode);
		this.dataRow.add(errorDescription);
		this.dataRow.add(dateStamp);
		if (getRowCount() >= 10) {
			this.dataRow.add("ALEX");
		} else {
			this.dataRow.add(timeStamp);	
		}
		addRow();
	}
	
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
	
}
