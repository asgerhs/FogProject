package data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 *
 * @author Andreas Vikke
 */
public class DatabaseConnector {

    private DataSource dataSource;
    private Connection connection;

    /**
     * Sets the DataSource of Connector
     * @param dataSource The DataSource to set
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new connection to the DataSource if closed
     * @return new or current DataSource
     * @throws SQLException 
     */
    public Connection open() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }

        return connection;
    }
}
