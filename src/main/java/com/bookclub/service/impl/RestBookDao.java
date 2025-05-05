/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;

/**
 * This class provides an in-memory implementation of the BookDao interface.
 * It stores and manages a list of Book objects in memory.
 */
public class RestBookDao implements BookDao {

    /**
     * Constructor for RestBookDao.
     */
    public RestBookDao() {
    }


    /**
     * Load book details from Open Library API
     * @param isbnString A string containing ISBNs to lookup in the API
     * @return A JSONPath readable JSON object
     */
    public Object getBooksDoc(String isbnString) {
        String openLibraryUrl = "https://openlibrary.org/api/books";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");
        HttpEntity<?> entity = new HttpEntity<>(httpHeaders);
        HttpEntity<String> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
        String jsonBooklist = response.getBody();
        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);
    }

    /**
     * Returns the list of all books.
     *
     * @return A List of Book objects.
     */
    @Override
    public List<Book> list() {
        String isbnString = "9780593099322,9780261102361,9780261102378,9780590302715,9780316769532";
        Object doc = getBooksDoc(isbnString);

        List<Book> books = new ArrayList<>();

        Configuration configuration = Configuration.defaultConfiguration()
                .addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL);
        DocumentContext documentContext = JsonPath.using(configuration).parse(doc);

        List<String> isbns = JsonPath.read(doc,"$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = documentContext.read("$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = documentContext.read("$..details.number_of_pages");

        for(int index = 0; index < titles.size(); index++) {

            Integer bookPages = pages.get(index) != null ? pages.get(index) : 0;
            String bookIsbn = isbns.get(index) != null ? isbns.get(index) : "N/A";
            String bookTitle = titles.get(index) != null ? titles.get(index) : "N/A";
            String bookSubtitle = subtitle.get(index) != null ? subtitle.get(index) : "N/A";
            String bookInfoUrl = infoUrls.get(index) != null ? infoUrls.get(index) : "N/A";

            books.add(new Book(bookIsbn, bookTitle, bookSubtitle, bookInfoUrl, bookPages));
        }


        return books;
    }

    /**
     * Finds a book by its ISBN.
     *
     * @param key The ISBN of the book to find.
     * @return The Book object if found, otherwise a new empty Book object.
     */
    @Override
    public Book find(String key) {
        Object doc = getBooksDoc(key);
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        Book book = new Book(isbn, title, desc, infoUrl, numOfPages);

        return book;
    }
}
