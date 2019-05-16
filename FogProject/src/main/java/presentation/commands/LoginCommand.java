package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MapperException;
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
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        try {
            boolean valid = uf.validateUser(username, password);
            if (valid) {
                User user = uf.getById(username);
                session.setAttribute("user", user);
                if(user.getRole().equals(RoleEnum.ADMIN) || user.getRole().equals(RoleEnum.EMPLOYEE)) return "FrontController?command=requestList";
                return target;
            } else {
                return "index.jsp";
            }

        } catch (MapperException ex) {
            ex.printStackTrace();
            throw new CommandException("Wrong username or password!");
        }
    }

}
