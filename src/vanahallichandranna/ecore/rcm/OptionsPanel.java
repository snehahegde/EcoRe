package vanahallichandranna.ecore.rcm;
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
public class OptionsPanel extends JPanel implements DynamicPanelInterface{
	private JPanel optionsButtonPanel;
	private JButton recycleItemButton;
	private JButton enquireButton;
	private JButton cancelButton;
	
	/**
	 * 
	 */
	public OptionsPanel(DynamicPanel dp) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		JPanel optionPanel  = createOptionsButtonPanel();
		this.add(optionPanel);
		
		createActionListeners(dp);
	}
	
	private JPanel createOptionsButtonPanel() {
		optionsButtonPanel  = new JPanel();
		
		enquireButton = new JButton("Enquire");
		enquireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		enquireButton.setLocation(Constants.DISPLAY_PANEL_WIDTH/2 - 50, 50);
		enquireButton.setSize(100, 50);
		this.add(enquireButton);
		
		recycleItemButton = new JButton("Recycle Item");
		recycleItemButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		recycleItemButton.setLocation(Constants.DISPLAY_PANEL_WIDTH/2 - 75, 150);
		recycleItemButton.setSize(150, 50);
		this.add(recycleItemButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setLocation(Constants.DISPLAY_PANEL_WIDTH/2 - 50, 250);
		cancelButton.setSize(100, 50);
		this.add(cancelButton);
		
		return optionsButtonPanel;		
		
	}
	
	private void createActionListeners(DynamicPanel dp) {
		enquireButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_ENQUIRE);
			}
		});
		
		recycleItemButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_RECYCLE_ITEM);
			}
		});
		
		cancelButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_START);
			}
		});
	}

		
	@Override
	public void showPanel() {
		setVisible(true);
	}

	@Override
	public void hidePanel() {
		setVisible(false);
	}

	@Override
	public void willBeShown(Object o) {
		
	}
	
}
