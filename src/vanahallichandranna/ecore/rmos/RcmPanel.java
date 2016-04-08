package vanahallichandranna.ecore.rmos;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import vanahallichandranna.ecore.ecore.ObservableRCM;
import vanahallichandranna.ecore.ecore.RecyclingMachine;


public class RcmPanel extends JPanel {
	private Rmos rmos;
	private JComboBox<String> recyclingMachinesComboBox;
	
	// Panels
	private AvailableAmountPanel availableAmountPanel;

	public RcmPanel(Rmos r) {
		rmos = r;
		setLayout(null);
		this.setLocation(220, 10);
		setBackground(new Color(228, 157, 103));
		this.setSize(570, Constants.WINDOW_HEIGHT - 2*10);
		createTabbedPanel();
		this.setVisible(true);
	}

	private void createTabbedPanel() {
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setSize(470, Constants.WINDOW_HEIGHT - 2*10 - 2*50);
        tabbedPane.setLocation(50, 50);
 
        tabbedPane.addTab("Add Machine", createAddMachinePanel());
        tabbedPane.addTab("Remove Machine", createRemoveMachinePanel());
        tabbedPane.addTab("Statistics", createStatisticsPanel());
        this.add(tabbedPane);	
	}
	
	private JPanel createAddMachinePanel() {
		JPanel addMachinePanel = new JPanel();
		addMachinePanel.setLayout(null);
		addMachinePanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel addMachineLabel = new JLabel("Add Machine", SwingConstants.CENTER);
	    addMachineLabel.setFont(newFont);
		addMachineLabel.setLocation(10, 50);
		addMachineLabel.setSize(400, 50);
		addMachinePanel.add(addMachineLabel);
		//addMachinePanel.setSize(300, 75);
		
		JLabel machineId = new JLabel("Machine ID : ");
		//machineId.setFont(font);
		machineId.setFont(myFont.deriveFont(Font.BOLD, 18F));
		machineId.setLocation(100, 200);
		machineId.setSize(150, 50);
		addMachinePanel.add(machineId);
		
		JTextField machineIdField = new JTextField(20);
		machineIdField.setLocation(250, 200);
		machineIdField.setSize(150, 50);
		addMachinePanel.add(machineIdField);
		
		JButton addButton = new JButton("Add");
		addButton.setLocation(150, 300);
		addButton.setSize(150, 50);
		addMachinePanel.add(addButton);
		
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (rmos.addRecyclingMachine(machineIdField.getText()) == true) {
					JOptionPane.showMessageDialog(null, "Recycling Machine added successfully.");
				}
				else {
					 JOptionPane.showMessageDialog(null, "Invalid recycling machine Id");
				}
				machineIdField.setText("");				
			}
		});
		
		return addMachinePanel;
	}
	
	private JPanel createRemoveMachinePanel() {
		JPanel removeMachinePanel = new JPanel();
		removeMachinePanel.setLayout(null);
		removeMachinePanel.setBackground(new Color(235, 202, 151));
		
		Font myFont = new Font("Sans Serif",Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(24F);
		JLabel removeMachineLabel = new JLabel("Remove Machine", SwingConstants.CENTER);
	    removeMachineLabel.setFont(newFont);
		removeMachineLabel.setLocation(10, 50);
		removeMachineLabel.setSize(400, 50);
		removeMachinePanel.add(removeMachineLabel);
		//addMachinePanel.setSize(300, 75);
		
		JLabel machineId = new JLabel("Select the machine Id:");
		//machineId.setFont(font);
		machineId.setFont(myFont.deriveFont(Font.BOLD, 18F));
		machineId.setLocation(40, 200);
		machineId.setSize(200, 50);
		removeMachinePanel.add(machineId);
		
		JComboBox<String> recyclingMachinesComboBox = new JComboBox<String>();
		recyclingMachinesComboBox.setLocation(260, 200);
		recyclingMachinesComboBox.setSize(150, 50);
		removeMachinePanel.add(recyclingMachinesComboBox);
		
		removeMachinePanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				recyclingMachinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					recyclingMachinesComboBox.addItem(machineId);
				}
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
		
		JButton removeButton = new JButton("Remove");
		removeButton.setLocation(150, 300);
		removeButton.setSize(150, 50);
		removeMachinePanel.add(removeButton);
		
		removeButton.addActionListener(new  ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rmos.removeRecyclingMachine(recyclingMachinesComboBox.getSelectedItem().toString());
				JOptionPane.showMessageDialog(null, "Recycling Machine removed successfully.");
				recyclingMachinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					recyclingMachinesComboBox.addItem(machineId);
				}
			}
		});
		
		return removeMachinePanel;
	}
	
	private JPanel createStatisticsPanel() {
		JPanel statisticsPanel = new JPanel();
		statisticsPanel.setLayout(null);
		statisticsPanel.setBackground(new Color(235, 202, 151));
		
		//Machine Id label and TextField
		JLabel machineId = new JLabel("Machine ID : ");
		Font myFont = new Font("Sans Serif", Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(18F);
		machineId.setFont(myFont.deriveFont(Font.BOLD));
		machineId.setLocation(100, 25);
		machineId.setSize(100, 50);
		statisticsPanel.add(machineId);
		
		recyclingMachinesComboBox = new JComboBox<String>();
		recyclingMachinesComboBox.setLocation(200, 25);
		recyclingMachinesComboBox.setSize(150, 50);
		statisticsPanel.add(recyclingMachinesComboBox);
		
		ObservableRCM observableRcm = new ObservableRCM();
		
		statisticsPanel.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				recyclingMachinesComboBox.removeAllItems();
				Set<String> machineIds = rmos.listRecyclingMachines();
				for (String machineId : machineIds) {
					recyclingMachinesComboBox.addItem(machineId);
				}
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
		
		ArrayList<Observer> panels = createStatisticsOptionTabbedPanel(statisticsPanel);
		for(Observer o: panels) {
			observableRcm.addObserver(o);
		}
		
		recyclingMachinesComboBox.addActionListener(new ActionListener() {
			
			private TimerTask task = new TimerTask() {
				
				@Override
				public void run() {
					updateRcm();
				}
			};
			
			private Timer timer = new Timer();
			
			// Initializer
			{
				timer.schedule(task, 1*1000, 1*1000);
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				updateRcm();
			}
			
			public void updateRcm() {
				String machineId = (String) recyclingMachinesComboBox.getSelectedItem();
				RecyclingMachine rcm = rmos.getRecyclingMachine(machineId);
				if(rcm != null) {
					observableRcm.updateRcm(rcm);
				}
			}
		});
		
		return statisticsPanel;
		
	}
	
	private ArrayList<Observer> createStatisticsOptionTabbedPanel(JPanel statisticsPanel) {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setSize(400, Constants.WINDOW_HEIGHT - 2 * 10 - 2 * 50 - 2
				* 50 - 100);
		tabbedPane.setLocation(30, 145);

		//Observer[] observers = new Observer();
		ArrayList<Observer> observers = new ArrayList<Observer>();
		
		OperationalStatusPanel operationalStatusPanel = new OperationalStatusPanel();
		observers.add(operationalStatusPanel);
		
		AvailableAmountPanel availableAmountPanel = new AvailableAmountPanel();
		observers.add(availableAmountPanel);
		
		CapacityPanel capacityPanel = new CapacityPanel();
		observers.add(capacityPanel);
		
		tabbedPane.addTab("Operational Status", operationalStatusPanel);
		tabbedPane.addTab("Available Amount", availableAmountPanel);
		tabbedPane.addTab("Capacity", capacityPanel);
		
		
		statisticsPanel.add(tabbedPane);
		
		return observers;
		
	}
	
	private class OperationalStatusPanel extends JPanel implements ComponentListener, Observer {
		private JLabel operationalStatusLabel;
		private JButton changeStatusButton;
		private String recyclingMachineId; 
	
		public OperationalStatusPanel() {
			this.setLayout(null);	
			setBackground(new Color(235, 202, 151));
			Font myFont = new Font("Sans Serif",Font.BOLD, 14);
		    Font newFont = myFont.deriveFont(16F);
			operationalStatusLabel = new JLabel("Operational Status : ", SwingConstants.CENTER);
			operationalStatusLabel.setFont(newFont);
			operationalStatusLabel.setLocation(10, 50);
			operationalStatusLabel.setSize(400, 50);
			this.add(operationalStatusLabel);
			
			changeStatusButton = new JButton("");
			changeStatusButton.setFont(newFont);
			changeStatusButton.setLocation(100, 150);
			changeStatusButton.setSize(200, 50);
			changeStatusButton.setVisible(false);
			
			changeStatusButton.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (changeStatusButton.getText() == "Activate") {
						rmos.activateRecyclingMachine(recyclingMachineId);
						updateOperationalStatus(recyclingMachineId, "Activated");
					}
					else {
						rmos.deactivateRecyclingMachine(recyclingMachineId);
						updateOperationalStatus(recyclingMachineId, "Deactivated");
						
					}
					
				}
			});
			
			this.add(changeStatusButton);
			this.addComponentListener(this);
		}
		
		public void updateOperationalStatus(String rcmId, String status) {
			String message = "Operational Status of ";
			message += rcmId;
			message += " is ";
			message += status;
			
			operationalStatusLabel.setText(message);
			if( status.equals("Activated")) {
				changeStatusButton.setText("Deactive");
			}
			else {
				changeStatusButton.setText("Activate");
			}			
			changeStatusButton.setVisible(true);
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentShown(ComponentEvent e) {
			//ObservableRCM rcm = (ObservableRCM) o;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			RecyclingMachine recyclingMachine = ((ObservableRCM) o).getRcm();
			updateOperationalStatus(recyclingMachine.getMachineId(), recyclingMachine.getOperationalStatus());
			recyclingMachineId = recyclingMachine.getMachineId();
		}
	}
	
	private class AvailableAmountPanel extends JPanel implements ComponentListener, Observer {
		private JLabel availableAmountLabel;
		
		public AvailableAmountPanel() {
			setLayout(null);
			setBackground(new Color(235, 202, 151));
			Font myFont = new Font("Sans Serif",Font.BOLD, 14);
		    Font newFont = myFont.deriveFont(16F);
			availableAmountLabel = new JLabel("Available amount : ", SwingConstants.CENTER);
			availableAmountLabel.setFont(newFont);
			availableAmountLabel.setLocation(0, 50);
			availableAmountLabel.setSize(400, 50);
			this.add(availableAmountLabel);
			
			this.addComponentListener(this);
		}
		
		public void updateAmount(String rcmId, double amount) {
			String message = "Available amount in ";
			message += rcmId;
			message += " is $";
			message += Double.toString(amount);
			
			availableAmountLabel.setText(message);							
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentShown(ComponentEvent e) {
			//ObservableRCM rcm = (ObservableRCM) o;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			RecyclingMachine rcm = ((ObservableRCM) o).getRcm();
			updateAmount(rcm.getMachineId(), rcm.getCashInRCM());
		}
	}
	
	private JPanel createAvailableAmountPanel() {		
		return availableAmountPanel;
	}
	
	private class CapacityPanel extends JPanel implements ComponentListener, Observer {
		private JLabel filledCapacityLabel;
		private JLabel availableCapacityLabel;
		private String machineId;
		
		public CapacityPanel() {
			this.setLayout(null);
			setBackground(new Color(235, 202, 151));
			
			Font myFont = new Font("Sans Serif",Font.BOLD, 14);
		    Font newFont = myFont.deriveFont(16F);
		    filledCapacityLabel = new JLabel("Filled Capacity : ", SwingConstants.CENTER);
		    filledCapacityLabel.setFont(newFont);
		    filledCapacityLabel.setLocation(10, 50);
		    filledCapacityLabel.setSize(400, 50);
			this.add(filledCapacityLabel);
			
			availableCapacityLabel = new JLabel("Available Capacity : ", SwingConstants.CENTER);
			availableCapacityLabel.setFont(newFont);
			availableCapacityLabel.setLocation(5, 150);
			availableCapacityLabel.setSize(400, 50);
			this.add(availableCapacityLabel);
			
			JButton emptyRecyclingMachineButton = new JButton("Empty Recycling Machine");
			emptyRecyclingMachineButton.setFont(newFont);
			emptyRecyclingMachineButton.setLocation(75, 250);
			emptyRecyclingMachineButton.setSize(250, 50);
			this.add(emptyRecyclingMachineButton);
			
			emptyRecyclingMachineButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					rmos.resetRecylingMachine(machineId);					
				}
			});
			
			this.addComponentListener(this);			
		}
		
		public void updateCapacity(String rcmId, double filledCapacity, double availableCapacity) {
			String filledMessage = "Current filled capacity in ";
			filledMessage += rcmId;
			filledMessage += " is ";
			filledMessage += filledCapacity;
			filledMessage += "lb";
			filledCapacityLabel.setText(filledMessage);
			
			String availableMessage = "Current available capacity in ";
			availableMessage += rcmId;
			availableMessage += " is ";
			availableMessage += availableCapacity;
			availableMessage += "lb";
			availableCapacityLabel.setText(availableMessage);	
			
			machineId = rcmId;
		}
		
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentResized(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void componentShown(ComponentEvent e) {
			//ObservableRCM rcm = (ObservableRCM) o;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			RecyclingMachine rcm = ((ObservableRCM) o).getRcm();
			if (rcm != null) {
				updateCapacity(rcm.getMachineId(), rcm.getCurrentFilledCapacity(), rcm.getCurrentAvailableCapacity());
			}
		}
	
	}
		
		
	
/*	private JPanel createCapacityPanel() {
		JPanel capacityPanel = new JPanel();
		capacityPanel.setLayout(null);
		
		Font myFont = new Font("Sans Serif", Font.ITALIC | Font.BOLD, 14);
	    Font newFont = myFont.deriveFont(18F);
		JLabel currentCapacityLabel = new JLabel("Current Capacity of RCM01 : 1000", SwingConstants.CENTER);
		currentCapacityLabel.setFont(newFont);
		currentCapacityLabel.setLocation(10, 50);
		currentCapacityLabel.setSize(400, 50);
		capacityPanel.add(currentCapacityLabel);
		
		JLabel availableCapacityLabel = new JLabel("Available Capacity of RCM01 : 2000", SwingConstants.CENTER);
		availableCapacityLabel.setFont(newFont);
		availableCapacityLabel.setLocation(10, 150);
		availableCapacityLabel.setSize(400, 50);
		capacityPanel.add(availableCapacityLabel);
		
		return capacityPanel;
	} */


}
