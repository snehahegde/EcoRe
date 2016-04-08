package vanahallichandranna.ecore.rcm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import vanahallichandranna.ecore.ecore.Database;
import vanahallichandranna.ecore.ecore.RecycledItem;
import vanahallichandranna.ecore.ecore.RecyclingMachine;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RecyclingMachineSimulation {

	/**
	 * @param args
	 */
	public void SimulateRCM_AB123(Database database) {
		// TODO Auto-generated method stub
		RecyclingMachine recyclingMachine = new RecyclingMachine(1000,"AB123", 500);
		// Database database = new Database();
		
		//recyclingMachine.setDatabase(database);
		//database.addRecyclingMachine("AB123", recyclingMachine);
		
		//enquire about type and price
		HashMap<String,Double> hashMap = recyclingMachine.enquire();
		Set<String> keys = hashMap.keySet();
		ArrayList<String> keyList = new ArrayList<String>(keys);
		Collections.sort(keyList);
		for( String key : keyList ) {
			System.out.println("Key: " + key + ". Value: " + hashMap.get(key));
		}
		
		//add a single recycling item
		recyclingMachine.createSession();
		boolean addResult = recyclingMachine.addItem("glass", 4);
		if (addResult == true) {
			System.out.println("Addition of item sucecessful");
		}
		else {
			System.out.println("was not successful");
		}
		//select to get the amount in cash
		System.out.println("Total amount" + recyclingMachine.getTotalAmount());

		//select to get the receipt
		List<RecycledItem> itemList = recyclingMachine.getRecycledItemInserted();
		for (RecycledItem item : itemList){
			System.out.println(item.getItemType() + item.getItemWeight());
		}
		System.out.println("-------------------------------------");
		System.out.println("Total : " + recyclingMachine.getTotalAmount());

		recyclingMachine.endSession();
		
		
		
		//multiple items
		recyclingMachine.createSession();
		recyclingMachine.addItem("paper", 4);
		recyclingMachine.addItem("plastic", 4);
		
		//select to get the amount in cash
		System.out.println("Total amount" + recyclingMachine.getTotalAmount());
		
		//user chooses to get the cash
		recyclingMachine.payCustomer();

		//select to get the receipt
		itemList = recyclingMachine.getRecycledItemInserted();
		for (RecycledItem item : itemList){
			System.out.println(item.getItemType() + item.getItemWeight());
		}
		System.out.println("-------------------------------------");
		System.out.println("Total : " + recyclingMachine.getTotalAmount());
		

		recyclingMachine.endSession();
		
		//get all the items in the recycling machine by RMOS
		List<RecycledItem> recycledItems = recyclingMachine.getAllItemsInTheRecyclingMachine();
		System.out.println("Items in the recycling machine are :");
		for (RecycledItem item : recycledItems) {		
			System.out.println(item.getItemType() + item.getItemWeight() + item.getPrice());
		}
	
		
		//select to get the amount in cash
		//recyclingMachine.getTotalAmount();
		
		//select to get the amount in token
		
		//cancel and take back the items
		
		recyclingMachine.getRecycledItemsInAMonth(new Date());
		
	}
	
	public void simulateRCM_ABC200(Database db) {
		// Create RCM with id ABC200
		RecyclingMachine recyclingMachine = new RecyclingMachine(1000,"ABC200", 500);
		//recyclingMachine.setDatabase(db);
		//db.addRecyclingMachine("ABC200", recyclingMachine);
		// Create session
		recyclingMachine.createSession();
		
		
		// Add items into it
		recyclingMachine.addItem("plastic", 6);
		recyclingMachine.addItem("glass", 6);
		
		// Pay the customer
		System.out.println("Total amount" + recyclingMachine.getTotalAmount());
		recyclingMachine.payCustomer();
		
		// End session
		recyclingMachine.endSession();
	}

}
