package edu.sru.booser.GoogleAPIRoadsData.Keys;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import edu.sru.booser.GoogleAPIRoadsData.Log.Log;



/**
 * File for accessing and editing
 * API Keys in GoogleAPIRoadsData.
 * 
 * @author Michael Booser
 *
 */
public class KEYS {

	/**
	 * Stores key for API_DistanceMatrix
	 */
	private static String keyDistance = "AIzaSyCRm7IoRW0gGqjIgh_I5OrpzLWYKxxTr5s";
	
	/**
	 * Stores key for API_Directions
	 */
	private static String keyDirections = "AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
	/**
	 * Stores default key for API API_GeoCode
	 */
	private static String keyGeoCode = "AIzaSyD390VrMYSzUckUBYiWeXy2ZvVDrNtWUPg";
	
	public static String getKeyDist()
	{
		return keyDistance;
	}
	public static String getKeyDir()
	{
		return keyDirections;
	}
	public static String getKeyGeo()
	{
		return keyGeoCode;
	}
	
	/**
	 * Sets the Distance matrix API key the key stored in FILE.
	 * @param FILE 
	 * @throws IOException
	 */
	public static void setKeyDist(String FILE) throws IOException
	{
		try
		{
			File myFILE = new File(FILE);
			Scanner myReader = new Scanner(myFILE);
			keyDistance = myReader.nextLine();	
			myReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			Log.logError("ERROR KEYS.setKeyDist() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	public static void setKeyDir(String FILE) throws IOException
	{
		try
		{
			File myFILE = new File(FILE);
			Scanner myReader = new Scanner(myFILE);
			keyDirections = myReader.nextLine();
			myReader.close();
		}
		catch (IOException e) {
			e.printStackTrace();
			Log.logError("ERROR KEYS.setKeyDir() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	public static void setKeyGeo(String FILE) throws IOException
	{
		try 
		{
			File myObj = new File(FILE);
			Scanner myReader = new Scanner(myObj);
			keyGeoCode = myReader.nextLine();
			myReader.close();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.logError("ERROR KEYS.setKeyGeo() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	
	/**
	 * Sets runs setKey*() for each API Key
	 * Uses the default file Location
	 * DistanceKey.txt stores new keyDistance
	 * DirectionsKey.txt stores new keyDirections
	 * GeoCodeKey.txt stores new keyGeoCode
	 * 
	 * @throws IOException
	 */
	public void updateAllKeys() throws IOException
	{
		try 
		{
			setKeyDist("DistanceKey.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.logError("File DistanceKey.txt not found:\n" + e.toString());
		}
		try
		{
			setKeyDir("DirectionsKey.txt");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Log.logError("File DirectionsKey.txt not found:\n" + e.toString());
		}
		try
		{
			setKeyGeo("GeoCodeKey.txt");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			Log.logError("File GeoCodeKey.txt not found: \n" + e.toString());
		}
	
	}
	
	public static void main(String args[]) throws IOException {
		KEYS.setKeyDist("k.txt");
	}
	
}
