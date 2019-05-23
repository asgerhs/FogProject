package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MaterialException;
import data.exceptions.RequestException;
import data.models.CommandTarget;
import data.models.Material;
import data.models.Request;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import logic.facades.MaterialFacade;
import logic.facades.RequestFacade;

/**
 *
 * @author Martin Frederiksen
 */
public class RequestCommand implements Command {

    private String target;
    private MaterialFacade mf;
    private RequestFacade rf;

    public RequestCommand(String target) {
        this.target = target;
        
        mf = new MaterialFacade();
        rf = new RequestFacade();
    }

    @Override
    public CommandTarget execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession();
        
        if(request.getParameter("requestId") != null) {
            try {
                Request r = rf.getSingle(Integer.parseInt(request.getParameter("requestId")));
                session.setAttribute("request", r);
            } catch (RequestException ex) {
                ex.printStackTrace();
                throw new CommandException("Can't find request");
            }
        }
        
        try {
            ArrayList<Material> mats;
            mats = mf.getAllByCategory(10);

            session.setAttribute("mats", mats);

            return new CommandTarget(target, "Request loaded successfully");
        } catch (MaterialException ex) {
            ex.printStackTrace();
            throw new CommandException(ex.getMessage());
        }
    }
}
