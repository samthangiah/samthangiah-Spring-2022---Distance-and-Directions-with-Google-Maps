package edu.sru.bayne.DistanceAndDirections.domain;

import com.opencsv.bean.CsvBindByName;

/**
 * Class to organize each set of long/lat coordinates into clusters.
 * (NOT YET IMPLEMENTED) **may be unnecessary, will likley be included in search class**
 * @author Gregory
 *
 */
public class StudentPickupInformation {

	@CsvBindByName
	private long id;
	
    @CsvBindByName
    private int count;
    
    @CsvBindByName
    private String pickupLoc;
    
    @CsvBindByName
    private String roadName;
    
    @CsvBindByName
    private float longitude;
    
    @CsvBindByName
    private float latitude;

    public StudentPickupInformation(long id, int count, String pickupLoc, String roadName, float lng, float lat) {
	        this.id = id;
	        this.count = count;
	        this.pickupLoc = pickupLoc;
	        this.roadName = roadName;
	        this.longitude = lng;
	        this.latitude = lat;
	    }
	    
	    // getters and setters removed for the sake of brevity
	 }
	
	

