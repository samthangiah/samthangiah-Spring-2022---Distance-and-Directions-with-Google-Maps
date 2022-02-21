package edu.sru.bayne.DistanceAndDirections.controller;

import java.io.IOException;
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
import edu.sru.bayne.DistanceAndDirections.domain.Search;
import edu.sru.bayne.DistanceAndDirections.repository.SearchRepository;
import edu.sru.booser.datastore.*;


//Controller for Distance Matrix HTML
@Controller
public class DistanceandDirectionsController{
	
	private SearchRepository searchRepo;
	
	public DistanceandDirectionsController(SearchRepository searchRepo) {
		this.searchRepo = searchRepo;
	}
	
	// Mapping for the home index displaying current queries
	@RequestMapping({"", "/distance-matrix"})
	public String showQueries(Model model) {
		model.addAttribute("dist", searchRepo.findAll());
		return "distMatrixIndex";
	}
	
	// Mapping to search fields
	@RequestMapping({"/newsearch"})
	public String addAddressesForm(Search search) {
        return "new-distance-query";
    }
	
	// Mapping for adding query. Uses DistanceMatrixAPI.java to return distance values. 
	//
	@RequestMapping({"/add-query"})
	public String addQuery(@Validated Search search, BindingResult result, Model model) throws IOException {
		if (result.hasErrors()) {
            return "new-distance-query";
        }
		
		//Query will need address elements delimited by %20 (check out 
		// https://maps.googleapis.com/maps/api/distancematrix/xml?origins=806%20Graywyck%20Drive%20Seven%20Fields%20PA%2016046%20USA&destinations=401%20Suncrest%20Drive%20Cranberry%20Township%20PA%2016066%20USA&mode=driving&units=imperial&key=AIzaSyCRm7IoRW0gGqjIgh_I5OrpzLWYKxxTr5s
		
		search.setOrigin();
		search.setDestination();
		System.out.println("Address 1: " + search.getOrigin());
		System.out.println("Address 2: " + search.getDestination());
	    
	    //hash table should be compared here before calling API
	    
	    /* Call to DistanceMatrixAPI and DirectionsAPI to find and set distance + directions
	     * Query will need address elements delimited by %20 (check out 'queryOrigAddress()', 'queryDestAddress()' in Search.java )
	     * 		Example:
	     * 		origins=806%20Graywyck%20Drive%20Seven%20Fields%20PA%2016046%20USA
	     * 		&destinations=401%20Suncrest%20Drive%20Cranberry%20Township%20PA%2016066%20USA
	    */ 
		
	    search.setqDistance(DistanceMatrixAPI.getDistance(search.queryOrigAddress(), search.queryDestAddress()));
	    System.out.println("Distance = " + search.getqDistance());
	    // Call DistanceMatrixAPI to find and set distance
	    // search.setqDirections(DirectionsAPI.getDirections(search.getOrigin(), search.getDestination()));
	    searchRepo.save(search);
	    
	   
	    
	
	    
	    return "redirect:/distance-matrix";
	}
	
	@RequestMapping("/find-directions/{id}")
    public String pullDirections(@PathVariable("id") long id, Model model) {
        Search search = searchRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid query:" + id));
        model.addAttribute("query", search);
        
        System.out.println(search.getOrigin());
        System.out.println(search.getDestination());
        //searchRepo.delete(search);
        return "/directions";
    }
	
	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Search search = searchRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid query:" + id));
        searchRepo.delete(search);
        return "redirect:/distance-matrix";
    }
	
	
}
