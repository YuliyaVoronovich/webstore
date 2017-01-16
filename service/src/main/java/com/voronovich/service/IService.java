package com.voronovich.service;

import java.util.List;

/**
 * Interface contains abstract methods for service layer
 *
 * @param <TYPE> entity
 * @author Dmitry V
 * @version 1.0
 */
public interface IService<TYPE> {

    TYPE get(int id);
    List<TYPE> get();
    boolean create(TYPE entity);
    boolean update(TYPE entity);
    boolean delete(TYPE entity);
}