package com.voronovich.dao;

import java.util.List;

/**
 * Interface contains abstract methods for dao layer
 *
 * @param <TYPE> entity
 * @author Dmitry V
 * @version 1.0
 */
public interface IDAO<TYPE> {

    /**
     * Method returns object by id
     *
     * @param id
     * @return entity
     */
    TYPE get(int id);

    /**
     * Method return list of objects
     *
     * @return list
     */
    List<TYPE> get();

    /**
     * Method creates new instance of entity
     *
     * @param entity
     * @return boolean
     */
    boolean create(TYPE entity);

    /**
     * Method updates instance of entity
     *
     * @param entity
     * @return boolean
     */
    boolean update(TYPE entity);

    /**
     * Method updates instance of entity
     *
     * @param entity
     * @return boolean
     */
    boolean delete(TYPE entity);
}
