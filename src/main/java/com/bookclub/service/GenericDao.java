/*
 * Kalahar, M. (2025). CIS 530 Intermediate Java Programming. Bellevue University.
 */
package com.bookclub.service;

import java.util.List;

/**
 * This interface defines the basic data access operations for entities.
 * It provides methods for listing and finding entities.
 *
 * @param <E> The type of the entity.
 * @param <K> The type of the key used to identify the entity.
 */
public interface GenericDao<E, K> {
    /**
     * Returns a list of all entities.
     *
     * @return A List of entities.
     */
    List<E> list();

    /**
     * Finds an entity by its key.
     *
     * @param key The key of the entity to find.
     * @return The entity if found, otherwise null or an empty entity.
     */
    E find(K key);
}