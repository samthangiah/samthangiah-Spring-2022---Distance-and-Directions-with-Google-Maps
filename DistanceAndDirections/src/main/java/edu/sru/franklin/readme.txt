/*
* Kari Franklin
* HashTable
* 3/25/22
* Creates a hashtable database that 
* holds values
*/

/*
* Table.java
*/

		Table class creates an instance of a hashtable that takes an integer and a dataobject. 
	It has a constructer that creates the instance of the table. It has a method that called size
	that looks for the number of elements in the hashtable. It also has a method called add that takes
	a string origin and string destination parameter, this adds a new data object with a origin destination 
	pair. Next this class has a getDataObject method that also contains a origin and destination parameter 
	that retrieves those two values. There is also a contain method that is type boolean that returns a true
	or false depending on if its in the table or not. Lastly, there is a print table method that enumerates 
	theough the table to print out the table's distance, directions and destination.
	
/*
* FranklinTests.java
*/

		This class is a test class used to execute the code. It creates an instance of the DataController 
	class. It then uses this instance to use the method readFromExcelDoc to read from the imported excel 
	data sheet. Lastly, it has a method called writeToTextFile that writes to a .txt file.
	
/*
* XSSFParser.java
*/

		XSSFParser uses a third party library called Apache POI to load and parse a .xlsx Microsoft Excel Sheet.
	It opens an inputstream to the file and loads it into a data structure called a Workbook, which can then be parsed into
	a raw String object using the built-in parser. The resulting string is the excel data, delimited by tabs and returns, making it easy to 
	break the string down into usable chunks with two simple regexes. These chunks of data(Start location, End location) are then stored into
	new DataObject objects and put in a hashtable, which gets returned.


/*
* DataObject.java
*/

		This class holds the getters and setters for origin, destination, and distance. It creates a string array
	called directions. It also makes a dataObject constructor that takes three parameters origin, destination, 
	and distance then sets the classes internal variables to these paramters. 
	
/*
* DataController.java
*/

		This class makes an instance of the table class and the parser class. It contains a method called 
	readFromExcelDoc that takes a parameter called filename. It allows you to choose to a excel document
	to read from and then parses that document. Next there is a method called readFromTextFile that creates
	an instance of a fileOutputStream and an objectOutputStream. This allows it to read from the textfile 
	and then write that information into the table. It also contains an add method with the paramters origin
	and destination that allows data to be added to the table. There is another method that is called getDistance 
	with the parameter pair origin and destination that checks for the distance of the pair, it also allows it to 
	work in both directions both looking for the combination origin and destination as well as destination and 
	origin. There is also a getDirections method that contains a origin and destination parameter that returns
	the string directions. There's also setters and getters for origin and destination as well.  

/*
* SaveTest.java 
*/

		This class is used to read the contents of the data.txt file. The data.txt file is where the hashtable 
	content is being displayed.  

/*
* data.txt 
*/

		This is where the contents of the hashtable is being saved and read from. 

