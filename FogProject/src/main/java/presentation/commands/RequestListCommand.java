package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.RequestExceptions;
import data.models.Request;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.RequestFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestListCommand implements Command {
    private String target;
    private RequestFacade rf;

    public RequestListCommand(String target) {
        this.target = target;
        rf = new RequestFacade();
    }
    
    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        HttpSession session = request.getSession();
        try {
            List<Request> requests = rf.getAll();
            session.setAttribute("requestList", requests);
        } catch (RequestExceptions ex) {
            ex.printStackTrace();
            throw new CommandExceptions("Something went wrong!");
        }
        
        
        return target;
    }

}
