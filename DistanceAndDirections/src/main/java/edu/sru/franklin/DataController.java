package edu.sru.franklin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;



public class DataController {
	
	private XSSFParser parser;
	private Table dataTable;
	private String storagePath = "./data/data.txt";
	
	// this class currently runs the xlsx parser and adds its data to a Table object
	 
	
	/**
	 * creates instance of text file to be used
	 * @throws IOException 
	 */
	public DataController(){
		//If the data storage file does not exist, make one.
		if(!Files.exists(new File(storagePath).toPath())) {
			System.out.printf("Warning! Data storage file not found. Creating under %s", storagePath);
			try {
				File file = new File(storagePath);
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//creates an empty Table to later be filled either with the excel or file reader methods
		dataTable = new Table();
	}
	
	/**
	 * Creates instance of parser that reads from excel doc and adds to hashtable
	 */
	public void readFromExcelDoc(String fileName) {
		parser = new XSSFParser();
		parser.parseFromFile(fileName);
		dataTable = parser.newDataTableFromFile();
	}
	
	/**
	 * Reads table from hashtable. Has try and catch for any errors that could occur.
	 */
	public void readFromTextFile() {
		//Checks if the data table is null
		if(dataTable == null) {
			System.out.println("Datatable == null");
			return;
		}		
		//Checks if the file exists, if not, does not make one, only returns
		if(!Files.exists(new File(storagePath).toPath())) {
			System.out.println("The file at " + storagePath + " does not exist");
			return;
		}
		
		//This is where the read in occurs
		
		
		//First, we make an ArrayList to hold the contents of the data.txt file.
		//ArrayList works well here because we don't know the number of entries in the data.txt file,
		//and ArrayList has a dynamic size
		ArrayList<String> input = new ArrayList<String>();
		//outputTable is a local member used to temporarily hold the final output of this method, until it is ready to be added to the real table
		Table outputTable = new Table();
		try {
			//Tries to read individual lines of file into an ArrayList of those lines
			//readAllLines is delimited by carriage returns
			input = (ArrayList<String>) Files.readAllLines(Path.of(storagePath));
			//for every String s in input
			for(String s:input) {
				//use split to break up the lines of text from the file into usable chunks, which are stored as a string array
				String[] temp = s.split("\\t");
				
				//The array locations here should represent what the variables are named
				//These locations should always align with these values. If not, there is a formatting issue somewhere else in the code.
				String origin = temp[0];
				String destination = temp[1];
				Float distance = Float.parseFloat(temp[2]);
				
				//The Directions are stored as a string array, and currently they are stuck in the temp array, so we need to use a for loop to add just the directions to the new directions array
				String[] directions = new String[temp.length - 3];
				for(int i = 3; i < temp.length; i++) {
					directions[i - 3] = temp[i];
				}
				
				//The final table, populated with the above information
				outputTable.add(origin, destination, distance, directions);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//after everything is done, set the controller's table equal to the one we just made.
		this.dataTable = outputTable;
		
	}
	
	
	//This is an alternate read method that just allows you to specify the path of the data file
	public void readFromTextFile(String path) {
		//Checks if the data table is null
				if(dataTable == null) {
					System.out.println("Datatable == null");
					return;
				}		
				//Checks if the file exists, if not, does not make one, only returns
				if(!Files.exists(new File(path).toPath())) {
					System.out.println("The file at " + path + " does not exist");
					return;
				}
				
				//This is where the read in occurs
				
				
				//First, we make an ArrayList to hold the contents of the data.txt file.
				//ArrayList works well here because we don't know the number of entries in the data.txt file,
				//and ArrayList has a dynamic size
				ArrayList<String> input = new ArrayList<String>();
				//outputTable is a local member used to temporarily hold the final output of this method, until it is ready to be added to the real table
				Table outputTable = new Table();
				try {
					//Tries to read individual lines of file into an ArrayList of those lines
					//readAllLines is delimited by carriage returns
					input = (ArrayList<String>) Files.readAllLines(Path.of(path));
					//for every String s in input
					for(String s:input) {
						//use split to break up the lines of text from the file into usable chunks, which are stored as a string array
						String[] temp = s.split("\\t");
						
						//The array locations here should represent what the variables are named
						//These locations should always align with these values. If not, there is a formatting issue somewhere else in the code.
						String origin = temp[0];
						String destination = temp[1];
						Float distance = Float.parseFloat(temp[2]);
						
						//The Directions are stored as a string array, and currently they are stuck in the temp array, so we need to use a for loop to add just the directions to the new directions array
						String[] directions = new String[temp.length - 3];
						for(int i = 3; i < temp.length; i++) {
							directions[i - 3] = temp[i];
						}
						
						//The final table, populated with the above information
						outputTable.add(origin, destination, distance, directions);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//after everything is done, set the controller's table equal to the one we just made.
				this.dataTable = outputTable;
				
	}
	
	/**
	 * Writes to text file. Checks if data table is null. if not, tries to write to file.
	 * @throws FileNotFoundException 
	 */
	public void writeToTextFile(){
		//Checks if the data table is null
		if(dataTable == null) {
			System.out.println("Datatable == null");
			return;
		}
		
		//Checks if the file exists, if not, does not make one, only returns
		if(!Files.exists(new File(storagePath).toPath())) {
			System.out.println("The file at " + storagePath + " does not exist");
			return;
		}
		else {
			
			
			//this string array is what we will populate with the hash table data before writing it to the data.txt file
			String[] output = new String[dataTable.getTable().size()];
			
			//int array is a way to use an integer iterator inside the foreach loop
			//if you try to use a regular int instead of an array, it won't work
			int[] i = new int[1];
			i[0] = 0;
			//hashTable has a built in foreach loop to iterate over all elements.
			//we loop over them and add them to the output array, which makes it easier to add them to the dat.txt file
			dataTable.getTable().forEach((k,v)->{	
				
				//directions gets set to none every loop
				String directions = "";
					//check to see if there are any directions to write
					if(v.getDirections() != null) {
						for(String s:v.getDirections()) {
							//If there are directions in the hash table, just combine them all into one string seperated by tabs
							directions = directions.concat(s).concat("	");
						}
					}else {
						//if there are no directions for the entry in the hash table, just set directions to null
						directions = "null";
					}
					
					//this is where the data.txt strings get created
					output[i[0]] = v.getOrigin() + "	" + v.getDestination() + "	" + v.getDistance() + "	" + directions;
					i[0]++;
			});
			
			//after we have looped through every entry in the hash table, we will try writing their strings to the file
			try {
				//printwriter is a basic java.io class that lets you write to a specific path
				PrintWriter out = new PrintWriter(storagePath);
				//for each string in the output array, add it to the data.txt
				for(String s : output) {
					//adding to the data.txt
					out.println(s);
				}
				//close the stream to prevent errors
				out.close();
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Wrote to data file.");
	}
	
	//alternate write method for custom path
	public void writeToTextFile(String path){
		//Checks if the data table is null
				if(dataTable == null) {
					System.out.println("Datatable == null");
					return;
				}
				
				//Checks if the file exists, if not, does not make one, only returns
				if(!Files.exists(new File(path).toPath())) {
					System.out.println("The file at " + path + " does not exist");
					return;
				}
				else {
					
					
					//this string array is what we will populate with the hash table data before writing it to the data.txt file
					String[] output = new String[dataTable.getTable().size()];
					
					//int array is a way to use an integer iterator inside the foreach loop
					//if you try to use a regular int instead of an array, it won't work
					int[] i = new int[1];
					i[0] = 0;
					//hashTable has a built in foreach loop to iterate over all elements.
					//we loop over them and add them to the output array, which makes it easier to add them to the dat.txt file
					dataTable.getTable().forEach((k,v)->{	
						
						//directions gets set to none every loop
						String directions = "";
							//check to see if there are any directions to write
							if(v.getDirections() != null) {
								for(String s:v.getDirections()) {
									//If there are directions in the hash table, just combine them all into one string seperated by tabs
									directions = directions.concat(s).concat("	");
								}
							}else {
								//if there are no directions for the entry in the hash table, just set directions to null
								directions = "null";
							}
							
							//this is where the data.txt strings get created
							output[i[0]] = v.getOrigin() + "	" + v.getDestination() + "	" + v.getDistance() + "	" + directions;
							i[0]++;
					});
					
					//after we have looped through every entry in the hash table, we will try writing their strings to the file
					try {
						//printwriter is a basic java.io class that lets you write to a specific path
						PrintWriter out = new PrintWriter(path);
						//for each string in the output array, add it to the data.txt
						for(String s : output) {
							//adding to the data.txt
							out.println(s);
						}
						//close the stream to prevent errors
						out.close();
					}catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("Wrote to data file.");
	}
	
	
	/**
	 * adds origin and destinations to table.
	 * @param origin The starting location
	 * @param destination The ending location
	 */
	public void add(String origin, String destination) {
		dataTable.add(origin, destination);
	}
	
	/**
	 * gets distance for destination and origin pair. Makes it so it works in both directions. 
	 * @param origin The starting location
	 * @param destination The ending location
	 * @return distance distance between locations
	 */
	public float getDistance(String origin, String destination) {
		//first pair
		if(dataTable.contains(origin, destination)) {
			return dataTable.getDataObject(origin, destination).getDistance();
		//second pair
		}else if(dataTable.contains(destination, origin)) {
			return dataTable.getDataObject(destination, origin).getDistance(); 
		}
		return -1;
	}
	
	/**
	 * Gets locations and directions from table. 
	 * @param origin The staring location
	 * @param destination The ending location
	 * @return the two locations and directions
	 */
	public String[] getDirections(String origin, String destination) {
		if(dataTable.contains(origin, destination)) {
			return dataTable.getDataObject(origin, destination).getDirections();
		}
		return null;
	}
	
	public String getOrigin(String origin, String destination) {
		return origin;
	}
	
	/**
	 * add distance based on location pair
	 * @param origin The starting location
	 * @param destination The ending location
	 * @param distance distance between locations
	 */
	public void setDistance(String origin, String destination, float distance) {
		
		if(!dataTable.contains(origin, destination)) {		
			dataTable.add(origin, destination);
		}
		
		dataTable.getDataObject(origin, destination).setDistance(distance);	
	}
	
	/**
	 * add directions based on location pair
	 * @param origin The staring location
	 * @param destination The ending location
	 * @param directions distance between locations
	 */
	public void setDirections(String origin, String destination, String[] directions) {
		if(!dataTable.contains(origin, destination)) {		
			dataTable.add(origin, destination);
		}
		
		dataTable.getDataObject(origin, destination).setDirections(directions);	 	
	}
	
	public void setOrigin(String origin, String destination, float distance) {
		if(!dataTable.contains(origin, destination)) {	
			dataTable.add(origin, destination);
		}
	}
	
	public Table getTable() {
		return dataTable;
	}
	
	/**
	 * prints data table
	 */
	public void printTable() {
			dataTable.printTable();
	}
	
	public void printTableHeader(int length) {		
			dataTable.printTableHeader(length);	
	}
	
	}
