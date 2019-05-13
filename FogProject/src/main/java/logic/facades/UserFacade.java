package logic.facades;

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
    UserMapper um = new UserMapper();
    
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
    
    public static void main(String[] args) throws MapperExceptions {
        UserFacade uf = new UserFacade();
        List<User> users = new ArrayList();
        User user = uf.getById("ALBERT");
        System.out.println(user.getEmail());
        uf.changePassword(user.getUsername(), "123456");
        System.out.println(uf.validateUser(user.getUsername(), "123456"));
        /*users = uf.getAll();
        for(User u : users){
            System.out.println(u.getEmail());
        }*/
        
    }
    
}
