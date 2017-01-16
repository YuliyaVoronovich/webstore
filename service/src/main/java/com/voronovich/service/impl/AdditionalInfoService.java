package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.AdditionalInfoEntity;
import com.voronovich.service.IService;

import java.util.List;

/**
 * Class implements part of the Service layer - AdditionalInfoService
 *
 * @author Dmitry V
 * @version 1.0
 */
public class AdditionalInfoService implements IService<AdditionalInfoEntity>{

    DAO dao = DAO.getDAO();

    @Override
    public List<AdditionalInfoEntity> get() {
        return dao.getAdditionalInfoEntityDAO().get();
    }

    @Override
    public AdditionalInfoEntity get(int id) {
        return dao.getAdditionalInfoEntityDAO().get(id);
    }

    @Override
    public boolean create(AdditionalInfoEntity entity) {
        return dao.getAdditionalInfoEntityDAO().create(entity);
    }

    @Override
    public boolean update(AdditionalInfoEntity entity) {
        return dao.getAdditionalInfoEntityDAO().update(entity);
    }

    @Override
    public boolean delete(AdditionalInfoEntity entity) {
        return dao.getAdditionalInfoEntityDAO().delete(entity);
    }
}
