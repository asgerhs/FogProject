package presentation.commands;

import data.exceptions.CommandException;
import data.models.CommandTarget;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public class RegisterCommand implements Command {
    
    private String target;
    
    public RegisterCommand(String target) {
        this.target = target;
    }
    
    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        return new CommandTarget(target, "Successfully loaded site");
    }
}
