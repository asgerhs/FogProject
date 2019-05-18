package presentation.commands;

import data.exceptions.CommandException;
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
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        List<Request> requests;
        if (request.getParameter("orderId") != null) {
            try {
                Request r = rf.getById(Integer.parseInt(request.getParameter("orderId")));
                of.createOrder(r);
                //rf.remove(Integer.parseInt(request.getParameter("orderId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions | OrderException ex) {
                ex.printStackTrace();
                throw new CommandException("Something went wrong!");
            }
        } else if (request.getParameter("requestId") != null) {
            try {
                rf.remove(Integer.parseInt(request.getParameter("requestId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandException("Something went wrong!");
            }
        } else {
            try {
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandException("Something went wrong!");
            }
        }

        return target;
    }

}
