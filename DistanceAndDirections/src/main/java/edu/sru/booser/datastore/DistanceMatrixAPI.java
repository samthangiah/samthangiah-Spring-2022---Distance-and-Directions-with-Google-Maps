
package edu.sru.booser.datastore;
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

import java.io.*;


/* Used for obtaining distance using Google Maps API*/
/**
 * Code from Dr.Thangiah for sending request to Google's Distance Matrix @API,
 * and parsing the XML response into miles.
 * 
 * @author Dr. Thangiah
 *
 */
public class DistanceMatrixAPI {
	
	/**
	 * Current Working Distance Matrix API KEY
	 */
	final static String apiKey = "&key=AIzaSyCRm7IoRW0gGqjIgh_I5OrpzLWYKxxTr5s";

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
	 */
	public static float getDistance(String orig, String dest) throws IOException
	{
		System.out.println("DISTANCE HAS BEEN CALLED");
		orig = orig.replaceAll(" ", "%20");
		dest = dest.replaceAll(" ", "%20");
		
		ReadableByteChannel inChannel = null;
		float distance=0;
		try
		{
			//set the origin and destination
			_fromAddress = "origins="+orig;
			_toAddress = "&destinations="+dest;
			final String _outRequest = _gapiUrlPart
					+ _fromAddress
					+ _toAddress
					+ "&mode=driving&units=imperial"
					+ apiKey;
			//System.out.println(_outRequest);
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
			System.out.println(recDataSB.toString());
			distance = parseDistance(recDataSB.toString());
		}
		catch (IOException e)
		{
			throw e; // Propagate the exception
		}
		finally
		{
			if (inChannel != null)
				inChannel.close();
		}	
		return distance;
	}

	

	public static float parseDistance(String xmlString)
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
			
		}

		return distance;

	}
}
