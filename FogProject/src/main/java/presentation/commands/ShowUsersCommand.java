package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MaterialException;
import data.exceptions.UsersException;
import data.models.User;
import java.util.List;
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
            if(request.getParameter("userId") != null){
                uf.remove(request.getParameter("userId")); 
            }        
            List<User> users = uf.getAll();
            session.setAttribute("users", users);
        } catch (UsersException ex) {
            ex.printStackTrace();
            throw new CommandException("Could not find users from database");
        }
        return target;
    }

}
