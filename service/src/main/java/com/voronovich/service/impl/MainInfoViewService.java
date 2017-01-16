package com.voronovich.service.impl;

import com.voronovich.dao.DAO;
import com.voronovich.service.IMainInfoViewService;

import java.util.List;

/**
 * Class implements part of the Service layer - MainInfoViewService
 *
 * @author Dmitry V
 * @version 1.0
 */
public class MainInfoViewService implements IMainInfoViewService {

    DAO dao = DAO.getDAO();

    @Override
    public List<String> getMainInfoView(int id){
        return  dao.getMainInfoViewEntityDAO().getByID(id);
    }
}
