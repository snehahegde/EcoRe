package vanahallichandranna.ecore.rcm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class CheckoutPanel extends JPanel implements DynamicPanelInterface{

	private JPanel buttonPanel;
	private JButton cashButton;
	private JButton couponButton;
	private Rcm recyclingMachine;

	/**
	 * 
	 */
	public CheckoutPanel(DynamicPanel dp, Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		recyclingMachine = rcm;
		createButtonPanel();
		createActionListeners(dp);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D)g;
		Font myFont = new Font("Serif", Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(22F);
	    graphics.setFont(newFont);
	    
	    String filledMessage = "Your items are worth : ";
		filledMessage += recyclingMachine.getTotalAmount();
		
	    graphics.drawString(filledMessage, 100, 50);
		
		graphics.drawString("Choose the payment method", 100, 150);
	}
	
	
	private JPanel createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		cashButton = new JButton("Cash");
		cashButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cashButton.setLocation(80, Constants.DISPLAY_PANEL_HEIGHT - 75);
		cashButton.setSize(100, 50);
		this.add(cashButton);
		
		couponButton = new JButton("Coupon");
		couponButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		couponButton.setLocation(220, Constants.DISPLAY_PANEL_HEIGHT - 75);
		couponButton.setSize(150, 50);
		this.add(couponButton);
		
		return buttonPanel;		
	
	}
	
	private void createActionListeners(DynamicPanel dp) {
		cashButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (recyclingMachine.getTotalAmount() <= recyclingMachine.getAvailableAmount()) {
					recyclingMachine.payCustomer();
					dp.showPanel(Constants.PANEL_ID_PAYMENT_CONFIRMATION, "Please collect your cash");
				} else {
					JOptionPane.showMessageDialog(null, "No sufficient cash");
				}
			}
		});
		
		couponButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_PAYMENT_CONFIRMATION, "Please collect your tokens");
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
