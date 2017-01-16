package com.voronovich.dao;

import com.voronovich.dao.impl.*;

/**
 * Class implements singleton pattern for getting necessary dao
 *
 * @author Dmitry V
 * @version 1.0
 */
public class DAO {

    private static DAO dao;

    private RoleEntityDAO roleEntityDAO;
    private UsersEntityDAO userEntityDAO;
    private CatalogEntityDAO catalogEntityDAO;
    private MainInfoEntityDAO mainInfoEntityDAO;
    private AdditionalInfoEntityDAO additionalInfoEntityDAO;
    private BasketEntityDAO basketEntityDAO;
    private MainInfoViewEntityDAO mainInfoViewEntityDAO;

    /**
     * Method returns exemplar of dao
     *
     * @return object DAO
     */
    public static DAO getDAO() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.roleEntityDAO = new RoleEntityDAO();
                    dao.userEntityDAO = new UsersEntityDAO();
                    dao.catalogEntityDAO = new CatalogEntityDAO();
                    dao.mainInfoEntityDAO = new MainInfoEntityDAO();
                    dao.additionalInfoEntityDAO = new AdditionalInfoEntityDAO();
                    dao.basketEntityDAO = new BasketEntityDAO();
                    dao.mainInfoViewEntityDAO = new MainInfoViewEntityDAO();
                }
            }
        }
        return dao;
    }

    public RoleEntityDAO getRoleEntityDAO() {
        return roleEntityDAO;
    }

    public UsersEntityDAO getUsersEntityDAO() {
        return userEntityDAO;
    }

    public CatalogEntityDAO getCatalogEntityDAO() {
        return catalogEntityDAO;
    }

    public MainInfoEntityDAO getMainInfoEntityDAO() {
        return mainInfoEntityDAO;
    }

    public AdditionalInfoEntityDAO getAdditionalInfoEntityDAO() {
        return additionalInfoEntityDAO;
    }

    public BasketEntityDAO getBasketEntityDAO() {
        return basketEntityDAO;
    }

    public MainInfoViewEntityDAO getMainInfoViewEntityDAO() {
        return mainInfoViewEntityDAO;
    }
}
