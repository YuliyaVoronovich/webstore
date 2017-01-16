package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.BasketEntity;
import com.voronovich.service.IBasketService;
import com.voronovich.service.IService;

import java.util.List;

public class BasketService implements IService<BasketEntity>,IBasketService{

    DAO dao = DAO.getDAO();

    @Override
    public List<BasketEntity> get() {
        return dao.getBasketEntityDAO().get();
    }

    @Override
    public List<BasketEntity> getByUser(int id) { return dao.getBasketEntityDAO().getByUser(id); }

    @Override
    public BasketEntity getByGood(int id) {
        return dao.getBasketEntityDAO().getByGood(id);
    }

    @Override
    public BasketEntity get(int id) {
        return dao.getBasketEntityDAO().get(id);
    }

    @Override
    public boolean create(BasketEntity entity) {
        return dao.getBasketEntityDAO().create(entity);
    }

    @Override
    public boolean update(BasketEntity entity) {
        return dao.getBasketEntityDAO().update(entity);
    }

    @Override
    public boolean delete(BasketEntity entity) {
        return dao.getBasketEntityDAO().delete(entity);
    }
}
