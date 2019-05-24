package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.UsersException;
import data.mappers.UserMapper;
import data.models.RoleEnum;
import data.models.User;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class UserFacade implements FacadeInterface<User, String> {
    UserMapper um;
    
    public UserFacade() {
        um = new UserMapper(new DataSourceMySQL().getDataSource());
    }
    public UserFacade(DataSource ds) {
        um = new UserMapper(ds);
    }

    @Override
    public List getAll() throws UsersException {
        return um.getAll();
    }

    @Override
    public User getSingle(String email) throws UsersException {
        return um.getSingle(email);
    }

    public boolean validateUser(String email, String password) throws UsersException {
        return um.validateUser(email, encryptPassword(password));
    }

    public int changePassword(String email, String password) throws UsersException {
        return um.changePassword(email, encryptPassword(password));
    }
    
    public int changeUserRole(String email, RoleEnum role) throws UsersException {
        return um.changeUserRole(email, role);
    }
    
    public void add(User user) throws UsersException {
        user.setPassword(encryptPassword(user.getPassword()));
        um.add(user);
    }

    public void remove(String email) throws UsersException {
        um.remove(email);
    }
    
    public static String encryptPassword(String password) throws UsersException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            throw new UsersException("Encryption failed");
        }
    }
}