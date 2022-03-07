package edu.sru.booser.datastore;
import java.io.IOException;
import java.util.Vector;

/**
 * Holds Data for Maps Queries
 * Stores the starting and ending locations
 * as well as the distance and directionsHolder.
 * 
 */
public class DataStore {

	/**
	 * The start location in any format.
	 */
	private String inputStart;
		public String getInputStart() {
			return inputStart;
		}
		public void setInputStart(String newInputStart) {
			this.inputStart = newInputStart;
		}
	
	/**
	 * The end location in any format.
	 */
	private String inputEnd;
		public String getInputEnd() {
			return inputEnd;
		}
		public void setInputEnd(String newInputEnd) {
			this.inputEnd = newInputEnd;
		}
	
	/**
	 * The distance in miles between inputStart and inputEnd.
	 */
	private float  distMiles;
		public float getDistMiles() {
			return distMiles;
		}
		public void setDistMiles(float distMiles) {
			this.distMiles = distMiles;
		}
	
	/**
	 * An object that holds trip information.
	 */
	protected DirectionsHolder Holder = new DirectionsHolder();
		public DirectionsHolder getHolder() {
			return Holder;
		}
		public void setHolder(DirectionsHolder holder) {
			Holder = holder;
		}

	/**
	 * Primary constructor for the DataStore Object
	 * Created with starting and ending location
	 * 
	 * @param lS route starting location
	 * @param lE route ending location
	 */
	public DataStore(String lS, String lE) {
		this.setInputStart(lS);
		this.setInputEnd(lE);
		this.setDistMiles(this.calcDistance());
		try {
			DirectionsAPI.getDirections(lS, lE, this.Holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Alternative constructor for the DataStore Object
	 * Allows for a known distance to be given when the object is instantiated
	 * 
	 * @param lS route starting location
	 * @param lE route ending locaction 
	 * @param dM known distance in miles
	 */
	public DataStore(String lS, String lE, float dM) {
		this.setInputEnd(lE);
		this.setInputStart(lS);
		this.setDistMiles(dM);
	}
	

	
	/**
	 * Output DistMiles for testing in console.
	 */
	public void printDistance() {
		System.out.println("It is " + this.getDistMiles() + " miles between " + this.getInputStart() + " and " + this.getInputEnd());
	}
	
	/**
	 * Gets a distance from DistanceMatrixAPI.java using data from this object
	 * 
	 * @return returns distance in miles
	 */
	public float calcDistance() {
		float value = -1;
				try {
		value = DistanceMatrixAPI.getDistance(this.getInputStart(), this.getInputEnd());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		this.setDistMiles(value);
		return value;	
	}
	
}