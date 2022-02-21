package edu.sru.franklin;

public class TableTest {
	
	public static void main(String args[]) {
		
		
		//FranklinTest instance = new FranklinTest();
		//Is below what you meant?
		TableTest instance = new TableTest();
		
		instance.start();	
	}
	
	public void start() {
		
		Table testTable = new Table(10);
		
		testTable.addToTable("SlipperyRock_PA Butler_PA", 24);
		testTable.addToTable("SlipperyRock_PA GroveCity_PA", 11);
		testTable.addToTable("GroveCity_PA Butler_PA", 31);
		testTable.addToTable("Clarion_PA Emlenton_PA", 35);
		testTable.addToTable("SlipperyRock_PA Emlenton_PA", 22);
		testTable.addToTable("Butler_PA GroveCity_PA", 36);
		
		
		System.out.println(testTable.contains(24)); //true
		System.out.println(testTable.contains(11)); //true
		System.out.println(testTable.contains(10)); //false
		
		System.out.println(testTable.containsKey("SlipperyRock_PA Butler_PA")); //true
		System.out.println(testTable.containsKey("Butler_PA GroveCity_PA")); //true
		System.out.println(testTable.containsKey("Emlenton_PA SlipperyRock_PA")); //false
		
		System.out.println(testTable.removeFromTable("SlipperyRock_PA Butler_PA", 24));
		
		System.out.println(testTable.containsKey("SlipperyRock_PA Butler_PA")); //false
		
		System.out.println(testTable.size()); //6
		
		System.out.println(testTable.getTable());
		
		
		System.out.printf("Start and End: %s " + "Distance: %s ", "", testTable.getFromTable("SlipperyRock_PA Butler_PA"));
		System.out.printf("Start and End: %s " + "Distance: %s ", "", testTable.getFromTable("SlipperyRock_PA GroveCity_PA"));
		System.out.printf("Start and End: %s " + "Distance: %s ", "", testTable.getFromTable("SlipperyRock_PA Emlenton_PA"));
	}
	
	
}
