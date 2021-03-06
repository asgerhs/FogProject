package data.mappers;

import data.DatabaseConnector;
import data.ExceptionLogger;
import data.exceptions.UsersException;
import data.interfaces.MapperInterface;
import data.models.LoggerEnum;
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

    /**
     * Gets all Users from the database
     * @return List of User
     * @throws UsersException UsersException
     */
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
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in getAll Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while getting data from database");
        }
    }

    /**
     * Gets single user from the database
     * @param email email of user
     * @return Single User
     * @throws UsersException UsersException
     */
    @Override
    public User getSingle(String email) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "SELECT * FROM accounts WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return new User(rs.getString("email"),
                        rs.getString("password"),
                        RoleEnum.valueOf(rs.getString("role")),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getString("zipCity"),
                        rs.getString("phone"));
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in getSingle Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while getting data from database");
        }
    }

    /**
     * Adds new user to the database
     * @param user User to be added
     * @throws UsersException UsersException
     */
    @Override
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
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in add Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while adding data to database");
        }
    }

    /**
     * Validates user by email and password
     * @param email Email of user
     * @param password Password of user
     * @return true/false
     * @throws UsersException UsersException
     */
    public boolean validateUser(String email, String password) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "SELECT email FROM accounts WHERE (email = ?) AND password = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            boolean valid = false;
            while (rs.next()) {
                    valid = true;
            }
            return valid;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in validateUser Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while validating user");
        }
    }

    /**
     * Changes password of specific user
     * @param email Email of user
     * @param password New password for user
     * @return -1/0/1
     * @throws UsersException UsersException
     */
    public int changePassword(String email, String password) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "UPDATE accounts SET password = ? WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, password);
            ps.setString(2, email);
            int result = ps.executeUpdate();
            return result;
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in changePassword Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while updating user");
        }
    }

    /**
     * Changes role of specific user
     * @param email Email of user
     * @param role New role of user
     * @return -1/0/1
     * @throws UsersException UsersException
     */
    public int changeUserRole(String email, RoleEnum role) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "UPDATE accounts SET role = ? WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, role.toString());
            ps.setString(2, email);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in changeUserRole Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error occoured while updating user");
        }
    }

    /**
     * Removes user from the database
     * @param email Email of user
     * @throws UsersException UsersException
     */
    public void remove(String email) throws UsersException {
        try (Connection con = dbc.open()) {
            String qry = "DELETE FROM accounts WHERE email = ?;";
            PreparedStatement ps = con.prepareStatement(qry);
            ps.setString(1, email);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ExceptionLogger.log(LoggerEnum.USERMAPPER, "Error in remove Method: \n" + ex.getMessage(), ex.getStackTrace());
            throw new UsersException("Error while removing request from database");
        }
    }
}
