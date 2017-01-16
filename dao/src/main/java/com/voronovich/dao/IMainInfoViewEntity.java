package com.voronovich.dao;

import java.util.List;

/**
 * Interface contains abstract methods for dao MainInfoViewEntityDAO
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface IMainInfoViewEntity {

    /**
     * Method returns list of characteristics of goods by it's id
     *
     * @param id
     * @return list
     */
    List<String> getByID(int id);
}
