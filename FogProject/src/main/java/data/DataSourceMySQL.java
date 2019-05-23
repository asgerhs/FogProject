package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Andreas Vikke
 */
public class DataSourceMySQL {
    private MysqlDataSource dataSource = new MysqlDataSource();
    
    /**
     * Generates a new MySQL Datasource
     */
    public DataSourceMySQL()
    {
        try
        {
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("fogproject");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setUseSSL(false);
            dataSource.setServerTimezone("UTC");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * @return the generated Java DataSource
     */
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}
