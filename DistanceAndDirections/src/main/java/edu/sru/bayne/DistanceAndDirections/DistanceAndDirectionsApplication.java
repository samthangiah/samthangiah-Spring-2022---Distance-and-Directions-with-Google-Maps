package edu.sru.bayne.DistanceAndDirections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DistanceAndDirectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistanceAndDirectionsApplication.class, args);
		System.out.println("Server is running on localhost:8080");
	}

}
