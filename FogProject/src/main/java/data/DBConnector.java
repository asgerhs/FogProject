package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Asger Hermind Sørensen
 */
public class DBConnector {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://INDSÆTSERVERIPHER:INDSÆTPORT/DBNAVN";
    public static final String USER = "indsæt-Brugernavn";
    public static final String PASSWORD = "???????";
    public static Connection  connection = null;
    
    public static Connection getConnection(){
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
