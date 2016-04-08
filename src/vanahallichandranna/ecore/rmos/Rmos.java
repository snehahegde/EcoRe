package vanahallichandranna.ecore.rmos;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

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
public class Rmos {
	private RmosUI rmosUI;
	private Database database;
	// // // private HashMap<String, RecyclingMachine> recyclingMachines;
	private HashMap<String, Double> typePrice;
	private Set<String> machineIds;
	
	public Rmos() {
		rmosUI = null;
		database = null;
		// recyclingMachines = new HashMap<String,RecyclingMachine>();
		this.machineIds = new HashSet<String>();
		
		typePrice = new HashMap<String, Double>();
		
		//// Test
	}
	
	private void addSimulatedRcms() {
		//addRecyclingMachine("RCM01");
		//addRecyclingMachine("ABC200");
	}
	
	public JPanel createUi() {
		rmosUI = new RmosUI(this);
		return rmosUI.getPanel();
	}

	public void setDatabase(Database database) {
		this.database = database;		
		addSimulatedRcms();
	}
	
	public Set<String> listRecyclingMachines() {
		return this.machineIds;
	}
	
	public RecyclingMachine getRecyclingMachine(String id) {
		if(id != null && id != "") {
			return database.getRecyclingMachine(id);
		}
		return null;
	}
	
	public void activateRecyclingMachine(String machineId) {
		database.updateRecyclingMachineState(machineId, Constants.RCM_OP_STATE_ACTIVED);
		//RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		//recyclingMachine.activateRecyclingMachine();
	}
	
	public void deactivateRecyclingMachine(String machineId) {
		database.updateRecyclingMachineState(machineId, Constants.RCM_OP_STATE_DEACTIVED);
		
		//RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		//recyclingMachine.deactivateRecyclingMachine();
	}
	
	public boolean addRecyclingMachine(String machineId) {
		RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		if(recyclingMachine != null) {
			this.machineIds.add(recyclingMachine.getMachineId());
			/*
			RecyclingMachine newRecyclingMachine = new RecyclingMachine(
					recyclingMachine);
			recyclingMachines.put(machineId, newRecyclingMachine);
			*/
			return true;
		} else {
			return false;
		}
	}
	
	public void removeRecyclingMachine(String machineId) {
		this.machineIds.remove(machineId);
	}
	
	public void addType(String type, double price) {
		typePrice.put(type, price);
		this.database.updateTypePrice(type, price);
		/*
		Set<String> machineIds = recyclingMachines.keySet();
		for (String machineId : machineIds) {
			recyclingMachines.get(machineId).getTypePrice().put(type, price);
		}
		*/
	}
	
	public void removeType(String type) {
		typePrice.remove(type);
		this.database.removeType(type);
		/*Set<String> machineIds = recyclingMachines.keySet();
		for (String machineId : machineIds) {
			recyclingMachines.get(machineId).getTypePrice().remove(type);
		}
		*/
	}
	
	public void changeTypePrice(String type, double newPrice) {
		typePrice.put(type, newPrice);
		this.database.updateTypePrice(type, newPrice);

		/*
		Set<String> machineIds = recyclingMachines.keySet();
		for (String machineId : machineIds) {
			RecyclingMachine rcm = recyclingMachines.get(machineId);
			//.getTypePrice().put(type, newPrice);
			rcm.changeTypePrice(type, newPrice);
		}
		*/
	}
	
	public double getAvailableCash(String machineId) {
		RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		return recyclingMachine.getCashInRCM();
	}
	
	public HashMap<String, Double> getTypePrice() {
		typePrice = database.getRecyclableItemsPrice();
		return typePrice;
	}
	
	public void resetRecylingMachine(String machineId) {
		database.resetRecyclingMachine(machineId);
		
		//RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		//recyclingMachine.emptyRecyclingMachine();
	}
	
	public int getRecycledItemsInMonth(String machineId, Date now) {
		RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		return recyclingMachine.getRecycledItemsInAMonth(now);
	}
	
	public Date getLastEmptiedTime(String machineId) {
		RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		return recyclingMachine.getLastEmptiedTime();
	}
}
