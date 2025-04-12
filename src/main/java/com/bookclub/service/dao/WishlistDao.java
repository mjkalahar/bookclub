/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.dao;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.GenericCrudDao;

/**
 * This interface defines the data access operations for Wishlist objects.
 * It extends the GenericDao interface, providing basic CRUD operations
 * for Wishlist entities, using the ISBN as the key.
 */
public interface WishlistDao extends GenericCrudDao<WishlistItem, String> {
    
}
