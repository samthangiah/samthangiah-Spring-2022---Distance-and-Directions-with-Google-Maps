package edu.sru.bayne.DistanceAndDirections.domain;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;

import edu.sru.bayne.DistanceAndDirections.GeoGrabber;

/**
 * Class depicting object model for query data stored in the H2 database. Holds start and 
 * end addresses, and other correlated data. 
 * @author Gregory Bayne
*/
@Entity
public class Search {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    //stores starting address
    @NonNull
    private String origin;

    //stores end/2nd address
	@NonNull
    private String destination;
	
	// Address 1 info
	private String street1;
	private String city1;
	private String state1;
	private String zip1;
	private String country1;
	private String lng1;
	private String lat1;
	
	// Address 2 info
	private String street2;
	private String city2;
	private String state2;
	private String zip2;
	private String country2;
	private String lng2;
	private String lat2;
	
	private String cluster;
	
	//stores distance between points
    @NonNull
    private float distance;
    
    //stores directions from address1 to address2
    @NonNull
    @Column(name="directions",columnDefinition="LONGTEXT")
    private String directions;
    
    
	
	//finding and setting coordinates from address------------------------------------------------------------------------------------
	/**
	 * This method uses GeoGrabber class to find and set coordinates for a provided human-readable address.
	 * The coordinates are set to both the Address1 (origin) data and the Address2 (destination) data.
	 * @param origin
	 * @param destination
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void geoBothAddressCoordinates(String origin, String destination) throws IOException, InterruptedException {
		geoAddress1ToCoordinates(origin);
		geoAddress2ToCoordinates(destination);
	}
	
	/**
	 * This method uses GeoGrabber class to find and set coordinates for a provided human-readable address.
	 * The coordinates are set to the Address1 (origin) data.
	 * @param address
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void geoAddress1ToCoordinates(String address) throws IOException, InterruptedException {
		GeoGrabber geo = new GeoGrabber(address);
		geo.fetchCoordinatesFromAddress(geo.buildFromAddress(address));
		this.setLat1(geo.getLat());
		this.setLng1(geo.getLng());
	}
	
	/**
	 * This method uses GeoGrabber class to find and set coordinates for a provided human-readable address.
	 * The coordinates are set to the Address2 (destination) data.
	 * @param address
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void geoAddress2ToCoordinates(String address) throws IOException, InterruptedException {
		GeoGrabber geo = new GeoGrabber(address);
		geo.fetchCoordinatesFromAddress(geo.buildFromAddress(address));
		this.setLat2(geo.getLat());
		this.setLng2(geo.getLng());
	}
	//--------------------------------------------------------------------------------------------------------------------------------
	
	//finding and setting coordinates from lng/lat
	public void geoC2A(String OriLat, String OriLng, String DesLat, String DesLng) throws IOException, InterruptedException {
        geoOriginAFromC(OriLat, OriLng);
        geoDestinationAFromC(DesLat, DesLng);
    }

    public void geoOriginAFromC(String lat, String lng) throws IOException, InterruptedException {
        GeoGrabber geo = new GeoGrabber(lat, lng);
        geo.fetchAddressFromCoordinates(geo.buildFromCoordinates(lat, lng));
        this.setOrigin(geo.getAddress());
    }

    public void geoDestinationAFromC(String lat, String lng)throws IOException, InterruptedException {
        GeoGrabber geo = new GeoGrabber(lat,lng);
        geo.fetchAddressFromCoordinates(geo.buildFromCoordinates(lat, lng));
        this.setDestination(geo.getAddress());
    }
  //--------------------------------------------------------------------------------------------------------------------------------
	
	
	
	
    
	
    
	// Setters and getters are named getqDistance, getqDirections, etc to avoid name issues with respective APIs ----------------------
    public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin.toUpperCase();
	}
	
	public void setOrigin() {
		this.setOrigin(this.getStreet1() + " " + this.getCity1() + " " +
				this.getState1() + " " + this.getZip1() + " " + this.getCountry1());
	};

	public float getqDistance() {
		return distance;
	}

	public void setqDistance(float distance) {
		this.distance = distance; //String.valueOf(distance);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination.toUpperCase();
	}
	
	public void setDestination() {
		this.setDestination(this.getStreet2() + " " + this.getCity2() + " " +
				this.getState2() + " " + this.getZip2() + " " + this.getCountry2());
	};

	public String getqDirections() {
		return directions;
	}

	public void setqDirections(String directions) {
		this.directions = directions;
	}
	
	public String getStreet1() {
		return street1;
	}

	public void setStreet1(String street1) {
		this.street1 = street1;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getZip1() {
		return zip1;
	}

	public void setZip1(String zip1) {
		this.zip1 = zip1;
	}

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public String getStreet2() {
		return street2;
	}

	public void setStreet2(String street2) {
		this.street2 = street2;
	}

	public String getCity2() {
		return city2;
	}

	public void setCity2(String city2) {
		this.city2 = city2;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getZip2() {
		return zip2;
	}

	public void setZip2(String zip2) {
		this.zip2 = zip2;
	}

	public String getCountry2() {
		return country2;
	}

	public void setCountry2(String country2) {
		this.country2 = country2;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getLng1() {
		
		if(lng1 != null) {
			return lng1;
		}
		//if it doesn't exist, it must be queried
		try {
			this.geoAddress1ToCoordinates(this.getOrigin());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return lng1;
	}

	public void setLng1(String lng1) {
		this.lng1 = lng1;
	}

	public String getLat1() {
		
		if(lng1 != null) {
			return lat1;
		}
		//if it doesn't exist, it must be queried		
		try {
			this.geoAddress1ToCoordinates(this.getOrigin());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}	
		return lat1;
	}

	public void setLat1(String lat1) {
		this.lat1 = lat1;
	}

	public String getLng2() {
		if(lng2 != null) {
			return lng2;
		}
		//if it doesn't exist, it must be queried		
		try {
			this.geoAddress2ToCoordinates(this.getDestination());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		return lng2;
	}

	public void setLng2(String lng2) {
		this.lng2 = lng2;
	}

	public String getLat2() {
		if(lat2 != null) {
			return lat2;
		}
		//if it doesn't exist, it must be queried		
		try {
			this.geoAddress2ToCoordinates(this.getDestination());
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}	
		return lat2;
	}

	public void setLat2(String lat2) {
		this.lat2 = lat2;
	}
	
	
}
