/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.dao;

import com.bookclub.model.Book;
import com.bookclub.service.GenericDao;

/**
 * This interface defines the data access operations for Book objects.
 * It extends the GenericDao interface, providing basic CRUD operations
 * for Book entities, using the ISBN as the key.
 */
public interface BookDao extends GenericDao<Book, String> {

}
