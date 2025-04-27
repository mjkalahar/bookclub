/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;


/**
 * REST controller for handling wishlist related API requests.
 * Provides endpoints for retrieving wishlist items.
 * The base path for this controller is /api/wishlist and it produces JSON responses.
 * Cross-Origin Resource Sharing (CORS) is enabled for all origins.
 */
@RestController
@RequestMapping(path = "/api/wishlist", produces = "application/json")
@CrossOrigin(origins = "*")
public class WishlistRestController {
    WishlistDao wishlistDao = new MongoWishlistDao();

    /**
     * Setter for the wishlistDao.
     * This method is used for dependency injection of the WishlistDao.
     * 
     * @param wishlistDao The WishlistDao implementation to be injected.
     */
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Handles GET requests to the base path (/api/wishlist).
     * Retrieves and returns a list of all wishlist items.
     *
     * @return A List containing all WishlistItem objects.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<WishlistItem> showWishlist()
    {
        return wishlistDao.list();
    }

    /**
     * Handles GET requests to /api/wishlist/{id}
     * Retrieves and returns a specific wishlist item based on its ID
     * 
     * @param id The ID of the wishlist item to retrieve, passed as a path variable.
     * @return The WishlistItem object corresponding to the given ID, or null if not found.
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById(@PathVariable String id) {
        return wishlistDao.find(id);
    }
}
