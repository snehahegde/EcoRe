package vanahallichandranna.ecore.rmos;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.JPanel;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class DynamicPanel {

	HashMap<String, JPanel> panels;
	

	public DynamicPanel() {
		panels = new HashMap<String, JPanel>();
	}
	
	public void addPanel(String id, JPanel panel) {
		panels.put(id, panel);
	}
	
	public void showPanel(String id) {
		hideAllPanels();
		panels.get(id).setVisible(true);
	}
	
	public void hideAllPanels() {
		for(JPanel panel: panels.values()) {
			panel.setVisible(false);
		}
	}

}
