package edu.sru.bayne.DistanceAndDirections;

import java.io.IOException;

import edu.sru.bayne.DistanceAndDirections.domain.Search;

/**
 * Testing class for checking GeoCoding methods. This class checks methods in the 'GeoGrabber' class,
 * and related methods in the 'Search' Class.
 * @author Gregory
 *
 */
public class GeoGrabberTest {
	public static void main(String[] args){
		
		//Testing GeoCoding
		System.out.println("------------Testing GeoCoding--------------");
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
		System.out.println("Latitude: " + newReq.getLat());
		System.out.println("Longitude:  " + newReq.getLng());
		
		//Testing Reverse GeoCoding
		System.out.println("------------Testing Reverse GeoCoding--------------");
		GeoGrabber newReq1 = new GeoGrabber();
		String ref1 = newReq1.buildFromCoordinates("40.7220003","-80.1291846");
		System.out.println("JSON file link: " + ref1);
		
		try {
			//Send url to find and parse coordinates
			newReq.fetchAddressFromCoordinates(ref1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {	
			e.printStackTrace();
		}
		
		System.out.println("Address:   " + newReq1.getAddress());
		System.out.println("Latitude: " + newReq1.getLat());
		System.out.println("Longitude:  " + newReq1.getLng());
		
		// Testing search methods
		// do not save to database
		
		//Address to Coordinates
		String add1 = "401 Suncrest Drive Cranberry Township PA 16066";
		String add2 = "322 North Mckean St Butler PA 16001";
		
		System.out.println("------------Testing Search Address2Coordinates--------------");
		Search mySearch = new Search();	
		mySearch.setOrigin(add1);
		mySearch.setDestination(add2);
		
		System.out.println("Origin Latitude: " + mySearch.getLat1() + " | Destination Latitude: " + mySearch.getLat2());
		System.out.println("Origin Longitude: " + mySearch.getLng1() + " | Destination Longitude: " + mySearch.getLng2());
		
		try {
			mySearch.geoBothAddressCoordinates(add1, add2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Origin Latitude: " + mySearch.getLat1() + " | Destination Latitude: " + mySearch.getLat2());
		System.out.println("Origin Longitude: " + mySearch.getLng1() + " | Destination Longitude: " + mySearch.getLng2());
		
		//Coordinates to Address
		String lati1 = "40.7220003";
		String long1 = "-80.1291846";
		String lati2 = "40.8641043";
		String long2 = "-79.8938038";
		
		
		System.out.println("------------Testing Search Coordinates2Address--------------");
		Search mySearch1 = new Search();	
		mySearch1.setLng1(long1);
		mySearch1.setLat1(lati1);
		mySearch1.setLng2(long2);
		mySearch1.setLat2(lati2);
		
		System.out.println("Origin Address: " + mySearch1.getOrigin());
		System.out.println("Destination Address: " + mySearch1.getDestination());
		
		try {
			mySearch1.geoC2A(lati1, long1, lati2, long2);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Origin Address: " + mySearch1.getOrigin());
		System.out.println("Destination Address: " + mySearch1.getDestination());
		
		
				

	}

}

