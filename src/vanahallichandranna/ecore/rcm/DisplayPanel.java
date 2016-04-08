package vanahallichandranna.ecore.rcm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;

import vanahallichandranna.ecore.ecore.RecyclingMachine;


/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class DisplayPanel extends JPanel implements DynamicPanelInterface {
	private StartPanel startPanel; 
	private OptionsPanel optionsPanel;
	private DynamicPanel dynamicPanel;
	private EnquirePanel enquirePanel;
	private RecycleItemPanel recycleItemPanel;
	private CheckoutPanel checkoutPanel;
	private PaymentConfirmationPanel paymentConfirmationPanel;
	
	/**
	 * @param rcm 
	 * 
	 */
	public DisplayPanel(Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(Constants.DISPLAY_PANEL_LOCATIONX, Constants.DISPLAY_PANEL_LOCATIONY);
		setBackground(Color.GREEN);
		setVisible(true);
	
		dynamicPanel = new DynamicPanel();
		
		startPanel = new StartPanel(dynamicPanel, rcm);
		this.add(startPanel);
		
		optionsPanel = new OptionsPanel(dynamicPanel);
		this.add(optionsPanel);
		
		enquirePanel = new EnquirePanel(dynamicPanel, rcm);
		this.add(enquirePanel);
		
		recycleItemPanel = new RecycleItemPanel(dynamicPanel, rcm);
		this.add(recycleItemPanel);
		
		checkoutPanel = new CheckoutPanel(dynamicPanel, rcm);
		this.add(checkoutPanel);
		
		paymentConfirmationPanel = new PaymentConfirmationPanel(dynamicPanel, rcm);
		this.add(paymentConfirmationPanel);
		
		
		dynamicPanel.addPanel(Constants.PANEL_ID_START, startPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_OPTION, optionsPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_ENQUIRE, enquirePanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_RECYCLE_ITEM, recycleItemPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_CHECKOUT, checkoutPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_PAYMENT_CONFIRMATION, paymentConfirmationPanel);


		this.add(startPanel);
		
		dynamicPanel.showPanel(Constants.PANEL_ID_START);
		
	}

	@Override
	public void willBeShown(Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showPanel() {
		this.setVisible(true);
		
	}

	@Override
	public void hidePanel() {
		this.setVisible(false);
	}
	

	
}
