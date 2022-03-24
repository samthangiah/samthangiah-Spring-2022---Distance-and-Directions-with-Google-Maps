package edu.sru.bayne.DistanceAndDirections;

import java.sql.*;
// Code written using https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm
// as resource
/**
 * Class to query coordinate data from provided ID. Intended for use with distance-matrix.html,
 * but will not work due to trying to access database while running.
 *  !!NOT USED!! Could be helpful for accessing DB without console
 * @author Gregory
 *
 */
public class DatabaseQuery {
	
	static final String JDBCDriver = "org.h2.Driver";
	static final String H2URL = "jdbc:h2:file:./database/searchDB";
	static final String UserName = "sa"; 
	static final String Password = ""; 
	
	/**
	 * Query Latitude and longitude from both origin and destination of a db object with a provided ID.
	 * @param Id of database entry
	 * @return
	 */
	public String queryCoordFromID(String Id) {
		
		Connection connection = null;
		Statement myStatement = null;
		String myQuery = "SELECT lat1, lng1, lat2, lng2 FROM Search WHERE Id=" + Id;
		String coordinates = "";
		try {
			//Connect to Database
			Class.forName(JDBCDriver);
			System.out.println("Attempting DB Connection");
			connection = DriverManager.getConnection(H2URL, UserName, Password);
			System.out.println("Connected!");
			
			//Attempt to Query
			myStatement = connection.createStatement();
			ResultSet results = myStatement.executeQuery(myQuery);
			while(results.next()) {
				coordinates = "lat1:" + results.getString("LAT1") + 
							  "lng1:" + results.getString("LNG1") +
							  "lat2:" + results.getString("LAT2") +
						  	  "lng2:" + results.getString("LNG2");
			}
			System.out.println("Coordinates: " + coordinates);
			results.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		// close resources
		finally {
			
				try {
					if(myStatement!=null) {
						myStatement.close();
					}
				} catch (SQLException e) {
					System.out.println("Could not close Statement");
					e.printStackTrace();
				}
				try {
					if(connection!=null) {
						connection.close();
					}
				} catch (SQLException e) {
					System.out.println("Could not close Connection");
					e.printStackTrace();
				}
		}
		
		
		return coordinates;
	}
	
	public static void main (String[] args) {
		//Testing. Id=66 for first entry in db
		DatabaseQuery dbq = new DatabaseQuery();
		String output = dbq.queryCoordFromID("385");
		System.out.println("Final return is: " + output);
	}
}

