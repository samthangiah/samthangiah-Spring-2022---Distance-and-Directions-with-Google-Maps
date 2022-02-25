 /* CODE SOURCED FROM
 * DR. THANGIAH
 * 
 * 
 * -CHANGE_LOG-
 * 
 * -CHANGE
 * 		LINE 37 :: _gapiUrlPart
 * 		'http://..' to 'https://..'
 * 		CHANGE AVOIDS ERROR=
 * 		Requests to this API must be over SSL. Load the API with &quot;https://&quot; instead of &quot;http://&quot;.</error_message
 * 
 * -ADDED
 * 		LINE 83 :: getDistance(string, string);
 * 		+ "&key=AIzaSyBVQLXpEAFeBH8tXxwsT8Uyh5rRcuV11Ak"
 * 		ADDS PERSONAL DISTANCE MATRIX API KEY
 * 
 * -CHANGE
 * 		LINE 98 & 145
 * 		Commented out output line
 * 
 * -ADDED 
 * 		Line 54
 * 		Constanct for storing the Distance Matrix API Key
 */

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
	//private String _toAddress = "&destinations=Pittsburgh+PA";
	private static String _toAddress = "&destinations=16057";

	/**
	 * Origin address.
	 */
	//private String _fromAddress = "origins=Slippery+Rock+PA";
	private static String _fromAddress = "origins=16058";

	/**
	 * Get the raw XML distance matrix.
	 * 
	 * @throws IOException
	 */
	public static float getDistance(String orig, String dest) throws IOException
	{
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
			//System.out.println(recDataSB.toString());
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
		//String message;
		/*String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><DistanceMatrixResponse> "
				+ "<status>OK</status> <origin_address>Turkey City, PA 16058, USA</origin_address> "
				+ "<destination_address>Slippery Rock, PA 16057, USA</destination_address> "
				+ "<row>  <element>   <status>OK</status>   <duration>    <value>2986</value>    <text>50 mins</text>   </duration>  "
				+ " <distance>    <value>62315</value>    <text>38.7 mi</text>   </distance>  </element> </row></DistanceMatrixResponse>";
		*/
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
		
		/*String xml = "<message>HELLO!</message>";
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;
		try {
		    db = dbf.newDocumentBuilder();
		    InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlRecords));
		    try {
		        Document doc = db.parse(is);
		         message = doc.getDocumentElement().getTextContent();
		         message = doc.getDocumentElement().g   getAttribute("distance");
		        System.out.println(message);
		    } catch (SAXException e) {
		        // handle SAXException
		    } catch (IOException e) {
		        // handle IOException
		    }
		} catch (ParserConfigurationException e1) {
		    // handle ParserConfigurationException
		} */
		return distance;

	}
}
