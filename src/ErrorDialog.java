import java.awt.Dimension;

import javax.swing.JDialog;

public class ErrorDialog extends JDialog {
	private String errorCode;
	
	
	@Override
	public void setSize(Dimension arg0) {
		super.setSize(300, 300);
	}
	
}
