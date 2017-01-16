package com.voronovich.service;

import com.voronovich.entity.BasketEntity;

import java.util.List;

public interface IBasketService {

    List<BasketEntity> getByUser(int id);

    BasketEntity getByGood(int id);

}
