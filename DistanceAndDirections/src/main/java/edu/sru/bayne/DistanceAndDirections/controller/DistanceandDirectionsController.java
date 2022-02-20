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
		//with primal/old format (block out code below)
		//searchRepo.save(search);
		
		//for full addresses
		search.setOrigin(search.trueAddress(search.getStreet1(), search.getCity1(), search.getState1(), search.getZip1(), search.getCountry1()));
		search.setDestination(search.trueAddress(search.getStreet2(), search.getCity2(), search.getState2(), search.getZip2(), search.getCountry2()));
		
		
		//code for storing input/output
	    searchRepo.save(search);
	    
	    //hash table should be compared here before calling API
	    
	    // Call to DistanceMatrixAPI and DirectionsAPI to find and set distance + directions
	    
	   // search.setqDistance(DistanceMatrixAPI.getDistance(search.getOrigin(), search.getDestination()));
	    // Call DistanceMatrixAPI to find and set distance
	    //search.setqDirections(DirectionsAPI.getDirections(search.getOrigin(), search.getDestination()));
	   // searchRepo.save(search);
	    
	   
	    
	
	    
	    return "redirect:/distance-matrix";
	}
	
	
	@GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Search search = searchRepo.findById(id)
          .orElseThrow(() -> new IllegalArgumentException("Invalid query:" + id));
        searchRepo.delete(search);
        return "redirect:/distance-matrix";
    }
	
	
}
