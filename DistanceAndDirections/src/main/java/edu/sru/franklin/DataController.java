package edu.sru.franklin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataController {
	
	private String inputFileName = "Addresses.xlsx";
	private XSSFParser parser;
	private Table dataTable;
	
	// this class currently runs the xlsx parser and adds its data to a Table object
	 
	
	/**
	 * default constructor
	 * Creates instance of parser from addresses.xlsx
	 */
	public DataController() { 		
		File dataFile = new File("data.txt");
		try {
			System.out.println(dataFile.createNewFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void readFromExcelDoc(String fileName) {
		parser = new XSSFParser();
		parser.parseFromFile(fileName);
		if(dataTable == null) {
			dataTable = parser.newDataTableFromFile();
		}
		else {
			//add code if the table already exists, should we merge?
		}
	}
	
	public void readFromTextFile() {
		try {
			FileInputStream fileInputStream = new FileInputStream("data.txt");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			if(dataTable == null) {
				dataTable = (Table) objectInputStream.readObject();
			}
			else {
				
			}
			objectInputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeToTextFile() {
		if(dataTable == null) {
			throw new NullPointerException("Data Table is null");
		}
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("data.txt");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(dataTable);
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Wrote project data to data.txt");
	}
	
	
	/**
	 * adds origin and destinations to table.
	 * @param origin The starting location
	 * @param destination The ending location
	 */
	public void add(String origin, String destination) {
		dataTable.add(origin, destination);
	}
	
	/**
	 * gets distance for destination and origin pair. Makes it so it works in both directions. 
	 * @param origin The starting location
	 * @param destination The ending location
	 * @return distance distance between locations
	 */
	public float getDistance(String origin, String destination) {
		//first pair
		if(dataTable.contains(origin, destination)) {
			return dataTable.getDataObject(origin, destination).getDistance();
		//second pair
		}else if(dataTable.contains(destination, origin)) {
			return dataTable.getDataObject(destination, origin).getDistance(); 
		}
		return -1;
	}
	
	/**
	 * Gets locations and directions from table. 
	 * @param origin The staring location
	 * @param destination The ending location
	 * @return the two locations and directions
	 */
	public String[] getDirections(String origin, String destination) {
		if(dataTable.contains(origin, destination)) {
			return dataTable.getDataObject(origin, destination).getDirections();
		}
		return null;
	}
	
	/**
	 * add distance based on location pair
	 * @param origin The starting location
	 * @param destination The ending location
	 * @param distance distance between locations
	 */
	public void addDistance(String origin, String destination, float distance) {
		
		if(!dataTable.contains(origin, destination)) {		
			dataTable.add(origin, destination);
		}
		
		dataTable.getDataObject(origin, destination).setDistance(distance);	
	}
	
	/**
	 * add directions based on location pair
	 * @param origin The staring location
	 * @param destination The ending location
	 * @param directions distance between locations
	 */
	public void addDirections(String origin, String destination, String[] directions) {
		if(!dataTable.contains(origin, destination)) {		
			dataTable.add(origin, destination);
		}
		
		dataTable.getDataObject(origin, destination).setDirections(directions);	 	
	}
	public void printTable() {
		dataTable.printTable();
	}
	
}

