import java.awt.List;
import java.util.ArrayList;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ErrorTable extends AbstractTableModel {

//	super("Header");
//	setSize(300,200);
//	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	
//	final int errorCode,
//    final String errorDescription,
//    final String dateStamp,
//    final String timeStamp) {
	
	String data[] = {"","a","c","b","Alex "};
	String headers[] = {"Row #","Error Code","Error Description" , "Date Stamp","Time Stamp"};
	private ArrayList errorMessages = new ArrayList();
	
	 
	 
	@Override
	public int getColumnCount() {
		return data.length; 
	}

	@Override
	public int getRowCount() {
		return 100;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[col] + row;
	}
	
	public String getColumnName(int col) {
		return headers[col];
	}
	
	
	public void addRow(List rowData)
    {
        errorMessages.add(rowData);
        fireTableRowsInserted(errorMessages.size() - 1, errorMessages.size() - 1);
    }
	
	
}
