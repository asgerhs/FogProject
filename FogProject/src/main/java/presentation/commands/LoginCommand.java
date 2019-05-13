package presentation.commands;

import data.exceptions.CommandExceptions;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public class LoginCommand implements Command{
    private String target;
    
    public LoginCommand(String target) {
        this.target = target;
    }
    
    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        return target;
    }

}
