package edu.sru.booser.GoogleAPIRoadsData.Hash;

public class FranklinTests {
	
	
	public static void main(String args[]) {
		DataController controller = new DataController();

		//read in data
		String filepath = "./data/Addresses.xlsx";
		controller.readFromExcelDoc(filepath);
		
		//test print
		controller.printTableHeader(1);
		
		controller.writeToTextFile("./data/testData.txt");

		//creating a new controller creates a blank hashtable, should be empty
		controller = new DataController();
		
		//reading from the text file should only work if the write actually works
		controller.readFromTextFile("./data/testData.txt");
		
		controller.printTableHeader(1);
	}

}
