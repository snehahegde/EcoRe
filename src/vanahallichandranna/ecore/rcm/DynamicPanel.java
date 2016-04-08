package vanahallichandranna.ecore.rcm;
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
	private HashMap<String, DynamicPanelInterface> panels;
	/**
	 * 
	 */
	public DynamicPanel() {
		panels = new HashMap<String, DynamicPanelInterface>();
	}
	
	public void addPanel(String id, DynamicPanelInterface panel) {
		panels.put(id, panel);
	}
	
	public void showPanel(String id, Object o) {
		hideAllPanels();
		panels.get(id).willBeShown(o);
		panels.get(id).showPanel();
	}

	public void showPanel(String id) {
		showPanel(id, null);
	}

	
	public void hideAllPanels() {
		for(DynamicPanelInterface panel: panels.values()) {
			panel.hidePanel();
		}
	}

}
