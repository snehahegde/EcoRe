/**
 * 
 */
package vanahallichandranna.ecore.ecore;

/**
 * @author Sneha
 *
 */
public class ObservableRCM extends java.util.Observable {
	private RecyclingMachine rcm;
	
	public void updateRcm(RecyclingMachine rcm) {
		this.rcm = rcm;
		this.setChanged();
		this.notifyObservers();
	}
	
	public RecyclingMachine getRcm() {
		return this.rcm;
	}
}
