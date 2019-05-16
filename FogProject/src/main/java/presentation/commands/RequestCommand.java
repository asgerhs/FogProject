package presentation.commands;

import data.exceptions.CommandException;
import data.exceptions.MaterialException;
import data.exceptions.RequestExceptions;
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
    public String execute(HttpServletRequest request) throws CommandException {
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
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandException("Can't find request");
            }
        }
        
        
        
        if(params.get("submit") != null) {
            try {
                Request re = new Request(
                    Integer.parseInt(params.get("width")),
                    Integer.parseInt(params.get("length")),
                    Integer.parseInt(params.get("shedWidth")),
                    Integer.parseInt(params.get("shedLength")),
                    params.get("roof"),
                    0, //angle
                    params.get("note"),
                    new User(
                            params.get("email"),
                            "1234", // Password
                            RoleEnum.CUSTOMER,
                            params.get("name"),
                            params.get("address"),
                            params.get("zipCity"),
                            params.get("phone")));
            
                System.out.println(re);
                rf.add(re);
                
                return target;
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandException("Test");
            }
        } else {
            try {
                ArrayList<Material> mats;
                mats = mf.getAllByCategory(10);

                session.setAttribute("mats", mats);

                return target;
            } catch (MaterialException ex) {
                ex.printStackTrace();
                throw new CommandException("Test");
            }
        }
    }
}
