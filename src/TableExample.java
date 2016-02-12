import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class TableExample extends AbstractTableModel implements TableModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TableExample example = new TableExample();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
