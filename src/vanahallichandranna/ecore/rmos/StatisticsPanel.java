package vanahallichandranna.ecore.rmos;

import graph.BarGraph;
import graph.DataSource;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import vanahallichandranna.ecore.ecore.RecycledItem;
import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class StatisticsPanel extends JPanel implements DataSource {
	private Rmos rmos;
	private BarGraph barGraphPanel;
	private String graphDataType;
	private String selectedRcmId;
	private JPanel thisPanel;
	
	/**
	 * 
	 */
	public StatisticsPanel(Rmos r) {
		rmos = r;
		graphDataType = "";
		selectedRcmId = "";
		setLayout(null);
		this.setLocation(220, 10);
		setBackground(new Color( 228, 157, 103));
		this.setSize(570, Constants.WINDOW_HEIGHT - 2*10);
		createTabbedPanel();
		this.setVisible(true);
		
		thisPanel = this;
		
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				for(Component c: thisPanel.getComponents() ) {
					c.setVisible(false);
					c.setVisible(true);
					c.repaint();
				}
				
				System.out.println("Statistics panel is being shown");
				repaint();
				revalidate();
				
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void createTabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setSize(470, Constants.WINDOW_HEIGHT - 2*10 - 2*50);
        tabbedPane.setLocation(50, 50);
 
        tabbedPane.addTab("Items Recycled", createRecycledItemsPanel());
        tabbedPane.addTab("Last Emptied", createLastEmptiedPanel());
        tabbedPane.addTab("Visualization", createUsageStatisticsPanel());
        this.add(tabbedPane);	
	}
	
	private JPanel createRecycledItemsPanel() {
		JPanel recycledItemsStatisticsPanel = new JPanel();
		recycledItemsStatisticsPanel.setLayout(null);
		recycledItemsStatisticsPanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel recycledItemLabel = new JLabel("Recycled Items", SwingConstants.CENTER);
		recycledItemLabel.setFont(newFont);
		recycledItemLabel.setLocation(25, 50);
		recycledItemLabel.setSize(400, 50);
		recycledItemsStatisticsPanel.add(recycledItemLabel);
		
		JLabel machineLabel = new JLabel("Select the machine : ");
		machineLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		machineLabel.setLocation(50, 150);
		machineLabel.setSize(200, 50);
		recycledItemsStatisticsPanel.add(machineLabel);
		
		JComboBox<String> machinesComboBox = new JComboBox<String>();
		machinesComboBox.setLocation(250, 150);
		machinesComboBox.setSize(150, 50);
		Set<String> machineIds = rmos.listRecyclingMachines();
		for (String machineId : machineIds) {
			machinesComboBox.addItem(machineId);
		}
		recycledItemsStatisticsPanel.add(machinesComboBox);
		
		recycledItemsStatisticsPanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				machinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					machinesComboBox.addItem(machineId);
				}
				System.out.println("Adding to combo box");
				repaint();
				revalidate();
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		
		JLabel numberOfRecycledItemsLabel = new JLabel();
		recycledItemsStatisticsPanel.add(numberOfRecycledItemsLabel);
		
		machinesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String message = "Number of items recycled in a month :  ";
				String machineId = (String) machinesComboBox.getSelectedItem();
				if(machineId != null) {
					message += rmos.getRecycledItemsInMonth(machinesComboBox.getSelectedItem().toString(), new Date());;
				}
				
				numberOfRecycledItemsLabel.setText(message);
				numberOfRecycledItemsLabel.setFont(myFont.deriveFont(Font.BOLD, 16F));
				numberOfRecycledItemsLabel.setLocation(100, 300);
				numberOfRecycledItemsLabel.setSize(350, 50);
				
				numberOfRecycledItemsLabel.setVisible(true);
			}
		});
			
		return recycledItemsStatisticsPanel; 
	}
	
	private JPanel createLastEmptiedPanel() {
		JPanel lastEmptiedPanel = new JPanel();
		lastEmptiedPanel.setLayout(null);
		lastEmptiedPanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif",Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel LastEmptiedTimeLabel = new JLabel("Last Emptied Time", SwingConstants.CENTER);
		LastEmptiedTimeLabel.setFont(newFont);
		LastEmptiedTimeLabel.setLocation(10, 50);
		LastEmptiedTimeLabel.setSize(400, 50);
		lastEmptiedPanel.add(LastEmptiedTimeLabel);
		
		JLabel machineLabel = new JLabel("Select the machine : ");
		machineLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		machineLabel.setLocation(50, 150);
		machineLabel.setSize(200, 50);
		lastEmptiedPanel.add(machineLabel);
		
		JComboBox<String> machinesComboBox = new JComboBox<String>();
		machinesComboBox.setLocation(250, 150);
		machinesComboBox.setSize(150, 50);
		Set<String> machineIds = rmos.listRecyclingMachines();
		for (String machineId : machineIds) {
			machinesComboBox.addItem(machineId);
		}
		lastEmptiedPanel.add(machinesComboBox);
		
		lastEmptiedPanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				machinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					machinesComboBox.addItem(machineId);
				}
				repaint();
				revalidate();
			}

			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		
		/*String[] types = new String[] {"All", "RCM01", "RCM02", "RCM03"};
		JComboBox<String> machinesComboBox = new JComboBox<String>(types);
		machinesComboBox.setLocation(250, 200);
		machinesComboBox.setSize(150, 50);
		lastEmptiedPanel.add(machinesComboBox);*/
			
		/*JButton enterButton = new JButton("Enter");
		enterButton.setLocation(150, 300);
		enterButton.setSize(150, 50);
		lastEmptiedPanel.add(enterButton); */
		
		JLabel lastEmptiedTimeLabel = new JLabel();
		lastEmptiedPanel.add(lastEmptiedTimeLabel);
		
		machinesComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String message = "Last emptied time :  ";
				String machineId = (String) machinesComboBox.getSelectedItem();
				if( machineId != null ) {
					message += rmos.getLastEmptiedTime(machinesComboBox.getSelectedItem().toString());
				}
				
				lastEmptiedTimeLabel.setText(message);
				lastEmptiedTimeLabel.setFont(myFont.deriveFont(Font.BOLD, 16F));
				lastEmptiedTimeLabel.setLocation(100, 300);
				lastEmptiedTimeLabel.setSize(350, 50);
				
				lastEmptiedTimeLabel.setVisible(true);
			}
		});
		
		return lastEmptiedPanel;		
	}
	
	private JPanel createUsageStatisticsPanel() {
		JPanel usageStatisticsPanel = new JPanel();
		usageStatisticsPanel.setLayout(null);
		usageStatisticsPanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel usageStatisticsLabel = new JLabel("Usage Statistics", SwingConstants.CENTER);
		usageStatisticsLabel.setFont(newFont);
		usageStatisticsLabel.setLocation(10, 50);
		usageStatisticsLabel.setSize(400, 50);
		usageStatisticsPanel.add(usageStatisticsLabel);
		
		JLabel machineLabel = new JLabel("Select the machine : ");
		machineLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		machineLabel.setLocation(50, 150);
		machineLabel.setSize(200, 50);
		usageStatisticsPanel.add(machineLabel);
		
		//String[] types = new String[] {"All", "RCM01", "RCM02", "RCM03"};
		JComboBox<String> machinesComboBox = new JComboBox<String>();
		machinesComboBox.setLocation(250, 150);
		machinesComboBox.setSize(150, 50);
		Set<String> machineIds = rmos.listRecyclingMachines();
		for (String machineId : machineIds) {
			machinesComboBox.addItem(machineId);
		}
		usageStatisticsPanel.add(machinesComboBox);
		
		machinesComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectedRcmId = (String) machinesComboBox.getSelectedItem();
				barGraphPanel.update();
			}
			
		});
		
		usageStatisticsPanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				machinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					machinesComboBox.addItem(machineId);
				}
				repaint();
				revalidate();
		}
			@Override
			public void componentHidden(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentMoved(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentResized(ComponentEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});


		JLabel dataTypeLabel = new JLabel("Select the data type : ");
		dataTypeLabel.setFont(myFont.deriveFont(Font.BOLD, 18F));
		dataTypeLabel.setLocation(50, 175);
		dataTypeLabel.setSize(200, 50);
		usageStatisticsPanel.add(dataTypeLabel);
		

		String[] dataTypes = new String[] {"Select a type", "Cash", "Recycled Items"};
		JComboBox<String> dataTypeComboBox = new JComboBox<String>(dataTypes);
		dataTypeComboBox.setLocation(250, 195);
		dataTypeComboBox.setSize(150, 50);
		usageStatisticsPanel.add(dataTypeComboBox);

		dataTypeComboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				graphDataType = (String) dataTypeComboBox.getSelectedItem();
				if(graphDataType != null && graphDataType != "") {
					barGraphPanel.update();
					barGraphPanel.setVisible(false);
					barGraphPanel.setVisible(true);
				}
			}
		});

		/*
		JButton enterButton = new JButton("Enter");
		enterButton.setLocation(150, 250);
		enterButton.setSize(150, 50);
		usageStatisticsPanel.add(enterButton);
		*/
		
		barGraphPanel = new BarGraph(this);
		barGraphPanel.setLayout(null);
		barGraphPanel.setSize(400, 400);
		barGraphPanel.setLocation(50, 250);
		barGraphPanel.setVisible(true);
		
		usageStatisticsPanel.add(barGraphPanel);
		
		return usageStatisticsPanel;		
	}

	@Override
	public ArrayList<String> getDataTypeLabels() {
		// TODO Auto-generated method stub
		RecyclingMachine rcm = rmos.getRecyclingMachine("SCURCM01");
		ArrayList<String> labels = new ArrayList<String>();
		ArrayList<String> typeKeys =  new ArrayList<>(rcm.getTypePrice().keySet());
		java.util.Collections.sort(typeKeys);

		labels.addAll(typeKeys);
		return labels;
	}

	@Override
	public int getDataPoints() {
		ArrayList<ArrayList<Double>> data = getValues();
		if(data != null) {
			return getValues().size();
		}
		return 0;
	}

	@Override
	public int getDataTypes() {
		RecyclingMachine rcm = rmos.getRecyclingMachine("SCURCM01");
		
		return rcm.getTypePrice().keySet().size();
	}


	@Override
	public ArrayList<ArrayList<Double>> getValues() {
		if(graphDataType.equals("Recycled Items")) {
			return getRecycledItems();
		} else if(graphDataType.equals("Cash")) {
			return getCashInRcm();
		}
		return null;
	}
	
	public ArrayList<ArrayList<Double>> getRecycledItems() {

		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
		RecyclingMachine rcm = rmos.getRecyclingMachine(selectedRcmId);
		if( rcm == null ) {
			System.err.println("Invalid RCM id: " + selectedRcmId);
			return null;
		}
		
		HashMap<Calendar, ArrayList<RecycledItem>> itemsByDay = rcm.getRecycledItemsByDay();
		ArrayList<Calendar> keys = new ArrayList<Calendar>(itemsByDay.keySet()) ;
		java.util.Collections.sort( keys );
		
		ArrayList<String> types = getDataTypeLabels();
		
		for( Calendar day : keys ) {
			//System.out.println(day);
			ArrayList<RecycledItem> items = itemsByDay.get(day);
			//System.out.println("Number of items = " + items.size());
			// Group by types
			HashMap<String, Double> itemWeights = new HashMap<>();
			for(RecycledItem i: items) {
				double currentWeight = 0;
				//System.out.println("Item: " + i);
				if(itemWeights.containsKey(i.getItemType())) {
					currentWeight = itemWeights.get(i.getItemType());
				}
				currentWeight += i.getItemWeight();
				itemWeights.put(i.getItemType(), currentWeight);
				//System.out.println("Current weight of " + i.getItemType() + " is " + currentWeight);
			}
			
			//System.out.println("Keys: " + itemWeights.keySet());
			ArrayList<Double> itemWeightsList = new ArrayList<>();
			for(String type : types) {
				//System.out.println("Type: " + type);
				if(itemWeights.containsKey(type)) {	
					//System.out.println("Contains " + type);
					itemWeightsList.add(itemWeights.get(type));
				} else {
					itemWeightsList.add(0.0);
				}
			}
			for(Double i : itemWeightsList) {
				//System.out.println(i);
			}
			data.add(itemWeightsList);
		}
		
		return data;

	}
	
	public ArrayList<ArrayList<Double>> getCashInRcm() {
		RecyclingMachine rcm = rmos.getRecyclingMachine(selectedRcmId);
		if(rcm == null) {
			System.out.println("Invalid RCM id: " + selectedRcmId);
			return null;
		}
		
		ArrayList<RecycledItem> items = rcm.getAllItemsInTheRecyclingMachine();
		HashMap<String, Double> cashPaidPerCategory = new HashMap<>();
		cashPaidPerCategory.put("All", 0.0);
		
		for(RecycledItem item: items) {
			double currentCashPaid = 0;
			String itemType = item.getItemType();
			if(cashPaidPerCategory.containsKey(itemType)) {
				currentCashPaid = cashPaidPerCategory.get(itemType);
			}
			currentCashPaid += item.getPricePaid();
			cashPaidPerCategory.put(itemType, currentCashPaid);
			
			double totalCashPaid = cashPaidPerCategory.get("All");
			cashPaidPerCategory.put("All", totalCashPaid + item.getPricePaid());
		}
		
		ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
		ArrayList<String> keys = new ArrayList<String>( cashPaidPerCategory.keySet() );
		java.util.Collections.sort(keys);
		
		int emptyBarsToAdd = 0; // So that different colors show up
		
		for( String type: keys ) {
			Double cashPaid = cashPaidPerCategory.get(type);
			//System.out.println("Type: " + type + " Amount: " + cashPaid);
			ArrayList<Double> l = new ArrayList<>();
			// Just one data
			for(int i=0; i<emptyBarsToAdd; i++) {
				l.add(0.0);
			}
			l.add(cashPaid);
			data.add(l);
			emptyBarsToAdd += 1;
		}
		
		
		return data;
	}
	

	@Override
	public ArrayList<String> getDataSourceLabels() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		barGraphPanel.update();
		barGraphPanel.repaint();
		revalidate();
	}
	
}
