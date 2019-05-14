package logic.facades;

import data.DataSourceMySQL;
import data.exceptions.MapperExceptions;
import data.mappers.UserMapper;
import data.models.User;
import java.util.ArrayList;
import java.util.List;
import logic.interfaces.FacadeInterface;

/**
 *
 * @author Martin Frederiksen
 */
public class UserFacade implements FacadeInterface<User, String>{
    UserMapper um = new UserMapper(new DataSourceMySQL().getDataSource());
    
    @Override
    public List getAll() throws MapperExceptions {
        return um.getAll();
    }

    @Override
    public User getById(String username) throws MapperExceptions {
        return um.getById(username);
    }

    public boolean validateUser(String username, String password) throws MapperExceptions {
        return um.validateUser(username, password);
    }
    
    public int changePassword(String username, String password) throws MapperExceptions {
        return um.changePassword(username, password);
    }    
}
