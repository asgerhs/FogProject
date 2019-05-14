package presentation.commands;

import data.exceptions.CommandExceptions;
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
    public String execute(HttpServletRequest request) throws CommandExceptions {
        HttpSession session = request.getSession();
        session.invalidate();
        return target;
    }

}
