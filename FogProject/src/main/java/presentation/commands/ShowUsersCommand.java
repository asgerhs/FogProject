package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MapperException;
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
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            List<User> users = uf.getAll();
            session.setAttribute("users", users);
        } catch (MapperException ex) {
            ex.printStackTrace();
            throw new CommandException("Could not find users from database");
        }
        return target;
    }

}
