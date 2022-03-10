package edu.sru.bayne.DistanceAndDirections;

public class MapTest {

	public static void main(String[] args) {
		String orig = "401 Suncrest Drive Cranberry Township PA 16066";
		String dest = "806 Graywyck Drive Seven Fields PA 16066";
		String myMapURL;
		
		CreateMap myMap = new CreateMap();
		myMapURL=myMap.createMap(orig, dest);
		System.out.println(myMapURL);
	}
}
