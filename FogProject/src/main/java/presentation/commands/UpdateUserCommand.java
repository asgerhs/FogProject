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
public class UpdateUserCommand implements Command {

    private String target;
    private UserFacade uf;

    public UpdateUserCommand(String target) {
        this.target = target;
        uf = new UserFacade();
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        try {
            User currentUser = (User)request.getSession().getAttribute("user");
            
            if(currentUser.getRole() == RoleEnum.ADMIN) {
                uf.changeUserRole(email, RoleEnum.valueOf(role));
                return new CommandTarget(target, "Role of user updated successfully");
            } else {
                return new CommandTarget(target, "Current user dosn't have access to update user");
            }
            
        } catch (UsersException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }

}
