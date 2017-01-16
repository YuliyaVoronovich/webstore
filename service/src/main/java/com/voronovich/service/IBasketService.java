package com.voronovich.service;

import com.voronovich.entity.BasketEntity;

import java.util.List;

/**
 * Interface contains abstract methods for service BasketService
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface IBasketService {

    /**
     * Method returns list of orders by user id
     *
     * @param id
     * @return list
     */
    List<BasketEntity> getByUser(int id);

    /**
     * Method returns order by it"s id
     *
     * @param id
     * @return entity
     */
    BasketEntity getByGood(int id);
}
