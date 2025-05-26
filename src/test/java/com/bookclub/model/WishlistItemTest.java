/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * Unit tests for the WishlistItem model class.
 * Tests cover constructors, getters, setters, and ID field behavior.
 */
class WishlistItemTest {
    /*
     * Tests the default constructor of the WishlistItem class.
     * Verifies that the ISBN and title are initialized to empty strings.
     */
    @Test
    void testDefaultConstructor() {
        WishlistItem item = new WishlistItem();
        assertEquals("", item.getIsbn());
        assertEquals("", item.getTitle());
    }

    /*
     * Tests the parameterized constructor of the WishlistItem class.
     * Verifies that the ISBN and title are set correctly upon object creation.
     */
    @Test
    void testParameterizedConstructor() {
        WishlistItem item = new WishlistItem("5556667778", "Wishlist Book");
        assertEquals("5556667778", item.getIsbn());
        assertEquals("Wishlist Book", item.getTitle());
    }

    /*
     * Tests the setter and getter methods of the WishlistItem class.
     * Verifies that the ISBN, title, username, and ID can be set and retrieved correctly.
     */
    @Test
    void testSettersAndGetters() {
        WishlistItem item = new WishlistItem();
        item.setIsbn("9998887776");
        item.setTitle("Another Wishlist Book");
        item.setUsername("testuser");
        item.setId("wish123");
        assertEquals("9998887776", item.getIsbn());
        assertEquals("Another Wishlist Book", item.getTitle());
        assertEquals("testuser", item.getUsername());
        assertEquals("wish123", item.getId());
    }
}
