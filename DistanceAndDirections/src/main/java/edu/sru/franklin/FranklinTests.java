package edu.sru.franklin;

public class FranklinTests {
	
	
	public static void main(String args[]) {
		DataController controller = new DataController();
		
		
		//read in data
		String filepath = "./data/Addresses.xlsx";
		controller.readFromExcelDoc(filepath);
		
		//test print
		controller.printTable();
		
		controller.writeToTextFile();
		
		//creating a new controller creates a blank hashtable, should be empty
		controller = new DataController();
		
		//reading from the text file should only work if the write actually works
		controller.readFromTextFile();
		
		//test print
		controller.printTable();
	}

}
