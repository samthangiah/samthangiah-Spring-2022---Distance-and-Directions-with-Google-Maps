package edu.sru.bayne.DistanceAndDirections.controller;

import java.io.IOException;
import java.io.Reader;
import java.io.*;
import java.util.Enumeration;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import edu.sru.bayne.DistanceAndDirections.domain.Search;
import edu.sru.bayne.DistanceAndDirections.domain.StudentPickupInformation;
import edu.sru.bayne.DistanceAndDirections.repository.SearchRepository;
import edu.sru.booser.datastore.*;
import edu.sru.franklin.DataController;
import edu.sru.franklin.DataObject;
import edu.sru.franklin.Table;


/**
 * The controller class for handling interaction between the following:
 * 1. The "SearchRepository" (CRUD Repository) and corresponding "search" entities.
 * 2. The html webpages found in ./src/main/resources/templates
 * 
 * @author Gregory Bayne
 *
 */
@Controller
public class DistanceandDirectionsController{
	
	private SearchRepository searchRepo;
	private DataController dataController = new DataController();
	private Table distanceTable;
	
	
	public DistanceandDirectionsController(SearchRepository searchRepo) {
		this.searchRepo = searchRepo;
		dataController.readFromTextFile();
		distanceTable = dataController.getTable();
	}
	
	
	/*
	/**
	 * Used for testing and checking html/javascript with dynamic map.
	 * WILL BE REMOVED once working and the javascript is cleaned up in resources
	 *
	@RequestMapping({"/maptest"})
	public String testMyMap(Model model) {
		model.addAttribute("points", searchRepo.findAll());
		
		return "mapstest.html";
	}
	*/
	
	
	/**
	 * Mapping for the home index displaying Search entities (queries) currently stored 
	 * in the database.
	 * @param model is the 'Search' entity used by the repository
	 * @return
	 */
	@RequestMapping({"", "/distance-matrix"})
	public String showQueries(Model model) {
		model.addAttribute("dist", searchRepo.findAll());
		return "distMatrixIndex";
	}
	
	/**
	 * Mapping for the second page for csv uploads, polygons, and routing (Page 2).
	 * @param model is the 'Search' entity used by the repository
	 * @return
	 */
	@RequestMapping({"/polyrouting"})
	public String polyAndRouting(@Validated Search search, BindingResult result, Model model) {
		
		return "polygon-and-routing";
	}
	
