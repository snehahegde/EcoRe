package vanahallichandranna.ecore.ecore;
import java.util.Date;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class RecycledItem {
	double itemPrice;
	Date itemInsertionTime;
	double itemWeight;
	String itemType;
	
	
	public RecycledItem(String type, double weight) {
		itemInsertionTime = new Date();
		itemType = type;
		itemWeight = weight;
		itemPrice = 0;
	}
	
	public double getPrice() {
		return itemPrice;
		
	}	
	
	public double getItemWeight() {
		return this.itemWeight;
	}
	
	public String getItemType() {
		return this.itemType;
	}
	
	public void setPrice(HashMap<String, Double> hashMap){
		double typePrice = 0;
		if(hashMap.containsKey(this.itemType)) {
			typePrice = hashMap.get(this.itemType);
		}
		itemPrice = typePrice * this.itemWeight;
	}

	public void setPricePaid(Double pricePaid) {
		itemPrice = pricePaid;
	}
	
	public double getPricePaid() {
		return itemPrice;
	}
	
	public void setInsertedTime(Date time) {
		itemInsertionTime = time;
	}

	public Date getInsertedTime() {
		// TODO Auto-generated method stub
		return this.itemInsertionTime;
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


