package vanahallichandranna.ecore.rcm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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
public class EnquirePanel extends JPanel implements DynamicPanelInterface{
	private JButton optionsButton;
	private JButton recycleButton;
	private JButton cancelButton;
	private JPanel buttonPanel;
	private JPanel footerPanel;
	private Rcm recyclingMachine;
	
	/**
	 * @param rcm 
	 * 
	 */
	public EnquirePanel(DynamicPanel dp, Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		footerPanel = createButtonPanel();
		recyclingMachine = rcm;
		createActionListeners(dp);
	}

	

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D)g;
		Font myFont = new Font("Serif", Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(24F);
	    graphics.setFont(newFont);
		graphics.drawString("Item Type", 120, 50);
		graphics.drawString("Price", 280, 50);
		
		/*HashMap<String, Double> data = new HashMap<String, Double>();
		data.put("Glass", (double) 3);
		data.put("Paper", (double) 4); */
		
		HashMap<String, Double> typePrice = recyclingMachine.getTypePrice();
				
		int x =120;
		int y = 50;
		for (String type : typePrice.keySet()) {
			graphics.drawString(type, x, y+50);
			graphics.drawString(Double.toString(typePrice.get(type)), x+160, y+50);
			y  = y + 50;
		}
		
			
		
	}
	
	private JPanel createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setLayout(null);
		
		optionsButton = new JButton("Options");
		optionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		optionsButton.setLocation(35, Constants.DISPLAY_PANEL_HEIGHT - 75);
		optionsButton.setSize(100, 50);
		this.add(optionsButton);
		
		recycleButton = new JButton("Recycle Item");
		recycleButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		recycleButton.setLocation(155, Constants.DISPLAY_PANEL_HEIGHT - 75);
		recycleButton.setSize(150, 50);
		this.add(recycleButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		cancelButton.setLocation(325, Constants.DISPLAY_PANEL_HEIGHT - 75);
		cancelButton.setSize(100, 50);
		this.add(cancelButton);
		
		return buttonPanel;		
	}
	
	private void createActionListeners(DynamicPanel dp) {
		optionsButton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dp.showPanel(Constants.PANEL_ID_OPTION);
			}
		});
		
		recycleButton.addActionListener( new ActionListener() {
			
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
