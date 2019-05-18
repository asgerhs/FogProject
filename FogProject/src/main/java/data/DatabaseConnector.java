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

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }

        return connection;
    }
}
