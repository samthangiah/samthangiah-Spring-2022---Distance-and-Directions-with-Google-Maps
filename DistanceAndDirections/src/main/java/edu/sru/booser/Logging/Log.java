package edu.sru.booser.Logging;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


/**
 * Log.java appends a file with time-stamped Event and Error data.
 * Default file is Log.txt and can be changed by editing the FILE constant.
 * 
 * @author Michael Booser
 *
 */
public class Log {
	
	//Name of LOG File
	private static final String FILE = "Log.txt";
	
	/**
	 * Takes any String  and appends it to FILE with Date/TimeStamp.
	 * @param Input The Information to be Logged.
	 * @throws IOException
	 */
	public static void logEvent(String Input) throws IOException {
		FileWriter logFile = new FileWriter(FILE, true);
		BufferedWriter bw = new BufferedWriter(logFile);
		String eTime = TimeStamp();
	
		String OUTSTRING = "\n-- New Event --\t" + eTime + "\n\n" +  Input + "\n\n-- End Event --\t" + eTime + "\n";
		//System.out.println(OUTSTRING);
		bw.write(OUTSTRING);
		bw.close();
	
	}
	
	/**
	 * Takes an Error and appends it to FILE with Date/TimeStamp.
	 * @param Input
	 * @throws IOException
	 */
	public static void logError(String Input) throws IOException {
		FileWriter logFile = new FileWriter(FILE, true);
		BufferedWriter bw = new BufferedWriter(logFile);
		String eTime = TimeStamp();
		
		String OUTSTRINGB = "\n-- New ERROR --\t" + eTime + "\n\n" +  Input + "\n\n-- End ERROR --\t" + eTime + "\n";
		//System.out.println(OUTSTRING);
		bw.write(OUTSTRINGB);
		bw.close();
	}
	
	/**
	 * Captures date and time information.
	 * Formatted to: "YEAR/MONTH/DAY HOUR/MIN/SEC"
	 * Uses java.time
	 * 
	 * @return Date/Time as a String
	 */
	public static String TimeStamp() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime TIME = LocalDateTime.now();
		String output = format.format(TIME);
		return output;
	
	}
	
	/**
	 * Outputs the entire FILE to the console.
	 * @throws IOException
	 */
	public static void PRINTLog() throws IOException {
		File myObj = new File(FILE);
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNext())
		{
			String data = myReader.nextLine();
			System.out.println(data);
			
		}
		myReader.close();
	}
	
	/**
	 * Deletes the contents FILE
	 * @throws IOException
	 */
	public static void CLEARLog() throws IOException {
		FileWriter writer = new FileWriter(FILE);
		
		System.out.print("Clearing Log... ");
		Writer.nullWriter();
		Log.PRINTLog();
		System.out.println(" ...Cleared!");
		writer.close();
	}
	
}
