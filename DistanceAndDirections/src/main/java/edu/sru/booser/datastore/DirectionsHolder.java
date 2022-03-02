package edu.sru.booser.datastore;

import java.util.Enumeration;
import java.util.Vector;

public class DirectionsHolder {
	private String summary;
	protected Vector directions;
	
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Vector getDirections() {
		return directions;
	}
	public void setDirections(Vector directions) {
		this.directions = directions;
	}
	
	public void printHolder() {
		System.out.println(this.getSummary());
		Enumeration enu = directions.elements();
		while (enu.hasMoreElements()) {
			System.out.println(enu.nextElement());
		}
	}
}
