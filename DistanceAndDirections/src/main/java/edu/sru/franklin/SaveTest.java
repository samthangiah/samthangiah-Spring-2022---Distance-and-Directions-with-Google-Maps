package edu.sru.franklin;

public class SaveTest {
	
	//prints out the contents of data.txt
	public static void main(String args[]) {
		//read in data
		String filepath = "./data/Addresses.xlsx";
		DataController controller = new DataController();
		controller.readFromTextFile();
		
		controller.printTable();
	}
}
