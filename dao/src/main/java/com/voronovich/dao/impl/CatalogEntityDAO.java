package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.entity.CatalogEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - CatalogEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class CatalogEntityDAO extends ADAO implements IDAO<CatalogEntity> {

    private static Logger log = Logger.getLogger(CatalogEntityDAO.class);

    @Override
    public List<CatalogEntity> get() {
        String sql = "SELECT * FROM catalog ;";
        List<CatalogEntity> catalog = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CatalogEntity catalogEntity = new CatalogEntity();
                catalogEntity.setId(rs.getInt("ID"));
                catalogEntity.setDepartment(rs.getString("Department"));
                catalog.add(catalogEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return catalog;
    }

    @Override
    public CatalogEntity get(int id) {
        CatalogEntity catalogEntity = new CatalogEntity();
        String sql = "SELECT * FROM catalog WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                catalogEntity.setId(rs.getInt("ID"));
                catalogEntity.setDepartment(rs.getString("Department"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return catalogEntity;
    }

    @Override
    public boolean create(CatalogEntity catalogEntity) {
        String sql = String.format(
                "INSERT INTO catalog(Department)" +
                        " values('%s');",
                catalogEntity.getDepartment()
        );
        synchronized (CatalogEntityDAO.class) {
            catalogEntity.setId(executeUpdate(sql));
        }
        if (catalogEntity.getId() > 0) {
            log.info("Department has been created successfully: " + catalogEntity);
        }
        return (catalogEntity.getId() > 0);
    }

    @Override
    public boolean update(CatalogEntity catalogEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `catalog` SET `Department` = '%s' WHERE `catalog`.`ID` = %d",
                catalogEntity.getDepartment(), catalogEntity.getId()
        );
        synchronized (CatalogEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Department has been updated successfully: " + catalogEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(CatalogEntity catalogEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `catalog` WHERE `catalog`.`ID` = %d;", catalogEntity.getId()
        );
        synchronized (CatalogEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Department has been deleted successfully: " + catalogEntity);
        }
        return (0 < resultQuery);
    }
}
