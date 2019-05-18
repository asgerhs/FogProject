package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.OrderException;
import data.exceptions.RequestException;
import data.models.CommandTarget;
import data.models.Request;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.OrderFacade;
import logic.facades.RequestFacade;
import logic.facades.UserFacade;

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
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        List<Request> requests;
        if (request.getParameter("orderId") != null) {
            try {
                Request r = rf.getSingle(Integer.parseInt(request.getParameter("orderId")));
                of.createOrder(r.getId());
                //rf.remove(Integer.parseInt(request.getParameter("orderId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestException | OrderException ex) {
                ex.printStackTrace();
                throw new CommandException(ex.getMessage());
            }
        } else if (request.getParameter("requestId") != null) {
            try {
                rf.remove(Integer.parseInt(request.getParameter("requestId")));
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestException ex) {
                ex.printStackTrace();
                throw new CommandException(ex.getMessage());
            }
        } else {
            try {
                requests = rf.getAll();
                session.setAttribute("requestList", requests);
            } catch (RequestException ex) {
                ex.printStackTrace();
                throw new CommandException(ex.getMessage());
            }
        }

        return new CommandTarget(target, "Request List Loaded Successfully");
    }

}
