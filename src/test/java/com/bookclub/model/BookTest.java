/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * Unit tests for the Book model class.
 * Tests cover constructors, getters, and setters for Book properties.
 */
class BookTest {
    /*
     * Tests the default constructor of the Book class.
     * Asserts that all string properties are initialized to empty strings,
     * numOfPages is initialized to 0.
     */
    @Test
    void testDefaultConstructor() {
        Book book = new Book();
        assertEquals("", book.getIsbn());
        assertEquals("", book.getTitle());
        assertEquals("", book.getDescription());
        assertEquals(0, book.getNumOfPages());
        assertEquals("", book.getInfoUrl());
    }

    /*
     * Tests the parameterized constructor of the Book class.
     * Asserts that all properties are correctly initialized with the given values.
     */
    @Test
    void testParameterizedConstructor() {
        Book book = new Book("1234567890", "Test Title", "Test Description", "http://info.url", 321);
        assertEquals("1234567890", book.getIsbn());
        assertEquals("Test Title", book.getTitle());
        assertEquals("Test Description", book.getDescription());
        assertEquals(321, book.getNumOfPages());
        assertEquals("http://info.url", book.getInfoUrl());
    }

    /*
     * Tests the setter and getter methods of the Book class.
     * Asserts that the properties are correctly updated and retrieved.
     */
    @Test
    void testSettersAndGetters() {
        Book book = new Book();
        book.setIsbn("0987654321");
        book.setTitle("Another Title");
        book.setDescription("Another Description");
        book.setNumOfPages(123);
        book.setInfoUrl("http://another.url");
        assertEquals("0987654321", book.getIsbn());
        assertEquals("Another Title", book.getTitle());
        assertEquals("Another Description", book.getDescription());
        assertEquals(123, book.getNumOfPages());
        assertEquals("http://another.url", book.getInfoUrl());
    }
}
