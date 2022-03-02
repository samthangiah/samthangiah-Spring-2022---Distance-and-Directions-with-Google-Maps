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
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;



public class DirectionsAPI {

	final static String apiKey = "&key=AIzaSyAStw6XHaUsvg_-LMDrOPGRl0ubLZi9aZ4";
	
	static final String CoreAPI = "https://maps.googleapis.com/maps/api/directions/xml?";
	static final String Origin = "origin=";
	static final String Destination = "&destination=";
	static final String Mode = "&driving";
	static final String Measurement = "&imperial";
	
	private static HttpURLConnection _httpConnection = null;
	
	
	public static DirectionsHolder getXMLDirections(String newOrigin, String newDestination) throws IOException {
		ReadableByteChannel inChannel = null;
		DirectionsHolder Output;
		
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
			//Output = parseString(recDataSB.toString());
			Output = SAXy_Parser.parseDirections(recDataSB.toString());
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
		return Output;
	}
	
	
	
	public static String getStringDirections(String newOrigin, String newDestination) throws IOException {
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
			//Output = parseString(recDataSB.toString());
			Output = recursiveStringParser(recDataSB.toString());
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
		return Output;
	}
	
	public static String recursiveStringParser(String xmlIn)
	{
		
		String outString = "";
		String htmlStart = "<html_instructions>";
		String htmlEnd = "</html_instructions>";
		
		
		int indHtmlStart = xmlIn.indexOf(htmlStart);
		int indHtmlEnd = xmlIn.indexOf(htmlEnd);
		String untrimmed= xmlIn.substring((indHtmlStart+19), indHtmlEnd);
		
		
		//System.out.println(xmlIn.substring(indHtmlEnd+20));
		//clean the string
		untrimmed = untrimmed.replaceAll("&lt;b&gt;", "");
		untrimmed = untrimmed.replaceAll("&lt;/b&gt;", "");
		untrimmed = untrimmed.replaceAll("/&lt;wbr/&gt;", "");
		untrimmed = untrimmed.replaceAll("&lt;div style=&quot;font-size:0.9em&quot;&gt;", "");
		untrimmed = untrimmed.replaceAll("&lt;/div&gt;", "");
		outString += untrimmed;
		
		
		//String subXmlIn = xmlIn.substring(indHtmlEnd)
		//Recursive Function Call he said laughing maniacally
		if(xmlIn.substring(indHtmlEnd+20).contains(htmlStart))
		{
			outString += recursiveStringParser(xmlIn.substring(indHtmlEnd+20));
		}
		
		
		
		return outString;
	}
}	


















