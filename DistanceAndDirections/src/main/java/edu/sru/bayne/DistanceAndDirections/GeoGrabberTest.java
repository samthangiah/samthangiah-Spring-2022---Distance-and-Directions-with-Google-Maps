package edu.sru.bayne.DistanceAndDirections;

import java.io.IOException;

public class GeoGrabberTest {
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
		System.out.println("Longitude: " + newReq.getLng());
		System.out.println("Latitude:  " + newReq.getLat());

	}

}

