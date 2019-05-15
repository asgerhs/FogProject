package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.OrderException;
import data.exceptions.RequestExceptions;
import data.models.Request;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.OrderFacade;
import logic.facades.RequestFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestListCommand implements Command {

    private String target;
    private RequestFacade rf;
    private OrderFacade of;
    
    public RequestListCommand(String target) {
        this.target = target;
        rf = new RequestFacade();
        of = new OrderFacade();
    }

    @Override
    public String execute(HttpServletRequest request) throws CommandExceptions {
        HttpSession session = request.getSession();
        List<Request> requests;
        
        if (request.getParameter("requestId") != null) {
            try {
                rf.remove(Integer.parseInt(request.getParameter("requestId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
            }
        } else if(request.getParameter("OrderId") != null) {
            try {
                of.createOrder(rf.getById(Integer.parseInt(request.getParameter("OrderId"))));
                rf.remove(Integer.parseInt(request.getParameter("OrderId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions | OrderException ex) {
                ex.printStackTrace();
            }
        }else {
            try {
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandExceptions("Something went wrong!");
            }
        }

        return target;
    }

}
