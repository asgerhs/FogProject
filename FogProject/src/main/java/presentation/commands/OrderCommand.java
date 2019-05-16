package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.MapperExceptions;
import data.models.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 *
 * @author Martin Frederiksen
 */
public class OrderCommand implements Command {
    private String target;

    public OrderCommand(String target) {
        this.target = target;
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        return target;
    }   
}
