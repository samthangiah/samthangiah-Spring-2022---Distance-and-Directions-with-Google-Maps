package edu.sru.franklin;

import java.io.IOException;

import edu.sru.booser.datastore.DistanceMatrixAPI;

public class Application {
	
	Table distanceTable;
	
	
	//TEMP array to make adding test locations easier
	String locationPairs[][] = 
	{
		{"Pittsburgh_PA", "Butler_PA"},
		{"SlipperyRock_PA", "GroveCity_PA"},
		{"Emlenton_PA", "Clarion_PA"}
	};
	

	public static void main(String []args) throws IOException {
		Application app = new Application(); 
		app.start();
	}
	
	public void start() throws IOException { 
		
		
		//creates hashtable with 10 initial locations
		distanceTable = new Table(10); 
		for(int i = 0; i < locationPairs.length; i++) {
			System.out.println(getDistance(locationPairs[i][0], locationPairs[i][1]));
		}

	}
	
	/*checks to see if distance from starting to ending location 
	 * is already in the table. if not it queries the distance matrix 
	 * api for the distance and adds it to the table. Then it returns the distance. 
	 */
	public float getDistance(String startLocation, String endLocation) throws IOException { 
		String locationKey = startLocation + " " + endLocation;
		
		
		if(!distanceTable.containsKey(locationKey)) {
			
			distanceTable.addToTable(locationKey, DistanceMatrixAPI.getDistance(startLocation, endLocation));
			
			System.out.println("Created new table entry " + locationKey + " from API call");
		}
		
		return distanceTable.getFromTable(locationKey);
	}
	

}
