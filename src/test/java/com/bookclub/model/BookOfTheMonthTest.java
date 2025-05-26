/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/*
 * Unit tests for the BookOfTheMonth model class.
 * Tests cover constructors, getters, setters, and ID field behavior.
 */
class BookOfTheMonthTest {
    /*
     * Verifies that the setter and getter methods for ISBN and month
     * work as expected.
     */
    @Test
    void testSettersAndGetters() {
        BookOfTheMonth botm = new BookOfTheMonth();
        botm.setIsbn("1112223334");
        botm.setMonth(5);
        assertEquals("1112223334", botm.getIsbn());
        assertEquals(5, botm.getMonth());
    }

    /*
     * Checks that the ID field can be set and retrieved correctly.
     */
    @Test
    void testIdField() {
        BookOfTheMonth botm = new BookOfTheMonth();
        botm.setId("abc123");
        assertEquals("abc123", botm.getId());
    }
}
