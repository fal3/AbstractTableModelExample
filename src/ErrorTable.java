import java.awt.List;
import java.util.ArrayList;
import java.util.Collection;

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
	private ArrayList errorMessages = new ArrayList(100);
	
	ErrorTable(String errorCode,String errorDescription, String dateStamp, String timeStamp) { 
		errorMessages.add("");
		errorMessages.remove(errorMessages.size()-1);
		makeRowList(errorCode, errorDescription, dateStamp, timeStamp);
	}
	 
	@Override
	public int getColumnCount() {
		return headers.length;
	}

	@Override
	public int getRowCount() {
		return errorMessages.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		if (col == 0) {
			return row+1;
		}
		ArrayList rowData = new ArrayList();
		rowData = (ArrayList)data.get(row);
		System.out.println("row Data" + rowData.get(col));
		
		return rowData.get(col);
	}
	
	public String getColumnName(int col) {
		return headers[col];
	}
	
	
	public void addRow()
    {
        errorMessages.add(0, dataRow);
        fireTableRowsInserted(errorMessages.size() - 1, errorMessages.size() - 1);
    }
	
	public void makeRowList(String errorCode,String errorDescription, String dateStamp, String timeStamp) {
		this.dataRow = new ArrayList();
		this.dataRow.add("");
		this.dataRow.add(errorCode);
		this.dataRow.add(errorDescription);
		this.dataRow.add(dateStamp);
		this.dataRow.add(timeStamp);
		this.data.add(0,dataRow);
	}
	
	@Override
		public void fireTableRowsInserted(int firstRow, int lastRow) {
		
		System.out.println("wtf");
			super.fireTableRowsInserted(firstRow, lastRow);
		}
	
	
}
