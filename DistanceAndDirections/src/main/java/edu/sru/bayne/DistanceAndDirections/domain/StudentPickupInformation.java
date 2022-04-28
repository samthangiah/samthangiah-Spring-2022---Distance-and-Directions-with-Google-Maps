package edu.sru.bayne.DistanceAndDirections.domain;

import java.io.IOException;

import com.opencsv.bean.CsvBindByName;

import edu.sru.bayne.DistanceAndDirections.GeoGrabber;

/**
 * Class to organize each set of long/lat coordinates into clusters.
 * (NOT YET IMPLEMENTED) **may be unnecessary, will likley be included in search class**
 * @author Gregory and Michael
 *
 */
public class StudentPickupInformation {

	@CsvBindByName
	private long id;
	
    	public long getId() {
    		return id;
    	}
    	public void setId(long id) {
    		this.id = id;
    	}
    	
    @CsvBindByName
    private int count;
    	public int getCount() {
    		return count;
    	}
    	public void setCount(int count) {
    		this.count = count;
    	}
    	
    @CsvBindByName
    private String pickupLoc;
    	public String getPickupLoc() {
    		return pickupLoc;
    	}
    	public void setPickupLoc(String pickupLoc) {
    		this.pickupLoc = pickupLoc;
    	}
    @CsvBindByName
    private String roadName;
    	public String getRoadName() {
    		return roadName;
    	}
    	public void setRoadName(String roadName) {
    		this.roadName = roadName;
    	}
    	
    @CsvBindByName
    private String longitude;
    	public String getLongitude() {
    		return longitude;
    	}
    	public void setLongitude(String longitude) {
    		this.longitude = longitude;
    	}
    	
    @CsvBindByName
    private String latitude;
    	public String getLatitude() {
    		return latitude;
    	}
    	public void setLatitude(String latitude) {
    		this.latitude = latitude;
    	}
	
    /**
     * Constructor for StudenPickupInformation.java
     * @throws InterruptedException 
     * @throws IOException 
     */
    public StudentPickupInformation(long id, int count, String pickupLoc, String roadName, String lng, String lat) throws IOException, InterruptedException {
	        this.id = id;
	        this.count = count;
	        this.pickupLoc = pickupLoc;
	        this.roadName = roadName;
	        this.longitude = lng;
	        this.latitude = lat;
	        this.checkGeos();
	    }
    /**
     * Confirms the existence of GeoCodes for the Point
     * @throws IOException
     * @throws InterruptedException
     */
    public void checkGeos() throws IOException, InterruptedException 
    {
    	if(this.getLatitude() == "0" || this.getLongitude() == "0" || this.getLatitude() == null || this.getLongitude() == null)
    	{
    		GeoGrabber codeCheck = new GeoGrabber();
    		codeCheck.fetchCoordinatesFromAddress(codeCheck.buildFromAddress(this.pickupLoc));
    		this.setLatitude(codeCheck.getLat());
    		this.setLongitude(codeCheck.getLng());
    		
    	}
    }
    
    public void completeAddress(String newZip)
    {
    	
    }
	    

}
	
	

