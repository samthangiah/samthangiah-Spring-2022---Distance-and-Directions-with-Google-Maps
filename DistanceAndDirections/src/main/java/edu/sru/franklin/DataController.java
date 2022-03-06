package edu.sru.franklin;


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
		parser = new XSSFParser();
		parser.parseFromFile(inputFileName);
		dataTable = parser.newDataTableFromFile();
		dataTable.printTable();

	}
	
	/**
	 * constructor allows option to pass different file for parser
	 * @param fileName The file being used
	 */
	public DataController(String fileName) {
		parser = new XSSFParser();
		parser.parseFromFile(fileName);
		dataTable = parser.newDataTableFromFile();
		dataTable.printTable();
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
	
	
}
