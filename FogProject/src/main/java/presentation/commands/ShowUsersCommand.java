package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.MapperExceptions;
import data.models.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.UserFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class ShowUsersCommand implements Command{
    private String target;
    UserFacade uf;

    public ShowUsersCommand(String target) {
        this.target = target;
        uf = new UserFacade();
    }
    
    

    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        HttpSession session = request.getSession();
        try {
            List<User> users = uf.getAll();
            session.setAttribute("users", users);
        } catch (MapperExceptions ex) {
            ex.printStackTrace();
            throw new CommandExceptions("Could not find users from database");
        }
        return target;
    }

}
