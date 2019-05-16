package presentation.commands;

import data.exceptions.CommandException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
