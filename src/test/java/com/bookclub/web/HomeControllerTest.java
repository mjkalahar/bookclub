/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
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
 * Unit tests for the HomeController endpoints related to the public-facing pages and Book of the Month display.
 * Tests cover home, about, contact, and book detail endpoints, including model attributes and content.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(TestNoSecurityConfig.class)
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Tests that the home page loads, returns the correct view, and contains the books model attribute.
     */
    @Test
    void testShowHomeReturnsIndex() throws Exception {
        BookOfTheMonth botm = new BookOfTheMonth();
        botm.setIsbn("1234567890");
        botm.setMonth(5);
        when(bookOfTheMonthDao.list(anyString())).thenReturn(Collections.singletonList(botm));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"));

        verify(bookOfTheMonthDao, atLeastOnce()).list(anyString());
    }

    /**
     * Tests that the about page loads and returns the correct view.
     */
    @Test
    void testShowAboutUsReturnsAbout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/about"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("about"));
    }

    /**
     * Tests that the contact page loads and returns the correct view.
     */
    @Test
    void testShowContactUsReturnsContact() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/contact"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("contact"));
    }

    /**
     * Tests that the book detail page loads, returns the correct view, and contains the book model attribute.
     */
    @Test
    void testGetMonthlyBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1234567890"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("monthly-books/view"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("book"));
    }

    /**
     * Tests that the home page model contains a list of books with the expected size.
     */
    @Test
    void testShowHomeModelHasBooksList() throws Exception {
        BookOfTheMonth botm1 = new BookOfTheMonth();
        botm1.setIsbn("1234567890");
        botm1.setMonth(5);
        BookOfTheMonth botm2 = new BookOfTheMonth();
        botm2.setIsbn("0987654321");
        botm2.setMonth(5);
        when(bookOfTheMonthDao.list(anyString())).thenReturn(Arrays.asList(botm1, botm2));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", org.hamcrest.Matchers.hasSize(2)));
    }

    /**
     * Tests that the book detail page model contains the correct book by ISBN.
     */
    @Test
    void testGetMonthlyBookByIdModelHasBook_Content() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/1234567890"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("book"))
                .andExpect(MockMvcResultMatchers.model().attribute("book", org.hamcrest.Matchers.hasProperty("isbn", org.hamcrest.Matchers.is("1234567890"))));
    }

    /**
     * Tests that the home page model contains a list of books with the correct content and order.
     */
    @Test
    void testShowHomeModelHasBooksList_Content() throws Exception {
        BookOfTheMonth botm1 = new BookOfTheMonth();
        botm1.setIsbn("1234567890");
        botm1.setMonth(5);
        BookOfTheMonth botm2 = new BookOfTheMonth();
        botm2.setIsbn("0987654321");
        botm2.setMonth(5);
        when(bookOfTheMonthDao.list(anyString())).thenReturn(Arrays.asList(botm1, botm2));

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
                .andExpect(MockMvcResultMatchers.model().attribute("books", org.hamcrest.Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.model().attribute("books", org.hamcrest.Matchers.contains(
                        org.hamcrest.Matchers.hasProperty("isbn", org.hamcrest.Matchers.is("1234567890")),
                        org.hamcrest.Matchers.hasProperty("isbn", org.hamcrest.Matchers.is("0987654321"))
                )));
    }
}
