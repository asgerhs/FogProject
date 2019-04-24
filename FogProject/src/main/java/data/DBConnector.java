package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
    
//                    private static final String IP	     = "localhost";
//	private static final String PORT     = "3306";
//	public  static final String DATABASE = "delfinen"; 
//	private static final String USERNAME = "root"; 
//	private static final String PASSWORD = "123";	          
//          
//  private Connection connection = null;
//	
//	public DBConnector() throws SQLException {
//            try {
//                Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//                String url = "jdbc:mysql://" + IP + ":" + PORT + "/" + DATABASE;
//                Properties props = new Properties();
//                props.put("user", USERNAME);
//                props.put("password", PASSWORD);
//                props.put("allowMultiQueries", true);
//                props.put("useUnicode", true);
//                props.put("useJDBCCompliantTimezoneShift", true);
//                props.put("useLegacyDatetimeCode", false);
//                props.put("serverTimezone", "CET");
//                this.connection = DriverManager.getConnection(url, props);
//            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
//                ex.printStackTrace();
//                throw new SQLException(ex.getMessage());
//            }
//	}
//	
//	public Connection getConnection() {
//   		return this.connection;
//	}
//}
