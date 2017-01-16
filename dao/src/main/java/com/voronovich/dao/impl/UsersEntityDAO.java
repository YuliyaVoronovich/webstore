package com.voronovich.dao.impl;

import org.apache.log4j.Logger;
import com.voronovich.dao.ADAO;
import com.voronovich.dao.IDAO;
import com.voronovich.dao.IUsersEntity;
import com.voronovich.entity.UsersEntity;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Class implements part of the DAO - UsersEntity
 *
 * @author Dmitry V
 * @version 1.0
 */
public class UsersEntityDAO extends ADAO implements IDAO<UsersEntity>, IUsersEntity {

    private static Logger log = Logger.getLogger(UsersEntityDAO.class);

    /**
     * Method contains common realization for several methods
     *
     * @param rs          Object ResultSet
     * @param usersEntity Entity UserEntity
     * @return UserEntity
     * @throws SQLException
     */
    private UsersEntity getUsersEntity(ResultSet rs, UsersEntity usersEntity) throws SQLException {
        usersEntity.setId(rs.getInt("ID"));
        usersEntity.setName(rs.getString("Name"));
        usersEntity.setSurname(rs.getString("Surname"));
        usersEntity.setEmail(rs.getString("Email"));
        usersEntity.setLogin(rs.getString("Login"));
        usersEntity.setPassword(rs.getString("Password"));
        usersEntity.setSalt(rs.getString("Salt"));
        usersEntity.setBlackList(rs.getString("BlackList"));
        usersEntity.setFk_Role(rs.getInt("FK_Role"));
        return usersEntity;
    }

    /**
     * Method contains common realization for several methods
     *
     * @param element     variable value
     * @param sql         command
     * @param usersEntity UserEntity
     * @return
     */
    private UsersEntity getEntity(String element, String sql, UsersEntity usersEntity) {
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, element);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getUsersEntity(rs, usersEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return usersEntity;
    }

    @Override
    public UsersEntity get(String login, String password) {
        UsersEntity usersEntity = new UsersEntity();
        String sql = "SELECT * FROM users WHERE Login = ? AND Password = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getUsersEntity(rs, usersEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return usersEntity;
    }

    @Override
    public UsersEntity get(String login) {
        UsersEntity usersEntity = new UsersEntity();
        String sql = "SELECT * FROM users WHERE Login = ? ;";
        getEntity(login, sql, usersEntity);
        return usersEntity;
    }

    @Override
    public UsersEntity getByEmail(String email) {
        UsersEntity usersEntity = new UsersEntity();
        String sql = "SELECT * FROM users WHERE Email = ? ;";
        getEntity(email, sql, usersEntity);
        return usersEntity;
    }

    @Override
    public UsersEntity get(int id) {
        UsersEntity usersEntity = new UsersEntity();
        String sql = "SELECT * FROM users WHERE ID = ? ;";
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                getUsersEntity(rs, usersEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return usersEntity;
    }

    @Override
    public List<UsersEntity> get() {
        String sql = "SELECT * FROM users ;";
        List<UsersEntity> users = new ArrayList<>();
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UsersEntity usersEntity = new UsersEntity();
                getUsersEntity(rs, usersEntity);
                users.add(usersEntity);
            }
            rs.close();
            preparedStatement.close();
        } catch (SQLException e) {
            log.error(e);
        }
        return users;
    }

    @Override
    public boolean create(UsersEntity usersEntity) {
        String sql = String.format(
                "INSERT INTO users(Name,Surname,Email,Login,Password,Salt,BlackList,FK_Role)" +
                        " values('%s','%s','%s','%s','%s','%s','%s',%d);",
                usersEntity.getName(), usersEntity.getSurname(), usersEntity.getEmail(),
                usersEntity.getLogin(), usersEntity.getPassword(), usersEntity.getSalt(),
                usersEntity.getBlackList(), usersEntity.getFk_Role()
        );
        synchronized (UsersEntityDAO.class) {
            usersEntity.setId(executeUpdate(sql));
        }
        if (usersEntity.getId() > 0) {
            log.info("User has been created successfully: " + usersEntity);
        }
        return (usersEntity.getId() > 0);
    }

    @Override
    public boolean update(UsersEntity usersEntity) {
        int resultQuery;
        String sql = String.format(
                "UPDATE `users` SET `Name` = '%s',`Surname` = '%s' , `Email` = '%s', `Login` = '%s', `Password` = '%s', `Salt` = '%s', `BlackList` = '%s',`FK_Role` = '%d' WHERE `users`.`ID` = %d",
                usersEntity.getName(), usersEntity.getSurname(), usersEntity.getEmail(),
                usersEntity.getLogin(), usersEntity.getPassword(), usersEntity.getSalt(),
                usersEntity.getBlackList(), usersEntity.getFk_Role(), usersEntity.getId()
        );
        synchronized (UsersEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("User has been updated successfully: " + usersEntity);
        }
        return (0 < resultQuery);
    }

    @Override
    public boolean delete(UsersEntity usersEntity) {
        int resultQuery;
        String sql = String.format(
                "DELETE FROM `users` WHERE `users`.`ID` = %d;", usersEntity.getId()
        );
        synchronized (UsersEntityDAO.class) {
            resultQuery = executeUpdate(sql);
        }
        if (resultQuery > 0) {
            log.info("User has been deleted successfully: " + usersEntity);
        }
        return (0 < resultQuery);
    }
}
