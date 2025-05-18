/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;

/**
 * MongoDB implementation of the {@link BookOfTheMonthDao} interface.
 * This class handles CRUD operations for {@link BookOfTheMonth} entities
 * using a MongoDB database.
 */
@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    private MongoTemplate mongoTemplate;

     /**
     * Adds a new BookOfTheMonth entity to the MongoDB database.
     *
     * @param entity The BookOfTheMonth entity to add.
     */
    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    /**
     * Updates an existing BookOfTheMonth entity in the MongoDB database.
     * <p>
     * TODO: Implement the update logic for BookOfTheMonth.
     * This method currently does not perform any update operations.
     * </p>
     *
     * @param entity The BookOfTheMonth entity with updated information.
     */
    @Override
    public void update(BookOfTheMonth entity) {

    }

     /**
     * Removes a BookOfTheMonth entity from the MongoDB database based on its ID.
     *
     * @param key The ID of the BookOfTheMonth entity to remove.
     * @return true if the operation was acknowledged by MongoDB (does not necessarily mean a document was deleted).
     */
    @Override
    public boolean remove(String key) {
        Query query = new Query();

        query.addCriteria(Criteria.where("id").is(key));

        mongoTemplate.remove(query, BookOfTheMonth.class);

        return true;
    }

     /**
     * Retrieves a list of BookOfTheMonth entities.
     * If the key is "999", all BookOfTheMonth entities are returned.
     * Otherwise, it returns entities matching the specified month.
     *
     * @param key A string representing the month (1-12) or "999" to retrieve all.
     * @return A list of {@link BookOfTheMonth} entities.
     */
    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);

        System.out.println("Month: " + month);

        if(month == 999) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query = new Query();

        query.addCriteria(Criteria.where("month").is(month));

        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    /**
     * Finds a specific BookOfTheMonth entity by its key (ID).
     * <p>
     * TODO: Implement the find logic for BookOfTheMonth.
     * This method currently returns null.
     * </p>
     *
     * @param key The ID of the BookOfTheMonth entity to find.
     * @return The found {@link BookOfTheMonth} entity, or null if not found or not implemented.
     */
    @Override
    public BookOfTheMonth find(String key) {
        return null;
    }
}
