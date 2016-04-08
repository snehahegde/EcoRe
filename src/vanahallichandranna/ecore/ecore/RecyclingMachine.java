package vanahallichandranna.ecore.ecore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Set;



/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RecyclingMachine {
	// RecycledItem recycledItem;
	private double totalCapacity;
	private double currentAvailableCapacity;
	private String machineId;
	private double cashInRCM;
	//private Database database;
	private Date lastEmptiedTime;
	private String operationalStatus;
	private HashMap<String, Double> typePrice = new HashMap<String, Double>();
	// private List<Session> sessionList = new ArrayList<Session>();
	private ArrayList<RecycledItem> recycledItems = new ArrayList<>();
	
	Session tempSession;
	
	public RecyclingMachine(double cash, String machineId, double capacity) {
		this.machineId = machineId; 
		cashInRCM = cash;
		totalCapacity = capacity;
		currentAvailableCapacity = capacity;
		/*
		typePrice.put("paper", 5.0);
		typePrice.put("glass", 4.0);
		typePrice.put("plastic", 3.0);
		*/
		tempSession = null;
		//database = null;
		lastEmptiedTime = null;
		operationalStatus = Constants.OP_STATUS_DEACTIVE;
	}
	
	//copy constructor
	public RecyclingMachine(RecyclingMachine recyclingMachine) {
		this.cashInRCM = recyclingMachine.cashInRCM;
		this.machineId = recyclingMachine.machineId;
		this.totalCapacity = recyclingMachine.totalCapacity;
		this.operationalStatus = recyclingMachine.operationalStatus;
		this.currentAvailableCapacity = recyclingMachine.currentAvailableCapacity;
		Set<String> typesSupported = recyclingMachine.typePrice.keySet();
		for (String type : typesSupported) {
			typePrice.put(type, recyclingMachine.typePrice.get(type));
		}
		//sessionList = recyclingMachine.sessionList;	
		recycledItems = recyclingMachine.recycledItems;
	}
	
	/*
	public void setDatabase(Database database) {
		this.database = database;
	}
	*/
	
	public HashMap<String, Double> enquire() {
		return typePrice;		
	}
	
	public HashMap<String, Double> getTypePrice() {
		return typePrice;
	}
	
	public void createSession() {
		tempSession = new Session();
	}
	
	public void endSession() {
		//sessionList.add(tempSession);
		recycledItems.addAll(tempSession.getRecycledItemsInSession());
		
		////RecyclingMachine recyclingMachine = database.getRecyclingMachine(machineId);
		payCustomer();
		////recyclingMachine.cashInRCM = this.cashInRCM;
		//recyclingMachine.sessionList.add(tempSession);
		currentAvailableCapacity = currentAvailableCapacity - tempSession.getTotalWeightInSession();
		//tempSession = null;
		
	}
	
	public boolean addItem(String type, double weight) {		
		Set<String> typeKeys = typePrice.keySet();
		for( String typeSupported : typeKeys ) {
			if ( type.equals(typeSupported)) {
				//currentAvailableCapacity = totalCapacity - weight;
				if(tempSession.getTotalWeightInSession() + weight > currentAvailableCapacity) {
					return false;
				}
				RecycledItem newRecycledItem = new RecycledItem(type, weight);
				tempSession.addRecycledItem(newRecycledItem);
				newRecycledItem.setPrice(typePrice);
				return true;
			}
		}
		return false;
	}
	
	public void addItem(RecycledItem item) {
		this.recycledItems.add(item);
	}
	
	public double getTotalAmount() {
		return tempSession.getTotalAmountToBePaid(typePrice);
		
	}
	
	public void payCustomer() {
		if ((tempSession.getTotalAmountToBePaid(typePrice)) < cashInRCM) {
			cashInRCM = cashInRCM - tempSession.getTotalAmountToBePaid(typePrice);
			System.out.println("payment successful: " + cashInRCM);
			System.out.println(cashInRCM);
		}
		else {
			System.out.println("No sufficient cash available");
		}
	}
	
	public List<RecycledItem> getRecycledItemInserted() {
		return tempSession.getRecycledItemsInSession();
	}
	
	public ArrayList<RecycledItem> getAllItemsInTheRecyclingMachine () {
		return recycledItems;
		/*
		List<RecycledItem> itemsInRecyclingMachine = new ArrayList<RecycledItem>(); 
		for (Session session : sessionList) {
			itemsInRecyclingMachine.addAll(session.getRecycledItemsInSession());
		}
		return itemsInRecyclingMachine;
		*/
	}
	
	public double getCashInRCM() {
		return cashInRCM;
		//this.database.getCashInRcm(this.machineId);
	}
	
	public void emptyRecyclingMachine() {
		currentAvailableCapacity = totalCapacity;
		lastEmptiedTime = new Date();
		operationalStatus = "Activated";		
	}
	
	public void activateRecyclingMachine() {
		operationalStatus = "Activated";
	}

	public String getMachineId() {
		// TODO Auto-generated method stub
		return machineId;
	}
	
	public double getCurrentAvailableCapacity() {
		return currentAvailableCapacity;
	}
	
	public double getCurrentFilledCapacity() {
		return totalCapacity - currentAvailableCapacity;
	}

	public void deactivateRecyclingMachine() {
		operationalStatus = "Dectivated";		
	}
	
	public String getOperationalStatus() {
		return operationalStatus;
		
	}
	
	public int getRecycledItemsInAMonth(Date now) {
		int numberOfRecycledItems = 0;
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.set(Calendar.DAY_OF_MONTH, 1);
		Date firstOfTheMonth = c.getTime();
		for( RecycledItem item : recycledItems ) {
			if(item.itemInsertionTime.compareTo(firstOfTheMonth) >= 0 ) {
				numberOfRecycledItems = numberOfRecycledItems + 1;
			}
		}
		return numberOfRecycledItems;
	}
	
	public HashMap<Calendar, ArrayList<RecycledItem>> getRecycledItemsByDay() {
		
		HashMap<Calendar, ArrayList<RecycledItem>> itemsByDay = new HashMap<Calendar, ArrayList<RecycledItem>>();
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_MONTH, 1);
		for( RecycledItem item : recycledItems ) {
			Calendar insertedDate = Calendar.getInstance();
			insertedDate.setTime(item.getInsertedTime());
			insertedDate.set(Calendar.HOUR, 0);
			insertedDate.set(Calendar.MINUTE, 0);
			insertedDate.set(Calendar.SECOND, 0);
			insertedDate.set(Calendar.MILLISECOND,  0);
			
			ArrayList<RecycledItem> ri = null;
			if( !itemsByDay.containsKey(insertedDate) ) {
				ri = new ArrayList<>();
				itemsByDay.put(insertedDate, ri);
			}
			ri = itemsByDay.get(insertedDate);
			ri.add(item);
		}
		
		return itemsByDay;
	}
	
	public Date getLastEmptiedTime() {
		return lastEmptiedTime;
	}

	public void clearAllRecycledItems() {
		this.recycledItems.clear();
	}

	public void setOperationalStatus(String status) {
		this.operationalStatus = status;
	}

	public void setTotalCapacity(Double capacity) {
		this.totalCapacity = capacity;
	}

	public void setAvailableCapacity(Double availableCapacity) {
		this.currentAvailableCapacity = availableCapacity;
	}

	public void setCash(Double cash) {
		this.cashInRCM = cash;
	}

	public void setLastEmptied(Date lastEmptied) {
		this.lastEmptiedTime = lastEmptied;
	}

	public void updateTypePrice(String type, double newPrice) {
		this.typePrice.put(type, newPrice);
	}

	public double getTotalCapacity() {
		return this.totalCapacity;
	}

	public void clearItemPrice() {
		this.typePrice.clear();
	}

	public double getTotalWeightInTempSession() {
		
		return this.tempSession.getTotalWeightInSession();
	}

	

}
