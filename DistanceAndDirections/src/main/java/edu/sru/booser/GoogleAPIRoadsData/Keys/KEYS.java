package edu.sru.booser.GoogleAPIRoadsData.Keys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import edu.sru.booser.GoogleAPIRoadsData.Log.LogUtil;
import edu.sru.booser.Logging.Log;



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
	private static String keyDistance = null; //"AIzaSyCRm7IoRW0gGqjIgh_I5OrpzLWYKxxTr5s";
	
	/**
	 * Stores key for API_Directions
	 */
	private static String keyDirections = null;// "AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
	/**
	 * Stores default key for API API_GeoCode
	 */
	private static String keyGeoCode = null; //"AIzaSyD390VrMYSzUckUBYiWeXy2ZvVDrNtWUPg";
	
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
	 * Sets keyDistance to the value of NewKey
	 * @param NewKey the new Distance Matrix API key to be used
	 * @throws IOException
	 */
	public static void setStringKeyDist(String NewKey) throws IOException
	{
		if (NewKey != null)
		{	
			
			System.out.println("Using Key = " + NewKey);
			keyDistance = NewKey;
	
		}
		else
		{
			String Error = "Failure setStringKeyDist() \nKey cannot be null.";
			LogUtil.logError(Error);
		}
	}
	
	/**
	 * Sets keyDirections to the value of NewKey
	 * @param NewKey the new Directions API key to be used
	 * @throws IOException
	 */
	public static void setStringKeyDir(String NewKey) throws IOException
	{
		if (NewKey != null)
		{	
			
			System.out.println("Using Key = " + NewKey);
			keyDirections = NewKey;
	
		}
		else
		{
			String Error = "Failure setStringKeyDir() \nKey cannot be null.";
			LogUtil.logError(Error);
		}
	}
	
	/**
	 * Sets keyGeoCode to the value of NewKey
	 * @param NewKey the new Geocoding API key to be used
	 * @throws IOException
	 */
	public static void setStringKeyGeo(String NewKey) throws IOException
	{
		if (NewKey != null)
		{	
			
			System.out.println("Using Key = " + NewKey);
			keyGeoCode = NewKey;
	
		}
		else
		{
			String Error = "Failure setStringKeyGeo() \nKey cannot be null.";
			LogUtil.logError(Error);
		}
	}
	
	/**
	 * Sets the Distance matrix API key to the key stored in FILE.
	 * @param FILE - File Path of text document with key.
	 * @throws IOException
	 */
	public static void setFileKeyDist(String FILE) throws IOException
	{
		try
		{
			String OldKey = KEYS.getKeyDist();
			String NewKey = "Null";
			FileReader myFILE = new FileReader(FILE);
			BufferedReader myReader = new BufferedReader(myFILE);
			System.out.println("Key File Found");
			NewKey = myReader.readLine();

			if (NewKey != null)
			{	
				
				System.out.println("Using Key = " + NewKey);
				keyDistance = NewKey;
		
			}
			else
			{
				String Error = "Failure setKeyDist() \nKey in file:\t"+FILE+" is null.";
				LogUtil.logError(Error);
			}
			myReader.close();
			System.out.println("Old Key = " + OldKey + " | New Key = " + NewKey);
		}
		catch (IOException e) {
			e.printStackTrace();
			LogUtil.logError("ERROR KEYS.setKeyDist() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	/**
	 * Sets the Directions API key to the key stored in FILE. 
	 * @param FILE - File Path of text document with key.
	 * @throws IOException
	 */
	public static void setFileKeyDir(String FILE) throws IOException
	{
		try
		{
			String OldKey = KEYS.getKeyDist();
			String NewKey = "Null";
			FileReader myFILE = new FileReader(FILE);
			BufferedReader myReader = new BufferedReader(myFILE);
			System.out.println("Key File Found");
			NewKey = myReader.readLine();

			if (NewKey != null)
			{	
				
				System.out.println("Using Key = " + NewKey);
				keyDirections = NewKey;
		
			}
			else
			{
				String Error = "Failure setKeyDist() \nKey in file:\t"+FILE+" is null.";
				LogUtil.logError(Error);
			}
			myReader.close();
			System.out.println("Old Key = " + OldKey + " | New Key = " + NewKey);
		}
		catch (IOException e) {
			e.printStackTrace();
			LogUtil.logError("ERROR KEYS.setKeyDir() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	
	/**
	 * Sets the GeoCode API key to the key stored in FILE
	 * @param FILE - File Path of text document with key.
	 * @throws IOException
	 */
	public static void setFileKeyGeo(String FILE) throws IOException
	{
		try
		{
			String OldKey = KEYS.getKeyDist();
			String NewKey = "Null";
			FileReader myFILE = new FileReader(FILE);
			BufferedReader myReader = new BufferedReader(myFILE);
			System.out.println("Key File Found");
			NewKey = myReader.readLine();

			if (NewKey != null)
			{	
				
				System.out.println("Using Key = " + NewKey);
				keyGeoCode = NewKey;
		
			}
			else
			{
				String Error = "Failure setKeyDist() \nKey in file:\t"+FILE+" is null.";
				LogUtil.logError(Error);
			}
			myReader.close();
			System.out.println("Old Key = " + OldKey + " | New Key = " + NewKey);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			LogUtil.logError("ERROR KEYS.setKeyGeo() :: File ("+FILE+") not found:\n" + e.toString());

		}
	}
	
	/**
	 * Updates all KEYS.java keys from the same file.
	 * @param FILE the file to update from.
	 * @throws IOException
	 */
	public static void singleSharedFileKey(String FILE) throws IOException
	{
		try {
			KEYS.setFileKeyDir(FILE);
			KEYS.setFileKeyDist(FILE);
			KEYS.setFileKeyGeo(FILE);
			
		} catch (IOException e) {
			e.printStackTrace();
			Log.logError(e.toString());
		}
		
	
	}
	
	/**
	 * Updates all KEYS.java keys to the NewKey
	 * @param NewKey the new API Key for all APIs
	 * @throws IOException
	 */
	public static void singleSharedStringKey(String NewKey) throws IOException
	{
		try 
		{
			KEYS.setStringKeyDist(NewKey);
			KEYS.setStringKeyDir(NewKey);
			KEYS.setStringKeyGeo(NewKey);
		}
		catch (IOException e)
		{
			Log.logError(e.toString());
		}
	}
	
	
}
