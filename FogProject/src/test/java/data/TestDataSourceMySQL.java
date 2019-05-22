package data;

import com.mysql.cj.jdbc.MysqlDataSource;

/**
 *
 * @author Andreas Vikke
 */
public class TestDataSourceMySQL {
    private MysqlDataSource dataSource = new MysqlDataSource();
    
    public TestDataSourceMySQL()
    {
        try
        {
            dataSource.setServerName("localhost");
            dataSource.setPort(3306);
            dataSource.setDatabaseName("fogprojectTest");
            dataSource.setUser("root");
            dataSource.setPassword("root");
            dataSource.setUseSSL(false);
            dataSource.setAllowMultiQueries(true);
            dataSource.setServerTimezone("UTC");
            dataSource.setAllowPublicKeyRetrieval(true);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public MysqlDataSource getDataSource()
    {
        return dataSource;
    }
}
