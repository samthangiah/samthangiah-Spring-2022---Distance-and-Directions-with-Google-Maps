package edu.sru.franklin;

import java.util.Hashtable;

//Hashtable: Accepts a key in the form of a location pair and displays distance between the two locations.

//Included Functions: size(), addToTable(), containsKey(), contains(), removeFromTable(), getFromTable(), getTable()

//TODO: test for hashtable stackoverflow handling
public class Table {
	
	private Hashtable<String, Float> table;
	
	public Table(int size) //Creates table that holds a string pair and a float value
	{
		table = new Hashtable<String, Float>(size);
	}
	
	public int size() //finds size of hashtable
	{
		return table.size();
	}
	
	public void addToTable(String locationPair, float distance) //Stores location pair and float value into hashtable
	{
		table.put(locationPair, distance);	 
	}
	
	public boolean containsKey(String locationPair) //searches for location pairs in hashtable
	{
		return table.containsKey(locationPair);
	}
	
	
	public boolean contains(float value) //searches for float values in hashtable
	{
		return table.contains(value);
	}
	
	public boolean removeFromTable(String locationPair, float distance) //removes location pair and distance value
	{
		return table.remove(locationPair, locationPair);
	}
	
	public Float getFromTable(String locationPair) //gets distance based on location pair specified
	{
		return table.get(locationPair);
	}
	
	public Hashtable<String, Float> getTable() //returns all data held in hashtable
	{
		return table;
	}
	
	}
	