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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class DirectionsAPI {

	final static String apiKey = "&key=AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
	static final String CoreAPI = "https://maps.googleapis.com/maps/api/directions/xml?";
	static final String Origin = "origin=";
	static final String Destination = "&destination=";
	static final String Mode = "&driving";
	static final String Measurement = "&imperial";
	
	private static HttpURLConnection _httpConnection = null;
	
	
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

	
	}
}
