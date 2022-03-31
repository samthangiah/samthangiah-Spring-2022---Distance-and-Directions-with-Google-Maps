package edu.sru.booser.GoogleAPIRoadsData;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import edu.sru.booser.GoogleAPIRoadsData.Keys.KEYS;
import edu.sru.booser.Logging.Log;



/**
 * Generates a request for the Google Directions @API and parses the response.
 * Based heavily on code provided by Dr. Thangiah for sending Google Distance Matrix request,
 * and parsing information from the XML response.
 * 
 * @author Michael Booser
 * @author Dr. Thangiah
 *
 */
public class API_Directions {
	
	/**
	 * The key used to access the Directions @api
	 */
	final static String apiKey = "AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
	static final String CoreAPI = "https://maps.googleapis.com/maps/api/directions/xml?";
	static final String Origin = "origin=";
	static final String Destination = "&destination=";
	static final String Mode = "&driving";
	static final String Measurement = "&imperial";
	
	private static HttpURLConnection _httpConnection = null;
	
	/**
	 * Returns a parsed XML reply from google's directions API.
	 * newOrigin and newDestination are the starting and ending points of the route.
	 * Holder is an object that can parse the xml response from Google.
	 * 
	 * @param newOrigin is the starting location requested by the user.
	 * @param newDestination is the end location requested by the user.
	 * @param Holder is an instance of a DirectionsHolder object used to store the parsed xml code
	 * @return returns the parsed xml file reply from Google's Directions API as a String
	 * @throws IOException
	 */
	public static String getDirections(String newOrigin, String newDestination, DirectionsHolder Holder) throws IOException {
	ReadableByteChannel inChannel = null;
	

	newOrigin = newOrigin.replaceAll(" ", "%20");
	newDestination = newDestination.replaceAll(" ", "%20");
		
	try {
		URL directionsCallAPI = new URL(
				CoreAPI +
				(Origin + newOrigin) +
				(Destination + newDestination) +
				Mode +
				Measurement +
				"&key=" + KEYS.getKeyDir()
				);
		_httpConnection = 
				(HttpURLConnection) directionsCallAPI.openConnection();
		InputStream stream = _httpConnection.getInputStream();
		BufferedReader bufReader = new BufferedReader(
				new InputStreamReader(stream));
		StringBuilder recDataSB = new StringBuilder();
		String recData;
		while ((recData = bufReader.readLine()) != null)

		{
			recDataSB.append(recData);
		}

		parseINSTRUCTIONS(recDataSB.toString(), Holder);
		parseSUMMARY(recDataSB.toString(), Holder);

	}
	catch (IOException e)
	{
		Log.logError("API call to Google Directions API Failed"
				+ "\nAPI Key USED = "
				+ KEYS.getKeyDir() + "\n"
				+ e.toString());
		throw e; // Propagate the exception
		//Log.logError(e.toString());
	}
	finally
	{
		if (inChannel != null)
			try {
				inChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	return Holder.toString();
	
}
	
	/**
	 * Parses the route summary from xmlIn into the DirectionsHolder.
	 * 
	 * @param xmlIn an XML response from the Google Distance API
	 * @param holder the object that will store the directions summary
	 * @throws IOException 
	 */
	public static void parseSUMMARY(String xmlIn, DirectionsHolder holder) throws IOException {
		try
		{
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(new ByteArrayInputStream(xmlIn.getBytes("UTF-8")));
			NodeList books = doc.getElementsByTagName("route");
			for (int i = 0; i < books.getLength(); i++)
			{
				Element book = (Element) books.item(i);
				Node title = book.getElementsByTagName("summary").item(0);
				String strIntake = title.getTextContent();
				holder.setSummary(strIntake);
			}
		
		}
		catch (Exception e)
		{
			Log.logError("API_Directions.parseSUMMARY Failure"
					+ "\nAPI Key USED = "
					+ KEYS.getKeyDir()
					+ "\nString to be parsed:\n\t"
					+ xmlIn
					+ "\nDirections Summary Used:\n"
					+ holder.getSummary()
					+ e.toString());
			try 
			{
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
		
	}
	
	/**
	 * Parses the directions from xmlIn into the directions vector
	 * of the holder object
	 * 
	 * @param xmlIn an XML response from the Google Distance API
	 * @param holder the object that will store the directions in a vector
	 */
	public static void parseINSTRUCTIONS(String xmlIn, DirectionsHolder holder) throws IOException {
		try
		{
			DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
			DocumentBuilder b = f.newDocumentBuilder();
			Document doc = b.parse(new ByteArrayInputStream(xmlIn.getBytes("UTF-8")));
			NodeList books = doc.getElementsByTagName("step");
			for (int i = 0; i < books.getLength(); i++)
			{
				Element book = (Element) books.item(i);
				Node title = book.getElementsByTagName("html_instructions").item(0);
				String strIntake = title.getTextContent();
				strIntake = strIntake.replace("<b>", "");
				strIntake = strIntake.replace("</b>", "");
				strIntake = strIntake.replace("<div style=\"font-size:0.9em\">", " ");
				strIntake = strIntake.replace("</div>", "");
				strIntake = strIntake.replace("<wbr/>", "");
				strIntake = strIntake.replace("&nbsp;", "");
				holder.directions.add(strIntake);
			}
		
		}
		catch (Exception e)
		{
			Log.logError("API_Directions.parseINSTRUCTIONS Failure"
					+ "\nAPI Key USED = "
					+ KEYS.getKeyDir()
					+ "\nString to be parsed:\n\t"
					+ xmlIn
					+ "\nDirections Holder Used:\n"
					+ holder.toString()
					+ e.toString());
			try 
			{
				Thread.sleep(5000);
			} catch (InterruptedException e1) 
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
}	