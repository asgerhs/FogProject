package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MaterialException;
import data.exceptions.RequestException;
import data.models.CommandTarget;
import data.models.Material;
import data.models.Request;
import data.models.RoleEnum;
import data.models.User;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
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
        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String> params = new HashMap();
        while(paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            params.put(pName, request.getParameter(pName));
        }
        
        if(request.getParameter("requestId") != null) {
            try {
                Request r = rf.getById(Integer.parseInt(request.getParameter("requestId")));
                session.setAttribute("request", r);
            } catch (RequestException ex) {
                ex.printStackTrace();
                throw new CommandException("Can't find request");
            }
        }
        
        if(params.get("submit") != null) {
            try {
                boolean shed = params.get("shed") == null ? false : true;
                boolean angle = params.get("angleCheck") == null ? false : true;
                Request re = new Request(
                    Integer.parseInt(params.get("width")),
                    Integer.parseInt(params.get("length")),
                    shed ? Integer.parseInt(params.get("shedWidth")) : 0,
                    shed ? Integer.parseInt(params.get("shedLength")) : 0,
                    params.get("roof"),
                    angle ? Integer.parseInt(params.get("angle")) : 0,
                    params.get("note"),
                    params.get("loggedin") != null ?
                    new User(
                            params.get("email"),
                            null,
                            RoleEnum.CUSTOMER,
                            null,
                            null,
                            null,
                            null) 
                    :
                    new User(
                            params.get("email"),
                            params.get("password"),
                            RoleEnum.CUSTOMER,
                            params.get("name"),
                            params.get("address"),
                            params.get("zipCity"),
                            params.get("phone")));
            
                rf.add(re);
                
                return new CommandTarget(target, "Request send successfully");
            } catch (RequestException ex) {
                ex.printStackTrace();
                throw new CommandException(ex.getMessage());
            }
        } else {
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
}
