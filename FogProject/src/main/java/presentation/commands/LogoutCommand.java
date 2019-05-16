package presentation.commands;

import data.exceptions.CommandException;
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
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        session.invalidate();
        return target;
    }

}
