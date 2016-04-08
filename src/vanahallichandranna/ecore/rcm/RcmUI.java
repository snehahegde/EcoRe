package vanahallichandranna.ecore.rcm;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import vanahallichandranna.ecore.ecore.Database;
import vanahallichandranna.ecore.ecore.RecyclingMachine;






/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RcmUI {
	private MainPanel mainPanel;
	/**
	 * 
	 */
	public RcmUI(Rcm rcm) {
		mainPanel = new MainPanel(rcm);
	}
	
	public JPanel getPanel() {
		return mainPanel;
	}
}
