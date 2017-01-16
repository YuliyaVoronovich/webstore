package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.dao.IBasketEntity;
import com.voronovich.entity.BasketEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - BasketEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class BasketEntityDAO extends ADAO implements IDAO<BasketEntity>, IBasketEntity {

    private static Logger log = Logger.getLogger(BasketEntityDAO.class);

    /**
     * Method contains common realization for several methods
     *
     * @param resultSet    Object ResultSet
     * @param basketEntity Object BasketEntity
     * @return BasketEntity
     * @throws SQLException
     */
    private BasketEntity getBasket(ResultSet resultSet, BasketEntity basketEntity) throws SQLException {
        basketEntity.setId(resultSet.getInt("ID"));
        basketEntity.setFk_Users(resultSet.getInt("FK_Users"));
        basketEntity.setFk_MainInfo(resultSet.getInt("FK_MainInfo"));
        return basketEntity;
    }

    /**
     * Method contains common realization for several methods
     *
     * @param element      variable value
     * @param sql          command
     * @param basketEntity BasketEntity
     * @return BasketEntity
     */
    private BasketEntity getCode(int element, String sql, BasketEntity basketEntity) {
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, element);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getBasket(rs, basketEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return basketEntity;
    }

    @Override
    public List<BasketEntity> getByUser(int id) {
        String sql = "SELECT * FROM basket WHERE FK_Users = ? ;";
        List<BasketEntity> basket = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BasketEntity basketEntity = new BasketEntity();
                getBasket(rs, basketEntity);
                basket.add(basketEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return basket;
    }

    @Override
    public List<BasketEntity> get() {
        String sql = "SELECT * FROM basket ;";
        List<BasketEntity> basket = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                BasketEntity basketEntity = new BasketEntity();
                getBasket(rs, basketEntity);
                basket.add(basketEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return basket;
    }

    @Override
    public BasketEntity getByGood(int id) {
        BasketEntity basketEntity = new BasketEntity();
        String sql = "SELECT * FROM basket WHERE FK_MainInfo = ? ;";
        getCode(id, sql, basketEntity);
        return basketEntity;
    }

    @Override
    public BasketEntity get(int id) {
        BasketEntity basketEntity = new BasketEntity();
        String sql = "SELECT * FROM basket WHERE ID = ? ;";
        getCode(id, sql, basketEntity);
        return basketEntity;
    }

    @Override
    public boolean create(BasketEntity basketEntity) {
        String sql = String.format(
                "INSERT INTO basket(FK_Users,FK_MainInfo) values(%d,%d);",
                basketEntity.getFk_Users(), basketEntity.getFk_MainInfo()
        );
        synchronized (BasketEntityDAO.class) {
            basketEntity.setId(executeUpdate(sql));
        }
        if (basketEntity.getId() > 0) {
            log.info("Order has been created successfully: " + basketEntity);
        }
        return (basketEntity.getId() > 0);
    }

    @Override
    public boolean update(BasketEntity basketEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `basket` SET `FK_Users` = %d," +
                        "`FK_MainInfo` = %d WHERE `basket`.`ID` = %d",
                basketEntity.getFk_Users(), basketEntity.getFk_MainInfo(), basketEntity.getId()
        );
        synchronized (BasketEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Order has been updated successfully: " + basketEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(BasketEntity basketEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `basket` WHERE `basket`.`ID` = %d;", basketEntity.getId()
        );
        synchronized (BasketEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Order has been deleted successfully: " + basketEntity);
        }
        return (0 < resultQuery);
    }
}