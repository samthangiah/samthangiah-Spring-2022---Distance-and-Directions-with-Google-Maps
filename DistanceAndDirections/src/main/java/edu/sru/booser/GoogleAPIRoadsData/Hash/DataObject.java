package edu.sru.booser.GoogleAPIRoadsData.Hash;

//This class is a simple container for all the data we want to have stored in the hash table. 
//just inner fields and getters/setters, should speak for itself.
public class DataObject implements java.io.Serializable{	
	
	
	private String origin = "";
	private String destination = "";
	
	private float distance = -1;
	private String[] directions;
	
	public DataObject(String origin, String destination, float distance) {
		this.origin = origin;
		this.destination = destination;
		
		this.distance = distance;
	}
	public DataObject(String origin, String destination, float distance, String[] directions) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
		this.directions = directions;
	}

	
	/**
	 * 
	 * @return origin string
	 */
	public String getOrigin() {
		return origin;
	}
	/**
	 * 
	 * @return destination string
	 */
	public String getDestination() {
		return destination;
	}
	/**
	 * 
	 * @return distance float
	 */
	public float getDistance() {
		return distance;
	}
	/**
	 * 
	 * @return directions string
	 */
	public String[] getDirections() {
		//TODO: directions should probably be an array of direction strings
		return directions;
	}
	/**
	 * the passed origin location string should conform to the Google API format for locations
	 * @param origin the origin location
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * the passed destination location string should conform to the Google API format for locations
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}
	/**
	 * 
	 * @param distance the distance between the two locations
	 */
	public void setDistance(float distance) {
		this.distance = distance;
	}
	/**
	 * 
	 * @param directions the directions between the two locations
	 */
	public void setDirections(String[] directions) {
		this.directions = directions;
	}

	
}


