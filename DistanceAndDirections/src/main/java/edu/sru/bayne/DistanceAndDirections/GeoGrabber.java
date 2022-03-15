package edu.sru.bayne.DistanceAndDirections;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;
import org.apache.commons.lang3.StringUtils;

/**
 * Class to handle geocoding and reverse-geocoding events. API requests may be built from human-readable address to
 * be geocoded into coordinates, or Coordinates can be used to build a call in order to find A human-readable address.
 * Can create class without address or coordinates, but is advised to create them by including parameters: 
 * 1. A single (String) Address or
 * 2. A set of two coordinates (String) Longitude and (String) Latitude
 * @author Gregory
 *
 */
public class GeoGrabber {
	final String key = "AIzaSyD390VrMYSzUckUBYiWeXy2ZvVDrNtWUPg";
	private String baseUrl = "https://maps.googleapis.com/maps/api/geocode/json?new_forward_geocoder=true&address=";
	private String address = "";
	private String lng = "";
	private String lat = "";
	
	// base constructor
	// must explicity use buildFromAddress() or buildFromCoordinates() or it will fail
	public GeoGrabber() {
		
	}
	
	// constructor using address
	public GeoGrabber(String address) {
		this.setAddress(address);
	}
	
	// constructor using lng/lat
	public GeoGrabber(String lng, String lat) {
		this.setLng(lng);
		this.setLat(lat);
	}
	

	/**
	 * Method builds valid url from provided attributes. User MUST pass human-readable address to call this method.
	 * Words in address should be delimited by " ", "+", or "%20"
	 * @return url for geocoding request (for JSON object)
	 */
	public String buildFromAddress(String hrAddress) {
		String url = getBaseUrl();
		setAddress(hrAddress);
		url += getAddress().replaceAll(" ", "+");
		url += "&key=";
		url += getKey();
		
		return url;
	}
	
	/**
	 * Provided url is scanned for JSON object. If found, finds and sets longitude and latitude to values of the JSON object.
	 * @param ref is a url for the geocoding request. Should be chained in from GeoGrabber.build();
	 * @return true if successful
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public boolean fetchCoordinatesFromAddress(String ref) throws IOException, InterruptedException {
		//Grabbing JSON with geoInformation
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder().uri(URI.create(ref)).build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				
			//store JSON obj to local copy (jObj)
			JSONObject jObj = new JSONObject(response.body());
				
			//output full jObj //test
			System.out.println("my jobj: " + jObj);
				
			String lngLat = StringUtils.substringBetween(jObj.toString(), "\"location\":{", ",\"location_type\"");
				
			//test
			System.out.println("my coordinates: " + lngLat);
				
			String lng = StringUtils.substringBetween(lngLat, "\"lng\":", ",");
			String lat = StringUtils.substringBetween(lngLat, ",\"lat\":", "}");
			this.setLng(lng);
			this.setLat(lat);
			//test
			System.out.println("my longitude: " + lng);
			System.out.println("my latitude: " + lat);
		
		return true;
	}
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getKey() {
		return key;
	}
	

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
	
	public static void main(String[] args){
		
		//Build Url for calling geocoding API with specific address and key
		GeoGrabber newReq = new GeoGrabber();
		String ref = newReq.buildFromAddress("401 Suncrest Drive Cranberry Township PA 16066");
		System.out.println("JSON file link: " + ref);
		
		try {
			//Send url to find and parse coordinates
			newReq.fetchCoordinatesFromAddress(ref);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
		
		System.out.println("Address:   " + newReq.getAddress());
		System.out.println("Longitude: " + newReq.getAddress());
		System.out.println("Latitude:  " + newReq.getAddress());
	
	}
}
