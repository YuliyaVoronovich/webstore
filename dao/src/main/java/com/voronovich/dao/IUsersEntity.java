package com.voronovich.dao;

import com.voronovich.entity.UsersEntity;

/**
 * Interface contains abstract methods for dao UsersEntityDAO
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface IUsersEntity {

    /**
     * Method returns user by login and password
     *
     * @param login
     * @param password
     * @return entity
     */
    UsersEntity get(String login, String password);

    /**
     * Method returns user by login
     *
     * @param login
     * @return entity
     */
    UsersEntity get(String login);

    /**
     * Method returns user by email
     *
     * @param email
     * @return entity
     */
    UsersEntity getByEmail(String email);
}
