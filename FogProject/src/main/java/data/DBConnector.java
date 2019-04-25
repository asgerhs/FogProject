package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class DBConnector {
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://157.230.110.215:3306/fogproject";
    public static final String USER = "root";
    public static final String PASSWORD = "123";
    public static Connection  connection = null;
    
    public static synchronized Connection getConnection(){
       if(connection == null){
           try{
               Class.forName(DRIVER);
               connection = DriverManager.getConnection(URL, USER, PASSWORD);
           }catch(SQLException | ClassNotFoundException ex){
               ex.printStackTrace();
           }
       }
       return connection;
    }
    
 





}
