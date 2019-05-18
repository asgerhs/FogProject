package presentation.commands;

import data.exceptions.CommandException;
import data.models.CommandTarget;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Martin Frederiksen
 */
public interface Command {
    CommandTarget execute(HttpServletRequest request) throws CommandException;
}
