/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.dao;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.GenericCrudDao;

/**
 * Data Access Object (DAO) interface for {@link BookOfTheMonth} entities.
 * This interface extends the {@link GenericCrudDao}, providing standard
 * CRUD operations for BookOfTheMonth objects, using a String as the key type.
 */
public interface BookOfTheMonthDao extends GenericCrudDao<BookOfTheMonth, String> {
}
