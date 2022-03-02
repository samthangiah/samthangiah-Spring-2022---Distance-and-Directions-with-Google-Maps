package edu.sru.franklin;


public class Input {
	
	private String inputFileName = "Addresses.xlsx";
	private XSSFParser parser;
	private Table dataTable;
	
	/**
	 * this class currently runs the xlsx parser and adds its data to a Table object
	 * <p>
	 * may have more functionality in the future
	 */
	public Input() { 		
		parser = new XSSFParser();
		parser.parseFromFile(inputFileName);
		dataTable = parser.newDataTableFromFile();
		dataTable.printTable();

	}
	
	/**
	 * simple getter for Table
	 * @return Table object
	 */
	public Table getDataTable() {
		return dataTable;
	}
	
	

}
