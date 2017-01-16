package com.voronovich.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class implements pool of connections HicariCP
 *
 * @author Dmitry V
 * @version 1.0
 */
public class ConnectionCreator {

    private static HikariDataSource ds;

    static{
        String db_url = ResourceBundle.getBundle("connection").getString("URL_DB");
        String user = ResourceBundle.getBundle("connection").getString("USER_DB");
        String password = ResourceBundle.getBundle("connection").getString("PASSWORD_DB");

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(db_url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setMaxLifetime(180000);
        config.setIdleTimeout(120000);
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.addDataSourceProperty("useUnicode", "true");
        config.addDataSourceProperty("characterEncoding", "UTF-8");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    /**
     * Method grants connection from pool of connections
     *
     * @return connection
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}