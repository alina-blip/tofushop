/**
 * WebshopBackendApplication Class
 *
 * This is the main class of the web application. It initializes and launches the Spring Boot application.
 */

package com.example.webshopbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebshopBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebshopBackendApplication.class, args);
	}

}
