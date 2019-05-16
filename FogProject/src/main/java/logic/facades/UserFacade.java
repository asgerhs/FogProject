package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.UsersException;
import data.mappers.UserMapper;
import data.models.User;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class UserFacade implements FacadeInterface<User, String>{
    UserMapper um = new UserMapper(new DataSourceMySQL().getDataSource());
    
    @Override
    public List getAll() throws UsersException {
        return um.getAll();
    }

    @Override
    public User getById(String email) throws UsersException {
        return um.getById(email);
    }

    public boolean validateUser(String username, String password) throws UsersException {
        return um.validateUser(username, password);
    }
    
    public int changePassword(String username, String password) throws UsersException {
        return um.changePassword(username, password);
    }
    public static void main(String[] args) throws UsersException {
        UserFacade uf = new UserFacade();
        List<User> users = uf.getAll();
        for(User u : users){
            System.out.println(u.getEmail());
        }
    }
}
