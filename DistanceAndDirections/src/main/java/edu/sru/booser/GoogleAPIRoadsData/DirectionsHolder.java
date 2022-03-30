package edu.sru.booser.GoogleAPIRoadsData;

import java.util.Enumeration;
import java.util.Vector;


/**
 * A class that holds parsed information from Google Directions @API.
 * Including the directions summary and step by step directions.
 * 
 * @author Michael Booser
 *
 */
public class DirectionsHolder {
	
	
	/**
	 * A simplified view of the directions route.
	 */
	private String summary;
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
	
	/**
	 * A string vector for storing step by step directions.
	 */
	protected Vector<String> directions = new Vector<String>();
		public Vector getDirections() {
			return directions;
		}
		public void setDirections(Vector directions) {
			this.directions = directions;
		}
	
	/**
	 * A testing method used to display raw directions data.
	 */
	public void printHolder() {
		System.out.println(this.getSummary());
		Enumeration enu = directions.elements();
		while (enu.hasMoreElements()) {
			System.out.println(enu.nextElement().toString());
		}
	}
	
	/**
	 * Used to format the DirectionsHolder object for printing.
	 * 
	 * @return A useable and printable version of the DirectionsHolder object.
	 */
	public String toString() {
		int counter = 1;
		String str = "Directions Summary (" + getSummary() + ")\n";
		Enumeration enu = directions.elements();
		while (enu.hasMoreElements())
		{
			str = str + "(Step " + counter + "): " + enu.nextElement().toString() + "\n";
			counter++;
		}
		return str;
	}
}
