package edu.sru.franklin;

public class FranklinTests {
	
	
	public static void main(String args[]) {
		DataController controller = new DataController();
<<<<<<< HEAD
		
		
		//read in data
		String filepath = "./data/Addresses.xlsx";
		controller.readFromExcelDoc(filepath);
		
		//test print
		controller.printTableHeader(1);
		
		controller.writeToTextFile("./data/testData.txt");
		
=======
		
		
		//read in data
		String filepath = "./data/Addresses.xlsx";
		controller.readFromExcelDoc(filepath);
		
		//test print
		controller.printTableHeader(1);
		
		controller.writeToTextFile();
		
>>>>>>> master
		//creating a new controller creates a blank hashtable, should be empty
		controller = new DataController();
		
		//reading from the text file should only work if the write actually works
<<<<<<< HEAD
		controller.readFromTextFile("./data/testData.txt");
=======
		controller.readFromTextFile();
>>>>>>> master
		
		controller.printTableHeader(1);
	}

}
