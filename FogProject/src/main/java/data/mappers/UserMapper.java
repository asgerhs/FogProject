package data.mappers;

import data.DataSourceMySQL;
import data.DatabaseConnector;
import data.exceptions.UsersException;
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
import javax.sql.DataSource;

/**
 *
 * @author Martin Frederiksen
 */
public class UserMapper implements MapperInterface<User, String> {
    
    DatabaseConnector dbc = new DatabaseConnector();
    
    public UserMapper(DataSource ds) {
        dbc.setDataSource(ds);
    }

    UserMapper() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getAll() throws UsersException {
        try (Connection con = dbc.open()) {
            List<User> users = new ArrayList();
            String qry = "SELECT * FROM accounts;";
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(qry);

            while (rs.next()) {
                users.add(new User(rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role")),
                        rs.getString("name"), 
                        rs.getString("address"), 
                        rs.getString("zipCity"), 
                        rs.getString("phone")));
            }
            return users;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UsersException("Error occoured while getting data from database");
        }
    }

    @Override
    public User getById(String email) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "SELECT * FROM accounts WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                if(email.equalsIgnoreCase(rs.getString("email"))){
                    return new User(rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role")),
                        rs.getString("name"), 
                        rs.getString("address"), 
                        rs.getString("zipCity"), 
                        rs.getString("phone"));
                }
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UsersException("Error occoured while getting data from database");
        }
    }
    
    public void add(User user) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "INSERT INTO accounts "
                    + "VALUES (?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole().toString());
            ps.setString(4, user.getName());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getZipCity());
            ps.setString(7, user.getPhone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            // TODO: Add Logger
            ex.printStackTrace();
            throw new UsersException("Error occoured while adding data to database");
        }
    }
    
    public boolean validateUser(String email, String password) throws UsersException {
       try(Connection con = dbc.open()){
            String qry = "SELECT email FROM accounts WHERE (email = ?) AND password = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean valid = false;
            while (rs.next()) {
                if (email.equalsIgnoreCase(rs.getString("email"))) {
                    valid = true;
                }
            }
            return valid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UsersException("Error occoured while validating user");
        }
    }
    
    public int changePassword(String email, String password) throws UsersException {
        try(Connection con = dbc.open()){
            String qry = "UPDATE accounts SET password = ? WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, password);
            ps.setString(2, email);
            con.setAutoCommit(false);
            int result = ps.executeUpdate();
            con.commit();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new UsersException("Error occoured while updating user");
        }
    }
}
