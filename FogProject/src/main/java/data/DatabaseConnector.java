package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Andreas Vikke
 */
public class DatabaseConnector {

    private DataSource dataSource;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DatabaseConnector() {
    }

    public DatabaseConnector setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public synchronized Connection open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }

        return connection;
    }

    public void close() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }

        if (statement != null) {
            statement.close();
        }

        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    public PreparedStatement preparedStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    public PreparedStatement preparedStatement(String sql, int indicator) throws SQLException {
        return connection.prepareStatement(sql, indicator);
    }
}
