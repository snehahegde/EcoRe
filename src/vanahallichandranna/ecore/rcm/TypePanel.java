/**
 * 
 */
package vanahallichandranna.ecore.rcm;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ConcurrentModificationException;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * @author Sneha
 *
 */
public class TypePanel extends JPanel {
	private JPanel typeButtonPanel;
	private JButton glassButton;
	private JButton paperButton;
	private Rcm recyclingMachine;
	private Icon glassIcon;
	private JButton newTypeButton;
	private JButton metalButton;
	private TimerTask timerTask;
	private Timer timer;
	
	/**
	 * 
	 */
	public TypePanel(Rcm rcm) {
		setLayout(null);
		setSize(Constants.TYPE_PANEL_WIDTH, Constants.TYPE_PANEL_HEIGHT);
		setLocation(Constants.TYPE_PANEL_LOCATIONX,Constants.TYPE_PANEL_LOCATIONY);
		//setBackground(new Color(  204, 204, 154));
		//setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		setVisible(true);
		
		recyclingMachine = rcm;
		this.add(createTypeButtonPanel());
		createActionListeners();
		timerTask = new TimerTask() {
			
			@Override
			public void run() {
				checkType();
				
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 1000, 1000);
	}
	
	private JPanel createTypeButtonPanel() {
		typeButtonPanel = new JPanel();
		typeButtonPanel.setLayout(null);
		typeButtonPanel.setSize(Constants.TYPE_PANEL_WIDTH, Constants.TYPE_PANEL_HEIGHT);
		typeButtonPanel.setLocation(0,0);
		//typeButtonPanel.setBackground(new Color(  204, 204, 154));
		typeButtonPanel.setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		
		glassButton = new JButton();
		glassButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		glassButton.setLocation(20, 20);
		glassButton.setSize(100, 50);
		glassButton.setVisible(false); 
		
		try {
			File imageFile = new File("resources/glass.jpg");
			Image img = ImageIO.read(imageFile);
			Image newImg = img.getScaledInstance( 100, 50,  java.awt.Image.SCALE_SMOOTH ) ;
			ImageIcon icon = new ImageIcon(newImg);
			glassButton.setIcon(new ImageIcon(newImg));
		} catch(IOException e) {
			System.err.println("Error reading the image file");
			
		}
		typeButtonPanel.add(glassButton);
		
		paperButton = new JButton();
		paperButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		paperButton.setLocation(140, 20);
		paperButton.setSize(100, 50);
		paperButton.setVisible(false);
		typeButtonPanel.add(paperButton);		
		
		try {
			File imageFile = new File("resources/paper.jpg");
			Image img = ImageIO.read(imageFile);
			Image newImg = img.getScaledInstance( 100, 50,  java.awt.Image.SCALE_SMOOTH ) ;
			ImageIcon icon = new ImageIcon(newImg);
			paperButton.setIcon(new ImageIcon(newImg));
			
		} catch(IOException e) {
			System.err.println("Error reading the image file");
		} 
		
		metalButton = new JButton();
		metalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		metalButton.setLocation(260, 20);
		metalButton.setSize(100, 50);
		metalButton.setVisible(false);
		typeButtonPanel.add(metalButton);		
		
		try {
			File imageFile = new File("resources/metal.png");
			Image img = ImageIO.read(imageFile);
			Image newImg = img.getScaledInstance( 100, 50,  java.awt.Image.SCALE_SMOOTH ) ;
			ImageIcon icon = new ImageIcon(newImg);
			metalButton.setIcon(new ImageIcon(newImg));
			
		} catch(IOException e) {
			System.err.println("Error reading the image file");
		} 

		return typeButtonPanel;
	
	}
	
	private void checkType() {

		try {
			Set<String> types = recyclingMachine.getTypePrice().keySet();

			/*
			for (String type : types) {
				System.out.println(type
						+ recyclingMachine.getTypePrice().get(type));
			}
			*/

			if (types.contains("glass")) {
				glassButton.setVisible(true);
			} else {
				glassButton.setVisible(false);
			}

			if (types.contains("paper")) {
				paperButton.setVisible(true);
			} else {
				paperButton.setVisible(false);
			}

			if (types.contains("metal")) {
				metalButton.setVisible(true);
			} else {
				metalButton.setVisible(false);
			}
		} catch (ConcurrentModificationException e) {

		}
	}
	
	
	private void createActionListeners() {
		glassButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int result = r.nextInt(High-Low) + Low;
				recyclingMachine.addItem("glass", result);
				
			}
		}); 
		
		paperButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int result = r.nextInt(High-Low) + Low;
				recyclingMachine.addItem("paper", result);
				
			}
		}); 
		
		metalButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int Low = 1;
				int High = 6;
				int result = r.nextInt(High-Low) + Low;
				recyclingMachine.addItem("metal", result);
				
			}
		}); 
	}
	
}
