package edu.sru.bayne.DistanceAndDirections.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.sru.bayne.DistanceAndDirections.domain.Search;
import edu.sru.bayne.DistanceAndDirections.repository.SearchRepository;
import edu.sru.booser.datastore.*;
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
	private Table distanceTable = new Table();
	
	
	public DistanceandDirectionsController(SearchRepository searchRepo) {
		this.searchRepo = searchRepo;
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
		System.out.println("Address 1: " + search.getOrigin());
		System.out.println("Address 2: " + search.getDestination());
		
	    //hash table is checked here before calling API
	    if (search.getqDistance() >= 0.0) {
	    	if(distanceTable.contains(search.getOrigin(), search.getDestination())||distanceTable.contains(search.getDestination(), search.getOrigin())) {
	    		search.setqDistance(distanceTable.getDataObject(search.getOrigin(), search.getDestination()).getDistance());
	    		System.out.println("got distance " + distanceTable.getDataObject(search.getOrigin(), search.getDestination()).getDistance() + " from HASHTABLE");
	    	}
	    	else {
	    		search.setqDistance(DistanceMatrixAPI.getDistance(search.getOrigin(), search.getDestination()));
				System.out.println("Distance called API to set to: " + search.getqDistance());
				distanceTable.add(search.getOrigin(), search.getDestination());
				distanceTable.add(search.getDestination(), search.getOrigin());
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
	
	@RequestMapping({"/polyrouting"})
	public String PolyAndRouting(@Validated Search search, BindingResult result, Model model) {
		
		return "polygon-and-routing";
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
