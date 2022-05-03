package edu.sru.booser.GoogleAPIRoadsData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

import javax.xml.parsers.*;

//import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;
import org.w3c.dom.*;

import edu.sru.booser.GoogleAPIRoadsData.Hash.DataController;
import edu.sru.booser.GoogleAPIRoadsData.Hash.Table;
import edu.sru.booser.GoogleAPIRoadsData.Keys.KEYS;
import edu.sru.booser.Logging.Log;

import java.io.*;


/* Used for obtaining distance using Google Maps API*/
/**
 * Code from Dr.Thangiah for sending request to Google's Distance Matrix @API,
 * and parsing the XML response into miles.
 * 
 * @author Dr. Thangiah
 *
 */
public class API_DistanceMatrix {
	
	private DataController dc = new DataController();
	private Table dt = new Table();
	
	/**
	 * Google Maps API URL base
	 */
	private static String _gapiUrlPart = 
			"https://maps.googleapis.com/maps/api/distancematrix/xml?";

	/**
	 * HttpURLConnection object for connecting to the Google Maps API
	 * web interface
	 */
	private static HttpURLConnection _httpConnection = null;

	/**
	 * Destination address.
	 */
	private static String _toAddress = "&destinations=16057";

	/**
	 * Origin address.
	 */
	private static String _fromAddress = "origins=16058";

	/**
	 * Get the raw XML distance matrix.
	 * 
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static float getDistance(String orig, String dest, DataController dc, Table dt) throws IOException, InterruptedException
	{
		System.out.println("Before QC Check: " + orig + "," + dest);
			//Removes White Space And Blanks
			orig = orig.replaceAll(" ", "+");
			dest = dest.replaceAll(" ", "+");
			if(orig == "" && dest != "")
			{
				orig = dest;
			}
			else if(dest == "" && orig != "")
			{
				dest = orig;
			}
			else if(orig == "" && dest == "")
			{
				orig = "Slippery Rock University of Pennsylvania";
				dest = "Slippery Rock University of Pennsylvania";
			}
		
			//Confirms Addresses are Cords or makes Cords
			if(isCords(orig) == false)
			{
				orig = makeCords(orig);
			}
				else
				{
					System.out.println(orig + " is cords");
				}
			if(isCords(dest) == false)
			{
				dest = makeCords(dest);
			}
				else
				{
					System.out.println(dest + " is cords");
				}
		System.out.println("After QC Check: " + orig + "," + dest);
		
		if(dt.contains(orig, dest) || dt.contains(dest, orig))
		{
			System.out.println("Distance from Table = " + dt.getDataObject(orig, dest).getDistance());
			return dt.getDataObject(orig, dest).getDistance();
			//return dt.getDataObject(orig, dest).getDistance();
		}
		else
		{
			System.out.println("Did not use Table");
		}
		
		ReadableByteChannel inChannel = null;
		float distance=0;
		try
		{
			//set the origin and destination
			_fromAddress = "origins="+orig;
			_toAddress = "&destinations="+dest;
			String _outRequest;
				_outRequest = _gapiUrlPart
						+ _fromAddress
						+ _toAddress
						+ "&mode=driving&units=imperial"
						+ "&key="+ KEYS.getKeyDist();
			System.out.println(_outRequest);
			URL urlRequest = new URL(_outRequest);
	//System.out.println(urlRequest+"\n");
			_httpConnection = 
					(HttpURLConnection) urlRequest.openConnection();
			InputStream stream = _httpConnection.getInputStream();
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(stream));
			StringBuilder recDataSB = new StringBuilder();
			String recData;
			while ((recData = bufReader.readLine()) != null)
			{
				recDataSB.append(recData);
			}
			//System.out.println(recDataSB.toString());
			distance = parseDistance(recDataSB.toString());
		}
		catch (IOException e)
		{
			Log.logError("API call to Google Distance Matrix API Failed"
					+ "\nAPI Key USED = " + KEYS.getKeyDist() + "\n"
					+ e.toString());
			throw e; // Propagate the exception
		}
		finally
		{
			if (inChannel != null)
				inChannel.close();
		}
			System.out.println("Is Hitting Table");
			System.out.println("Storing Ori: " + orig + " Des: " + dest + " Distance: " + distance);
			dt.add(orig, dest, distance);
			dt.getDataObject(orig, dest).setDistance(distance);
			dt.add(dest, orig, distance);
			dt.getDataObject(dest, orig).setDistance(distance);
			dc.writeToTextFile();
		return distance;
	}

	

	public static float parseDistance(String xmlString) throws IOException
	{
		float distance=0;
		String strMileage;
		
		try {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = f.newDocumentBuilder();
		Document doc = b.parse(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		NodeList books = doc.getElementsByTagName("distance");
		for (int i = 0; i < books.getLength(); i++) {
		    Element book = (Element) books.item(i);
		    Node title = book.getElementsByTagName("text").item(0);
		    strMileage = title.getTextContent();
		    strMileage = strMileage.trim();
		    //remove the mi that is in the text string
		    strMileage = strMileage.replace("mi","");
		    strMileage = strMileage.replace(",", "");
		    strMileage = strMileage.trim();
		    //convert the mileage to a float
		    distance = Float.valueOf(strMileage);
		   // System.out.println(distance);
		}
		}
		catch (Exception e)
		{
			Log.logError("API_DistanceMatrix.parseDistance() Failure"
					+ "XML Being Parsed:\n" 
					+ xmlString
					+ "\n"
					+ e.toString());
	
		}

		return distance;

	}
	
	/**
	 * Returns false if address is human readable.
	 * @param address
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static boolean isCords(String address) throws IOException, InterruptedException
	{
		API_GeoCode ctest = new API_GeoCode();
		ctest.fetchCoordinatesFromAddress(ctest.buildFromAddress(address));
		if(ctest.getLat() == null)
		{
			return true;
		}

		return false;
	}
	
	public static String makeCords(String address) throws IOException, InterruptedException 
	{
		API_GeoCode cmake = new API_GeoCode();
		cmake.fetchCoordinatesFromAddress(cmake.buildFromAddress(address));
		String output = cmake.getLat() + "," + cmake.getLng();
		return output;
	}
	
	public static boolean qualityControl(String orig, String dest, boolean noAdd) throws IOException, InterruptedException
	{
		//Removes White Space And Blanks
		orig = orig.replaceAll(" ", "%20");
		dest = dest.replaceAll(" ", "%20");
		if(orig == "" && dest != "")
		{
			orig = dest;
			noAdd = true;
		}
		else if(dest == "" && orig != "")
		{
			dest = orig;
			noAdd = true;
		}
		else if(orig == "" && dest == "")
		{
			orig = "Slippery Rock University of Pennsylvania";
			dest = "Slippery Rock University of Pennsylvania";
			noAdd = true;
		}
		
		//Confirms Addresses are Cords or makes Cords
		if(isCords(orig) == false)
		{
			orig = makeCords(orig);
		}
		if(isCords(dest) == false)
		{
			dest = makeCords(dest);
		}
		
		return true;
	}
}
