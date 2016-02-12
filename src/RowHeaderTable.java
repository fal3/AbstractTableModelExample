import javax.swing.JFrame;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class RowHeaderTable extends JFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public RowHeaderTable() {
		super("Header");
		setSize(300,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		AbstractTableModel tm = new AbstractTableModel() {
			String data[] = {"","a","c","b","e","d"};
			String headers[] = {"Row #","Column 1","Column 2" , "Column 3"};
			public int getColumnCount() {return data.length; }
			public int getRowCount() {return 100; }
			public String getColumnName(int col) {return headers[col]; }
			
			public Object getValueAt(int row, int col) {
				return data[col] + row;
			}
		};
		
		
	}

}
