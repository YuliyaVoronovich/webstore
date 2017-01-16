package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.entity.MainInfoEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - MainInfoEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class MainInfoEntityDAO extends ADAO implements IDAO<MainInfoEntity> {

    private static Logger log = Logger.getLogger(MainInfoEntityDAO.class);

    /**
     * Method contains common realization for several methods
     *
     * @param resultSet      Object ResultSet
     * @param mainInfoEntity Entity MainInfoEntity
     * @return MainInfoEntity
     * @throws SQLException
     */
    private MainInfoEntity getMainInfoEntity(ResultSet resultSet, MainInfoEntity mainInfoEntity) throws SQLException {
        mainInfoEntity.setId(resultSet.getInt("ID"));
        mainInfoEntity.setBrand(resultSet.getString("Brand"));
        mainInfoEntity.setModel(resultSet.getString("Model"));
        mainInfoEntity.setPrice(resultSet.getDouble("Price"));
        mainInfoEntity.setReleaseDate(resultSet.getString("ReleaseDate"));
        mainInfoEntity.setImg(resultSet.getString("Picture"));
        mainInfoEntity.setFk_Catalog(resultSet.getInt("FK_Catalog"));
        return mainInfoEntity;
    }

    @Override
    public List<MainInfoEntity> get() {
        String sql = "SELECT * FROM mainInfo ;";
        List<MainInfoEntity> mainInfo = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                MainInfoEntity mainInfoEntity = new MainInfoEntity();
                getMainInfoEntity(rs, mainInfoEntity);
                mainInfo.add(mainInfoEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return mainInfo;
    }

    @Override
    public MainInfoEntity get(int id) {
        MainInfoEntity mainInfoEntity = new MainInfoEntity();
        String sql = "SELECT * FROM mainInfo WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getMainInfoEntity(rs, mainInfoEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return mainInfoEntity;
    }

    @Override
    public boolean create(MainInfoEntity mainInfoEntity) {
        String sql = String.format(
                "INSERT INTO mainInfo(Brand,Model,Price,ReleaseDate,Picture,FK_Catalog) values('%s','%s','%s','%s','%s',%d);",
                mainInfoEntity.getBrand(), mainInfoEntity.getModel(), mainInfoEntity.getPrice(),
                mainInfoEntity.getReleaseDate(), mainInfoEntity.getImg(), mainInfoEntity.getFk_Catalog()
        );
        synchronized (MainInfoEntityDAO.class) {
            mainInfoEntity.setId(executeUpdate(sql));
        }
        if (mainInfoEntity.getId() > 0) {
            log.info("Main information has been created successfully: " + mainInfoEntity);
        }
        return (mainInfoEntity.getId() > 0);
    }

    @Override
    public boolean update(MainInfoEntity mainInfoEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `mainInfo` SET `Brand` = '%s', `Model` = '%s'," +
                        "`Price` = '%s',`ReleaseDate` = '%s', `Picture` = '%s', `FK_Catalog` = %d WHERE `mainInfo`.`ID` = %d",
                mainInfoEntity.getBrand(), mainInfoEntity.getModel(), mainInfoEntity.getPrice(),
                mainInfoEntity.getReleaseDate(), mainInfoEntity.getImg(), mainInfoEntity.getFk_Catalog(), mainInfoEntity.getId()
        );
        synchronized (MainInfoEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (mainInfoEntity.getId() > 0) {
            log.info("Main information has been updated successfully: " + mainInfoEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(MainInfoEntity mainInfoEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `mainInfo` WHERE `mainInfo`.`ID` = %d;", mainInfoEntity.getId()
        );
        synchronized (MainInfoEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (mainInfoEntity.getId() > 0) {
            log.info("Main information has been deleted successfully: " + mainInfoEntity);
        }
        return (0 < resultQuery);
    }
}
