/*
 *	DataStore Object
 *	Created 2/16/2022
 *	by Michael Booser
 *	for Storing and Obtaining
 *	Google Maps API Responses
 *
 */

/*
 * DataStore.java
 */
 
 			DataStore is an object that can be instantiated to hold data.
		It's primary constructor "public DataStore(String lS, String lE)"
 		accepts a starting and ending location
 		and calculates a distance in miles from DistanceMatrixAPI.java
 
 /*
  * DistanceMatrixAPI.java
  * The core of this code was provided by Dr. Thangiah
  */
 
			DistanceMatrixAPI is a class with two static methods for interfacing
 		with Google's DISTANCE MATRIX API.
 
			The first method 'getDistance(origin, destination)
 		takes two strings for the starting and ending location
 		and passes them to the Distance Matrix API,
 		this returns an XML file. The XML file is typecasted to String
 		and passed to DistanceMatrixAPI.parseDistance()
 		returns distance as a float.
 
			 The second method parseDistance(xmlString)
		is used to break the xmlString into usable data
		which is returned as a float.
  
 /*
  * DirectionsAPI.java
  */
  
  			DirectionsAPI is a class with two static methods
  		for interfacing with Google's DIRECTIONS API.
  		
  			The first method getDirections(origin, destination)
  		takes two strings and for the starting and ending location
  		and passes them to the DIRECTIONS API, this returns an 
  		XML file. The XML file is typecast to String and sent to
  		DirectionsAPI.parseDirections().
  		
  			The second method is parseDirections(xmlIn)
		and is used to break the XML into usable data.
		
 /*
  * Michael_Code_Test.java
  */
  
  			This simple program is used to test other programs in the package.
  