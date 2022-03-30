package edu.sru.booser.Logging;

import java.io.IOException;

public class LogTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		final String TestEvent = "Test Data";
		Log.logEvent(TestEvent);
		Log.PRINTLog();
		Log.CLEARLog();
		
	}

}
