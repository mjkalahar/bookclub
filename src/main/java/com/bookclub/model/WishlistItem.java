/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

/**
 * Represents an item on a user's wishlist, containing the ISBN and title of a book.
 */
public class WishlistItem {

    @Id
    private String id;
    @NotNull
    @NotEmpty(message = "ISBN is a required field.")
    private String isbn;

    @NotNull
    @NotEmpty(message = "Title is a required field.")
    private String title;

    /**
     * Default constructor for the WishlistItem class.
     * Initializes an empty WishlistItem with default values:
     * - Empty ISBN
     * - Empty title
     */
    public WishlistItem() {
        this.isbn = "";
        this.title = "";
    }

    /**
     * Parameterized constructor for the WishlistItem class.
     *
     * @param isbn  The ISBN of the book in the wishlist.
     * @param title The title of the book in the wishlist.
     */
    public WishlistItem(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    /**
     * Gets the ISBN of the book in the wishlist.
     *
     * @return The ISBN of the book.
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets the ISBN of the book in the wishlist.
     *
     * @param isbn The ISBN to set.
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

     /**
     * Gets the title of the book in the wishlist.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book in the wishlist.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the Database Id of the book in the wishlist.
     *
     * @return The Database Id of the book.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a string representation of the WishlistItem object.
     *
     * @return A string representation of the WishlistItem object.
     */
    @Override
    public String toString() {
        return "WishlistItem{id='" + id + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
