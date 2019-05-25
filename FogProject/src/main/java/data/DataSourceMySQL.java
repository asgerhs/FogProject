package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Andreas Vikke
 */
public class DataSourceMySQL {
    private MysqlDataSource dataSource = new MysqlDataSource();
    private MysqlDataSource dataSourceLive = new MysqlDataSource();
    
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
            dataSource.setAllowPublicKeyRetrieval(true);
            
            dataSourceLive.setServerName("andreasvikke.dk");
            dataSourceLive.setPort(3306);
            dataSourceLive.setDatabaseName("fogproject");
            dataSourceLive.setUser("transformer");
            dataSourceLive.setPassword("f7qGtArm");
            dataSourceLive.setUseSSL(false);
            dataSourceLive.setServerTimezone("UTC");
            dataSourceLive.setAllowPublicKeyRetrieval(true);
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
