package edu.sru.booser.GoogleAPIRoadsData;

import java.io.IOException;

public class Test_JAR 
{
	
	public static void main(String args[]) throws IOException, InterruptedException
	{
		//Common Variables
		String LocS = "322 N McKean St Butler PA"; //Starting Location
		String LocE = "1 Morrow Way SlipperyRock PA"; //Ending Location
		
		
		
		//Requires Input Formatting
		//Objective A: Distance between two points (Lat/Lng)
		System.out.println("*****************************************\nStarting: Distance between points (Lat/Lng)");
			//Get Coordinates from Address LocS
			API_GeoCode A1 = new API_GeoCode(LocS);
			A1.fetchCoordinatesFromAddress(A1.buildFromAddress(LocS));
			String OriA =  A1.getLat() + "," + A1.getLng();
			System.out.println("A1 = " + OriA);
		
			//Get Coordinates from Address LocE
			API_GeoCode A2 = new API_GeoCode(LocE);
			A2.fetchCoordinatesFromAddress(A2.buildFromAddress(LocE));
			String DestA = A2.getLat() + "," + A2.getLng();
			System.out.println("A2 = " + DestA);
		
			DataStore A = new DataStore(OriA, DestA);
			A.calcDistance();
			System.out.println("Distance = " + A.getDistMiles());
		
		
		System.out.println("Ending: Distance between points (Lat/Lng)\n*****************************************");
		
		
		
		//REQUIRES INPUT FORMATTING
		//Objective B: Distance between to points a that are addresses
		System.out.println("*****************************************\nStarting: Distance between to points (Address)");
			DataStore B = new DataStore(LocS, LocE);
			B.calcDistance();
			System.out.println("Distance between the Maridon Museum and Slippery Rock University is: " + B.getDistMiles() + " Miles.");
		System.out.println("Ending: Distance between to points (Address)\n*****************************************");
		
		
		//Objective C: Convert between Address and Coordinates (Both Ways)
		System.out.println("*****************************************\nStarting: Address to Coordinates");
			//Get Coordinates from Address LocS
			API_GeoCode C1 = new API_GeoCode(LocS);
			C1.fetchCoordinatesFromAddress(C1.buildFromAddress(LocS));
			String OriC =  C1.getLat() + "," + C1.getLng();
			System.out.println("A1 = " + OriC);
			
			//Get Coordinates from Address LocE
			API_GeoCode C2 = new API_GeoCode(LocE);
			C2.fetchCoordinatesFromAddress(C2.buildFromAddress(LocE));				
			String DestC = C2.getLat() + "," + C2.getLng();
			System.out.println("A2 = " + DestC);
			
			System.out.println("Address " + LocS + " became " + OriC);
			System.out.println("Address " + LocE + " became " + DestC);
		System.out.println("Ending: Address to Coordinates\n*****************************************");
		
		System.out.println("*****************************************\nStarting: Coordinates to Address");
		System.out.println("Ending: Coordinates to Address\n*****************************************");
		
		
		//	FUNCTIONING 
		//Objective D: Distance Between Zip Codes
		System.out.println("*****************************************\nStarting: Distance between points (Zip)\n");
			LocS = "11101"; LocE = "90027";
			DataStore D = new DataStore(LocS, LocE);
			D.calcDistance();
			System.out.println("Distance between Long Island NY(Zip: 11101) and Hollywood LA(Zip: 90027) is: " + D.getDistMiles() + " Miles.");
		System.out.println("Ending: Distance between points (Zip)\n*****************************************");
		
		
		
		//Objective E: Directions From Zip, Coordinates, or Address
		System.out.println("*****************************************\nStarting: Directions between to points (Address)");
			DataStore E1 = new DataStore(LocS, LocE);
			API_Directions.getDirections(LocS,LocE, E1.Holder);
			System.out.println(E1.Holder.toString());	
		System.out.println("Ending: Directions between to points (Address)\n*****************************************");
		
		System.out.println("*****************************************\nStarting: Directions between to points (Coordinates)");
			//Get Coordinates from Address LocS
			API_GeoCode Ea = new API_GeoCode(LocS);
			Ea.fetchCoordinatesFromAddress(Ea.buildFromAddress(LocS));
			String OriE =  Ea.getLat() + "," + Ea.getLng();
			System.out.println("Ea = " + OriC);
		
			//Get Coordinates from Address LocE
			API_GeoCode Eb = new API_GeoCode(LocE);
			Eb.fetchCoordinatesFromAddress(Eb.buildFromAddress(LocE));				
			String DestE = Eb.getLat() + "," + Eb.getLng();
			System.out.println("Eb = " + DestE);
			
			DataStore E2 = new DataStore(OriE, DestE);
			API_Directions.getDirections(OriE, DestE, E2.Holder);
			System.out.println(E2.Holder.toString());
		System.out.println("Ending: Directions between to points (Coordinates)\n*****************************************");
		
		System.out.println("*****************************************\nStarting: Directions between to points (Zip)");
			DataStore E3 = new DataStore("16001", "16057");
			API_Directions.getDirections(E3.getInputStart(),E3.getInputEnd(),E3.Holder);
			System.out.println(E3.Holder.toString());
		System.out.println("Ending: Directions between to points (Zip)\n*****************************************");
		
		
	}
	
}
