package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.UsersException;
import data.mappers.UserMapper;
import data.models.RoleEnum;
import data.models.User;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class UserFacade implements FacadeInterface<User, String> {

    UserMapper um = new UserMapper(new DataSourceMySQL().getDataSource());

    @Override
    public List getAll() throws UsersException {
        return um.getAll();
    }

    @Override
    public User getSingle(String email) throws UsersException {
        return um.geSingle(email);
    }

    public boolean validateUser(String email, String password) throws UsersException {
        return um.validateUser(email, password);
    }

    public int changePassword(String email, String password) throws UsersException {
        return um.changePassword(email, password);
    }
    
    public void changeUserRole(String email, RoleEnum role) throws UsersException {
        um.changeUserRole(email, role);
    }

    public void remove(String email) throws UsersException {
        um.remove(email);
    }
}