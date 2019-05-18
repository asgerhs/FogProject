package presentation.commands;

import data.exceptions.CommandException;
import data.models.CommandTarget;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin Frederiksen
 */
public class LogoutCommand implements Command{
    private String target;

    public LogoutCommand(String target) {
        this.target = target;
    }
    

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.invalidate();
        return new CommandTarget(target, "Logged out Successfully");
    }

}
