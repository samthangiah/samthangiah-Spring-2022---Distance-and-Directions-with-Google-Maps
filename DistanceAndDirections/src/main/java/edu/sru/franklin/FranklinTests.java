package edu.sru.franklin;

public class FranklinTests {
	
	
	public static void main(String args[]) {
		DataController controller = new DataController();
		//controller.readFromExcelDoc("Addresses.xlsx");
		controller.readFromExcelDoc("./data/Addresses.xlsx");
		controller.writeToTextFile();
		System.out.print("Test");
		
	}

}
