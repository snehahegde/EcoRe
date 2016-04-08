package vanahallichandranna.ecore.rmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RecyclingItemPanel extends JPanel {
	private Rmos rmos;
	private JLabel priceLabel;
	/**
	 * 
	 */
	public RecyclingItemPanel(Rmos r) {
		rmos = r;
		setLayout(null);
		//setSize(Constants.OPTIONS_PANEL_WIDTH, Constants.OPTIONS_PANEL_HEIGHT);
		this.setLocation(220, 10);
		setBackground(new Color( 228, 157, 103));
		this.setSize(570, Constants.WINDOW_HEIGHT - 2*10);
		createTabbedPanel();
		this.setVisible(true);
	}
	
	private void createTabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setSize(470, Constants.WINDOW_HEIGHT - 2*10 - 2*50);
        tabbedPane.setLocation(50, 50);
 
        tabbedPane.addTab("Add Type", createAddTypePanel());
        tabbedPane.addTab("Remove Type", createRemoveTypePanel());
        tabbedPane.addTab("Change Price", createChangePricePanel());
        this.add(tabbedPane);	
	}
	
	private JPanel createAddTypePanel() {
		JPanel addTypePanel = new JPanel();
		addTypePanel.setLayout(null);
		addTypePanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif",Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel addTypeLabel = new JLabel("Add Type", SwingConstants.CENTER);
		addTypeLabel.setFont(newFont);
		addTypeLabel.setLocation(10, 50);
		addTypeLabel.setSize(400, 50);
		addTypePanel.add(addTypeLabel);
		//addMachinePanel.setSize(300, 75);
		
		JLabel typeLabel = new JLabel("Enter the new type:");
		//machineId.setFont(font);
		typeLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		typeLabel.setLocation(70, 150);
		typeLabel.setSize(180, 50);
		addTypePanel.add(typeLabel);
		
		JTextField typeField = new JTextField(20);
		typeField.setLocation(250, 150);
		typeField.setSize(150, 50);
		addTypePanel.add(typeField);
		
		JLabel priceLabel = new JLabel("Enter the price: ");
		//machineId.setFont(font);
		priceLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		priceLabel.setLocation(100, 250);
		priceLabel.setSize(150, 50);
		addTypePanel.add(priceLabel);
		
		JTextField priceField = new JTextField(20);
		priceField.setLocation(250, 250);
		priceField.setSize(150, 50);
		addTypePanel.add(priceField);
		
		JButton addButton = new JButton("Add");
		addButton.setLocation(150, 400);
		addButton.setSize(150, 50);
		addTypePanel.add(addButton);
		
		addButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				rmos.addType(typeField.getText(), Integer.parseInt(priceField.getText()));	
				typeField.setText("");
				priceField.setText("");
				
			}
		});
		
		return addTypePanel;
	}
	
	private JPanel createRemoveTypePanel() {
		JPanel removeTypePanel = new JPanel();
		removeTypePanel.setLayout(null);
		removeTypePanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel removeTypeLabel = new JLabel("Remove Type", SwingConstants.CENTER);
		removeTypeLabel.setFont(newFont);
		removeTypeLabel.setLocation(10, 50);
		removeTypeLabel.setSize(400, 50);
		removeTypePanel.add(removeTypeLabel);
		//addMachinePanel.setSize(300, 75);
		
		JLabel typeLabel = new JLabel("Select the type to be removed : ");
		//machineId.setFont(font);
		typeLabel.setFont(myFont.deriveFont(Font.BOLD, 16F));
		typeLabel.setLocation(30, 200);
		typeLabel.setSize(250, 50);
		removeTypePanel.add(typeLabel);
		
		JComboBox<String> typesComboBox = new JComboBox<String>();
		typesComboBox.setLocation(280, 200);
		typesComboBox.setSize(150, 50);
		removeTypePanel.add(typesComboBox);
		
		removeTypePanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				typesComboBox.removeAllItems();
				Set<String> types = rmos.getTypePrice().keySet();
				for (String type : types) {
					typesComboBox.addItem(type);
				}
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
					
		JButton removeButton = new JButton("Remove");
		removeButton.setLocation(150, 300);
		removeButton.setSize(150, 50);
		removeTypePanel.add(removeButton);
		
		removeButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (typesComboBox.getSelectedItem() != null) {
					rmos.removeType(typesComboBox.getSelectedItem().toString());
					typesComboBox.removeAllItems();
					Set<String> types = rmos.getTypePrice().keySet();
					for (String type : types) {
						typesComboBox.addItem(type);
					}
				}
				//rmos.removeType(typesComboBox.getSelectedItem().toString());				
			}
		});
		
		return removeTypePanel;
	}
	
	private JPanel createChangePricePanel() {
		JPanel changePricePanel = new JPanel();
		changePricePanel.setLayout(null);
		changePricePanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel changePriceLabel = new JLabel("Change Price", SwingConstants.CENTER);
		changePriceLabel.setFont(newFont);
		changePriceLabel.setLocation(10, 50);
		changePriceLabel.setSize(400, 50);
		changePricePanel.add(changePriceLabel);
		
		JLabel typeLabel = new JLabel("Select the type : ");
		typeLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		typeLabel.setLocation(100, 150);
		typeLabel.setSize(150, 50);
		changePricePanel.add(typeLabel);
		
		JComboBox<String> typesComboBox = new JComboBox<String>();
		typesComboBox.setLocation(250, 150);
		typesComboBox.setSize(150, 50);
		changePricePanel.addComponentListener(new ComponentListener() {			
			@Override
			public void componentShown(ComponentEvent e) {
				typesComboBox.removeAllItems();
				Set<String> types = rmos.getTypePrice().keySet();
				for (String type : types) {
					typesComboBox.addItem(type);
				}
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
			}
		});
		changePricePanel.add(typesComboBox);
		
		typesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(typesComboBox.getSelectedItem() != null) {
					double price = rmos.getTypePrice().get(
							typesComboBox.getSelectedItem());
					priceLabel.setText(Double.toString(price));
					priceLabel.setVisible(true);
				}
				
			}
		});
		
		JLabel currentPriceLabel = new JLabel("Current price : ");
		currentPriceLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		currentPriceLabel.setLocation(100, 250);
		currentPriceLabel.setSize(150, 50);
		changePricePanel.add(currentPriceLabel);
		
		priceLabel = new JLabel();
		priceLabel.setVisible(false);
		priceLabel.setLocation(250, 250);
		priceLabel.setSize(150, 50);
		changePricePanel.add(priceLabel);
		
		JLabel newPriceLabel = new JLabel("New price : ");
		newPriceLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		newPriceLabel.setLocation(100, 350);
		newPriceLabel.setSize(150, 50);
		changePricePanel.add(newPriceLabel);
		
		JTextField newPriceField = new JTextField(20);
		newPriceField.setLocation(250, 350);
		newPriceField.setSize(150, 50);
		changePricePanel.add(newPriceField);
		
		JButton changeButton = new JButton("Change");
		changeButton.setLocation(150, 450);
		changeButton.setSize(150, 50);
		changePricePanel.add(changeButton);
		
		changeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rmos.changeTypePrice(typesComboBox.getSelectedItem().toString(), Double.parseDouble(newPriceField.getText()));
				double price = rmos.getTypePrice().get(
						typesComboBox.getSelectedItem());
				priceLabel.setText(Double.toString(price));
				priceLabel.setVisible(true);
				newPriceField.setText("");
			}
		});
		
		return changePricePanel;
	}


	
}
