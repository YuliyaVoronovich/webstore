package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.MainInfoEntity;
import com.voronovich.service.IService;

import java.util.List;

public class MainInfoService implements IService<MainInfoEntity>{

    DAO dao = DAO.getDAO();

    @Override
    public List<MainInfoEntity> get() {
        return dao.getMainInfoEntityDAO().get();
    }

    @Override
    public MainInfoEntity get(int id) {
        return dao.getMainInfoEntityDAO().get(id);
    }

    @Override
    public boolean create(MainInfoEntity entity) {
        return dao.getMainInfoEntityDAO().create(entity);
    }

    @Override
    public boolean update(MainInfoEntity entity) {
        return dao.getMainInfoEntityDAO().update(entity);
    }

    @Override
    public boolean delete(MainInfoEntity entity) {
        return dao.getMainInfoEntityDAO().delete(entity);
    }
}
