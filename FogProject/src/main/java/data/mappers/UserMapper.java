package data.mappers;

import data.DBConnector;
import data.exceptions.MapperExceptions;
import data.interfaces.MapperInterface;
import data.models.RoleEnum;
import data.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements MapperInterface<User, String> {

    @Override
    public List getAll() throws MapperExceptions {
        try (Connection con = DBConnector.getConnection()) {
            List<User> users = new ArrayList();
            String qry = "SELECT * FROM users;";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                users.add(new User(rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role"))));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }

    @Override
    public User getById(String username) throws MapperExceptions {
        try (Connection con = DBConnector.getConnection()) {
            String qry = "SELECT * FROM users WHERE username = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if(username.equalsIgnoreCase(rs.getString("username"))){
                    return new User(rs.getString("username"), 
                            rs.getString("email"), 
                            rs.getString("password"), 
                            RoleEnum.valueOf(rs.getString("role")));
                }
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MapperExceptions("Error occoured while getting data from database");
        }
    }
    
    public boolean validateUser(String username, String password) throws MapperExceptions {
       try(Connection con = DBConnector.getConnection()){
            String qry = "SELECT username, email FROM users WHERE (username = ? OR email = ?)AND password = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, username);
            ps.setString(2, username);
            ps.setString(3, password);
            ResultSet rs = ps.executeQuery();
            boolean valid = false;
            while (rs.next()) {
                if (username.equalsIgnoreCase(rs.getString("username")) || username.equalsIgnoreCase(rs.getString("email"))) {
                    valid = true;
                }
            }
            return valid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new MapperExceptions(ex.getMessage());
        }
    }
    
    public int changePassword(String username, String password) throws MapperExceptions {
        try(Connection con = DBConnector.getConnection()){
            String qry = "UPDATE users SET password = ? WHERE username = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, password);
            ps.setString(2, username);
            con.setAutoCommit(false);
            int result = ps.executeUpdate();
            con.commit();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            /*if (con != null) {
                con.rollback();
            }*/
            throw new MapperExceptions(ex.getMessage());
        }
    }
    

    public static void main(String[] args) throws MapperExceptions {
        UserMapper um = new UserMapper();
        List<User> u = new ArrayList();
        User user = null;
        //user = um.getById("admin");
        //System.out.println(RoleEnum.ADMIN.equals(user.getRole()));
        /*user = um.getById("ALBERT");
        System.out.println(user.getEmail());
        System.out.println(um.validateUser(user.getUsername(), "1234"));
        um.changePassword(user.getUsername(), "12345");
        */
        /*u = um.getAll();
        for (User us : u) {
            System.out.println(us.getUsername());
        }*/
    }
}
