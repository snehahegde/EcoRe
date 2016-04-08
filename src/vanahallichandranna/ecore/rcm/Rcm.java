package vanahallichandranna.ecore.rcm;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vanahallichandranna.ecore.ecore.Database;
import vanahallichandranna.ecore.ecore.RecycledItem;
import vanahallichandranna.ecore.ecore.RecyclingMachine;

public class Rcm extends Observable {

	private RcmUI ui;
	private RecyclingMachine recyclingMachine;
	Database database;
	
	public Rcm(Database db, String machineId) {
		this.database = db;
		this.recyclingMachine = this.database.getRecyclingMachine(machineId);
		ui = new RcmUI(this);
	}
	
	public JPanel createUi() {
		return ui.getPanel();
	}

	public void createSession() {
		recyclingMachine.createSession();
	}

	public HashMap<String, Double> getTypePrice() {
		return this.database.getRecyclableItemsPrice(this.recyclingMachine.getMachineId());

	}

	public List<RecycledItem> getRecycledItemInserted() {
		return this.recyclingMachine.getRecycledItemInserted();
	}

	public void addItem(String type, int weight) {
		boolean added = this.recyclingMachine.addItem(type, weight);
		if(!added) {
			JOptionPane.showMessageDialog(null, "No sufficient capacity");
		}
		setChanged();
		notifyObservers();
	}

	public double getTotalAmount() {
		return this.recyclingMachine.getTotalAmount();
	}

	public void endSession() {
		this.recyclingMachine.endSession();
		this.database.endSession(this.recyclingMachine.getMachineId());
	}

	public String getOperationalStatus() {
		return this.database.getRecyclingMachine(recyclingMachine.getMachineId()).getOperationalStatus();
		//return null;
	}

	public double getAvailableAmount() {		
		return this.database.getRecyclingMachine(recyclingMachine.getMachineId()).getCashInRCM();
	}

	public void payCustomer() {
		this.database.getRecyclingMachine(recyclingMachine.getMachineId()).payCustomer();
		
	}

	public double getAvailableCapacity() {
		return this.database.getRecyclingMachine(recyclingMachine.getMachineId()).getCurrentAvailableCapacity();
	}
	
	public double getTotalCapacity() {
		return this.database.getRecyclingMachine(recyclingMachine.getMachineId()).getTotalWeightInTempSession();
	}

}
