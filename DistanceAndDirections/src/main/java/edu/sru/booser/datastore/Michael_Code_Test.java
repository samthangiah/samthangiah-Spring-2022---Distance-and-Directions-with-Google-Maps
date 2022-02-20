package edu.sru.booser.datastore;
public class Michael_Code_Test {

	
	
	public static void main(String[] args) {

			DataStore testOne = new DataStore("OH", "Cleveland", "PA", "Butler");
			System.out.println("Test ONE Output the Input Variable\n" + "Start: " + testOne.getInputStart() + "\nEnd: " + testOne.getInputEnd() );
			//Distance
			testOne.calcDistance();
			//Directions
			testOne.printDistance();
			
			DataStore testTwo = new DataStore("Butler_PA", "SlipperyRock_PA");
			System.out.println("\nTest TWO Output the Input Variable\n" + "Start: " + testTwo.getInputStart() + "\nEnd: " + testTwo.getInputEnd() );
			//Distance
			testTwo.calcDistance();
			testTwo.printDistance();
			//Directions
			testTwo.calcDirections();
			System.out.println(testTwo.getDirections());
			
			//System.out.println("\nEnd Program");
			//testTwo.calcDirections();
	}
}
