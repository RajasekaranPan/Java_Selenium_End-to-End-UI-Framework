package database;

import config.ConfigReader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public final class DatabaseManager {

    private static HikariDataSource dataSource;

    private DatabaseManager() {
    }

    public static void initialize() {

        if (dataSource != null) {
            return;
        }

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(ConfigReader.getDbUrl());
        config.setUsername(ConfigReader.getDbUsername());
        config.setPassword(ConfigReader.getDbPassword());

        config.setMaximumPoolSize(
                ConfigReader.getDbMaximumPoolSize()
        );

        config.setMinimumIdle(
                ConfigReader.getDbMinimumIdle()
        );

        config.setConnectionTimeout(
                ConfigReader.getDbConnectionTimeout()
        );

        config.setIdleTimeout(
                ConfigReader.getDbIdleTimeout()
        );

        config.setMaxLifetime(
                ConfigReader.getDbMaxLifetime()
        );

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() {

        if (dataSource == null) {
            initialize();
        }

        try {
            return dataSource.getConnection();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to obtain database connection",
                    e
            );
        }
    }

    public static void closePool() {

        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }
    }
    
    private static void setParameters(
            PreparedStatement statement,
            Object... parameters)
            throws SQLException {

        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
    
    public static ResultSet executeQuery(
            String sql,
            Object... parameters) {

        try {
            Connection connection = getConnection();

            PreparedStatement statement =
                    connection.prepareStatement(sql);

            setParameters(statement, parameters);

            return statement.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to execute query: " + sql,
                    e
            );
        }
    }
    
    public static int executeUpdate(
            String sql,
            Object... parameters) {

        try (Connection connection = getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            setParameters(statement, parameters);

            return statement.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(
                    "Failed to execute update: " + sql,
                    e
            );
        }
    }
    
    
}