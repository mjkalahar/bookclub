/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;

/*
 * Unit tests for the AdminController endpoints related to Book of the Month management.
 * Tests cover valid and invalid form submissions, list retrieval, and form model attributes.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestNoSecurityConfig.class)
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Tests that the monthly books list page loads and contains the expected model attribute.
     */
    @Test
    void testShowBookOfTheMonthReturnsList() throws Exception {
        List<BookOfTheMonth> books = Arrays.asList(new BookOfTheMonth());
        when(bookOfTheMonthDao.list("999")).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/monthly-books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("monthly-books/list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"));
        verify(bookOfTheMonthDao, times(1)).list("999");
    }

    /**
     * Tests that the form for adding a new Book of the Month loads correctly.
     */
    @Test
    void testBookOfTheMonthFormReturnsNew() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/monthly-books/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("monthly-books/new"));
    }

    /**
     * Tests that a valid Book of the Month submission redirects to the list page and calls the DAO add method.
     */
    @Test
    void testAddBookOfTheMonth_Valid() throws Exception {
        doNothing().when(bookOfTheMonthDao).add(any(BookOfTheMonth.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/monthly-books")
                .param("isbn", "1234567890")
                .param("month", "5"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/monthly-books"));
        verify(bookOfTheMonthDao, times(1)).add(any(BookOfTheMonth.class));
    }

    /**
     * Tests that an invalid Book of the Month submission (missing fields) returns to the form and does not call the DAO add method.
     */
    @Test
    void testAddBookOfTheMonth_Invalid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/monthly-books")
                .param("isbn", "") // invalid: empty isbn
                .param("month", "")) // invalid: empty month
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("monthly-books/new"));
        verify(bookOfTheMonthDao, never()).add(any(BookOfTheMonth.class));
    }

    /**
     * Tests that a valid update of a Book of the Month redirects to the list page and calls the DAO update method.
     */
    @Test
    void testUpdateBookOfTheMonth_Valid() throws Exception {
        doNothing().when(bookOfTheMonthDao).update(any(BookOfTheMonth.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/monthly-books/update")
                .param("id", "testid")
                .param("isbn", "1234567890")
                .param("month", "6"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/monthly-books"));
        verify(bookOfTheMonthDao, times(1)).update(any(BookOfTheMonth.class));
    }

    /**
     * Tests that a Book of the Month submission with missing parameters returns to the form and does not call the DAO add method.
     */
    @Test
    void testAddBookOfTheMonth_MissingParams() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/monthly-books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("monthly-books/new"));
        verify(bookOfTheMonthDao, never()).add(any(BookOfTheMonth.class));
    }

    /**
     * Tests that the form model for adding a Book of the Month contains the expected attributes for the form and months.
     */
    @Test
    void testBookOfTheMonthFormModelHasAttributes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/monthly-books/new"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("bookofthemonth"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("months"))
                .andExpect(MockMvcResultMatchers.model().attribute("months", org.hamcrest.Matchers.allOf(
                        org.hamcrest.Matchers.aMapWithSize(12),
                        org.hamcrest.Matchers.hasEntry(1, "January"),
                        org.hamcrest.Matchers.hasEntry(12, "December")
                )));
    }

    /**
     * Tests that the monthly books list page contains the correct content and model attributes.
     */
    @Test
    void testShowBookOfTheMonthReturnsList_Content() throws Exception {
        BookOfTheMonth book = new BookOfTheMonth();
        book.setIsbn("1234567890");
        book.setMonth(5);
        List<BookOfTheMonth> books = Arrays.asList(book);
        when(bookOfTheMonthDao.list("999")).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/monthly-books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("books", org.hamcrest.Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.model().attribute("books", org.hamcrest.Matchers.contains(
                        org.hamcrest.Matchers.hasProperty("isbn", org.hamcrest.Matchers.is("1234567890"))
                )));
        verify(bookOfTheMonthDao, times(1)).list("999");
    }
}
