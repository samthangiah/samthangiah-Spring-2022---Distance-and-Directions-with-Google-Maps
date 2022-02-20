package edu.sru.booser.datastore;
import java.io.IOException;
/*
 * requestDistanceMatrix
 * Stores the starting and ending locations
 * as well as the distance.
 * 
 */
public class DataStore {

	/*
	 * Var Locations
	 */
	private String locStartState;
		public String getLocStartState() {
			return locStartState;
		}
		public void setLocStartState(String locStart) {
			this.locStartState = locStart;
		}
	
	private String locStartCity;
		public String getLocStartCity() {
			return locStartCity;
		}
		public void setLocStartCity(String startCity) {
			this.locStartCity = startCity;
		}
	
	private String locEndState;
		public String getLocEndState() {
			return locEndState;
		}
		public void setLocEndState(String locEnd) {
			this.locEndState = locEnd;
		}
			
	private String locEndCity;
		public String getLocEndCity() {
			return locEndCity;
		}
		public void setLocEndCity(String newEndCity) {
			this.locEndCity = newEndCity;
		}
	
	private String inputStart;
		public String getInputStart() {
			return inputStart;
		}
		public void setInputStart(String newInputStart) {
			this.inputStart = newInputStart;
		}
	
	private String inputEnd;
		public String getInputEnd() {
			return inputEnd;
		}
		public void setInputEnd(String newInputEnd) {
			this.inputEnd = newInputEnd;
		}
	
	private float  distMiles;
		public float getDistMiles() {
			return distMiles;
		}
		public void setDistMiles(float distMiles) {
			this.distMiles = distMiles;
		}
		
	private String Directions;
		public String getDirections() {
			return Directions;
		}
		public void setDirections(String newDirections) {
			this.Directions = newDirections;
		}
		
	
	/*
	 * Constructors
	 * For Simple and Complex Entries
	 * with or without a distance already included.
	 * 
	 */
		
	/*
	 * Constructor for DataStore with Formated Locations
	 * No Data
	 */
	public DataStore(String lS, String lE) {
		this.setInputStart(lS);
		this.setInputEnd(lE);
		this.setDistMiles(this.calcDistance());
	}
	
	/*
	 * Constructor for DataStore with Formated Locations
	 * With Distance Measured
	 */
	public DataStore(String lS, String lE, float dM) {
		this.setInputEnd(lE);
		this.setInputStart(lS);
		this.setDistMiles(dM);
	}
	
	/*
	 * Constructor for DataStore without formated Locations
	 * No Data
	 */
	public DataStore(String startState, String startCity, String endState, String endCity) {
		this.setLocStartState(startState);
		this.setLocStartCity(startCity);
		this.setLocEndCity(endCity);
		this.setLocEndState(endState);
		this.setInputStart(combineString(this.getLocStartCity(),this.getLocStartState()));
		this.setInputEnd(combineString(this.getLocEndCity(),this.getLocEndState()));
		this.setDistMiles(this.calcDistance());
	}
	
	/*
	 * Constructor for DataStore without formated Locations
	 * With Distance Measured
	 */
	public DataStore(String startState, String startCity, String endState, String endCity, float dM) {
		this.setLocStartCity(startCity);
		this.setLocStartState(startState);
		this.setLocEndCity(endCity);
		this.setLocEndState(endState);
		this.setDistMiles(dM);
		this.setInputStart(combineString(this.getLocStartCity(),this.getLocStartState()));
		this.setInputEnd(combineString(this.getLocEndCity(),this.getLocEndState()));
	}
	
	/*
	 * Functional Methods
	 * CALCULATE and PRINT distances.
	 * Combine CITY and STATE strings.
	 */
	public void printDistance() {
		System.out.println("It is " + this.getDistMiles() + " miles between " + this.getInputStart() + " and " + this.getInputEnd());
	}
	
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
	
	//Prints Unmodified XML
	public void printDirectionsU() {
		System.out.println(this.getDirections());
	}
	
	public String calcDirections() {
		String directions = "Failed"; 
		try {
			directions = DirectionsAPI.getDirections(this.getInputStart(), this.getInputEnd());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setDirections(directions);
		return directions;
	}
	
	private String combineString(String City, String State) {
		String outString = City + "_" + State;
		return outString;
	}
	
	
	
}