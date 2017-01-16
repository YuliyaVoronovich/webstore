package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.entity.UsersEntity;
import com.voronovich.service.IService;
import com.voronovich.service.IUsersService;

import java.util.List;

public class UsersService implements IService<UsersEntity>,IUsersService {

    DAO dao = DAO.getDAO();

    @Override
    public UsersEntity get(String login, String password){
        return dao.getUsersEntityDAO().get(login,password);
    }

    @Override
    public UsersEntity get(String login){
        return  dao.getUsersEntityDAO().get(login);
    }

    @Override
    public UsersEntity getByEmail(String email){
        return  dao.getUsersEntityDAO().getByEmail(email);
    }

    @Override
    public List<UsersEntity> get() {
        return dao.getUsersEntityDAO().get();
    }

    @Override
    public UsersEntity get(int id) {
        return dao.getUsersEntityDAO().get(id);
    }

    @Override
    public boolean create(UsersEntity entity) {
        return dao.getUsersEntityDAO().create(entity);
    }

    @Override
    public boolean update(UsersEntity entity) {
        return dao.getUsersEntityDAO().update(entity);
    }

    @Override
    public boolean delete(UsersEntity entity) {
        return dao.getUsersEntityDAO().delete(entity);
    }
}
