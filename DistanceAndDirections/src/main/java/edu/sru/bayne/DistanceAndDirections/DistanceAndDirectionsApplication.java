package edu.sru.bayne.DistanceAndDirections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Application class to launch website on localhost:8080
 * @author Gregory Bayne
 *
 */
@SpringBootApplication
public class DistanceAndDirectionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistanceAndDirectionsApplication.class, args);
		System.out.println("Server is running on localhost:8080");
	}

}
