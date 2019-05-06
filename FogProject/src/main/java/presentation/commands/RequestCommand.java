package presentation.commands;

import data.exceptions.CommandExceptions;
import data.exceptions.MapperExceptions;
import data.exceptions.RequestExceptions;
import data.models.Material;
import data.models.Request;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TreeMap;
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
    public String execute(HttpServletRequest request) throws CommandExceptions {
        Enumeration<String> paramNames = request.getParameterNames();
        HashMap<String, String> params = new HashMap();
        while(paramNames.hasMoreElements()) {
            String pName = paramNames.nextElement();
            params.put(pName, request.getParameter(pName));
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
                    params.get("name"),
                    params.get("address"),
                    params.get("zipCity"),
                    params.get("phone"),
                    params.get("email"),
                    params.get("note"));
            
                System.out.println(re);
                rf.add(re);
                
                return target;
            } catch (RequestExceptions ex) {
                ex.printStackTrace();
                throw new CommandExceptions("Test");
            }
        } else {
            try {
                HttpSession session = request.getSession();

                ArrayList<Material> mats;
                mats = mf.getAllByCategory(10);

                session.setAttribute("mats", mats);

                return target;
            } catch (MapperExceptions ex) {
                ex.printStackTrace();
                throw new CommandExceptions("Test");
            }
        }
    }
}
