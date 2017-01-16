package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.entity.AdditionalInfoEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - AdditionalInfoEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class AdditionalInfoEntityDAO extends ADAO implements IDAO<AdditionalInfoEntity> {

    private static Logger log = Logger.getLogger(AdditionalInfoEntityDAO.class);

    /**
     * Method contains common realization for several methods
     *
     * @param rs                   Object ResultSet
     * @param additionalInfoEntity Object AdditionalInfoEntity
     * @return AdditionalInfoEntity
     * @throws SQLException
     */
    private AdditionalInfoEntity getAdditionalInfoEntity(ResultSet rs, AdditionalInfoEntity additionalInfoEntity) throws SQLException {
        additionalInfoEntity.setId(rs.getInt("ID"));
        additionalInfoEntity.setTitle(rs.getString("Title"));
        additionalInfoEntity.setValue(rs.getString("Value"));
        additionalInfoEntity.setFk_MainInfo(rs.getInt("FK_MainInfo"));
        return additionalInfoEntity;
    }

    @Override
    public List<AdditionalInfoEntity> get() {
        String sql = "SELECT * FROM additionalInfo ;";
        List<AdditionalInfoEntity> additionalInfo = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                AdditionalInfoEntity additionalInfoEntity = new AdditionalInfoEntity();
                getAdditionalInfoEntity(rs, additionalInfoEntity);
                additionalInfo.add(additionalInfoEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return additionalInfo;
    }

    @Override
    public AdditionalInfoEntity get(int id) {
        AdditionalInfoEntity additionalInfoEntity = new AdditionalInfoEntity();
        String sql = "SELECT * FROM additionalInfo WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getAdditionalInfoEntity(rs, additionalInfoEntity);
            }
        } catch (SQLException e) {
            log.error(e);
        }
        return additionalInfoEntity;
    }

    @Override
    public boolean create(AdditionalInfoEntity additionalInfoEntity) {
        String sql = String.format(
                "INSERT INTO additionalInfo(Title,Value,FK_MainInfo) values('%s','%s',%d);",
                additionalInfoEntity.getTitle(), additionalInfoEntity.getValue(), additionalInfoEntity.getFk_MainInfo()
        );
        synchronized (AdditionalInfoEntityDAO.class) {
            additionalInfoEntity.setId(executeUpdate(sql));
        }
        if (additionalInfoEntity.getId() > 0) {
            log.info("Additional information has been created successfully: " + additionalInfoEntity);
        }
        return (additionalInfoEntity.getId() > 0);
    }

    @Override
    public boolean update(AdditionalInfoEntity additionalInfoEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `additionalInfo` SET `Title` = '%s', `Value` = '%s'," +
                        "`FK_MainInfo` = %d WHERE `additionalInfo`.`ID` = %d",
                additionalInfoEntity.getTitle(), additionalInfoEntity.getValue(),
                additionalInfoEntity.getFk_MainInfo(), additionalInfoEntity.getId()
        );
        synchronized (AdditionalInfoEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Additional information has been updated successfully: " + additionalInfoEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(AdditionalInfoEntity additionalInfoEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `additionalInfo` WHERE `additionalInfo`.`ID` = %d;", additionalInfoEntity.getId()
        );
        synchronized (AdditionalInfoEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Additional information has been deleted successfully: " + additionalInfoEntity);
        }
        return (0 < resultQuery);
    }
}