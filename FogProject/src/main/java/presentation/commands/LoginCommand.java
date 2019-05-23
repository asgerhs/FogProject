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
 * @author Martin Frederiksen
 */
public class LoginCommand implements Command {

    private String target;
    private UserFacade uf;

    public LoginCommand(String target) {
        this.target = target;
        uf = new UserFacade();
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            if (email != null) {
                boolean valid = uf.validateUser(email, password);
                if (valid) {
                    User user = uf.getSingle(email);
                    session.setAttribute("user", user);
                    if (user.getRole().equals(RoleEnum.ADMIN) || user.getRole().equals(RoleEnum.EMPLOYEE)) {
                        return new CommandTarget("FrontController?command=requestList", "Logged in as Admin/Employee Successfully");
                    }

                    return new CommandTarget(target, "Logged in as Customer Successfully");
                } else {
                    return new CommandTarget("WEB-INF/index.jsp", "Wrong email or password");
                }
            } else {
                return new CommandTarget("WEB-INF/index.jsp", "Redirect to login");
            }

        } catch (UsersException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }

}
