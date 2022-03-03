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
	/**
	 * getter for location start state
	 */
	private String locStartState;
		public String getLocStartState() {
			return locStartState;
		}
		public void setLocStartState(String locStart) {
			this.locStartState = locStart;
		}
	/**
	 * getter for location start city
	 */
	private String locStartCity;
		public String getLocStartCity() {
			return locStartCity;
		}
		public void setLocStartCity(String startCity) {
			this.locStartCity = startCity;
		}
	/**
	 * getter for location end state
	 */
	private String locEndState;
		public String getLocEndState() {
			return locEndState;
		}
		public void setLocEndState(String locEnd) {
			this.locEndState = locEnd;
		}
	/**
	 * getter for location end city	
	 */
	private String locEndCity;
		public String getLocEndCity() {
			return locEndCity;
		}
		public void setLocEndCity(String newEndCity) {
			this.locEndCity = newEndCity;
		}
	
		/**
		 * getter for input start
		 */
	private String inputStart;
		public String getInputStart() {
			return inputStart;
		}
		public void setInputStart(String newInputStart) {
			this.inputStart = newInputStart;
		}
	
		/**
		 * getter for input end
		 */
	private String inputEnd;
		public String getInputEnd() {
			return inputEnd;
		}
		public void setInputEnd(String newInputEnd) {
			this.inputEnd = newInputEnd;
		}
	
		/**
		 * getter for distance miles
		 */
	private float  distMiles;
		public float getDistMiles() {
			return distMiles;
		}
		public void setDistMiles(float distMiles) {
			this.distMiles = distMiles;
		}
		
		/**
		 * getter for directions
		 */
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
		/**
		 * Gets start and end and then calculates distance
		 * @param lS The start input
		 * @param lE The end input
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
	/**
	 * Sets starting and end values and then finds distance
	 * @param lS The start input
	 * @param lE The end input
	 * @param dM The distance in miles 
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
	/**
	 * Sets start state, start city, and end city. Sets start location as a combination of start city and start state.
	 * Sets input end as a combination of end city and end state. It also sets distance miles calculation. 
	 * @param startState The start State location
	 * @param startCity The start city location
	 * @param endState The end State location
	 * @param endCity The end City location
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
	/**
	 * Super constructor for data store that includes distance in miles.
	 * @param startState The start State location
	 * @param startCity The start city location
	 * @param endState The end State location
	 * @param endCity The end City location
	 * @param dM The distnace miles
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
	/**
	 * prints out distance
	 */
	public void printDistance() {
		System.out.println("It is " + this.getDistMiles() + " miles between " + this.getInputStart() + " and " + this.getInputEnd());
	}
	/**
	 * Calculates distance
	 * @return distance value
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
	
	//Prints Unmodified XML
	/**
	 * prints out directions
	 */
	public void printDirectionsU() {
		System.out.println(this.getDirections());
	}
	/**
	 * Calculates directions
	 * @return direction value
	 */
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
	/**
	 * combines city and state
	 * @param City 
	 * @param State
	 * @return city and state
	 */
	private String combineString(String City, String State) {
		String outString = City + "_" + State;
		return outString;
	}
	
	
	
}