/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service;

import java.util.List;

/**
 * This interface defines the basic CRUD data access operations for entities.
 * It provides methods for adding, updating, removing, listing and finding entities.
 *
 * @param <E> The type of the entity.
 * @param <K> The type of the key used to identify the entity.
 */
public interface GenericCrudDao<E, K> {
    /**
     * Add an entity to the database
     *
     * @param entity The entity to add
     */
    void add(E entity);

    /**
     * Update an existing entity in the database
     *
     * @param entity The entity object to update with
     */
    void update(E entity);

    /**
     * Remove an existing entity from the database
     *
     * @param key The key of object to delete
     * @return boolean value if the operation is successful or not
     */
    boolean remove(K key);

    /**
     * Returns a list of all entities.
     *
     * @param key The username for the objects to list
     * @return A List of entities.
     */
    List<E> list(K key);

    /**
     * Finds an entity by its key.
     *
     * @param key The key of the entity to find.
     * @return The entity if found, otherwise null or an empty entity.
     */
    E find(K key);
}
