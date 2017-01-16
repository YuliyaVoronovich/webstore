package com.voronovich.service;

import com.voronovich.service.impl.*;

/**
 * Class implements singleton pattern for getting necessary service
 *
 * @author Dmitry V
 * @version 1.0
 */
public class Service {

    private static Service service;

    private RoleService roleService;
    private UsersService userService;
    private CatalogService catalogService;
    private MainInfoService mainInfoService;
    private MainInfoViewService mainInfoViewService;
    private AdditionalInfoService additionalInfoService;
    private BasketService basketService;

    /**
     * Method returns exemplar of service
     *
     * @return object DAO
     */
    public static Service getService() {
        if (service == null) {
            synchronized (Service.class) {
                if (service == null) {
                    service = new Service();
                    service.roleService = new RoleService();
                    service.userService = new UsersService();
                    service.catalogService = new CatalogService();
                    service.mainInfoService = new MainInfoService();
                    service.mainInfoViewService = new MainInfoViewService();
                    service.additionalInfoService = new AdditionalInfoService();
                    service.basketService = new BasketService();
                }
            }
        }
        return service;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public UsersService getUserService() {
        return userService;
    }

    public CatalogService getCatalogService() {
        return catalogService;
    }

    public MainInfoService getMainInfoService() {
        return mainInfoService;
    }

    public MainInfoViewService getMainInfoViewService() {
        return mainInfoViewService;
    }

    public AdditionalInfoService getAdditionalInfoService() {
        return additionalInfoService;
    }

    public BasketService getBasketService() {
        return basketService;
    }

}