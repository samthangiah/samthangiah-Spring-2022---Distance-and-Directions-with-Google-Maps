package edu.sru.bayne.DistanceAndDirections.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.lang.NonNull;
import edu.sru.booser.datastore.DirectionsHolder;

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
	
	private String street1;
	private String city1;
	private String state1;
	private String zip1;
	private String country1;
	
	private String street2;
	private String city2;
	private String state2;
	private String zip2;
	private String country2;
	
	private String mapURL;
	
	//public DirectionsHolder Holder;
	
    
	
	// Setters and getters are named getqDistance, getqDirections, etc to avoid name issues with respective APIs
	//stores distance
    @NonNull
    private float distance;
    
    //stores directions
    @NonNull
    @Column(name="directions",columnDefinition="LONGTEXT")
    private String directions;
    
    public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
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
		this.destination = destination;
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

	public String getMapURL() {
		return mapURL;
	}

	public void setMapURL(String mapURL) {
		this.mapURL = mapURL;
	}
	
	
}
