package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.RoleEntity;
import com.voronovich.service.IService;

import java.util.List;

/**
 * Class implements part of the Service layer - RoleService
 *
 * @author Dmitry V
 * @version 1.0
 */
public class RoleService implements IService<RoleEntity> {

    DAO dao = DAO.getDAO();

    @Override
    public List<RoleEntity> get() {
        return dao.getRoleEntityDAO().get();
    }

    @Override
    public RoleEntity get(int id) {
        return dao.getRoleEntityDAO().get(id);
    }

    @Override
    public boolean create(RoleEntity entity) {
        return dao.getRoleEntityDAO().create(entity);
    }

    @Override
    public boolean update(RoleEntity entity) {
        return dao.getRoleEntityDAO().update(entity);
    }

    @Override
    public boolean delete(RoleEntity entity) {
        return dao.getRoleEntityDAO().delete(entity);
    }
}
