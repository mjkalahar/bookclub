/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

/**
 * This class provides an in-memory implementation of the BookDao interface.
 * It stores and manages a list of Book objects in memory.
 */
public class MemBookDao implements BookDao {
    private List<Book> books;

    /**
     * Constructor for MemBookDao.
     * Initializes the in-memory list of books with some sample data.
     */
    public MemBookDao() {
        books = new ArrayList<>();
        books.add(new Book("978-0134685991", "Effective Java", 
    "A must-read for Java developers, covering best practices and design principles.", 
    416, List.of("Joshua Bloch")));

        books.add(new Book("978-1260440232", "Java: The Complete Reference", 
            "A comprehensive guide to Java, covering core concepts, APIs, and advanced topics.", 
            1248, List.of("Herbert Schildt")));

        books.add(new Book("978-0596009205", "Head First Java", 
            "An engaging and visually rich introduction to Java programming for beginners.", 
            720, List.of("Kathy Sierra", "Bert Bates")));

        books.add(new Book("978-0132350884", "Clean Code: A Handbook of Agile Software Craftsmanship", 
            "A guide to writing readable, maintainable, and efficient Java code.", 
            464, List.of("Robert C. Martin")));

        books.add(new Book("978-0321349606", "Java Concurrency in Practice", 
            "A deep dive into multithreading and concurrency in Java, with real-world examples.", 
            432, List.of("Brian Goetz", "Tim Peierls", "Joshua Bloch", "Joseph Bowbeer", "David Holmes", "Doug Lea")));
    }

    /**
     * Returns the list of all books.
     *
     * @return A List of Book objects.
     */
    @Override
    public List<Book> list() {
        return this.books;
    }

    /**
     * Finds a book by its ISBN.
     *
     * @param key The ISBN of the book to find.
     * @return The Book object if found, otherwise a new empty Book object.
     */
    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if (book.getIsbn().equals(key)) {
                return book;
            }
        }

        return new Book();
    }
}
