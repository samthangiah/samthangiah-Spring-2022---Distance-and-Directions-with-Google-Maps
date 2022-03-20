package edu.sru.franklin;

public class FranklinTests {
	
	public static void main(String args[]) {
		DataController controller = new DataController();
		//controller.readFromExcelDoc("Addresses.xlsx");
		controller.readFromTextFile();
		controller.writeToTextFile();
		controller.printTable();
		
	}

}
