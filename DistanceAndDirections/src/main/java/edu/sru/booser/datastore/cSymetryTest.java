package edu.sru.booser.datastore;

import java.io.IOException;

import com.google.maps.GeocodingApi;

import edu.sru.bayne.DistanceAndDirections.GeoGrabber;

public class cSymetryTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		String butler = "40.8913626,-79.9248433";
			String b1 = "40.8913626";
			String b2 = "-79.9248433";
		String slip = "41.0666688,-80.0516223";
			String s1 = "41.0666688";
			String s2 = "-80.0516223";
		DataStore a = new DataStore(butler, slip);
		DataStore b = new DataStore(slip, butler);
		
		GeoGrabber c1 = new GeoGrabber();
		c1.fetchAddressFromCoordinates(c1.buildFromCoordinates(b1, b2));
		GeoGrabber c2 = new GeoGrabber();
		c2.fetchAddressFromCoordinates(c2.buildFromCoordinates(s1, s2));
		DataStore c = new DataStore(c1.getAddress(),c2.getAddress());
		DataStore d = new DataStore(c2.getAddress(),c1.getAddress());
		
		//Test A and B
		if (a.getDistMiles() == b.getDistMiles())
		{
			System.out.println("A and B have the same Distance: " + a.getDistMiles() + " vs " + b.getDistMiles());
		}
		else
		{
			System.out.println("A and B have different Distances: " + a.getDistMiles() + " vs " + b.getDistMiles());
		}
		
		//Test C and D
		if (c.getDistMiles() == d.getDistMiles())
		{
			System.out.println("C and D have the same Distance: " + c.getDistMiles() + " vs " + d.getDistMiles());
		}
		else
		{
			System.out.println("C and D have different Distances: " + c.getDistMiles() + " vs " + d.getDistMiles());
		}
	}

}
