package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.UsersException;
import data.models.CommandTarget;
import data.models.RoleEnum;
import data.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.UserFacade;

/**
 *
 * @author Andreas Vikke
 */
public class AddUserCommand implements Command {

    private String target;
    private UserFacade uf;

    public AddUserCommand(String target) {
        this.target = target;
        uf = new UserFacade();
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        try {
            User user = new User(
                    request.getParameter("email"),
                    request.getParameter("password"),
                    RoleEnum.CUSTOMER,
                    request.getParameter("name"),
                    request.getParameter("address"),
                    request.getParameter("zipCity"),
                    request.getParameter("phone"));
            uf.add(user);
            uf.validateUser(request.getParameter("email"), request.getParameter("password"));
            session.setAttribute("user", user);
        } catch (UsersException ex) {
            ex.printStackTrace();
            throw new CommandException("Error user already exists!");
        }
        CommandTarget ct = new CommandTarget(target, "Success user added");
        ct.setAjaxRedirect();
        return ct;
    }

}
