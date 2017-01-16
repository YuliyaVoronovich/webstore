package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.service.IMainInfoViewService;

import java.util.List;

public class MainInfoViewService implements IMainInfoViewService {

    DAO dao = DAO.getDAO();

    @Override
    public List<String> getMainInfoView(int id){
        return  dao.getMainInfoViewEntityDAO().getByID(id);
    }
}
