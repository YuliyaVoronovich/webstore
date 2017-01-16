package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.service.IService;

import java.util.List;

/**
 * Class implements part of the Service layer - CatalogService
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CatalogService implements IService<CatalogEntity> {

    DAO dao = DAO.getDAO();

    @Override
    public List<CatalogEntity> get() {
        return dao.getCatalogEntityDAO().get();
    }

    @Override
    public CatalogEntity get(int id) {
        return dao.getCatalogEntityDAO().get(id);
    }

    @Override
    public boolean create(CatalogEntity entity) {
        return dao.getCatalogEntityDAO().create(entity);
    }

    @Override
    public boolean update(CatalogEntity entity) {
        return dao.getCatalogEntityDAO().update(entity);
    }

    @Override
    public boolean delete(CatalogEntity entity) {
        return dao.getCatalogEntityDAO().delete(entity);
    }
}
