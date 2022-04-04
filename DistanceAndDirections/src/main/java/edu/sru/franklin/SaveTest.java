package edu.sru.franklin;

public class SaveTest {
	public static void main(String args[]) {
		DataController controller = new DataController();
		controller.readFromTextFile();
		controller.printTable();
	}
}
