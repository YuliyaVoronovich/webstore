package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.entity.RoleEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - RoleEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class RoleEntityDAO extends ADAO implements IDAO<RoleEntity> {

    private static Logger log = Logger.getLogger(RoleEntityDAO.class);

    @Override
    public List<RoleEntity> get() {
        String sql = "SELECT * FROM role ;";
        List<RoleEntity> role = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(rs.getInt("ID"));
                roleEntity.setName(rs.getString("Name"));
                role.add(roleEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return role;
    }

    @Override
    public RoleEntity get(int id) {
        RoleEntity roleEntity = new RoleEntity();
        String sql = "SELECT * FROM role WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                roleEntity.setId(rs.getInt("ID"));
                roleEntity.setName(rs.getString("Name"));
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return roleEntity;
    }

    @Override
    public boolean create(RoleEntity roleEntity) {
        String sql = String.format(
                "insert INTO role(Name) values('%s');", roleEntity.getName()
        );
        synchronized (RoleEntityDAO.class) {
            roleEntity.setId(executeUpdate(sql));
        }
        if (roleEntity.getId() > 0) {
            log.info("Role has been created successfully: " + roleEntity);
        }
        return (roleEntity.getId() > 0);
    }

    @Override
    public boolean update(RoleEntity roleEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `role` SET `Name` = '%s' WHERE `role`.`ID` = %d",
                roleEntity.getName(), roleEntity.getId()
        );
        synchronized (RoleEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Role has been updated successfully: " + roleEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(RoleEntity roleEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `role` WHERE `role`.`ID` = %d;", roleEntity.getId()
        );
        synchronized (RoleEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("Role has been deleted successfully: " + roleEntity);
        }
        return (0 < resultQuery);
    }
}
