package com.voronovich.service;

import java.util.List;

/**
 * Interface contains abstract methods for service MainInfoViewService
 *
 * @author Dmitry V
 * @version 1.0
 */
public interface IMainInfoViewService {

    /**
     * Method returns list of characteristics of goods by it's id
     *
     * @param id
     * @return list
     */
    List<String> getMainInfoView(int id);
}
