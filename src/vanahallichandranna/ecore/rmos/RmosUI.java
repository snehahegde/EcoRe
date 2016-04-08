package vanahallichandranna.ecore.rmos;

import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RmosUI {
	private MainPanel mainPanel;
	private Rmos rmos;
	/**
	 * 
	 */
	public RmosUI(Rmos r) {
		
		/*JFrame mainFrame = new JFrame("RMOS");
		mainFrame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		mainFrame.setLayout(null);  */
		this.rmos = r;
		mainPanel = new MainPanel(rmos);
		
		/*
		mainFrame.add(mainPanel);
		mainFrame.setVisible(true);*/
	}

	public JPanel getPanel() {
		return mainPanel;
	}
}
