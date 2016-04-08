package vanahallichandranna.ecore.ecore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.security.acl.LastOwnerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Sneha
 *
 */
public class Database {
	HashMap<String, RecyclingMachine> recyclingMachines;
	private Connection connection = null;
	
	// Prepared statements
	private PreparedStatement updateTypePriceStatement = null;
	private PreparedStatement getRecyclingMachineStatement = null;
	private PreparedStatement getRecycledItemsStatement = null;
	private PreparedStatement getRecyclableItemsPriceStatement = null;
	private PreparedStatement insertRecycledItemsStatement = null;
	private PreparedStatement createRecyclingMachineStatement = null;
	private PreparedStatement updateRecyclingMachineStatement = null;
	private PreparedStatement deleteTypePriceStatement = null;
	private PreparedStatement updateRecyclingMachineStateStatement = null;
	private PreparedStatement emptyRecyclingMachineStateStatement = null;
	
	public Database() {
		recyclingMachines = new HashMap<String, RecyclingMachine>();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			connection = DriverManager.getConnection("jdbc:mysql://localhost/ecore?"
							+ "user=dbuser&password=password");

			createRecyclingMachineStatement  = connection.prepareStatement("insert into ecore.recyclingmachine values (?, ?, ?, ?, ?, ?)");
			updateRecyclingMachineStatement   = connection.prepareStatement("update ecore.recyclingmachine set " +
																			"totalCapacity=?, " +
																			"availableCapacity=?, "+
																			"cash=?, "+
																			"lastEmptied=?, "+
																			"operationalStatus=? "+
																			"where machineId = ?");
			updateRecyclingMachineStateStatement    = connection.prepareStatement("update ecore.recyclingmachine set " +
																				 "operationalStatus=? "+
																				 "where machineId = ?");
			emptyRecyclingMachineStateStatement     = connection.prepareStatement("update ecore.recyclingmachine set " +
																				 "availableCapacity=?, "+
																				 "lastEmptied=?, " +
																				 "cash=? "+
																				 "where machineId = ?");

			updateTypePriceStatement = connection.prepareStatement("replace into ecore.recycleitemtypes values (?, ?)");
			deleteTypePriceStatement  = connection.prepareStatement("delete from ecore.recycleitemtypes where type=?");
			getRecyclingMachineStatement = connection.prepareStatement("select * from ecore.recyclingmachine where machineid = ?");
			getRecycledItemsStatement = connection.prepareStatement("select * from ecore.recycleditems where machineid = ?");
			getRecyclableItemsPriceStatement = connection.prepareStatement("select * from ecore.recycleitemtypes");
			
			insertRecycledItemsStatement  = connection.prepareStatement("insert into ecore.recycleditems values (default, ?, ?, ?, ?, ?)");
	
			/*
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * from RecyclingMachines");
			
			while(resultSet.next()) {
				System.out.println("Recycling machine");
				System.out.println("Machine id: " + resultSet.getString("machineid"));
				System.out.println("Location: " + resultSet.getString("location"));
			}
			
			statement.executeUpdate("insert into RecyclingMachines values ('rcm03', 'location03')");
			*/
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createRecyclingMachine(String machineId, double totalCapacity, double availableCapacity, double cash, Date lastEmptied, String operationalStatus) {
		try {
			createRecyclingMachineStatement.setString(1, machineId);
			createRecyclingMachineStatement.setDouble(2, totalCapacity);
			createRecyclingMachineStatement.setDouble(3, availableCapacity);
			createRecyclingMachineStatement.setDouble(4, cash);
			createRecyclingMachineStatement.setTimestamp(5, new Timestamp(lastEmptied.getTime()));
			createRecyclingMachineStatement.setString(6, operationalStatus);
			createRecyclingMachineStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateRecyclingMachine(String machineId, double totalCapacity, double availableCapacity, double cash, Date lastEmptied, String operationalStatus) {
		try {
			updateRecyclingMachineStatement.setDouble(1, totalCapacity);
			updateRecyclingMachineStatement.setDouble(2, availableCapacity);
			updateRecyclingMachineStatement.setDouble(3, cash);
			updateRecyclingMachineStatement.setTimestamp(4, new Timestamp(lastEmptied.getTime()));
			updateRecyclingMachineStatement.setString(5, operationalStatus);
			updateRecyclingMachineStatement.setString(6, machineId);
			
			//System.out.println("SQL: " + updateRecyclingMachineStatement);
			updateRecyclingMachineStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	public void addRecyclingMachine(String machineId, RecyclingMachine recyclingMachine) {
		recyclingMachines.put(machineId, recyclingMachine);
	}
	*/
	
	public RecyclingMachine getRecyclingMachine(String machineId) {
		
		/*
		sqlQuery = "SELECT from rcm where rcm.machineId == " + machineId;
		HashMap<String, String> data = db.run(sqlQuery);
		
		r.setCash(data.get("cash"));
		*/
		getRecyclingMachineFromDb(machineId);
		if(recyclingMachines.containsKey(machineId)) {
			return recyclingMachines.get(machineId);
		}
		return null;
	}

	private void getRecyclingMachineFromDb(String machineId) {
		try {
			
			getRecyclingMachineStatement.setString(1, machineId);
			//System.out.println("Query: " + getRecyclingMachineStatement.toString());
			ResultSet result = getRecyclingMachineStatement.executeQuery();
			//System.out.println("Result: " + result);
			boolean machineAdded = false;
			while(result.next()) {
				Double totalCapacity = result.getDouble("totalCapacity");
				Double availableCapacity = result.getDouble("availableCapacity");
				Double cash = result.getDouble("cash");
				Date lastEmptied = result.getTimestamp("lastEmptied");
				String status = result.getString("operationalStatus");
				RecyclingMachine rcm = recyclingMachines.get(machineId);
				if( rcm == null ) {
					rcm = new RecyclingMachine(cash, machineId, totalCapacity);
					recyclingMachines.put(machineId, rcm);
				}
				rcm.setOperationalStatus(status);
				rcm.setTotalCapacity(totalCapacity);
				rcm.setAvailableCapacity(availableCapacity);
				rcm.setCash(cash);
				rcm.setLastEmptied(lastEmptied);
				machineAdded = true;
			}
			
			if(machineAdded) {
				getRecycledItems(machineId);
			}
			
		} catch (SQLException e) {
			//System.out.println("Cannot find Recycling machine" + machineId);
			e.printStackTrace();
		}
		
		
	}

	private void getRecycledItems(String machineId) {
		
		try {
			getRecycledItemsStatement.setString(1, machineId);
			ResultSet result = getRecycledItemsStatement.executeQuery();
			RecyclingMachine rcm = recyclingMachines.get(machineId);
			rcm.clearAllRecycledItems();
			while (result.next()) {
				String type = result.getString("type");
				Double weight = result.getDouble("weight");
				Double pricePaid = result.getDouble("pricePaid");
				Date insertedTime = result.getTimestamp("insertedTime");
				RecycledItem i = new RecycledItem(type, weight);
				i.setPricePaid(pricePaid);
				i.setInsertedTime(insertedTime);
				rcm.addItem(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateTypePrice(String type, double newPrice) {
		for(RecyclingMachine rcm: recyclingMachines.values()) {
			// rcm.getTypePrice().put(type, newPrice);
			rcm.updateTypePrice(type, newPrice);
			
		}
		
		updateTypePriceInDb(type, newPrice);
	}

	private void updateTypePriceInDb(String type, double newPrice) {
		try {
			updateTypePriceStatement.setString(1, type);
			updateTypePriceStatement.setDouble(2, newPrice);
			updateTypePriceStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeType(String type) {
		/*
		for(RecyclingMachine rcm: recyclingMachines.values()) {
			rcm.getTypePrice().remove(type);
		}
		*/
		try {
			deleteTypePriceStatement.setString(1, type);
			deleteTypePriceStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public double getCashInRcm(String machineId) {
		return recyclingMachines.get(machineId).getCashInRCM();
		
	}

	public HashMap<String, Double> getRecyclableItemsPrice(String machineId) {
		this.getRecyclableItemsPriceFromDb(machineId);
		return recyclingMachines.get(machineId).getTypePrice();
	}

	private void getRecyclableItemsPriceFromDb(String machineId) {
		ResultSet result;
		try {
			//System.out.println("Query: " + getRecyclableItemsPriceStatement);
			result = getRecyclableItemsPriceStatement.executeQuery();
			RecyclingMachine rcm = recyclingMachines.get(machineId);
			rcm.clearItemPrice();
			while(result.next()) {
				String type = result.getString("type");
				Double price = result.getDouble("price");
				rcm.updateTypePrice(type, price);
				//System.out.println("Updating the price to " + type + " " + price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String, Double> getRecyclableItemsPrice() {
		HashMap<String, Double> typePrice = new HashMap<>();
		ResultSet result;
		try {
			result = getRecyclableItemsPriceStatement.executeQuery();
			while(result.next()) {
				String type = result.getString("type");
				Double price = result.getDouble("price");
				typePrice.put(type, price);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return typePrice;
	}


	public void endSession(String machineId) {
		// Copy data in the tmp session into the database
		RecyclingMachine rcm = this.recyclingMachines.get(machineId);
		List<RecycledItem> items = rcm.getRecycledItemInserted();
		for(RecycledItem item: items) {
			String type = item.getItemType();
			Double weight = item.getItemWeight();
			Double pricePaid = item.getPrice();
			Date insertedTime = item.getInsertedTime();
		
			try {
				insertRecycledItemsStatement.setString(1, rcm.getMachineId());
				insertRecycledItemsStatement.setString(2, type);
				insertRecycledItemsStatement.setDouble(3, weight);
				insertRecycledItemsStatement.setDouble(4, pricePaid);
				insertRecycledItemsStatement.setTimestamp(5, new Timestamp(insertedTime.getTime()));
				
				//System.out.println("SQL: " + insertRecycledItemsStatement);
				insertRecycledItemsStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Update the other recycling machine parameters
		updateRecyclingMachine(machineId, rcm.getTotalCapacity(), rcm.getCurrentAvailableCapacity(), rcm.getCashInRCM(), rcm.getLastEmptiedTime(), rcm.getOperationalStatus());
	}
	
	public void updateRecyclingMachineState(String machineId, String state) {
		try {
			RecyclingMachine rcm = recyclingMachines.get(machineId);
			rcm.setOperationalStatus(state);
			updateRecyclingMachineStateStatement.setString(1, state);
			updateRecyclingMachineStateStatement.setString(2, machineId);
			updateRecyclingMachineStateStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void resetRecyclingMachine(String machineId) {
		
		RecyclingMachine rcm = this.recyclingMachines.get(machineId);
		try {
			emptyRecyclingMachineStateStatement.setDouble(1, rcm.getTotalCapacity());
			emptyRecyclingMachineStateStatement.setTimestamp(2, new Timestamp(new Date().getTime()));
			emptyRecyclingMachineStateStatement.setDouble(3, 50);
			emptyRecyclingMachineStateStatement.setString(4, machineId);
			emptyRecyclingMachineStateStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
