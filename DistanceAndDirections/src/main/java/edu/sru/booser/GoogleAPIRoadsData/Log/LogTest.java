package edu.sru.booser.GoogleAPIRoadsData.Log;

import java.io.IOException;

import edu.sru.booser.GoogleAPIRoadsData.API_DistanceMatrix;
import edu.sru.booser.GoogleAPIRoadsData.Keys.KEYS;

public class LogTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	KEYS.setKeyDist("k.txt");
	API_DistanceMatrix.getDistance("Butler PA", "Slippery Rock PA");
	Log.logError("ERROR MESSAGE");
	Log.PRINTLog();
	Log.CLEARLog();
		
	}

}
