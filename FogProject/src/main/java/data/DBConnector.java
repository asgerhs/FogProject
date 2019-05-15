package data;

import data.mappers.MaterialMapper;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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

    
    private static Logger logger = Logger.getLogger(DBConnector.class.getName());
     
    public DBConnector() {
        try {

            FileHandler handler = new FileHandler("logs/DBConnector/DBConnector-log.%u.%g.txt",
                    1024 * 1024, 10);
            logger.addHandler(handler);

            handler.setFormatter(new SimpleFormatter());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error in logger", new IOException("Error: "));

        }
    }
    
    
    
    public static synchronized Connection getConnection(){
           try{
               Class.forName(DRIVER);
               connection = DriverManager.getConnection(URL, USER, PASSWORD);
           }catch(SQLException | ClassNotFoundException ex){
               logger.log(Level.SEVERE, "Error in DBConnector", ex );
           }
       return connection;
    }
    
 





}
