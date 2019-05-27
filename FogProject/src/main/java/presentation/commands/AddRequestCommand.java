package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.RequestException;
import data.models.CommandTarget;
import data.models.Request;
import data.models.RoleEnum;
import data.models.User;
import javax.servlet.http.HttpServletRequest;
import logic.facades.RequestFacade;

/**
 *
 * @author Andreas Vikke
 */
public class AddRequestCommand implements Command {

    private String target;
    private RequestFacade rf;

    public AddRequestCommand(String target) {
        this.target = target;
        rf = new RequestFacade();
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        try {
            boolean shed = request.getParameter("shed") == null ? false : true;
            boolean angle = request.getParameter("angleCheck") == null ? false : true;

            Request re = new Request(
                    Integer.parseInt(request.getParameter("width")),
                    Integer.parseInt(request.getParameter("length")),
                    shed ? Integer.parseInt(request.getParameter("shedWidth")) : 0,
                    shed ? Integer.parseInt(request.getParameter("shedLength")) : 0,
                    request.getParameter("roof"),
                    angle ? Integer.parseInt(request.getParameter("angle")) : 0,
                    request.getParameter("note"),
                    request.getParameter("loggedin") != null
                            ? new User(
                                    request.getParameter("email"),
                                    null,
                                    RoleEnum.CUSTOMER,
                                    null,
                                    null,
                                    null,
                                    null)
                            : new User(
                                    request.getParameter("email"),
                                    request.getParameter("password"),
                                    RoleEnum.CUSTOMER,
                                    request.getParameter("name"),
                                    request.getParameter("address"),
                                    request.getParameter("zipCity"),
                                    request.getParameter("phone")));

            if (request.getParameter("requestId") != null) {
                re.setId(Integer.parseInt(request.getParameter("requestId")));
                rf.update(re);
                return new CommandTarget(target, "Request updated successfully");
            }
            rf.add(re);
            return new CommandTarget(target, "Request send successfully");
        } catch (RequestException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }

}
