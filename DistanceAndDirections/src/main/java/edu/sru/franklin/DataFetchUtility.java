package edu.sru.franklin;

import java.io.IOException;
import java.util.Hashtable;

import edu.sru.booser.datastore.DataStore;
import edu.sru.booser.datastore.DistanceMatrixAPI;

public class DataFetchUtility {
	
	
	
	
	public static void main(String[] args) {
		DataFetchUtility i = new DataFetchUtility();
		DataController controller = new DataController();
		controller.readFromTextFile();
		Hashtable<Integer, DataObject> t = controller.getTable().getTable();
		i.populateDirections(controller, t);
		controller.writeToTextFile();
	}
	
	@SuppressWarnings("unchecked")
	public void populateDirections(DataController controller, Hashtable<Integer, DataObject> t) {
		
		t.forEach((k,v)->{
			int[] iterator = new int[1];
			iterator[0] = 0;
			DataStore dataStore = new DataStore(v.getOrigin(), v.getDestination());
			String[] output = new String[dataStore.getHolder().getDirections().size()];
			dataStore.getHolder().getDirections().forEach((s)->{
				output[iterator[0]] = s.toString();
				iterator[0]++;
			});
			v.setDirections(output);
			for(String a: v.getDirections()) {
				System.out.println("pDir " + a);
			}
			System.out.println("Added Directions to " + v.getOrigin());
		});
	}
	
	public void populateDistance(DataController controller, Hashtable<Integer, DataObject> t){
		t.forEach((k,v)->{
			try {
				float distance = DistanceMatrixAPI.getDistance(v.getOrigin(), v.getDestination());
				v.setDistance(distance);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		});
		controller.writeToTextFile();
	}
}
