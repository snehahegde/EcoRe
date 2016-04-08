/**
 * 
 */
package vanahallichandranna.ecore.ecore;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * @author Sneha
 *
 */
public class RCMSelectorPanel extends JPanel {
	private JComboBox machineIdsComboBox; 
	/**
	 * 
	 */
	public RCMSelectorPanel() {
		setLayout(null);
		setSize(Constants.RCM_SELECTOR_WIDTH, Constants.RCM_SELECTOR_HEIGHT);
		setLocation(Constants.RCM_SELECTOR_PANEL_LOCATIONX,Constants.RCM_SELECTOR_PANEL_LOCATIONY);
		setBackground(new Color( 254, 132, 2));
		setVisible(true);
		
		createMachineSelector();
		
	}
	
	private void createMachineSelector() {
		JLabel machineId = new JLabel("Machine Id:");
		Font myFont = new Font("Sans Serif",Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(18F);
		machineId.setFont(newFont);
		machineId.setLocation(10, 10);
		machineId.setSize(200, 50);
		this.add(machineId);
		
		machineIdsComboBox = new JComboBox<String>();
		machineIdsComboBox.setLocation(210, 10);
		machineIdsComboBox.setSize(150, 50);
		this.add(machineIdsComboBox);
		
		machineIdsComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
			
		});
	}

	
}
