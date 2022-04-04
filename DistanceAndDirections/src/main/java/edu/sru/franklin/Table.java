package edu.sru.franklin;

import java.util.Enumeration;
import java.util.Hashtable;

//Hashtable: Accepts a key in the form of a location pair and displays distance between the two locations.

//Included Functions: size(), addToTable(), containsKey(), contains(), removeFromTable(), getFromTable(), getTable()

//TODO: test for hashtable stackoverflow handling
public class Table implements java.io.Serializable{
	
	
	private Hashtable<Integer, DataObject> table;
	
	/**
	 * Constructor for Table
	 */
	public Table() //Creates table that holds a string pair and a float value
	{
		table = new Hashtable<Integer, DataObject>();
		
	}
	
	/**
	 * generic size method
	 * @return number of elements in table (int)
	 */
	public int size() //finds size of hashtable
	{
		return table.size();
	}
	
	/**
	 * adds a new data object with a origin-destination pair
	 * the key is a hash of the origin and destination
	 * @param origin starting location
	 * @param destination ending location
	 */
	public void add(String origin, String destination) {
		String temp = origin.concat(destination);
		float distance = 0;
		table.put(temp.hashCode(), new DataObject(origin, destination, distance));
	}
	/**
	 * 
	 * @param origin starting location
	 * @param destination ending location
	 * @return return dataObject with this hash
	 */
	public DataObject getDataObject(String origin, String destination) {
		String temp = origin.concat(destination);
		return  table.get(temp.hashCode());
	}
	
	public Boolean contains(String origin, String destination) {
		return table.containsKey(origin.concat(destination).hashCode());
	}
	
	/**
	 * method to print out entire table to console
	 * used for TESTING should be removed
	 */
	public void printTable() {
		Enumeration<DataObject> e = table.elements();
		DataObject temp;
		while(e.hasMoreElements()) {
			temp = e.nextElement();
			System.out.println("Origin: " + temp.getOrigin() + 
					" Destination: " + temp.getDestination() +
					" Distance: " + temp.getDistance() +
					" Directions: " + temp.getDirections() + "\n");
		}
	
	}
	public void printTableHeader(int length) {
		Enumeration<DataObject> e = table.elements();
		int j = 0;
		DataObject temp;
		while(j < length && e.hasMoreElements()) {
			temp = e.nextElement();
			System.out.println("Origin: " + temp.getOrigin() + 
					" Destination: " + temp.getDestination() +
					" Distance: " + temp.getDistance() +
					" Directions: " + temp.getDirections() + "\n");
			j++;
		}
	
	}
	public Hashtable<Integer,DataObject> getTable() {
		return table;
	}

	
	
}