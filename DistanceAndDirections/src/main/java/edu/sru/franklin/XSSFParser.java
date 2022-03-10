package edu.sru.franklin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.extractor.XSSFExcelExtractor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class XSSFParser {

	private XSSFWorkbook wb;
	private Table dataTable;
	private String[] locations;
	private String output;
	
	
	
	/**
	 * creates a inner field called output which stores the raw input data from a xlsx file
	 * file must be a xlsx file and will not work with any other file type
	 * this method must be run before anything else as all other methods rely on this inner field 
	 * @param fileName the path of the xlsx file of location pairs to be read into the program
	 */
	public void parseFromFile(String fileName) {

		//Try to create inputstream to file. will fail if filepath is bad
		try(InputStream fileIn = this.getClass().getResourceAsStream(fileName)){
			//workbook is a object to hold the xlsx data. has many utility methods that could be used in the future
			wb = new XSSFWorkbook(fileIn);
			
			//right now the extractor just converts the xlsx file into a single string, there may be functionality here to 
			//make parsing easier but for now it is unused.
			
			XSSFExcelExtractor extractor = new XSSFExcelExtractor(wb);
			
			//just need to remove sheet names and formula info so that the data can be parsed properly
			extractor.setFormulasNotResults(true);
			extractor.setIncludeSheetNames(false);
			
			//the output string is currently line delimited by carriage returns and the location pairs are tab delimited
			output = extractor.getText();
			
		}
		catch(IOException e) {
			//there should probably be better error handling than this idk...
			System.out.println("IOException");
		}
	}
	
	/**
	 * creates a new Table object from preloaded file
	 * if parseFromFile has not sucessfully completed, will return null
	 * @return a Table populated with dataObjects with Origins and Destinations
	 */
	
	public Table newDataTableFromFile() {
		String temp[];
		
		//check if parseFromFile has succesfully run
		if(output == null) {throw new NullPointerException();}
		
		//easy parse of the output string into origin-destination pairs
		//this regex just checks for all newline types because why not
		locations = output.split("\\r?\\n|\\r");
		
		//create new dataTable with size equal to dataset size
		dataTable = new Table(locations.length);
		
		//cut up location pairs and add to the datatable
		//can this be done better??
		for(int i = 0; i < locations.length; i++) {
			temp = locations[i].split("\\t");
			if(!dataTable.contains(temp[0], temp[1])) {
				dataTable.add(temp[0], temp[1]);
			}
			
		}
		return dataTable;
	}

}