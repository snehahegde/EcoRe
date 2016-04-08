/**
 * 
 */
package vanahallichandranna.ecore.ecore;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vanahallichandranna.ecore.rcm.Rcm;
import vanahallichandranna.ecore.rmos.Rmos;


/**
 * @author Sneha
 *
 */
public class EcoRe {

	/**
	 * 
	 */
	public EcoRe() {
		
	}
	
	static void simulateRCMs(Database db) {
		RecyclingMachine r1 = new RecyclingMachine(1000, "AB12", 500);
		//r1.setDatabase(db);
		r1.createSession();
		r1.addItem("glass", 1);
		
		// ...
		RecyclingMachine r2 = new RecyclingMachine(1000, "AB100", 500);
		//r2.setDatabase(db);
		r2.createSession();
		r2.addItem("metal", 2);
		//db.addRecyclingMachine("AB12", r1);
		
		// ...
		
		//db.addRecyclingMachine("AB100", r2);
		r1.endSession();
		r2.endSession();
		
		vanahallichandranna.ecore.rcm.RecyclingMachineSimulation ab123_sim = new vanahallichandranna.ecore.rcm.RecyclingMachineSimulation();
		ab123_sim.SimulateRCM_AB123(db);
		
		vanahallichandranna.ecore.rcm.RecyclingMachineSimulation abc200_sim = new vanahallichandranna.ecore.rcm.RecyclingMachineSimulation();
		abc200_sim.simulateRCM_ABC200(db);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Database database = new Database();
		simulateRCMs(database);
		
		JFrame mainFrame = new JFrame("EcoRe");
		mainFrame.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		mainFrame.setLayout(null);
		
		Rmos rmos = new Rmos();
		rmos.setDatabase(database);
		
		JPanel rmosPanel = rmos.createUi();

		mainFrame.add(rmosPanel);
		
		Rcm rcm = new Rcm(database, "SCURCM01"); // database.getRecyclingMachine("AB123"));
		JPanel rcmPanel = rcm.createUi();
		mainFrame.add(rcmPanel);
		
		mainFrame.setVisible(true);
		rcmPanel.setLocation(800, 0);
		
	}

}
