package vanahallichandranna.ecore.rcm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vanahallichandranna.ecore.ecore.RecycledItem;
import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RecycleItemPanel extends JPanel implements DynamicPanelInterface, Observer {
	private JButton checkoutButton;
	private JButton cancelButton;
	private JButton enquireButton;
	private JPanel buttonPanel;
	private Rcm recyclingMachine;
	
	/**
	 * 
	 */
	public RecycleItemPanel(DynamicPanel dp, Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		recyclingMachine = rcm;
		
		// Receive notifications whenever the rcm changes
		recyclingMachine.addObserver(this);
		
		this.add(createButtonPanel());
		
	
		
		createActionListeners(dp);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D)g;
		Font myFont = new Font("Serif", Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(22F);
	    graphics.setFont(newFont);
		graphics.drawString("Enter the item in the slot provided below", 40, 50);
		
		graphics.drawString("Type", 100,100);
		graphics.drawString("Weight", 200,100);
		graphics.drawString("Price", 300, 100);
		
		
		try{
			int y = 25;
			List<RecycledItem> recycleItemList = recyclingMachine.getRecycledItemInserted();
			for (RecycledItem recycleItem : recycleItemList ) {
				graphics.drawString(recycleItem.getItemType(), 100,100 + y);
				graphics.drawString(String.valueOf(recycleItem.getItemWeight()), 210, 100 + y);
				graphics.drawString(String.valueOf(recycleItem.getPrice()), 305,100 + y);
				
				y = y + 25;
			}
		} catch (NullPointerException e) {
			// noop
		}
			
		/*
		//inputdata hard coded
		
		
		graphics.drawString("Glass", 100,150);
		graphics.drawString("3 lbs", 150,150);
		graphics.drawString("$9", 200, 150);
		
		graphics.drawString("Paper", 100,200);
		graphics.drawString("2 lbs", 150,200);
		graphics.drawString("$8", 200, 200);
		*/
	}
	
	private JPanel createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		checkoutButton = new JButton("Checkout");
		checkoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		checkoutButton.setLocation(50, Constants.DISPLAY_PANEL_HEIGHT - 75);
		checkoutButton.setSize(100, 50);
		this.add(checkoutButton);
		
		enquireButton = new JButton("Enquire");
		enquireButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		enquireButton.setLocation(170, Constants.DISPLAY_PANEL_HEIGHT - 75);
		enquireButton.setSize(150, 50);
		this.add(enquireButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setLocation(340, Constants.DISPLAY_PANEL_HEIGHT - 75);
		cancelButton.setSize(100, 50);
		this.add(cancelButton);
		
		return buttonPanel;		
	
	}
	
	
	
	private void createActionListeners(DynamicPanel dp) {
		checkoutButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (recyclingMachine.getAvailableCapacity() > recyclingMachine.getTotalCapacity()) {
					dp.showPanel(Constants.PANEL_ID_CHECKOUT);
				} else {
					JOptionPane.showMessageDialog(null, "No sufficient space");
					//recyclingMachine.deactivate();
				}
				dp.showPanel(Constants.PANEL_ID_CHECKOUT);
			}
		});
		
		enquireButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_ENQUIRE);
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

	@Override
	public void update(Observable o, Object arg) {
		repaint();
	}

	

}
