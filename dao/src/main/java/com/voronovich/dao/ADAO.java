package com.voronovich.dao;

import org.apache.log4j.Logger;
import com.voronovich.connection.ConnectionCreator;

import java.sql.*;

/**
 * Class contains method that includes standard operations with entities
 *
 * @author Dmitry V
 * @version 1.0
 */
public abstract class ADAO {

    private static Logger log = Logger.getLogger(ADAO.class);

    /**
     * Custom command for operations Create, Update, Delete
     *
     * @param sql Request sql for create, update, delete
     * @return result of operation create/update/delete
     * @throws SQLException
     */
    protected int executeUpdate(String sql) {
        int resultQuery = -1;
        try (Connection connection = ConnectionCreator.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            resultQuery = preparedStatement.executeUpdate();
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                ResultSet resultSet = preparedStatement.executeQuery("SELECT LAST_INSERT_ID();");
                if (resultSet.next()) {
                    resultQuery = resultSet.getInt(1);
                }
                resultSet.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            log.error("Error connection or sql operation!" + e);
        }
        return resultQuery;
    }
}
