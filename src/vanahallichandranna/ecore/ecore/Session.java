package vanahallichandranna.ecore.ecore;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 */

/**
 * @author Sneha
 * @param <sessionList>
 *
 */
public class Session {
	private static int sessionCounter;
	private double totalAmount;
	private int sessionId;
	private double totalWeightInSession;
	
	List<RecycledItem> recycledItemList = new ArrayList<RecycledItem>();
	
	public Session() {
		sessionCounter = sessionCounter + 1;
		sessionId = sessionCounter;
		totalWeightInSession = 0;
	}
	
	//copy constructor
	public Session(Session session) {
		session.recycledItemList = this.recycledItemList;
	}
	
	public void addRecycledItem(RecycledItem item) {
		recycledItemList.add(item);
		
	}
	
	public List<RecycledItem> getRecycledItemsInSession() {
	/*	for(RecycledItem recycledItem : sessionList) {
			System.out.println(recycledItem.getItemType());
		} */
		return recycledItemList;
	}
	
	public double getTotalAmountToBePaid(HashMap<String, Double> hashMap) {
		totalAmount = 0;
		for(RecycledItem recycledItem : recycledItemList) {
			recycledItem.itemPrice = (hashMap.get(recycledItem.getItemType()))* (recycledItem.getItemWeight());
			totalAmount = totalAmount + recycledItem.getPrice();
		}
		return totalAmount;
	}

	public double getTotalWeightInSession() {
		totalWeightInSession = 0;
		for (RecycledItem recycledItem : recycledItemList) {
			totalWeightInSession = totalWeightInSession + recycledItem.getItemWeight();
		}
		return totalWeightInSession;
	}
}
