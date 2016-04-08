package vanahallichandranna.ecore.rcm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import vanahallichandranna.ecore.ecore.Database;
import vanahallichandranna.ecore.ecore.RecyclingMachine;



/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class MainPanel extends JPanel {
	private JPanel displayPanel;
	private BufferedImage rcmImage;
	private DynamicPanel dynamicPanel;
	private JPanel typePanel;
	//private EnquirePanel enquirePanel;
	//private RecycleItemPanel recycleItemPanel;

	/**
	 * 
	 */
	public MainPanel(Rcm rcm) {
		this.setLayout(null);
		this.setVisible(true);
		
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		
		loadImage();
		
		displayPanel = new DisplayPanel(rcm);
		this.add(displayPanel);
		
		typePanel = new TypePanel(rcm);
		this.add(typePanel);
	}
	
	private void loadImage() {
		
		try {
			File imageFile = new File("resources/rcm.jpg");
			rcmImage = ImageIO.read(imageFile);
		} catch(IOException e) {
			System.err.println("Error reading the image file");
			rcmImage = null;
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		displayPanel.repaint();
		typePanel.repaint();
		
		if(rcmImage != null) {
			g.drawImage(rcmImage, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, null);
		}
		
	}

}
