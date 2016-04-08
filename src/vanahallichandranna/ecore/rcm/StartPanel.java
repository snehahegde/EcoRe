package vanahallichandranna.ecore.rcm;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class StartPanel extends JPanel implements DynamicPanelInterface {
	private JButton startButton;
	private Rcm recyclingMachine;
	private BufferedImage rcmImage;
	/**
	 * 
	 */
	public StartPanel(DynamicPanel dp, Rcm rcm) {
		setLayout(null);
		setSize(Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT);
		setLocation(0,0);
		//setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		//setBackground(bg);
		//setBackground(new Color(153, 255, 153));
		setVisible(true);
		loadImage();
		recyclingMachine = rcm;
		createStartButton(dp);
		
		
	}
	
	private void loadImage() {
		
		try {
			File imageFile = new File("resources/ecore.jpg");
			rcmImage = ImageIO.read(imageFile);
		} catch(IOException e) {
			System.err.println("Error reading the image file");
			rcmImage = null;
		}
		
	}
	
	public void paint(Graphics g){
        super.paint(g);
        if(rcmImage != null) {
			g.drawImage(rcmImage, 0, 0, Constants.DISPLAY_PANEL_WIDTH, Constants.DISPLAY_PANEL_HEIGHT, null);
		}

        Font myFont = new Font("Serif", Font.ITALIC | Font.BOLD, 16);
	    Font newFont = myFont.deriveFont(38F);
	    g.setFont(newFont);
        g.drawString("Eco Re",Constants.DISPLAY_PANEL_WIDTH/2 - 60, 50);
        
        startButton.paintComponents(g);
        //startButton.paint(g);
        startButton.repaint();
    }

	private void createStartButton(DynamicPanel dp) {
		startButton = new JButton("Start");
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		startButton.setLocation(Constants.DISPLAY_PANEL_WIDTH/2 - 50, 75);
		startButton.setBackground(Constants.DISPLAY_PANEL_BACKGROUND_COLOR);
		startButton.setSize(100, 50);
		this.add(startButton);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(recyclingMachine.getOperationalStatus().equals("Activated")){
					recyclingMachine.createSession();
					dp.showPanel(Constants.PANEL_ID_OPTION);
				} else {
					JOptionPane.showMessageDialog(null, "Recycling machine is deactivated");
				}
				
				
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
