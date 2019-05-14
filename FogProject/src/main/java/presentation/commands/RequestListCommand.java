package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.MapperExceptions;
import data.exceptions.RequestExceptions;
import data.models.Material;
import data.models.Request;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.MaterialFacade;
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
        try {
            HttpSession session = request.getSession();

            List<Request> requests;
            requests = rf.getAll();

            session.setAttribute("requests", requests);

            return target;
        } catch (RequestExceptions ex) {
            ex.printStackTrace();
            throw new CommandExceptions("Test");
        }
    }
}
