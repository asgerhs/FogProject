package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class DBConnector {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/fogproject?useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "root";
    public static Connection  connection = null;
    
    public static synchronized Connection getConnection(){
           try{
               Class.forName(DRIVER);
               connection = DriverManager.getConnection(URL, USER, PASSWORD);
           }catch(SQLException | ClassNotFoundException ex){
               ex.printStackTrace();
           }
       return connection;
    }
    
 





}
