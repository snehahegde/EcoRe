package vanahallichandranna.ecore.rmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JPanel;





/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class MainPanel extends JPanel {
	private OptionsPanel optionsPanel;
	private RcmPanel rcmPanel;
	private RecyclingItemPanel recyclingItemPanel;
	private DynamicPanel dynamicPanel;
	private StatisticsPanel statisticsPanel;
	private Rmos rmos;
	private LoginPanel loginPanel;
	
	/**
	 * 
	 */
	public MainPanel(Rmos r) {
		rmos = r;
		this.setLayout(null);
		dynamicPanel = new DynamicPanel();
		
		setBackground(new Color( 235, 202, 151));
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		loginPanel = new LoginPanel(dynamicPanel);
		this.add(loginPanel);
		
		optionsPanel = new OptionsPanel(dynamicPanel);
		this.add(optionsPanel);
		
		
		
		rcmPanel = new RcmPanel(rmos);
		this.add(rcmPanel);
		
		recyclingItemPanel = new RecyclingItemPanel(rmos);
		this.add(recyclingItemPanel);
		
		statisticsPanel = new StatisticsPanel(rmos);
		this.add(statisticsPanel);
		
		//dynamicPanel.addPanel(Constants.PANEL_ID_OPTIONS, optionsPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_RECYCLING_MACHINE, rcmPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_RECYCLING_ITEM, recyclingItemPanel);
		dynamicPanel.addPanel(Constants.PANEL_ID_STATISTICS, statisticsPanel);
		dynamicPanel.hideAllPanels();
		
		
	}
	
	/*public void paint(Graphics g) {
		 Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(38F);
	    g.setFont(newFont);
	    g.drawString("Welcome to Eco Re", Constants.WINDOW_WIDTH/4, 100);
	    super.paint(g);
	       
	} */

}
