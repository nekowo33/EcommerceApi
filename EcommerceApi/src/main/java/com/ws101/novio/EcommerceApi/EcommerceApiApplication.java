package com.ws101.novio.EcommerceApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the EcommerceApi Spring Boot application.
 *
 * This application provides a RESTful API for managing
 * flower bouquet products in an e-commerce system.
 *
 * @author Novio, Mariel Kimberly B.
 * @author Cosino, Vivian Faith C.
 */
@SpringBootApplication
public class EcommerceApiApplication {

	/**
	 * Starts the Spring Boot application.
	 *
	 * @param args command-line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiApplication.class, args);
	}

}
