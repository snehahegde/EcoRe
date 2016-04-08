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
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class PaymentConfirmationPanel extends JPanel implements DynamicPanelInterface {
	private JButton doneButton;
	String message;
	private Rcm recyclingMachine;
	
	/**
	 * 
	 */
	public PaymentConfirmationPanel(DynamicPanel dp, Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		recyclingMachine = rcm;
		
		createDoneButton(dp);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D)g;
		Font myFont = new Font("Serif", Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(22F);
	    graphics.setFont(newFont);
		graphics.drawString(message, 120, 100); //"Please collect your cash from the cash dispenser", 50, 50);
		
		//graphics.drawString("Please collect your coupons from the coupon dispenser", 100, 100);
	}
	
	private void createDoneButton(DynamicPanel dp) {
		doneButton = new JButton("Done");
		doneButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		doneButton.setLocation(Constants.DISPLAY_PANEL_WIDTH/2 - 50, 200);
		doneButton.setSize(100, 50);
		this.add(doneButton);
		
		doneButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recyclingMachine.endSession();
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
		if(o instanceof String) {
			message = (String) o;
		}
	}
	

}
