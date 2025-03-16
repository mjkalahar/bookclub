/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This class represents a simple Bookclub web application
 */
@SpringBootApplication
public class BookclubApplication {

	/**
     * Main method to launch the application.
     *
     * @param args Command line arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(BookclubApplication.class, args);
	}

}
