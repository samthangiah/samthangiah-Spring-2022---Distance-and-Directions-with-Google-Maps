package edu.sru.bayne.DistanceAndDirections.domain;

/**
 * Class to organize each set of long/lat coordinates into clusters.
 * (NOT YET IMPLEMENTED) **may be unnecessary, will likley be included in search class**
 * @author Gregory
 *
 */
public class PolyCluster {
	
	private float longitude;
	private float latitude;
	private String cluster;
	
	/**
	 * Constructor for PolyCluster object.
	 * Sets longitude and latitude with respect to clustering group.
	 * @param lng longitude component
	 * @param lat latitude component
	 * @param cluster cluster these coordinates belong to
	 */
	public void polyCluster(float lng, float lat, String cluster) {
		this.setLongitude(longitude);
		this.setLatitude(latitude);
		this.setCluster(cluster);
	}


	public float getLongitude() {
		return longitude;
	}


	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}


	public float getLatitude() {
		return latitude;
	}


	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}


	public String getCluster() {
		return cluster;
	}


	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
}
