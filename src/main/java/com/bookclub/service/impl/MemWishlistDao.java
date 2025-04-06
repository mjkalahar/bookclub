/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

/**
 * This class provides an in-memory implementation of the WishlistDao interface.
 * It stores and manages a list of WishlistItem objects in memory.
 */
public class MemWishlistDao implements WishlistDao {
    private List<WishlistItem> wishlist;

    /**
     * Constructor for MemWishlistDao.
     * Initializes the in-memory list of wishlist items with some sample data.
     */
    public MemWishlistDao() {
        wishlist = new ArrayList<>();
        wishlist.add(new WishlistItem("978-0590353427", "Harry Potter and the Sorcerer's Stone"));
        wishlist.add(new WishlistItem("978-0439064873", "Harry Potter and the Chamber of Secrets"));
        wishlist.add(new WishlistItem("978-0439136365", "Harry Potter and the Prisoner of Azkaban"));
        wishlist.add(new WishlistItem("978-0439139601", "Harry Potter and the Goblet of Fire"));
        wishlist.add(new WishlistItem("978-0439358071", "Harry Potter and the Order of the Phoenix"));
        wishlist.add(new WishlistItem("978-0439784542", "Harry Potter and the Half-Blood Prince"));
        wishlist.add(new WishlistItem("978-0545139700", "Harry Potter and the Deathly Hallows"));
    }

    /**
     * Returns the list of all WishlistItems.
     *
     * @return A List of WishlistItem objects.
     */
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    /**
     * Finds a wishlist item by its ISBN.
     *
     * @param key The ISBN of the wishlist item to find.
     * @return The Wishlist object if found, otherwise a new empty Wishlist object.
     */
    @Override
    public WishlistItem find(String key) {
        for (WishlistItem wishlistItem : this.wishlist) {
            if (wishlistItem.getIsbn().equals(key)) {
                return wishlistItem;
            }
        }

        return new WishlistItem();
    }
}
