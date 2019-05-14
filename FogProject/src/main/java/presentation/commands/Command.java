package presentation.commands;

import data.exceptions.CommandExceptions;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandExceptions;
}
