/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

 /**
 * This class provides a MongoDB implementation of the WishlistDao interface.
 * It interacts with a MongoDB database to store and manage WishlistItem objects.
 */
@Repository("wishlistDao")
public class MongoWishlistDao implements WishlistDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * Adds a new WishlistItem to the MongoDB database.
     *
     * @param entity The WishlistItem to add.
     */
    @Override
    public void add(WishlistItem entity) {
        mongoTemplate.save(entity);
    }

    /**
     * NYI
     * Update an existing WishlistItem in the MongoDB database.
     *
     * @param entity The WishlistItem to update.
     */
    @Override
    public void update(WishlistItem entity) {

    }

    /**
     * NYI
     * Remove a WishlistItem from the database.
     *
     * @param entity The WishlistItem to remove.
     * @return boolean value if the operation is successful or not
     */
    @Override
    public boolean remove(WishlistItem entity) {
        return false;
    }

    /**
     * Returns the list of all WishlistItems.
     *
     * @return A List of WishlistItem objects.
     */
    @Override
    public List<WishlistItem> list() {
        return mongoTemplate.findAll(WishlistItem.class);
    }

    /**
     * NYI
     * Finds a wishlist item by the key.
     *
     * @param key The value to search for in the WishlistItem.
     * @return The Wishlist object if found, otherwise a new empty Wishlist object.
     */
    @Override
    public WishlistItem find(String key) {
        return null;
    }
}
