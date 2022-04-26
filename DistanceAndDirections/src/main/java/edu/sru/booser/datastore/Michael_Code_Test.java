package edu.sru.booser.datastore;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Michael_Code_Test {

	
	
	public static void main(String[] args) {
		
		String Blank = "            ";
		Blank.strip();
		Blank = Blank.replaceAll("\\s", "");
		if (Blank == null)
		{
			System.out.println("1 Blank is a null String");
		}
		else if(Blank == "")
		{
			System.out.println("2 Blank is \"\"");
		}
		else
		{
			System.out.println("3 Blank is \"" + Blank + "\"");
		}
		
		/*
		 	DataStore testOne = new DataStore("OH", "Cleveland", "PA", "Butler");
			System.out.println("Test ONE Output the Input Variable\n" + "Start: " + testOne.getInputStart() + "\nEnd: " + testOne.getInputEnd() );
			//Distance
			testOne.calcDistance();
			//Directions
			testOne.printDistance();
		*/	
		/*
			DataStore testTwo = new DataStore("167HinchbergerRoad_Butler_PA_16002_US", "322NMcKeanSt,Butler,PA16001");
			System.out.println("\nTest TWO Output the Input Variable\n" + "Start: " + testTwo.getInputStart() + "\nEnd: " + testTwo.getInputEnd() );
			//Distance
			testTwo.calcDistance();
			testTwo.printDistance();
			//Directions
			testTwo.calcStringDirections();
			System.out.println(testTwo.getDirections());
			
			//System.out.println("\nEnd Program");
			//testTwo.calcDirections();
			 
		*/
		/*
			DataStore testThree = new DataStore("167HinchbergerRoad_Butler_PA_16002_US", "322NMcKeanSt,Butler,PA16001");
			
			System.out.println("\nSummary is: " + testThree.Holder.getSummary() + "\n");
			testThree.Holder.printHolder();
			System.out.println("\n-----------------------------------------------------\n");
			
			DataStore testFour = new DataStore("777_BrocktonAvenue_Abington_MA", "2575_Us_Hwy_43_Winfield_AL,35594");
			System.out.println("Distance = " + testFour.getDistMiles());
			System.out.println("\nSummary is: " + testFour.Holder.getSummary() + "\n");
			testFour.Holder.printHolder();
			System.out.println("\n-----------------------------------------------------\n");
		*/
		
	}
}
