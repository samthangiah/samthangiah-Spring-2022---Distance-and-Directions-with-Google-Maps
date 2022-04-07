package edu.sru.booser.GoogleAPIRoadsData.Keys;

import java.io.IOException;

import edu.sru.booser.GoogleAPIRoadsData.API_DistanceMatrix;
import edu.sru.booser.GoogleAPIRoadsData.DataStore;
import edu.sru.booser.GoogleAPIRoadsData.Log.LogUtil;

public class UploadKeyTest {

	public static void main(String[] args) throws IOException
	{
		DataStore Test = new DataStore("Butler_PA", "SlipperyRock_PA");
		LogUtil.CLEARLog();
		KEYS.setFileKeyDist("C:\\Users\\Michael Booser\\Desktop\\ExamplePath\\ExampleDistanceKey.txt");
		//KEYS.setKeyDist("C:\\Users\\Michael Booser\\Desktop\\aids\\bullshit.txt");
		System.out.println("Key used: " + KEYS.getKeyDist());
		Test.calcDistance();
		System.out.println(Test.getDistMiles());
		
		LogUtil.logEvent("HELP");
		LogUtil.PRINTLog();
		LogUtil.CLEARLog();
		
		
	}
}
