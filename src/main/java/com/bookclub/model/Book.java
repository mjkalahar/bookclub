/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.model;


/**
 * Represents a book with its ISBN, title, description, number of pages, and info URL.
 */
public class Book {
    private String isbn;
    private String title;
    private String description;
    private int numOfPages;
    private String infoUrl;

    /**
     * Default constructor for the Book class.
     * Initializes an empty book with default values:
     * - Empty info Url
     * - Empty ISBN
     * - Empty title
     * - Empty description
     * - 0 number of pages
     */
    public Book() {
        this.infoUrl = "";
        this.isbn = "";
        this.title = "";
        this.description = "";
        this.numOfPages = 0;
    }

    /**
     * Parameterized constructor for the Book class.
     *
     * @param isbn        The ISBN of the book.
     * @param title       The title of the book.
     * @param description The description of the book.
     * @param infoUrl     The info url of the book.
     * @param numOfPages  The number of pages in the book.
     */
    public Book(String isbn, String title, String description, String infoUrl, int numOfPages) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.numOfPages = numOfPages;
        this.infoUrl = infoUrl;
    }

    /**
     * Gets the ISBN of the book.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book.
     *
     * @param isbn The ISBN to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the description of the book.
     *
     * @return The description of the book.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the book.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the number of pages in the book.
     *
     * @return The number of pages in the book.
     */
    public int getNumOfPages() {
        return numOfPages;
    }

    /**
     * Sets the number of pages in the book.
     *
     * @param numOfPages The number of pages to set.
     */
    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    /**
     * Gets the info url of the book
     *
     * @return The info url of the book.
     */
    public String getInfoUrl() {
        return infoUrl;
    }

    /**
     * Sets the info url of the book
     *
     * @param infoUrl The info url to set.
     */
    public void setInfoUrl(String infoUrl) {
        this.infoUrl = infoUrl;
    }


    /**
     * Returns a string representation of the Book object.
     *
     * @return A string representation of the Book object.
     */
    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn + ", title=" + title + ", description=" + description + ", infoUrl=" + infoUrl + ", numOfPages=" + numOfPages  + '}';
    }
}
