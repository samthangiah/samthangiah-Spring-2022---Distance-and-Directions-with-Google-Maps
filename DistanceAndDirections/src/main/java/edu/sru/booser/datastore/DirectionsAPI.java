 /* Code for accessing the Google Directions API
 */
package edu.sru.booser.datastore;
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



/**
 * Generates a request for the Google Directions @API and parses the response.
 * Based heavily on code provided by Dr. Thangiah for sending Google Distance Matrix request,
 * and parsing information from the XML response.
 * 
 * @author Michael Booser
 * @author Dr. Thangiah
 *
 */
public class DirectionsAPI {
	
	/**
	 * The key used to access the Directions @api
	 */
	final static String apiKey = "&key=AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
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
	
	/**
	 * It contacts the google APi and attempts to get directions from the past origin to the directions.
	 * It currently outputs test data.
	 * @param newOrigin New starting location
	 * @param newDestination
	 * @return "Temp from directions api" 
	 * @throws IOException throws if API call is bad
	 */
	public static String getDirections(String newOrigin, String newDestination) throws IOException {
		ReadableByteChannel inChannel = null;
		String Output = "";
		
		try {
			URL directionsCallAPI = new URL(
					CoreAPI +
					(Origin + newOrigin) +
					(Destination + newDestination) +
					Mode +
					Measurement +
					apiKey
					);
			_httpConnection = 
					(HttpURLConnection) directionsCallAPI.openConnection();
			InputStream stream = _httpConnection.getInputStream();
			BufferedReader bufReader = new BufferedReader(
					new InputStreamReader(stream));
			/*
			 * Makes recDataSB to read the xml file
			 * from the bufReader 
			 */
			StringBuilder recDataSB = new StringBuilder();
			String recData;
			while ((recData = bufReader.readLine()) != null)
			{
				recDataSB.append(recData);
				//System.out.println(recDataSB.toString());
			}
			System.out.println(recDataSB.toString());
			System.out.println(recDataSB);
			Output = parseDirections(recDataSB.toString());
		}
		catch (IOException e)
		{
			throw e; // Propagate the exception
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
		
		//return Output;
		
		//Temporary Output to test HTML. Will remove once Output parses correctly
		return "Temp from Directions API";
	}
	/**
	 * It takes in the xml data from the google api and parses it into a usable form. 
	 * @param xmlIn the xml data from google api
	 * @return the formatted xml data
	 */
	public static String parseDirections(String xmlIn) {
		String output = "";
		try
		{	
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder Builder = factory.newDocumentBuilder();
			Document Doc = Builder.parse(new ByteArrayInputStream(xmlIn.getBytes("UTF-8")));
			NodeList instructionList = Doc.getElementsByTagName("html_instructions");
			
			for(int i=0; i < instructionList.getLength(); i++)
			{	
				Element instruction = (Element) instructionList.item(i);
				//Error Segment
				Node task = instruction.getAttributes().item(i);	//.getElementsByTagName("html_instructions").item(i);
				output = output + "Step " + (i+1) + " " + instruction.getAttribute("html_instructions") + "end\n";
			
			}
			
			return output;
		}	
			catch (ParserConfigurationException e) 
			{
				e.printStackTrace();
			}
			catch (SAXException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				
			}
		return output;

	newOrigin = newOrigin.replaceAll(" ", "%20");
	newDestination = newDestination.replaceAll(" ", "%20");
		
	try {
		URL directionsCallAPI = new URL(
				CoreAPI +
				(Origin + newOrigin) +
				(Destination + newDestination) +
				Mode +
				Measurement +
				apiKey
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
		throw e; // Propagate the exception
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
	 */
	public static void parseSUMMARY(String xmlIn, DirectionsHolder holder) {
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
			System.out.println("Occurred at 2");
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
	public static void parseINSTRUCTIONS(String xmlIn, DirectionsHolder holder) {
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
			System.out.println("Occurred at 2");
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
		
	















