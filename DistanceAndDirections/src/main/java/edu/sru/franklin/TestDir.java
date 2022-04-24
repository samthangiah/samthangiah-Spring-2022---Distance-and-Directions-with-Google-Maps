package edu.sru.franklin;

import java.io.IOException;

import edu.sru.booser.datastore.DirectionsAPI;
import edu.sru.booser.datastore.DirectionsHolder;

public class TestDir {
	public static void main(String args[]) {
		String origin = "111 Little Beaver Drive Beaver PA 15009 United States";
		String destination = "227 West Cooper Street Slippery Rock PA 16057 United States";
		try {
			String test = DirectionsAPI.getDirections(origin, destination, new DirectionsHolder());
			System.out.println(test);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
