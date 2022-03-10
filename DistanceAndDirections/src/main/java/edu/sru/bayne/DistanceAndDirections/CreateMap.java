package edu.sru.bayne.DistanceAndDirections;

import edu.sru.booser.datastore.DistanceMatrixAPI;

public class CreateMap {

	private static String baseURL = "https://maps.googleapis.com/maps/api/staticmap?";
	private String center = "";
	private String zoom = "";
	private String mapSize = "&size=720x720";
	private String mapType = "&maptype=roadmap";
	private String origMarker = "&markers=color:green%7Clabel:START%7C";
	private String destMarker = "&markers=color:red%7Clabel:END%7C";
	private String path = "&path=color:blue|";
	private String key = "&key=AIzaSyD390VrMYSzUckUBYiWeXy2ZvVDrNtWUPg";
	
	
	private String finalURL = "";
	
	public String createMap(String origin, String destination) {
		String url = "";
		origin = origin.replace(" ", "+");
		destination = destination.replace(" ", "+");
		url = url + getBaseURL() + getMapType() + getMapSize();
		
		setOrigMarker(getOrigMarker() + origin);
		setDestMarker(getDestMarker() + destination);
		setPath(getPath() + origin + "|" + destination);
		
		url = url + getOrigMarker() + getDestMarker() + getPath() + getKey();
		//System.out.println("url: " + url);
		
		return url;
	}
	
	//Getters and Setters
	public static String getBaseURL() {
		return baseURL;
	}

	public static void setBaseURL(String baseURL) {
		CreateMap.baseURL = baseURL;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getZoom() {
		return zoom;
	}

	public void setZoom(String zoom) {
		this.zoom = zoom;
	}

	public String getMapSize() {
		return mapSize;
	}

	public void setSize(String mapSize) {
		this.mapSize = mapSize;
	}

	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		this.mapType = mapType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getFinalURL() {
		return finalURL;
	}

	public void setFinalURL(String finalURL) {
		this.finalURL = finalURL;
	}

	public String getOrigMarker() {
		return origMarker;
	}

	public void setOrigMarker(String origMarker) {
		this.origMarker = origMarker;
	}

	public String getDestMarker() {
		return destMarker;
	}

	public void setDestMarker(String destMarker) {
		this.destMarker = destMarker;
	}























	public String getPath() {
		return path;
	}























	public void setPath(String path) {
		this.path = path;
	}
}
