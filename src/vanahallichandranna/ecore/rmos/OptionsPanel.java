package vanahallichandranna.ecore.rmos;

import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;



/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class OptionsPanel extends JPanel {
	private JButton rcmButton;
	private JButton recyclingItemButton;
	private JButton statisticsButton;
	private JButton logoutButton;
	/**
	 * 
	 */
	public OptionsPanel(DynamicPanel dp) {
		setLayout(null);
		setSize(Constants.OPTIONS_PANEL_WIDTH, Constants.OPTIONS_PANEL_HEIGHT);
		this.setLocation(10, 10);
		setBackground(new Color(   228, 157, 103));
		createOptions();
		createActionListeners(dp);
	}

	private void createOptions() {
		
		rcmButton = new JButton("RCM");
		rcmButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rcmButton.setBackground(new Color(235, 202, 151));
		rcmButton.setLocation(10, 10);
		rcmButton.setSize(180, 100);
		this.add(rcmButton);
		
		recyclingItemButton = new JButton("Recycling Item");
		recyclingItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		recyclingItemButton.setBackground(new Color(235, 202, 151));
		recyclingItemButton.setLocation(10, 120);
		recyclingItemButton.setSize(180, 100);
		this.add(recyclingItemButton);
		
		statisticsButton = new JButton("Statistics");
		statisticsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		statisticsButton.setBackground(new Color(235, 202, 151));
		statisticsButton.setLocation(10, 230);
		statisticsButton.setSize(180, 100);
		this.add(statisticsButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		logoutButton.setBackground(new Color(235, 202, 151));
		logoutButton.setLocation(10, 340);
		logoutButton.setSize(180, 100);
		this.add(logoutButton);

	}
	
	private void createActionListeners(DynamicPanel dp) {
		rcmButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_RECYCLING_MACHINE);
			}
		});
		
		recyclingItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_RECYCLING_ITEM);
			}
		});
		
		statisticsButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_STATISTICS);
			}
		});
		
		logoutButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_LOGIN);
			}
		});
	}
}