	/**
	 * Mapping for the third page for csv uploads and routing for student pickup locations (Page 3).
	 * @param model is the 'Search' entity used by the repository
	 * @return
	 */
	@RequestMapping({"/bus-routing-students"})
	public String studentRouting(@RequestParam("file") MultipartFile file, Model model) {
		
		
		// validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<StudentPickupInformation> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(StudentPickupInformation.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<StudentPickupInformation> users = csvToBean.parse();

                // TODO: save users in DB?

                // save users list on model
                model.addAttribute("users", users);
                model.addAttribute("status", true);

            } catch (Exception ex) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }
		return "student-routing";
	}
	
	/**
	 * Mapping for a "New Query" button in the home webpage to send for a new webpage, 
	 * one containing an address form to input new data in an instance of Search.
	 * 
	 * @param search is an instance of the Entity Search
	 * @return path to address form webpage (new-distance-query.html)
	 */
	@RequestMapping({"/newsearch"})
	public String addAddressesForm(Search search) {
        return "new-distance-query";
    }
	 
	/**
	 * Mapping for adding query (new-distance-query.html). Data entered in address 
	 * form from the webpage is saved to an instance in the database. Additionally, 
	 * a hash table is checked to see if the distance has been calculated. If it is not,
	 * the DistanceAPI.java calculates and saved the Distance associated with the query.
	 * The "/add-query" page redirects to the "/newsearch" page.
	 * 
	 * @param search is an instance of the Entity Search
	 * @param result 
	 * @param model is the 'Search' entity used by the repository
	 * @return path to home (disMatrixIndex.html)
	 * @throws IOException
	 */
	@RequestMapping({"/add-query"})
	public String addQuery(@Validated Search search, BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
            return "new-distance-query";
        }
		
		search.setOrigin();
		search.setDestination();
		search.setOrigin(search.getOrigin());
		search.setDestination(search.getDestination());
		String testOri = search.getOrigin().replaceAll("\\s", "");
		String testDes = search.getDestination().replaceAll("\\s", "");
		boolean noAdd = false;
		
		
		//Failsafe for missing data.
		if(testOri == "" && testDes != "")
		{
			search.setOrigin(search.getDestination());
			noAdd = true;
		}
		else if(testDes == "" && testOri != "")
		{
			search.setDestination(search.getOrigin());
			noAdd = true;
		}
		else if(testOri == "" && testDes == "")
		{
			search.setOrigin("Slippery Rock University of Pennsylvania");
			search.setDestination("Slippery Rock University of Pennsylvania");
			noAdd = true;
		}
		
		System.out.println("Address 1: " + search.getOrigin());
		System.out.println("Address 2: " + search.getDestination());
		
		for(int i=0; i<5; i++) {}
		
		
	    //hash table is checked here before calling API
		if(noAdd != true)
		{

	    		if((distanceTable.contains(search.getOrigin(), search.getDestination())||distanceTable.contains(search.getDestination(), search.getOrigin())) && distanceTable.getDataObject(search.getOrigin(), search.getDestination()).getDistance() != 0) {
	    		
	    			search.setqDistance(distanceTable.getDataObject(search.getOrigin(), search.getDestination()).getDistance());
	    			System.out.println("got distance " + distanceTable.getDataObject(search.getOrigin(), search.getDestination()).getDistance() + " from HASHTABLE");
	    		}
	    		else {
	    			search.setqDistance(DistanceMatrixAPI.getDistance(search.getOrigin(), search.getDestination()));
					System.out.println("Distance called API to set to: " + search.getqDistance());
					distanceTable.add(search.getOrigin(), search.getDestination());
					distanceTable.getDataObject(search.getOrigin(), search.getDestination()).setDistance(search.getqDistance());
					distanceTable.add(search.getDestination(), search.getOrigin());
					distanceTable.getDataObject(search.getDestination(), search.getOrigin()).setDistance(search.getqDistance());
					dataController.writeToTextFile();
	    		}
	    	
		}
	    //these getters call the setters and set the cooridnates if null
	    System.out.println(search.getOrigin() + " has coordinates " + search.getLat1() + ", " + search.getLng1());
	    System.out.println(search.getDestination() + " has coordinates " + search.getLat2() + ", "  + search.getLng2());
	/*
		if(search.getqDistance()==null) {
			search.setqDistance(DistanceMatrixAPI.getDistance(search.getOrigin(), search.getDestination()));
			System.out.println("Distance set to: " + search.getqDistance());
		}*/
	    System.out.println("Distance = " + search.getqDistance());
	    searchRepo.save(search);
	    
	    
	    return "redirect:/distance-matrix";
	}
	
	/**
	 * Gets mapping for directions (directions.html)page. Mapping is dependent on 
	 * id of the Search Instance. This entity is used to display directions of the 
	 * instance with the same id.
	 * 
	 * @param id is the ID of the instance of the Search entity
	 * @param model is the 'Search' entity used by the repository
	 * @return path to Home
	 */
	@RequestMapping("/find-directions/{id}")
    public String pullDirections(@PathVariable("id") long id, Model model) {
		
		//selects specified query
        Search search = searchRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid query:" + id));
        model.addAttribute("query", search);
       DirectionsHolder Holder = new DirectionsHolder();
        
     // Call DistanceMatrixAPI to find and set directions
	    try {
			search.setqDirections(DirectionsAPI.getDirections(search.getOrigin(), search.getDestination(), Holder));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    //Generate URL for static map
	    //CreateMap myMap = new CreateMap();
	    //search.setMapURL(myMap.createMap(search.getOrigin(), search.getDestination()));
        
        System.out.println("Start at: " + search.getOrigin());
        System.out.println("Arrive at: " + search.getDestination());
        
        return "/directions";
    }
	
	/**
	 * Mapping for a call in which a Search instance with a provided id is deleted.
	 * The "/delete/{id}" page instantly redirects to the "/newsearch" page.
	 * 
	 * @param id is the ID of the instance of the Search entity
	 * @param model is the 'Search' entity used by the repository
	 * @return
	 */
	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Search search = searchRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid query:" + id));
        searchRepo.delete(search);
        return "redirect:/distance-matrix";
    }
	
	
}
